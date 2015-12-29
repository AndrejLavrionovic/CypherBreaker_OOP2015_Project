package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuadGramMap {
	
	private String line;
	private String key;
	private int value;
	
	public Map<String, Integer> parseQuadGramMap(){
		// INSTANCE
		Map<String, Integer> quadGramMap = new ConcurrentHashMap<String, Integer>();
		
		// READING FROM FILE
		try{
			BufferedReader br = new BufferedReader(new FileReader("4grams.txt"));
			
			while((this.line = br.readLine()) != null){
				
				// splitting each line on keys and values
				String[] arr = line.split(" ");
				key = arr[0];
				value = Integer.parseInt(arr[1]);
				
				quadGramMap.put(key,  value); // adding the key-values into concurrenthashmap
			}
		}catch(Exception e){
			System.err.println(e.getMessage());
		}
		
		return quadGramMap;
	}
	
}
