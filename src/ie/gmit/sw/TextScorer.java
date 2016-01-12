package ie.gmit.sw;

import java.util.Map;

public class TextScorer {
	
	private Double score = 0.0;
	
	public Double getScore(String plainText, Map<String, Double> qgm){
		
		
		String quadGram = null;
		
		for(int i = 0, j = 4; j <= plainText.length(); i++, j++){
			quadGram = plainText.substring(i, j);
			
			if(qgm.containsKey(quadGram)){
				score += qgm.get(quadGram);
			}
			
			//System.out.println(quadGram);
		}
		
		return score;
	}
} // class
