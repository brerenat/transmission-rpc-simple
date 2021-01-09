package brere.nat.transmission.rpc.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TorrentResult {
	
	@JsonProperty("torrent-added")
	private TorrentAdded torrentAdded = null;

}
