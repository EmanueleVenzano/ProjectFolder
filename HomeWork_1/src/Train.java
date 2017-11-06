import java.nio.file.*;
import java.util.ArrayList;
import java.io.FileReader;//
import java.io.BufferedReader;//
import java.io.*;
import java.util.Arrays;

//TODO vedere dopo gli argomenti passati
public class Train {
	void feed(String[] args) throws Exception {
		
		Path dirPathDict = Paths.get(args[2]);
		Path dirPathOk = Paths.get(args[3]);
		Path dirPathSpam = Paths.get(args[4]);
		Path dirPathTab = Paths.get(args[5]);
		
		DirectoryStream<Path> dirStreamOk = Files.newDirectoryStream(dirPathOk);
		DirectoryStream<Path> dirStreamSpam = Files.newDirectoryStream(dirPathSpam);
		String FILENAME = dirPathDict.toString();
		String T_FILENAME = dirPathTab.toString();
		
		ArrayList <String> Dictionary = new ArrayList <String>();		
		Dictionary=uploadFile(FILENAME,0);
				
		ArrayList <Path> percorsiOk = new ArrayList<Path>();
		ArrayList <Path> percorsiSpam = new ArrayList<Path>();
		for (Path entry: dirStreamOk) {
			percorsiOk.add(entry);
		}
		for (Path entry: dirStreamSpam) {
			percorsiSpam.add(entry);
		}
		
		//directory.size, dirextory.size/10
		for (int i=6; i<60; i+=6) {//i indicizza la fine del pacchetto su cui faremo testing
			//ok part
			writeTable(T_FILENAME, "OK");
			for (int j=0; j<i-6; j++) {//tutti i file prima di quelli del pacchetto di testing
				makeOutput(percorsiOk.get(j), Dictionary, T_FILENAME);
			}
			for (int j=i; j<60; j++){//tutti i file dopo di quelli del pacchetto di testing
				makeOutput(percorsiOk.get(j), Dictionary, T_FILENAME);
			}
			
			//spam part
			writeTable(T_FILENAME, "SPAM");
			for (int j=0; j<i-6; j++) {
				makeOutput(percorsiSpam.get(j), Dictionary, T_FILENAME);
			}
			for (int j=i; j<60; j++){
				makeOutput(percorsiSpam.get(j), Dictionary, T_FILENAME);
			}
			for (int j=i-6; j<i; j++) {
				//TEST(percorsiOk.get(j),1)
				//TEST(percorsiSpam.get(j),1)
			}
		}
	}
	
	void makeOutput (Path arg, ArrayList <String> Dictionary, String T_FILENAME){
		ArrayList <String> temp = new ArrayList <String>();
		ArrayList <Integer> cont = new ArrayList <Integer>(Dictionary.size());
		
		for (Integer i: cont) {
			i=0;
		}
		String FILENAME = arg.toString();
		temp=uploadFile(FILENAME,1);
		for (int k=0; k<Dictionary.size(); k++){
			for (String s: temp) {
				if (s==Dictionary.get(k)) {
					cont.set(k,cont.get(k)+1);
				}
			}
		}
		writeTable(T_FILENAME, arg.getFileName()+" ");
		for (int  k=0; k<Dictionary.size(); k++) {
			writeTable(T_FILENAME, cont.get(k)+" ");
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
	
	void writeTable (String T_FILENAME, String content) {
		BufferedWriter bw = null;
		FileWriter fw = null;
		try {
			fw = new FileWriter(T_FILENAME);
			bw = new BufferedWriter(fw);
			bw.write(content);
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
}