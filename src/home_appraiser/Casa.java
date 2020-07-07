package home_appraiser;

public class Casa {
	
	private float suprafata;
	private String tip;
	private int nr_camere;
	private String oras;
	
	public String getOras() {
		return oras;
	}

	public void setOras(String oras) {
		this.oras = oras;
	}
	
	public float getSuprafata() {
		return suprafata;
	}
	
	public void setSuprafata(float suprafata) {
		this.suprafata = suprafata;
	}
	
	public int getNrCamere() {
		return nr_camere;
	}
	
	public void setNrCamere(int nr_camere) {
		this.nr_camere = nr_camere;
	}
	
	public String getType() {
		return tip;
	}
	
	public void setType(String tip) {
		this.tip = tip;
	}
	
	public Casa() {
		this.setOras(oras);
		this.setNrCamere(nr_camere);
		this.setOras(oras);
		this.setType(tip);
	}
	
}