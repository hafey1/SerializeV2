import org.junit.Test;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class AlbumTest {

	@Test
	public void SerializingToAndDeserializingFromCSVTest() throws IOException {
		System.out.println("Testing for Serializing and Deserializing an Object");
		Album test = new Album("Wincing the Night Away", "The Shins", "2009", "10");
	Path tempFile = Files.createTempFile("Album",".csv");

	Album.serializetoCSV(test, tempFile);
	Album readFromCSV = Album.deserializefromCSV(tempFile);
	assertEquals(test, readFromCSV);
	}


}