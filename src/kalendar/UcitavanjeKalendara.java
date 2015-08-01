/*
 * klasa za rad sa fajlom kalendar.txt
 */

package kalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UcitavanjeKalendara {
	
	ArrayList<Godina> godine = new ArrayList<>();
	
	/*
	 * metoda za ucitavanje podataka iz fajla "kalendar.txt"
	 */
	public void ucitajKalendar() throws FileNotFoundException{
		File file = new File("kalendar.txt");
		Scanner in = new Scanner(file);
		//prolazak kroz sadrzaj fajla, pravljenje objekata Godina i njihovo smijestanje u listu godine
		while(in.hasNextLine()){
			Godina godina = new Godina(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			godine.add(godina);
		}
		in.close();
	}
	/*
	 * metoda za prosljedjivanje liste godine
	 */
	public ArrayList<Godina> getGodine(){
		return godine;
	}

}
