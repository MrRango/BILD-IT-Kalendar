package kalendar;

import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Kalendar {
	
	static ArrayList<Godina>godine = new ArrayList<>();
	static boolean isOn = true;
	
	
	public static void pokreniKalendar() throws FileNotFoundException{
		
		System.out.println("***************************************************\n" 
		                 + "*                K A L E N D A R                  *");
		
		Scanner in = new Scanner(System.in);
		int brojacGodina = 0, brojacMjesec = 0;

		String[] danasDatu = danasnjiDatum().split(" ");
		int godina = Integer.parseInt(danasDatu[0]);
		int mjesec = Integer.parseInt(danasDatu[1]);
		
		while(isOn){
			ispisiKalendar(godina + brojacGodina, mjesec + brojacMjesec);
			System.out.println("\n\n         [1] <<  [2] <   [3] >  [4]  >>");
			int unos = in.nextInt(); 
			
			switch(unos){
			case 1:{
				if(godina + brojacGodina > 2000){
					brojacGodina--;				
				}
				break;
			}
			case 2:{
				if(mjesec + brojacMjesec > 1){
					brojacMjesec--;					
				}
				break;
			}
			case 3:{
				if(mjesec + brojacMjesec < 12){
					brojacMjesec++;					
				}
				break;
			}
			case 4:{
				if(godina + brojacGodina < 2030){
					brojacGodina++;					
				}
				break;
			}
			}
			
		}
	}
	
	public static void ispisiKalendar(int godina, int mjesec) throws FileNotFoundException{
		
		int[] god = new int[13];
		
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
				
		int brojac = 0;
		String mjesecText = null;
		int brojDana = 0;
		
		switch (mjesec){
		case 1:{
			mjesecText = "Januar";
			brojDana = 31;
			break;
		}
		case 2:{
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
		
		System.out.println("***************************************************\n" 
		        + "___________________________________________________\n\n" 
				+ "                     " + mjesecText + " " + godina 
				+ "\n___________________________________________________\n"
				+ "\nPon     Uto     Sri     Cet     Pet     Sub     Ned");
		
		for(int i = 1; i <= brojDana; i++){
			if(brojac < god[mjesec]){
				for(int j = 0; j < god[mjesec]; j++){
					System.out.print("        ");
					brojac++;
				}
			}
			
			if(brojac % 7 == 0){
				System.out.println();
			}
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
	public static String danasnjiDatum(){
		
		Date date = new Date(System.currentTimeMillis());
		//kreiranje formata za prikaz datuma
		SimpleDateFormat format = new SimpleDateFormat("yyyy MM");
		
		return format.format(date);
	}

}
