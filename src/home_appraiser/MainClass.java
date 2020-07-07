package home_appraiser;

import java.util.Scanner;



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
		
		
		/*
		System.out.println("Ati introdus urmatoarea proprietate: " + property.getType() + ", " + property.getSuprafata() + " m2, " +
		property.getNrCamere() + " camere, in orasul " + property.getOras());
		*/
		
	}

}
