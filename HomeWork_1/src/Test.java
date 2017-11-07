import java.nio.file.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
	/*main test dizionario tabella direct_testi
	 * 
	 * 
	 *TEST
	 * if (args(5)!=1){
	 * 	for each (args(4))
	 * 		testa questo file (args(5)=0)
	 * }else{
	 * 	testa args(4) (args(5)=1)
	 * }
	 * 
	 * 
	 * testa
	 * if (args(5)!=1){
	 * 	//mi ha invocato il main in fase di test, usa il delta giusto
	 * }eles{
	 * 	//mi ha invocato train, usa delta fisso e variabile
	 * }
	*/
	
	void getDistance(Path pathDict, Path pathTab, Path pathFile, ArrayList<Integer> ok, ArrayList<Integer> spam) {
		ArrayList<Integer> occInFile = new ArrayList<Integer>();
		ArrayList <String> Dictionary = new ArrayList <String>();		
		String FILENAME = pathDict.toString();
		String TAB_FILENAME = pathTab.toString();
		Dictionary=uploadFile(FILENAME,0);
		occInFile=countWord(pathFile,Dictionary);
		
		ArrayList<Integer> temp = new ArrayList<Integer>();
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(TAB_FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			int lab=0;
			while ((sCurrentLine = br.readLine()) != null) {
				ArrayList <String> aList= new ArrayList <String> (Arrays.asList(sCurrentLine.split(" ")));
				if (sCurrentLine == "OK") {
					lab=1;
					continue;
				}else if (sCurrentLine == "SPAM") {
					lab=2;
					continue;
				}
				if (lab == 1) {
					for (int i=1; i<aList.size(); i++) {
						temp.set(i-1,Integer.valueOf(aList.get(i)));
						Integer S=calcD(occInFile,temp);
						ok.set(i-1, S);
					}
				}else if (lab == 2) {
					for (int i=1; i<aList.size(); i++) {
						temp.set(i-1,Integer.valueOf(aList.get(i)));
						Integer S=calcD(occInFile,temp);
						spam.set(i-1, S);
					}
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
	}
	
	Integer calcD (ArrayList<Integer> occInFile, ArrayList<Integer> temp) {
		Integer S=0;
		for (int i=0; i<occInFile.size(); i++) {
			S+=(occInFile.get(i)-temp.get(i))*(occInFile.get(i)-temp.get(i));
		}
		return S;
	}
}
