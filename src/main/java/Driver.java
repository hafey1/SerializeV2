import java.io.IOException;

public class Driver {

	public static void main(String[] args) throws IOException {

		System.out.println("This is a test");
		System.out.println("Is gradle actually working?");

		Album theShins = new Album("Wincing the Night Away", "The Shins", "1990", "8.4");
		Album checkTheShins = theShins;


		Album fooFighters = new Album("Foo Fighters", "Foo Fighters", "1999", "6.4");

		theShins.equals(fooFighters);

		theShins.equals(checkTheShins);


		Album[] albumArray = new Album[3];
		albumArray[0] = theShins;

		Album.serializetoCSV(theShins, "dreams.csv");
		Album hopefully = Album.deserializetoCSV("dreams.csv");
		System.out.print(hopefully.toString());
	}
}
