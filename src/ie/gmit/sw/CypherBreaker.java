package ie.gmit.sw;

import java.util.*;
import java.util.concurrent.*;


public class CypherBreaker {

	private static final int MAX_QUEUE_SIZE = 100;
	private BlockingQueue<Resultable> queue;
	private String cypherText;
	private volatile int key = 1;
	
	private volatile double highestScore = 0f;
	private Resultable finalResult;
	private Resultable POISONED_RESULT = null;
	private volatile int counter = 1;
	
	private Map<String, Double> qgm;
	
	Object lock = new Object();
	
	public CypherBreaker(String cypherText, Map<String, Double> qgm){
		queue = new ArrayBlockingQueue<Resultable>(MAX_QUEUE_SIZE);
		this.cypherText = cypherText;
		this.qgm = qgm;
		
		init();
	}
	
	public void init(){
		
		// Start a load of producers
		while(true){
			key++; // incrementing key (starts from 2)
			
			produce(key);
			
			
			if(key == cypherText.length() / 2){ // if last thread produced
				
				// PLAN: After all producers are pushed, then poisoned pill is putting into queue
				// NEVER WORK FOR ME
				// PLAN RUINED
				/*
				 try {
					poison();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				*/
				break; // stop producing
			}
			
		}
		
		Thread t = new Thread(new Runnable(){
			public void run(){
				
				// setting up counter to 2
				counter = 2;
				
				while(true){
					try{
						Resultable r = queue.take();
						
						// tying to poison the queue, but poisoned result never waits for all producers???????
						/*
						if(r.equals(POISONED_RESULT)){
							break;
						}
						*/
						
						// keep the highest score
						double tempScore = r.getScore();
						
						if(tempScore > highestScore){
							 highestScore = tempScore;
							 finalResult = r;
						}
						 
						// killing the queue
						if(counter == cypherText.length() / 2){
							break;
						}
						counter++;
						
						System.out.println(r.getPlainText() + " KEY > " + r.getKey());
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
	
	// create Poisoned Pill to kill the queue
	public void poison() throws InterruptedException{
		synchronized(lock){

			POISONED_RESULT = new PoisonedResult();
			try {
				queue.put(POISONED_RESULT); // push poisoned pill
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	// producers
	public void produce(int key){
		new Thread(new Decrypter(queue, cypherText, key, qgm)).start();
	}
}
