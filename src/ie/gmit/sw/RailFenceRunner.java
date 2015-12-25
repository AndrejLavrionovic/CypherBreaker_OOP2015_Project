package ie.gmit.sw;

public class RailFenceRunner {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// INSTANCE
		RailFence rf = new RailFence();
		
		// VARIABLES
		int key = 5;
		String encriptedText = "TTFOHATGRNREEANOETYRCIMHHAKT";

		String s = rf.decrypt(encriptedText, key);
		System.out.println(">" + s);
	}

}
