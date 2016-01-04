package ie.gmit.sw;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public interface FileParser {

	//put into its own class
	public Map<String, Double> parse(String file) throws Exception;
}
