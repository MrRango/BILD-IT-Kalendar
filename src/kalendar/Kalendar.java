/*
 * klasa glavna za ispis kalendara
 * u njoj se odvija sve vezano za stampanje kalendara
 */

package kalendar;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import podsjetnik.Podsjetnik;


public class Kalendar {
	
	static ArrayList<Godina>godine = new ArrayList<>();
	static boolean isOn = true;
	
	static String mjesecText = null;
	static int brojDana = 0;
	
	/*
	 * metoda koja se poziva iz klase App
	 * sluzi kao osnovna metoda, iz koje se pozivaju sve ostale metode
	 */
	public static void pokreniKalendar() throws FileNotFoundException{
		
		Podsjetnik podsjetnik = new Podsjetnik();
		
		Scanner in = new Scanner(System.in);
		int brojacGodina = 0, brojacMjesec = 0;
		//pozivanje metode za odredjivanje danasnjeg dana i smijestanje rezultata
		//u odgovarajuce promjenljive
		String[] danasDatum = danasnjiDatum().split(" ");
		int godina = Integer.parseInt(danasDatum[0]);
		int mjesec = Integer.parseInt(danasDatum[1]);
		//petlja koja omogucava neprekidan rad programa
		while(isOn){
			//pozivanje metode za stampanje kalendara
			ispisiKalendar(godina + brojacGodina, mjesec + brojacMjesec);
			//pozivanje metode za stampanje podsjetnika
			podsjetnik.stampajPodsjetnik(mjesec + brojacMjesec, godina + brojacGodina);
			//ispisivanje opcija 
			System.out.println("\n\n         [1] <<  [2] <   [3] >  [4]  >>\n" 
							 + "             [5] Dodaj podsjetnik");
			//uzimanje odabrane opcije
			int unos = in.nextInt(); 
			//u zavisnosti od unosa, odradjuje se odredjeni dio koda
			switch(unos){
			case 1:{
				//opcija 1
				//prikaz kalendara za proslu godinu
				if(godina + brojacGodina > 2000){
					brojacGodina--;				
				}
				break;
			}
			case 2:{
				//opcija 2
				//prikaz kalendara za prosli mjesec
				if(mjesec + brojacMjesec > 1){
					brojacMjesec--;					
				}
				break;
			}
			case 3:{
				//opcija 3
				//prikaz kalendara za sljedeci mjeses
				if(mjesec + brojacMjesec < 12){
					brojacMjesec++;					
				}
				break;
			}
			case 4:{
				//opcija 4
				//prikaz kalendara za sljedecu godinu
				if(godina + brojacGodina < 2030){
					brojacGodina++;					
				}
				break;
			}
			case 5:{
				//opcija 5
				//pozivanje metode za unos novog podsjetnika, za mjesec koji je trenutno prikazan
				podsjetnik.dodajZapis(mjesec + brojacMjesec, godina + brojacGodina);
				break;
			}
			}
			
			
		}
	}
	
	/*
	 * metoda za stampanje kalendara
	 */
	public static void ispisiKalendar(int godina, int mjesec) throws FileNotFoundException{
		
		int[] god = new int[13];
		//ucitavanje podataka o godinama iz fajla
		//za trazenu godinu, smijestanje podataka u niz "god"
		UcitavanjeKalendara ucitavanjeKalendara = new UcitavanjeKalendara();
		ucitavanjeKalendara.ucitajKalendar();
		godine = ucitavanjeKalendara.getGodine();
		for(int i = 0; i < godine.size(); i++){
			if(godine.get(i).getGodina() == godina){
				god[0] = godine.get(i).getGodina();
				god[1] = godine.get(i).getJanuar();
				god[2] = godine.get(i).getFebruar();
				god[3] = godine.get(i).getMart();
				god[4] = godine.get(i).getApril();
				god[5] = godine.get(i).getMaj();
				god[6] = godine.get(i).getJun();
				god[7] = godine.get(i).getJul();
				god[8] = godine.get(i).getAvgust();
				god[9] = godine.get(i).getSeptembar();
				god[10] = godine.get(i).getOktobar();
				god[11] = godine.get(i).getNovembar();
				god[12] = godine.get(i).getDecembar();
			}
			
		}
				
		//odredjivanje broja dana za trazeni mjesec
		//odredjivanje "imena" mjeseca
		switch (mjesec){
		case 1:{
			mjesecText = "Januar";
			brojDana = 31;
			break;
		}
		case 2:{
			//testiranje da li je godina prestupna, jer to utice na duzinu februara
			if(isPrestupna(godina)){
				brojDana = 29;
			}else{
				brojDana = 28;
			}
			mjesecText = "Februar";
			break;
		}
		case 3:{
			mjesecText = "Mart";
			brojDana = 31;
			break;
		}
		case 4:{
			mjesecText = "April";
			brojDana = 30;
			break;
		}
		case 5:{
			mjesecText = "Maj";
			brojDana = 31;
			break;
		}
		case 6:{
			mjesecText = "Jun";
			brojDana = 30;
			break;
		}
		case 7:{
			mjesecText = "Jul";
			brojDana = 31;
			break;
		}
		case 8:{
			mjesecText = "Avgust";
			brojDana = 31;
			break;
		}
		case 9:{
			mjesecText = "Septembar";
			brojDana = 30;
			break;
		}
		case 10:{
			mjesecText = "Octobar";
			brojDana = 31;
			break;
		}
		case 11:{
			mjesecText = "Novembar";
			brojDana = 30;
			break;
		}
		case 12:{
			mjesecText = "Decembar";
			brojDana = 31;
			break;
		}
		}
		//stampanje zaglavlja kalendara
		System.out.println("***************************************************\n" 
		                 + "*                K A L E N D A R                  *\n"
				+ "***************************************************\n" 
		        + "___________________________________________________\n\n" 
				+ "                     " + mjesecText + " " + godina 
				+ "\n___________________________________________________\n"
				+ "\nPon     Uto     Sri     Cet     Pet     Sub     Ned");
		
		int brojac = 0;
		//ispisivanje brojeva/dana od 1 do broja dana koliko mjesec ima
		for(int i = 1; i <= brojDana; i++){
			//koristenje podataka o mjesecu za odredjivanje da li i koliko puta treba stampati prazna mjesta
			if(brojac < god[mjesec]){  		//bez ovog uslova bi se prazna mjesta dodavala ispred svakog broja
				for(int j = 0; j < god[mjesec]; j++){
					System.out.print("        ");
					brojac++;
				}
			}
			//prelazak u novi red/sedmicu
			if(brojac % 7 == 0){
				System.out.println();
			}
			//za dvocifrene brojeve stampa se razmak manji za jedan karakter
			if(i < 10){
				System.out.print(" " + i + "      ");
			}else{
				System.out.print(" " + i + "     ");
			}
			brojac++;
		}
		
	}
	/*
	 * metoda koja provjerava da li je godina prestupna
	 */
	public static boolean isPrestupna(int godina){
		boolean isPrestupna = false;
		//provjeravanje da li je godina prestupna
		if(godina % 4 == 0 && (godina % 100 != 0 || godina % 400 == 0)){
			isPrestupna = true;
		}
		return isPrestupna;
	}
	/*
	 * metoda koja odredjuje danasnji datum
	 */
	public static String danasnjiDatum(){
		
		Date date = new Date(System.currentTimeMillis());
		//kreiranje formata za prikaz datuma
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM");
			
		return format.format(date);
	}

	public static String getMjesecText() {
		return mjesecText;
	}

	public static int getBrojDana() {
		return brojDana;
	}

}
