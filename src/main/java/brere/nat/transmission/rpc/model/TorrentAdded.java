package brere.nat.transmission.rpc.model;

public class TorrentAdded {

	private String hashString = null;
	private int id = 0;
	private String name = null;

	public String getHashString() {
		return hashString;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setHashString(String hashString) {
		this.hashString = hashString;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

}
