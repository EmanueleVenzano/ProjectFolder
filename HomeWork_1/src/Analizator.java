import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public class Analizator {
	public class collec {
		private String valore;
		private String delta;
		private int count;
		
		public collec() {
			valore = "";
			delta = "";
			count = 0;
		}
	}

	//pathok pathspam pathfile 
	public static void main (String[] args) throws Exception {
		Path okPath = Paths.get(args[0]);
		Path spamPath = Paths.get(args[1]);
		DirectoryStream<Path> dirOkStream = Files.newDirectoryStream(okPath);
		DirectoryStream<Path> dirSpamStream = Files.newDirectoryStream(spamPath);
		ArrayList<String> ok = new ArrayList<String>();
		ArrayList<String> spam = new ArrayList<String>();
		for (Path entry : dirOkStream) {
			ok.add(entry.getFileName().toString());
		}
		for (Path entry : dirSpamStream) {
			spam.add(entry.getFileName().toString());
		}
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader(args[2]);
			br = new BufferedReader(fr);
			collec temp;
			String sCurrentLine;
			FileWriter fw =  new FileWriter("output.txt");
			PrintWriter pw = new PrintWriter (fw);
			int lab;
			while ((sCurrentLine = br.readLine()) != null) {
				ArrayList <String> aList= new ArrayList <String> (Arrays.asList(sCurrentLine.split(" ")));
				if (aList.get(0) == "D") {
					pw.println(temp.delta+" "+temp.valore+" "+ new Integer(temp.count).toString());
					temp.delta = "D";
					temp.valore = aList.get(1);
					temp.count = 0;
					continue;
				}else if (aList.get(0) == "p") {
					pw.println(temp.delta+" "+temp.valore+" "+ new Integer(temp.count).toString());
					temp.delta = "p";
					temp.valore = aList.get(1);
					temp.count = 0;
					continue;
				}
				if (aList.get(1) == "OK") {
					lab = 0;
					for (int i=0; i<ok.size(); i++) {
						if (ok.get(i)==aList.get(0)) {
							lab = 1;
						}
					}
					if (lab != 1) {
						temp.count ++;
					}
				}else if (aList.get(1) == "SPAM") {
					lab = 0;
					for (int i=0; i<spam.size(); i++) {
						if (spam.get(i)==aList.get(0)) {
							lab = 1;
						}
					}
					if (lab != 1) {
						temp.count ++;
					}
				}else {
					temp.count ++;
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
}