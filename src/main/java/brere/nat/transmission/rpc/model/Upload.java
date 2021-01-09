package brere.nat.transmission.rpc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Upload {

	private TorrentAdd arguments = null;

	private String method = null;
	private String result = null;
	private int tag = 0;

	public TorrentAdd getArguments() {
		return arguments;
	}

	public void setArguments(TorrentAdd arguments) {
		this.arguments = arguments;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

}
