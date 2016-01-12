package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;


public class CypherBreaker {

	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private int key = 2;
	
	private volatile double highestScore = 0f;
	private Resultable finalResult;
	private Resultable POISONED_RESULT = null;
	private boolean run = true;
	private volatile int counter = 1;
	
	private Map<String, Double> qgm;
	
	private Object lock = new Object();
	
	public CypherBreaker(String cypherText, Map<String, Double> qgm){
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		this.qgm = qgm;
		
		init();
	}
	
	public void init(){
		// Start a load of producers
		
		for(int i = 2; i <= cypherText.length() / 2; i++){
			new Thread(new Decrypter(queue, cypherText, i, qgm)).start();
			try {
				increment();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				
				while(!queue.isEmpty()){

					try{
						Resultable r = queue.take();
						increment();
						if(r.equals(POISONED_RESULT)){
							
						}
						
						/*
						counter++;
						if(counter == (cypherText.length() / 2 - 1)){
							queue.put(new PoisonedResult());
						}
						*/
						
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
			e.printStackTrace();
		}
	}
	
	public Resultable getFinalResult(){
		return finalResult;
	}
	
	public void increment() throws InterruptedException{
		synchronized(lock){
			counter++;
			
			if(counter == (cypherText.length() / 2 - 1)){
				POISONED_RESULT = new PoisonedResult();
				queue.put(POISONED_RESULT);
			}
		}
	}
	
}
