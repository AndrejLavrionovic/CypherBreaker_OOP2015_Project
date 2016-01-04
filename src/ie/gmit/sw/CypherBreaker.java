package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;


public class CypherBreaker {

	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	
	private volatile Double highestScore = 0.0;
	private Resultable finalResult;
	
	private Map<String, Double> qgm;
	
	public CypherBreaker(String cypherText, Map<String, Double> qgm){
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		this.qgm = qgm;
		
		init();
	}
	
	public void init(){
		// Start a load of producers
		
		for(int i = 2; i < cypherText.length() / 2; i++){
			new Thread(new Decrypter(queue, cypherText, i, qgm)).start();
		}
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				
				while(!queue.isEmpty()){
					try{
						Resultable r = queue.take();
						// Do something
						double tempScore = 0.0;
						tempScore = r.getScore();
						
						if(tempScore > highestScore){
							 highestScore = tempScore;
							 finalResult = r;
						}
					}
					catch(InterruptedException e){
						e.printStackTrace();
					}
				}
			}
		});
		
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Resultable getFinalResult(){
		return finalResult;
	}
	
}
