package kalendar;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class UcitavanjeKalendara {
	
	ArrayList<Godina> godine = new ArrayList<>();
	
	public void ucitajKalendar() throws FileNotFoundException{
		File file = new File("kalendar.txt");
		Scanner in = new Scanner(file);
		
		while(in.hasNextLine()){
			Godina godina = new Godina(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
			godine.add(godina);
		}
		in.close();
	}
	public ArrayList<Godina> getGodine(){
		return godine;
	}

}
