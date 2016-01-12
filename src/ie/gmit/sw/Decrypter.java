package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

public class Decrypter implements Runnable{
	
	// INSTANCES

	private BlockingQueue<Resultable> queue;
	private String sypherText;
	private int key;
	
	private Map<String, Double> qgm;


	// source -> Generate constructor from the fields
	public Decrypter(BlockingQueue<Resultable> queue, String sypherText, int key, Map<String, Double> qgm) {
		super();
		this.queue = queue;
		this.sypherText = sypherText;
		this.key = key;
		this.qgm = qgm;
	}
	
	@Override
	public void run() {
		try {
		
		// Decryption of text using key
		RailFence rf = new RailFence();
		String plainText = rf.decrypt(sypherText, key);
		
		// Getting score
		TextScorer ts = new TextScorer();
		double score = 0f;
		score = ts.getScore(plainText, qgm);
		
		// Push result into queue
		Resultable r = new Result(plainText, key, score);
		
		queue.put(r);
		
		System.out.println(plainText + "; key > " + key + "; Score > " + score);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
