package ie.gmit.sw;

import java.util.Map;

public interface FileParser {

	//put into its own class
	public Map<String, Double> parse(String file) throws Exception;
}
