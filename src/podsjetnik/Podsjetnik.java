/*
 * klasa glavna za ispis podsjetnika
 * u njoj se odvija sve vezano za podsjetnik
 */

package podsjetnik;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import kalendar.Kalendar;

public class Podsjetnik {
	
	ArrayList<Zapis> zapisi = new ArrayList<>();
	/*
	 * metoda za stampanje podsjetnika
	 */
	public void stampajPodsjetnik (int mjesec, int godina) throws FileNotFoundException{
		
		boolean imaLiZapisa = false;
		//ucitavanje podataka/zapisa iz fajla
		InOutPodsjetnik podsjetnik = new InOutPodsjetnik();
		podsjetnik.ucitajPodsjetnik();
		zapisi = podsjetnik.getPodsjetnici();
		//stampanje zaglavlja
		System.out.println("\n\n***************************************************\n" 
			              + "*                   PODSJETNIK                    *\n"
			              + "***************************************************\n" );
		
		//prolazak kroz sve zapise
		for(int i = 0; i < zapisi.size(); i++){
			//ako za trazeni mjesec ima zapisa, stampaju se zapisi
			if(zapisi.get(i).getMjesec() == mjesec && zapisi.get(i).getGodina() == godina){
				System.out.println(zapisi.get(i).getText());
				imaLiZapisa = true;
			}
			//ako za trazeni mjesec nema zapisa, stampa se odgovarajuca poruka
			if(i == zapisi.size() - 1 && imaLiZapisa == false){
				System.out.println("Nema zapisa za ovaj mjesec.");
			}
		}
		
	}
	/*
	 * metoda za dodavanje novih zapisa/podsjetnika
	 */
	public void dodajZapis(int mjesec, int godina) throws FileNotFoundException{
		
		Kalendar kalendar = new Kalendar();
		
		boolean notLegit = true;
		int dan = 0;
		
		Scanner in = new Scanner(System.in);
		
		System.out.println("Unosenje podsjetnika za mjesec : " + kalendar.getMjesecText() + " " + godina);
		//ako korisnik unese datum kojeg nema u tom mjesecu, trazi se da pokusa ponovo
		do{
			
			System.out.println("Za koji dan zelite unijeti podsjetnik");
			dan = in.nextInt();
			if(dan > 0 && dan <= kalendar.getBrojDana()){
				notLegit = false;
			}
			
		}while(notLegit);
		
		
		System.out.println("Unesite podsjetnik");
		//ovo je malo nelogicno, ali mora ovako
		//naime, prvi unos nece da prihvati, ne znam zasto, ne dopusta mi da unesem tekst, samo preskoci liniju (barem tako izgleda)
		//ako stavim in.next() radice, ali to mi ne pase jer moram omoguciti unos vise rijeci
		String text = in.nextLine();
		text = in.nextLine();
		
		InOutPodsjetnik podsjetnik = new InOutPodsjetnik();
		podsjetnik.ucitajPodsjetnik();
		zapisi = podsjetnik.getPodsjetnici();
		//pravljenje novog objekta Zapis
		Zapis zapis = new Zapis();
		zapis.setDan(dan);
		zapis.setMjesec(mjesec);
		zapis.setGodina(godina);
		zapis.setText(dan + "/" + mjesec + "/" + godina + " "  + text);
		//ako korisnik potvrdi da zeli sacuvati podatke novi objekat se dodaje u listu
		//i lista se snima u fajl
		if(potvrdiUnos()){
			zapisi.add(zapis);
			podsjetnik.setPodsjetnike(zapisi);
		}
	}
	
	/*
	 * Metoda koja trazi od korisnika da potvrdi unos
	 */
	boolean potvrdiUnos(){
		boolean odluka = false;
		boolean test = true;
		Scanner in = new Scanner(System.in);
		//trazenje od korisnika da odabere opciju
		do{
			System.out.println("Izvrsiti?(DA/NE)");
			String karakter = in.next();
			if(karakter.equals("DA") || karakter.equals("da")){
				odluka = true;
				test = false;
			}
			if(karakter.equals("NE") || karakter.equals("ne")){
				test = false;
			}
			
		}while(test);
		return odluka;
	}

}
