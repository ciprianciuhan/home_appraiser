package home_appraiser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class MainClass {

	public static void main(String[] args) {
		
		
		Scanner input = new Scanner(System.in);
		
		Casa property = new Casa();
		
		System.out.println("Ce tip de proprietate aveti (casa/apartament): ");
		
		property.setType(input.nextLine());
		
		System.out.println("Orasul: ");
		
		property.setOras(input.nextLine());
		
		System.out.println("Suprafata in metri patrati: ");
		
		property.setSuprafata(input.nextFloat());
		
		System.out.println("Numarul de camere: ");
		
		property.setNrCamere(input.nextInt());
		
		int sup_min = (int) property.getSuprafata() - 5;
		int sup_max = (int) property.getSuprafata() + 5;
		
		String tip_casa_url = "";
		
		if (property.getType().charAt(0) == 'c') {
			tip_casa_url = "case";
		} else {
			tip_casa_url = "apartamente-garsoniere";
		}
		
		int nr_camere = 0;
		
		if (property.getNrCamere() > 4) {
			nr_camere = 4;
		} else nr_camere = property.getNrCamere();
		
		System.out.println(tip_casa_url);
		
		
		ArrayList <String> vector_preturi = new ArrayList<String>();
		int i = 0;
		
		try {
			
			Document doc = Jsoup.connect("https://www.olx.ro/imobiliare/" + tip_casa_url + "-de-vanzare/" + nr_camere 
			+ "-camere/" + property.getOras().toLowerCase() + "/?search%5Bfilter_float_m%3Afrom%5D=" + sup_min 
			+ "&search%5Bfilter_float_m%3Ato%5D=" + sup_max).get();
			
			
			//Document doc = Jsoup.connect("https://www.olx.ro/imobiliare/apartamente-garsoniere-de-vanzare/2-camere/bucuresti/?search%5Bfilter_float_m%3Afrom%5D=35&search%5Bfilter_float_m%3Ato%5D=45&page=98").get();
			Elements temp = doc.select("p.price");
			
			//ArrayList <String> vector_preturi = new ArrayList<String>();
			
			for (Element priceList:temp) {
				
				i += 1;
				
				//System.out.println(i + " " + priceList.getElementsByTag("strong").first().text());
				
				vector_preturi.add(priceList.getElementsByTag("strong").first().text());
				
				
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (int x = 0; x < i; x++) {
			System.out.println(vector_preturi.get(x));
		}
		
		//trebuie sa fac un for sau ceva care sa ma duca prin toate paginile fara sa imi dea eroare 
		//ca sa pot sa adun cat mai multe preturi
		//dupa trebuie sa convertesc preturile in int si sa sortez vectorul, apoi sa afisez min, max si 
		//sa fac media pentru apartament, cumva printr-o diferentiere a cartierelor
		
		
		
		/*
		System.out.println("Ati introdus urmatoarea proprietate: " + property.getType() + ", " + property.getSuprafata() + " m2, " +
		property.getNrCamere() + " camere, in orasul " + property.getOras());
		*/
		
	}

}
