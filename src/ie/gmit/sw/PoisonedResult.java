package ie.gmit.sw;

public class PoisonedResult implements Resultable {
	
	// Instant variables
	private String plainText;
	private int key;
	private double score;
	
	public PoisonedResult(){}

	@Override
	public String getPlainText() {
		return plainText;
	}

	@Override
	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	@Override
	public int getKey() {
		return key;
	}

	@Override
	public void setKey(int key) {
		this.key = key;
	}

	@Override
	public double getScore() {
		return score;
	}

	@Override
	public void setScore(double score) {
		this.score = score;
	}

}
