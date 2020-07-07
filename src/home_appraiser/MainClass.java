package home_appraiser;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;




public class MainClass {

	public static void main(String[] args) {
		/*
		
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
		
		String tip_casa_url = null;
		
		if (property.getType() == "casa") {
			tip_casa_url = "case";
		} else {
			tip_casa_url = "apartamente-garsoniere";
		}
		*/
		
		try {
			/*
			Document doc = Jsoup.connect("https://www.olx.ro/imobiliare/" + tip_casa_url + "-de-vanzare/" + property.getNrCamere() 
			+ "/" + property.getOras().toLowerCase() + "/?search%5Bfilter_float_m%3Afrom%5D=" + sup_min 
			+ "&search%5Bfilter_float_m%3Ato%5D=" + sup_max).userAgent("Mozilla/17.0").get();
			*/
			
			Document doc = Jsoup.connect("https://www.olx.ro/imobiliare/apartamente-garsoniere-de-vanzare/2-camere/bucuresti/?search%5Bfilter_float_m%3Afrom%5D=35&search%5Bfilter_float_m%3Ato%5D=45").userAgent("Mozilla/17.0").get();
			Elements temp = doc.select("p.price");
			
			int i = 0;
			
			for (Element priceList:temp) {
				
				i += 1;
				
				System.out.println(i + " " + priceList.getElementsByTag("strong").first().text());
				
				
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		/*
		System.out.println("Ati introdus urmatoarea proprietate: " + property.getType() + ", " + property.getSuprafata() + " m2, " +
		property.getNrCamere() + " camere, in orasul " + property.getOras());
		*/
		
	}

}
