package brere.nat.transmission.rpc.api;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

import javax.ws.rs.core.MediaType;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import brere.nat.transmission.rpc.model.Result;
import brere.nat.transmission.rpc.model.TorrentAdd;
import brere.nat.transmission.rpc.model.Upload;

public class RPCAPI {
	
	private static final Logger LOG = LoggerFactory.getLogger(RPCAPI.class);
	private static final String RPC_SESSION_ID = "X-Transmission-Session-Id";
	private static final String BASE_URL_END = ":9091/transmission/rpc";
	private static final String BASE_URL_START = "http://";
	private final String baseUrl;
	protected HttpClientBuilder httpClientBuilder = null;
	private String sessionID = null;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	public RPCAPI() throws UnknownHostException {
		baseUrl = new StringBuilder(BASE_URL_START).append(InetAddress.getLocalHost().getHostName()).append(BASE_URL_END).toString();
	}
	
	public RPCAPI(final String hostName) {
		baseUrl = new StringBuilder(BASE_URL_START).append(hostName).append(BASE_URL_END).toString();
	}
	
	protected HttpClient getHttpClient() {
		if (httpClientBuilder == null) {
			httpClientBuilder = HttpClientBuilder.create();
		}
		return httpClientBuilder.build();
	}
	
	protected URI getURI() throws URISyntaxException {
		return new URIBuilder(baseUrl).build();
	}
	
	private void setUpHttpRequestBase(final HttpRequestBase req) {
		req.setHeader("Accept", MediaType.APPLICATION_JSON);
		req.setHeader("Connection", "keep-alive");
		req.setHeader("User-Agent", "Apache HTTP Client");
		if (sessionID != null) {
			req.setHeader(RPC_SESSION_ID, sessionID);
		}
	}
	
	public void addTorrent(final String filename) throws URISyntaxException, ClientProtocolException, IOException {
		LOG.info("Starting to add Torrent :" + filename);
		final HttpClient client = getHttpClient();
		
		final HttpPost post = new HttpPost(getURI());
		setUpHttpRequestBase(post);
		
		Upload add = new Upload();
		add.setMethod("torrent-add");
		TorrentAdd torrent = new TorrentAdd();
		torrent.setFilename(filename);
		add.setArguments(torrent);
		
		post.setEntity(new StringEntity(MAPPER.writeValueAsString(add)));
		
		HttpResponse response = client.execute(post);
		if (response.getStatusLine().getStatusCode() == 409) {
			LOG.info("Got session response, adding session to request");
			for (final Header header : response.getAllHeaders()) {
				if (RPC_SESSION_ID.equals(header.getName())) {
					sessionID = header.getValue();
				}
			}
			setUpHttpRequestBase(post);
			LOG.info("Connecting with session id:" + sessionID);
			response = client.execute(post);
		}
		
		if (response.getStatusLine().getStatusCode() == 200) {
			final Result result = MAPPER.readValue(response.getEntity().getContent(), Result.class);
			LOG.info("Added :" + result.getResult());
		}
		
	}
}
