import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
	private Path tabPath;
	private ArrayList<String> dictionary;
	private ArrayList<Path> filePath;
	private int Delta;
	
	public Test(Path dictPath, Path tabPath, Path dirFilePath) throws Exception {
		this.tabPath=tabPath;
		DirectoryStream<Path> dirFileStream = Files.newDirectoryStream(dirFilePath);
		for (Path entry: dirFileStream) {
			filePath.add(entry);
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
	
	void analyze () {
		ArrayList<Integer> distanceFromOk = new ArrayList<Integer>();
		ArrayList<Integer> distanceFromSpam = new ArrayList<Integer>();
		for (Path entry : filePath) {
			ArrayList<Integer> occInFile = occArray (uploadFile(entry.toString(), 1), dictionary);
			getDistance(occInFile, tabPath.toString(), distanceFromOk, distanceFromSpam);
			String res= attribute(distanceFromOk, distanceFromSpam, Delta);
			System.out.println(entry.getFileName().toString()+" "+res);
			distanceFromOk.clear();
			distanceFromSpam.clear();
		}
	}
	
	void getDistance (ArrayList<Integer> occInFile, String tabPath, ArrayList<Integer> ok, ArrayList<Integer> spam) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(tabPath);
			br = new BufferedReader(fr);
			int label = 0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				if (sCurrentLine == "OK") {
					label = 1;
					continue;
				}if (sCurrentLine == "OK") {
					label = 2;
					continue;
				}
				ArrayList <String> aList= new ArrayList <String> (Arrays.asList(sCurrentLine.split(" ")));
				for (int i=1; i<aList.size(); i++) {
					temp.add(Integer.valueOf(aList.get(i)));
				}
				Integer S = calcD (occInFile, temp);
				if (label == 1) {
					ok.add(S);
				}else if (label == 2) {
					spam.add(S);
				}
				temp.clear();
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
	}
	
	// Assumo abbiano la stessa lunghezza
	Integer calcD (ArrayList<Integer> occInFile, ArrayList<Integer> temp) {
		Integer S = 0;
		for (int i=0; i<occInFile.size(); i++) {
			S+=(occInFile.get(i)-temp.get(i))*(occInFile.get(i)-temp.get(i));
		}
		return S;
	}
	
	String attribute (ArrayList<Integer> ok, ArrayList<Integer> spam, int D) {
		int countOk = 0;
		int countSpam = 0;
		for (int i=0; i<ok.size(); i++) {
			if (ok.get(i)<=D) countOk++;
		}
		for (int i=0; i<ok.size(); i++) {
			if (ok.get(i)<=D) countSpam++;
		}
		if (countOk<countSpam) {
			return "SPAM";
		}else if (countSpam<countOk) {
			return "OK";
		}
		return "UNDEFINED";
	}
	
	String analyze (int D, Path file, ArrayList<String> dictionary, String tabPath) {
		ArrayList<Integer> distanceFromOk = new ArrayList();
		ArrayList<Integer> distanceFromSpam = new ArrayList();
		ArrayList<Integer> occInFile = occArray (uploadFile(file.toString(), 1), dictionary);
		getDistance(occInFile, tabPath, distanceFromOk, distanceFromSpam);
		String res= attribute(distanceFromOk, distanceFromSpam, D);
		return file.getFileName().toString().concat(" ").concat(res);
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
