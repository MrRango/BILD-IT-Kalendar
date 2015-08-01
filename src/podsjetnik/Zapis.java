/*
 * klasa za kreiranje objekta Zapis
 * koristi se za cuvanje podsjetnika
 */

package podsjetnik;

public class Zapis {
	
	private int dan;
	private int mjesec; 
	private int godina; 
	private String text;
	
	Zapis(){
		
	}
	
	Zapis(int newDan, int newMjesec, int newGodina, String newText){
		dan = newDan;
		mjesec = newMjesec;
		godina = newGodina;
		text = newText;
	}

	public int getDan() {
		return dan;
	}

	public void setDan(int dan) {
		this.dan = dan;
	}

	public int getMjesec() {
		return mjesec;
	}

	public void setMjesec(int mjesec) {
		this.mjesec = mjesec;
	}

	public int getGodina() {
		return godina;
	}

	public void setGodina(int godina) {
		this.godina = godina;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
