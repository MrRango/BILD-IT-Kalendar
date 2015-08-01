/*
 * pocetna klasa sa main metodom
 * iz nje se kreira novi kalendar
 */

package kalendar;

import java.io.FileNotFoundException;

public class App {

	public static void main(String[] args) throws FileNotFoundException {
	
		Kalendar kalendar = new Kalendar();
		kalendar.pokreniKalendar();
		
	}

}
