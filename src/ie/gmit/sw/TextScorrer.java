package ie.gmit.sw;

import java.util.Map;

public class TextScorrer extends QuadGramMap {
	
	private int score = 0;
	private Map<String, Integer> qgm;
	
	public TextScorrer(){
		this.qgm = super.parseQuadGramMap();
	}

	// checking the text and returning the score
	
	public int calculateScore(String text){
		
		String quadGram;
		
		for(int i = 0, j = 4; j <= text.length(); i++, j++){
			
			quadGram = text.substring(i, j);
			score += qgm.get(quadGram);
		}
		
		return score;
	}
	
}
