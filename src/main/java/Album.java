import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;

//albumName singular
//create test file under source for junit testing
public class Album implements Comparable<Album> {
	//this is the state of the class
	// every object must have an albumName entry
	// would like to compare by rating, but comparisons by albumName are guaranteed
	private static final String DEFAULT_VALUE = "default";
	private String albumName;
	private String artistName;
	private String yearReleased;
	private String personalRating;

	public Album() {this(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE);}

	//this calls the construtor with all four attributes
	public Album(String albumName, String artistName, String yearReleased, String personalRating) {
		this.albumName = albumName;
		this.artistName = artistName;
		this.yearReleased = yearReleased;
		this.personalRating = personalRating;
	}

	//setter block
	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}
	public void setArtistName(String artistName) {
		this.artistName = artistName;
	}
	public void setYearReleased(String yearReleased) {
		this.yearReleased = yearReleased;
	}
	public void setPersonalRating(String personalRating) {
		this.personalRating = personalRating;
	}
	//getter block
	public String getAlbumName() {
		return albumName;
	}
	public String getArtistName() {
		return artistName;
	}
	public String getYearReleased() {
		return yearReleased;
	}
	public String getPersonalRating() {
		return personalRating;
	}


	public static Album deserializefromCSV(Path tempFile) throws IOException{
			//create the path, the line reader
			BufferedReader csvLineReader = Files.newBufferedReader(tempFile, StandardCharsets.UTF_8);


			//write to a string, split up the phrases by commas
			//first is albumName name, second is artist name
			//third is year released, fourth is personal rating
			String currLine = "";

			Album createdAlbum = new Album(DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE, DEFAULT_VALUE);
			if (csvLineReader.ready()) {
				currLine = csvLineReader.readLine();
				String[] albumInfoSplit = currLine.split(",");

				createdAlbum.setAlbumName(albumInfoSplit[0]);
				createdAlbum.setArtistName(albumInfoSplit[1]);
				createdAlbum.setYearReleased(albumInfoSplit[2]);
				createdAlbum.setPersonalRating(albumInfoSplit[3]);
			}
			csvLineReader.close();
			return createdAlbum;
	}


	public static void serializetoCSV(Album outGoingAlbum, Path tempFile) throws IOException {

		BufferedWriter serializer = Files.newBufferedWriter(tempFile, StandardCharsets.UTF_8);

		String csvLine = outGoingAlbum.getAlbumName() + "," + outGoingAlbum.getArtistName() + "," + outGoingAlbum.getYearReleased() + "," + outGoingAlbum.getPersonalRating() + "\n";

		serializer.write(csvLine, 0, csvLine.length());

		serializer.close();

	}

	//this will enforce ordering by album name
	@Override
	public int compareTo(Album compared) {

		return this.albumName.compareToIgnoreCase(compared.getAlbumName());
	}

	//the hash will be created by all the field information
	@Override
	public int hashCode() {

		return this.albumName.hashCode() + this.artistName.hashCode() + this.yearReleased.hashCode() + this.personalRating.hashCode();
	}

	//equality between two albums will be determined if the album name strings are the same;
	@Override
	public boolean equals(Object o) {
		boolean isEqual = false;

		if (o instanceof Album) {

				Album album = (Album) o;
				isEqual = albumName.equals(album.albumName) && artistName.equals(album.artistName);
		}

		return isEqual;
	}

	//for possible console log depbugging
	@Override
	public String toString() {

		return albumName + " " + artistName + " " + yearReleased + " " + personalRating;
	}
}