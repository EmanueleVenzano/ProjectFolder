import java.nio.file.*;
import java.util.ArrayList;
import java.io.FileReader;//
import java.io.BufferedReader;//
import java.io.*;
import java.util.Arrays;
import java.util.Collections;

public class Optimize {
	
	// Assumo che la tabella sia stata compilata nel modo corretto
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
				}if (sCurrentLine == "SPAM") {
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
	
	String analyzeCost (int D, Path file, ArrayList<String> dictionary, String tabPath) {
		ArrayList<Integer> distanceFromOk = new ArrayList<Integer>();
		ArrayList<Integer> distanceFromSpam = new ArrayList<Integer>();
		ArrayList<Integer> occInFile = occArray (uploadFile(file.toString(), 1), dictionary);
		getDistance(occInFile, tabPath, distanceFromOk, distanceFromSpam);
		String res = resCost(distanceFromOk, distanceFromSpam, D);
		return file.getFileName().toString().concat(" ").concat(res);
	}
	
	String analyzeVar (int p, Path file, ArrayList<String> dictionary, String tabPath) {
		ArrayList<Integer> distanceFromOk = new ArrayList<Integer>();
		ArrayList<Integer> distanceFromSpam = new ArrayList<Integer>();
		ArrayList<Integer> occInFile = occArray (uploadFile(file.toString(), 1), dictionary);
		getDistance(occInFile, tabPath, distanceFromOk, distanceFromSpam);
		String res = resVar(distanceFromOk, distanceFromSpam, p);
		return file.getFileName().toString().concat(" ").concat(res);
	}
	
	String resCost (ArrayList<Integer> ok, ArrayList<Integer> spam, int D) {
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
	
	String resVar (ArrayList<Integer> ok, ArrayList<Integer> spam, int p) {
		Collections.sort(ok);
		Collections.sort(spam);
		int countOk = 0;
		int countSpam = 0;
		do {
			int i = 0;
			int j = 0;
			while (ok.get(i) < spam.get(j)) {
				countOk++;
				i++;
			}
			while (ok.get(i) > spam.get(j)) {
				countSpam++;
				j++;
			}
			if (ok.get(i) == spam.get(j)) {
				i++;
				j++;
			}
		} while ((countOk + countSpam) < p);
		if (countOk < countSpam) {
			return "SPAM";
		}else if (countSpam < countOk) {
			return "OK";
		}
		return "UNDEFINED";
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
		String FILENAME = arg.getFileSystem().toString();
		ArrayList<String> temp = uploadFile (arg.toString(), 1);
		ArrayList <Integer> cont = occArray(temp, dictionary);
		for (int i=0; i< cont.size(); i++) {
			FILENAME=FILENAME.concat(" ").concat(cont.get(i).toString());
		}
		return FILENAME;
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
