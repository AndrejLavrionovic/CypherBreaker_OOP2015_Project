package ie.gmit.sw;

import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class RailFenceRunner {
	
	private static int key = 1;

	public static void main(String[] args){
		// INSTANCE
		RailFence rf = new RailFence();
		FileParser fp = new QuadGramMap();
		
		// Map
		Map<String, Double> qgm = null;
		try {
			qgm = fp.parse("4gram.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// VARIABLES
		System.out.println("Enter the text -->   ");
		String text = new Scanner(System.in).nextLine();
		
		String encriptedText = rf.encrypt(text, 8);
		
		//String encriptedText = "TTFOHATGRNREEANOETYRCIMHHAKT";
		
		CypherBreaker cb = new CypherBreaker(encriptedText, qgm);
		
		Resultable r = cb.getFinalResult();
		
		System.out.println("Result is > " + r.getPlainText() + "; Key is > " + r.getKey());
		
	} // main
} // class
