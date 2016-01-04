package ie.gmit.sw;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

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
		
		// Decryption of text using key
		RailFence rf = new RailFence();
		String plainText = rf.decrypt(sypherText, key);
		
		// Getting score
		TextScorer ts = new TextScorer();
		Double score = 0.0;
		score = ts.getScore(plainText, qgm);
		
		// Push result into queue
		Resultable r = new Result(plainText, key, score);
		
		try {
			queue.put(r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(plainText + "; key > " + key + "; Score > " + score);
		
	}
}
