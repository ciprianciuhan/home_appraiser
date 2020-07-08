package home_appraiser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

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
		
		System.out.println("Orasul (pentru Ilfov Bucuresti-Ilfov): ");
		
		property.setOras(input.nextLine());
		
		System.out.println("Cartierul (pentru Ilfov, localitatea): ");
		
		property.setCartier(input.nextLine());
		
		
		System.out.println("Suprafata in metri patrati: ");
		
		property.setSuprafata(input.nextFloat());
		
		System.out.println("Numarul de camere: ");
		
		property.setNrCamere(input.nextInt());
		
		int sup_min = (int) (property.getSuprafata()/10)*10;
		int sup_max = (int) (property.getSuprafata()/10)*10;
		
		String tip_casa_url = "";
		
		//Momentan incerc sa imi dau seama care site este mai ok pentru scraping de informatii
		//As prefera imobiliare pentru ca ma lasa sa caut si dupa cartier
		
		//asta e pentru olx
		
//		if (property.getType().charAt(0) == 'c') {
//			tip_casa_url = "case";
//		} else {
//			tip_casa_url = "apartamente-garsoniere";
//		}
		
		//asta e pentru imobiliare.ro
		
		if (property.getType().charAt(0) == 'c') {
			tip_casa_url = "case-vile";
		} else {
			tip_casa_url = "apartamente";
		}
		
		int nr_camere = 0;
		
		if (property.getNrCamere() > 4) {
			nr_camere = 4;
		} else nr_camere = property.getNrCamere();
		
		String cartier = property.getCartier();
		
		//cartier.replace(' ', '-');
		
		//System.out.println(tip_casa_url);
		
		
		ArrayList <String> vector_preturi = new ArrayList<String>();
		
		int i = 0;
		
		//asta e pentru olx
		/*
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
		*/
		
		//asta e pentru imobiliare.ro
			
//		try {
//			Document doc = Jsoup.connect("https://www.imobiliare.ro/vanzare-" + tip_casa_url + "/" + property.getOras().toLowerCase() + "/" + cartier + "?nrcamere=" + nr_camere).get();
//			Elements temp = doc.select("div.pret");
//			
//			//System.out.println("link-ul este " + "https://www.imobiliare.ro/vanzare-" + tip_casa_url + "/" + property.getOras().toLowerCase() + "/" + cartier + "?nrcamere=" + nr_camere);
//			
//			for (Element priceList:temp) {
//				
//				i += 1;
//				
//				vector_preturi.add(priceList.getElementsByClass("pret-mare").first().text());
//			}
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		int pagina = 1;
		
		while (true) {
			try {
				Document doc = Jsoup.connect("https://www.imobiliare.ro/vanzare-" + tip_casa_url + "/" + property.getOras().toLowerCase() + "/" + cartier + "?nrcamere=" + nr_camere + "&pagina=" + pagina).get();
				Elements temp = doc.select("div.pret");
				
				//System.out.println("link-ul este " + "https://www.imobiliare.ro/vanzare-" + tip_casa_url + "/" + property.getOras().toLowerCase() + "/" + cartier + "?nrcamere=" + nr_camere);
				
				for (Element priceList:temp) {
					
					i += 1;
					
					vector_preturi.add(priceList.getElementsByClass("pret-mare").first().text());
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			pagina += 1;
			
			//scanez 30 de pagini de rezultate, cu cate 30 de anunturi, aka 900 de rezultate
			
			if (pagina == 30) {
				break;
			}
		}
		
		
		
//		for (int x = 0; x < i; x++) {
//			System.out.println(vector_preturi.get(x));
//		}
		
		//conversia in vector de inti
		
		int preturi[] = new int[vector_preturi.size()];
		
		//TODO: de reparat cand o casa costa un milion de euro sau mai mult
		//REPARAT
		
		for (int x = 0; x < vector_preturi.size(); x++) {
			
//			String aux = vector_preturi.get(x).substring(0, vector_preturi.get(x).indexOf('.')) + vector_preturi.get(x).substring(vector_preturi.get(x).indexOf('.')+1);
			String aux = vector_preturi.get(x);
			while (aux.indexOf('.') != -1) {
				aux = aux.substring(0, aux.indexOf('.')) + aux.substring(aux.indexOf('.')+1);

			}
			
			preturi[x] = Integer.parseInt(aux);
			
		}
		
		long avg = 0;
		
		for (int x = 0; x < vector_preturi.size(); x++) {
			avg += preturi[x];
		}
		
		avg /= vector_preturi.size();
		
		int ieftin = 0, scump = 0;
		
		Arrays.sort(preturi);
		
		ieftin = preturi[0];
		scump = preturi[vector_preturi.size()-1];
		
		System.out.println("Pretul estimat pentru proprietatea dumneavoastra este: " + avg + " Euro");
		System.out.println("Cea mai ieftina proprietate din zona: " + ieftin + " Euro");
		System.out.println("Cea mai scumpa proprietate din zona: " + scump + " Euro");
		
//		for (int x = 0; x < vector_preturi.size(); x++) {
//			System.out.println(preturi[x]);
//		}
		
		
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
