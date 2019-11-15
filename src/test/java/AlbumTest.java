import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class AlbumTest {

	@Test
	public void SerializingToAndDeserializingFromCSVTest() throws IOException {
	Album test = new Album("Wincing the Night Away", "The Shins", "2009", "10");
	Album.serializetoCSV(test, "Test.csv");
	Album readFromCSV = Album.deserializefromCSV("Test.csv");
	assertEquals(test, readFromCSV);
	}


}