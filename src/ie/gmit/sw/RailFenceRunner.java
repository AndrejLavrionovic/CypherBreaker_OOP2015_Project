package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RailFenceRunner {
	
	private static int key = 1;

	public static void main(String[] args) {
		// INSTANCE
		RailFence rf = new RailFence();
		TextScorrer ts = new TextScorrer();
		
		// VARIABLES
		int i;
		String encriptedText = "TTFOHATGRNREEANOETYRCIMHHAKT";
		
		// TEXT SCORRER
		//**********************************************************************************
		
		
		
		//**********************************************************************************
		// 2) Creating the number of treads that will check for keys
		//**********************************************************************************
		for(i = 2; i <= encriptedText.length() / 2; i++){
			new Thread(new Runnable(){
				int k = nextKey(encriptedText);
				@Override
				public void run() {
					String text = rf.decrypt(encriptedText, k);
					int score = ts.calculateScore(text);
				}
				
			});
		}
		//**********************************************************************************
		//**********************************************************************************
	}
	
	public static synchronized int nextKey(String encriptedText){
		if(key <= encriptedText.length() / 2){
			key++;
		}
		return key;
	}
	
	

}
