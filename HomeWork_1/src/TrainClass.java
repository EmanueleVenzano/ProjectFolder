import java.nio.file.*;
import java.util.ArrayList;
import java.io.FileReader;//
import java.io.BufferedReader;//
import java.io.*;
import java.util.Arrays;

public class TrainClass extends Optimize {
	private ArrayList<Path> okPath = new ArrayList<Path>();
	private ArrayList <Path> spamPath = new ArrayList<Path>();
	private ArrayList <String> dictionary = new ArrayList<String>();
	private String T_FILENAME;
	private String outputFile;
	private int D;
	private int p;
	
	public TrainClass (Path dictPath, Path tabPath, Path dirOkPath, Path dirSpamPath, Path output, int D, int p) {
		T_FILENAME = tabPath.toString();
		outputFile = output.toString();
		DirectoryStream<Path> dirOkStream = Files.newDirectoryStream(dirOkPath);
		DirectoryStream<Path> dirSpamStream = Files.newDirectoryStream(dirSpamPath);
		for (Path entry: dirOkStream) {
			okPath.add(entry);
		}
		for (Path entry: dirSpamStream) {
			spamPath.add(entry);
		}
		dictionary = uploadFile (dictPath.toString(),0);
		this.D=D;
		this.p=p;
	}
	
	// Assumo che okPath.size()==spamPath.size()
	void createTable () {
		for (int z=0; z<2; z++) {
			if (z == 0) {
				makeOutput (outputFile, "D " + new Integer(D).toString());
			}else {
				makeOutput (outputFile, "p "+ new Integer(p).toString());
			}
			for (int i=6; i<okPath.size(); i+=6) {                                                    
		    	String temp;                                                                          
		    	makeOutput (T_FILENAME, "OK");                                                        
		    	for (int j=0; j<i-6; j++) {                                                           
		    		temp = calcOcc(okPath.get(j), dictionary);                                        
		    		makeOutput (T_FILENAME, temp);                                                    
		    	}                                                                                     
		    	for (int j=i; j<okPath.size(); j++) {                                                 
		    		temp = calcOcc(okPath.get(j), dictionary);                                        
		    		makeOutput (T_FILENAME, temp);                                                    
		    	}                                                                                     
		    	makeOutput (T_FILENAME, "SPAM");                                                      
		    	for (int j=0; j<i-6; j++) {                                                           
		    		temp = calcOcc(spamPath.get(j), dictionary);                                      
		    		makeOutput (T_FILENAME, temp);                                                    
		        }                                                                                     
		    	for (int j=i; j<okPath.size(); j++) {                                                 
	        		temp = calcOcc(spamPath.get(j), dictionary);                                      
		    		makeOutput (T_FILENAME, temp);                                                    
		    	}                                                                                     
		    	for (int j=i-6; j<i; j++) {                                                           
		    		if (z==0) {
		    			makeOutput(outputFile,analyzeCost(D, okPath.get(j), dictionary, T_FILENAME));
		    			makeOutput(outputFile,analyzeCost(D, spamPath.get(j), dictionary, T_FILENAME));
		    		}else {
		    			makeOutput(outputFile,analyzeVar(p, okPath.get(j), dictionary, T_FILENAME));
		    			makeOutput(outputFile,analyzeVar(p, spamPath.get(j), dictionary, T_FILENAME));
		    		}
		    	}                                                                                     
		    }                                                                                         
		}
	}
}