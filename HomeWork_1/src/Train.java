import java.nio.file.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;
import java.util.Arrays;

public class Train {
	private ArrayList<Path> okPath = new ArrayList<Path>();
	private ArrayList <Path> spamPath = new ArrayList<Path>();
	private ArrayList <String> dictionary = new ArrayList<String>();
	private String T_FILENAME;
	
	public Train (Path dictPath, Path tabPath, Path dirOkPath, Path dirSpamPath) throws Exception {
		T_FILENAME = tabPath.toString();
		DirectoryStream<Path> dirOkStream = Files.newDirectoryStream(dirOkPath);
		DirectoryStream<Path> dirSpamStream = Files.newDirectoryStream(dirSpamPath);
		for (Path entry: dirOkStream) {
			okPath.add(entry);
		}
		for (Path entry: dirSpamStream) {
			spamPath.add(entry);
		}
		dictionary = uploadFile (dictPath.toString(),0);
	}
	
	ArrayList<Integer> occArray (ArrayList<String> temp, ArrayList<String> dictionary){
		ArrayList<Integer> cont = new ArrayList<Integer>();
		for (int k=0; k<dictionary.size(); k++){
			for (String s: temp) {
				if (s==dictionary.get(k)) {
					cont.set(k,cont.get(k)+1);
				}
			}
		}
		return cont;
	}
	
	String calcOcc (Path arg, ArrayList<String> dictionary) {
		String FILENAME = arg.getFileName().toString();
		ArrayList<String> temp = uploadFile (arg.toString(), 1);
		ArrayList <Integer> cont = occArray(temp, dictionary);
		for (int i=0; i< cont.size(); i++) {
			FILENAME=FILENAME.concat(" ");
			FILENAME=FILENAME.concat(cont.get(i).toString());
		}
		return FILENAME;
	}
	
	void createTable () {
		String temp;
		makeOutput(T_FILENAME, "OK");
		for (int i=0; i<okPath.size(); i++) {
			temp = calcOcc(okPath.get(i), dictionary);                                        
    		makeOutput (T_FILENAME, temp);
		}
		makeOutput(T_FILENAME, "SPAM");
		for (int i=0; i<spamPath.size(); i++) {
			temp = calcOcc(okPath.get(i), dictionary);                                        
    		makeOutput (T_FILENAME, temp);
		}
	}
		
	void makeOutput (String T_FILENAME, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(T_FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	
	ArrayList<String> uploadFile(String FILENAME, int choose) {
		ArrayList <String> temp = new ArrayList <String>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				if (choose==1) {
					ArrayList <String> aList= new ArrayList <String> (Arrays.asList(sCurrentLine.split(" ")));
					for(int i=0;i<aList.size();i++)
					{
					    temp.add(aList.get(i));
					}
				}else if (choose==0) {
					temp.add(sCurrentLine);
				}
			}
		 } catch (IOException e) {
			e.printStackTrace();
		 } finally {
			try {
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		return temp;
	}
}