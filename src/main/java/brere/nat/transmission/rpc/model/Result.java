package brere.nat.transmission.rpc.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

	private TorrentResult arguments = null;

	private String method = null;
	private String result = null;
	private int tag = 0;

	public TorrentResult getArguments() {
		return arguments;
	}

	public void setArguments(TorrentResult arguments) {
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
