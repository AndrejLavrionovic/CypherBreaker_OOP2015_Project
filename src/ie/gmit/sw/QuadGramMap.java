package ie.gmit.sw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class QuadGramMap implements FileParser {
	public static final int GRAM_SIZE = 0;
	
	//put into its own class
		public Map<String, Double> parse(String file) throws Exception{
			
			String key;
			Double value;
			String line = "";
			
			// INSTANCE
			Map<String, Double> qgm = new ConcurrentHashMap<String, Double>();
			
			// READING FROM FILE
			try{
				BufferedReader br = new BufferedReader(new FileReader("4grams.txt"));
				
				while((line = br.readLine()) != null){
					
					// splitting each line on keys and values
					String[] arr = line.split(" ");
					
					key = arr[0];
					value = Double.parseDouble(arr[1]);
					
					qgm.put(key, value); // adding the key-values into concurrenthashmap
				}
			}catch(Exception e){
				System.err.println(e.getMessage());
			}
			
			return qgm;
		}
}
