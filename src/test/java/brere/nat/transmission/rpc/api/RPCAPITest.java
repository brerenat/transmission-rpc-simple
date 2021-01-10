package brere.nat.transmission.rpc.api;

import static org.junit.jupiter.api.Assertions.fail;

import java.io.IOException;
import java.net.URISyntaxException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RPCAPITest {

	private static RPCAPI api;
	private static final Logger LOG = LoggerFactory.getLogger(RPCAPITest.class);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		api = new RPCAPI();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testAddTorrent() {
		try {
			api.addTorrent("magnet:?xt=urn:btih:3c2a4037b10a580a12d758d8dfee8e27f83ecb43&dn=Vikings.S06E17.1080p.WEB.H264-GLHF%5Brartv%5D&tr=http%3A%2F%2Ftracker.trackerfix.com%3A80%2Fannounce&tr=udp%3A%2F%2F9.rarbg.me%3A2710&tr=udp%3A%2F%2F9.rarbg.to%3A2710&tr=udp%3A%2F%2Fopen.demonii.com%3A1337%2Fannounce");
		} catch (URISyntaxException | IOException e) {
			LOG.error(e.getClass().getName(), e);
			fail();
		}
	}

}
