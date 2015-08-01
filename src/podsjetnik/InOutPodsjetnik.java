/*
 * klasa za rad sa fajlom podsjetnik.txt
 */

package podsjetnik;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class InOutPodsjetnik {

	ArrayList<Zapis> zapisi = new ArrayList<>();
	/*
	 * metoda za ucitavanje podataka iz fajla podsjetnik.txt
	 */
	public void ucitajPodsjetnik() throws FileNotFoundException {
		File file = new File("podsjetnik.txt");
		Scanner in = new Scanner(file);
		//prolazak kroz sadrzaj fajla, pravljenje objekata Zapis i njihovo smijestanje u listu zapisi
		while (in.hasNext()) {
			Zapis zapis = new Zapis(in.nextInt(), in.nextInt(), in.nextInt(),
					in.nextLine().trim());
			zapisi.add(zapis);
		}
		in.close();
	}
	/*
	 * metoda za snimanje zapisa u fajl podsjetnik.txt
	 */
	private void sacuvajPodsjetnik() throws FileNotFoundException {
		// Brisanje sadrzaja fajla prije novog upisa
		PrintWriter writer = new PrintWriter("podsjetnik.txt");
		writer.print("");
		writer.close();
		
		int dan, mjesec, godina;
		String text;
		
		// Vadjenje podataka iz liste korisnika i upisivanje u fajl
		for (int i = 0; i < zapisi.size(); i++) {
			dan = zapisi.get(i).getDan();
			mjesec = zapisi.get(i).getMjesec();
			godina = zapisi.get(i).getGodina();
			text = zapisi.get(i).getText();

			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new FileOutputStream("podsjetnik.txt",
						true));
				pw.append(dan + " " + mjesec + " " + godina + " "
						+ text + "\n");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pw.close();
			}
		}
	}
	/*
	 * metoda za prosljedjivanje lista zapisi
	 */
	public ArrayList<Zapis> getPodsjetnici() {
		return zapisi;
	}
	/*
	 * metoda za prihvatanje liste zapisi i pozivanje metode za njihovo snimanje u fajl
	 */
	public void setPodsjetnike(ArrayList<Zapis> newPodsjetnici)
			throws FileNotFoundException {
		zapisi = newPodsjetnici;
		sacuvajPodsjetnik();
	}

}
