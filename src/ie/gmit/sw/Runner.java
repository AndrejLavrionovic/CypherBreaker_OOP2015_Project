package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class Runner {
	
	// CONSTANTS
	private static final String GRAM_FILE = "4grams.txt";
	private static final String PROLOGUE_FILE =  "overview.txt";
	public static void main(String[] args){
		
		// INSTANCE
		RailFence rf = new RailFence();
		FileParser fp = new QuadGramMap();
		
		// Map
		Map<String, Double> qgm = null;
		try {
			qgm = fp.parse(GRAM_FILE);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String encriptedText = encriptText(rf);
		
		//String encriptedText = "TTFOHATGRNREEANOETYRCIMHHAKT";
		
		CypherBreaker cb = new CypherBreaker(encriptedText, qgm);
		
		Resultable r = cb.getFinalResult();
		
		System.out.println("Result is > " + r.getPlainText() + "; Key is > " + r.getKey());
		
	} // main
	
	public static String encriptText(RailFence rf){
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		
		// VARIABLES
		int key = 2;
		String text = null;
		int opt;
		String fileName;
		
		// PROLOGUE
		System.out.println("A Multi-threaded Cypher Breaker\n");
		prologue(PROLOGUE_FILE);
		
		printOpt();
		System.out.print("> ");
		opt = s.nextInt();
		s.nextLine();
		
		while(opt < 1 || opt > 2){
			System.out.println("Choose again:");
		}
		
		switch(opt){
		case 1:
			System.out.println("\nYou must to place file into root folder:");
			System.out.print("As you've done this print the full file name (e.g. file.txt):\n> ");
			fileName = s.next();
			text = getFileText(fileName);
			break;
		case 2:
			// Prompt text
			System.out.print("Enter the text -->   ");
			text = s.nextLine().toUpperCase().replaceAll(" ", "");
			break;
			default:
				System.out.println("Option is not valid");
		}
		
		// Prompt key
		System.out.print("Enter key -->   ");
		key = s.nextInt();
		
		while(key < 2 || key > text.length() / 2){
			System.out.println("Key must be greater than 2 and less than " + text.length() / 2);
			System.out.println("Try again -->   ");
			
			key = s.nextInt();
		}
		
		String encriptedText = rf.encrypt(text, key);
		
		return encriptedText;
	}
	
	public static void printOpt(){
		
		System.out.println("\n1 - Text from file");
		System.out.println("2 - Propt Text");
	}
	
	private static void prologue(String file){
		String str = null;
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			while((str = br.readLine()) != null){
				System.out.println(str);
			}
			
		} catch (FileNotFoundException fExc) {
			System.err.println(fExc.getMessage());
		}catch (IOException ioExc){
			System.err.println(ioExc.getMessage());
		}
	}
	
	private static String getFileText(String file){
		String line = null;
		String str = "";
		
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			while((line = br.readLine()) != null){
				str += line.toUpperCase().replaceAll(" ", "");
			}
		} catch (FileNotFoundException fExc) {
			System.err.println(fExc.getMessage());
		} catch (IOException ioExc){
			System.err.println(ioExc.getMessage());
		}
		
		return str;
	}
} // class
