import java.nio.file.*;
import java.util.ArrayList;
import java.io.FileReader;//
import java.io.BufferedReader;//
import java.io.*;

//TODO vedere dopo gli argomenti passati
public class Train {
	void feed(String[] args) throws Exception {
		
		Path dirPathDict = Paths.get(args[2]);
		Path dirPathOk = Paths.get(args[3]);
		Path dirPathSpam = Paths.get(args[4]);
		
		DirectoryStream<Path> dirStreamOk = Files.newDirectoryStream(dirPathOk);
		DirectoryStream<Path> dirStreamSpam = Files.newDirectoryStream(dirPathSpam);
		
		
		ArrayList <String> Dictionary = new ArrayList <String>();		
		FileReader fr = null;
		BufferedReader br = null;
		String FILENAME = dirPathDict.toString();
		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				Dictionary.add(sCurrentLine);
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
			// TODO gestire scrittura file
			
			//ok part
			System.out.println("OK");
			for (int j=0; j<i-6; j++) {//tutti i file prima di quelli del pacchetto di testing
				makeOutput(percorsiOk.get(j), Dictionary);
			}
			for (int j=i; j<60; j++){//tutti i file dopo di quelli del pacchetto di testing
				makeOutput(percorsiOk.get(j), Dictionary);
			}
			
			//spam part
			System.out.println("SPAM");
			for (int j=0; j<i-6; j++) {
				makeOutput(percorsiSpam.get(j), Dictionary);
			}
			for (int j=i; j<60; j++){
				makeOutput(percorsiSpam.get(j), Dictionary);
			}
		}
		
	}
	
	//TODO gestire scrittura file
	//TODO ottimizzare
	void makeOutput (Path arg, ArrayList <String> Dictionary){
		String[] temp = new String[128];
		String[] fina = new String[0];
		String[] temp1;
		ArrayList <Integer> cont = new ArrayList <Integer>(Dictionary.size());
		
		for (Integer i: cont) {
			i=0;
		}
		
		FileReader fr = null;
		BufferedReader br = null;
		String FILENAME = arg.toString();
		try {
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				temp=sCurrentLine.split(" ");
				temp1= new  String[fina.length+temp.length];
				temp1=fina;
				for(int i=0; i<temp.length;i++) {
					temp1[(fina.length)+i]=temp[i];
				}
				fina=temp1;
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
		
		//carica arg in temp
		for (int k=0; k<Dictionary.size(); k++){
			for (String s: fina) {
				if (s==Dictionary.get(k)) {
					cont.set(k,cont.get(k)+1);
				}
			}
		}
		System.out.println(arg.getFileName()+" ");
		for (int  k=0; k<Dictionary.size(); k++) {
			System.out.print(cont.get(k)+" ");
		}
	}
	
}
