import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
* COP 3530: Project 4 – Binary Search Trees
* <p>
* This class tests the binary tree being built. 
* A series of countries are found and deleted. 
* A series of traversals are executed and printed
* in InOrder, PostOrder, and PreOrder. The file
* Countries4.csv is read and the countries are 
* turned into nodes within the tree. 
*
* @author Camille Copeland
* @version 4/13/2020
*/
public class Project4 {

	/**
	* The purpose of this method is to delete several countries
	* and traverse the binary tree using various methods.
	*
	* @param parameter description of the parameter (one for each)
	* @return there are none
	*/
	public static void main(String[] args) throws FileNotFoundException {
		Country country;
		double x;
		
		Scanner in = new Scanner(System.in);//scanner to take input
		System.out.println("Please enter file name: ");
		String b = in.nextLine();// user input
		
		
		File file = new File(b);// takes the file 
		Scanner read = new Scanner(file);// allows for the file to be read 
		read.useDelimiter("\n|,");//allows parsing of each line
		read.nextLine();// skips the header
		
		BinarySearchTree test = new BinarySearchTree();
			
		for(int i = 0; i < 155; i++) {// for initializes each individual object in the array and assigns values to its attributes
			String ab = read.next();
			String ac = read.next();
			String ad = read.next();
			String ae = read.next();
			String af = read.next();
			String ag = read.next();
			country = new  Country(ab, ac, ad, ae, af, ag);
			country.setCountry(ab);
			country.setCountryCode(ac);
			country.setCapitol(ad);
			country.setPopulation(ae);
			country.setGDP(af);
			country.setHappy(ag);
			country.setperCap(ae, af);
			 
			
			x = country.getperCap();
			test.insert(ab, x);
			
			read.nextLine();	
		}
		System.out.println("Name                     GDP" + "\n" + "-----------------------------------------");
		test.printInorder(test.root);
		String a = "Australia";
		String d = "Greece";
		String c = "Norway";
		
		
		test.delete("Australia");
		test.delete(d);
		test.delete(c);
		
		System.out.println("Name                     GDP" + "\n" + "-----------------------------------------");
		test.printPreorder(test.root);
		
		
		test.find("Sri Lanka");
		test.find("North Cyprus");
		test.find("Greece");
		test.find("Australia");
		
		test.delete("Malawi");
		test.delete("Somalia");
		test.delete("Canada");
		test.delete("Tunisia");
		test.delete("New Zealand");
		
		System.out.println("Name                     GDP" + "\n" + "-----------------------------------------");
		test.printPostorder(test.root);
		
		System.out.println("Name                     GDP" + "\n" + "-----------------------------------------");
		test.printBottomFive(test.root);
		System.out.println("Name                     GDP" + "\n" + "-----------------------------------------");
		test.printTopFive(test.root); 

	}

}
