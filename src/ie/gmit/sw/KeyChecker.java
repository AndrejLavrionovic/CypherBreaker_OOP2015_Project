package ie.gmit.sw;

public class KeyChecker implements Runnable{
	
	// VARIABLES
	private String cypherText;
	private int key;
	private String decriptedText;
	private int score;
	
	public KeyChecker(String cypherText, int key){
		this.cypherText = cypherText;
		this.key = key;
	}

	@Override
	public void run() {
		
		
		
	}
	
}
