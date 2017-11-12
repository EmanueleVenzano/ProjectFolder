import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.lang.Exception;
import java.nio.file.DirectoryIteratorException;

public class prova {
	public static void main (String args[]) throws Exception {
		System.out.println(Paths.get(args[0]).toString());
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(args[0]), "*.txt")) {
		    /*for (Path file: stream) {
		        System.out.println(file.getFileName().toString());
		        for (Path entry: stream) {
					System.out.println(entry.toString());
					FileReader fr = null;
					BufferedReader br = null;
					try {
						fr = new FileReader(entry.toString());
						br = new BufferedReader(fr);

						String sCurrentLine;
						while ((sCurrentLine = br.readLine()) != null) {
							System.out.println(sCurrentLine);
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
		    }*/
		} catch (IOException | DirectoryIteratorException x) {
		    // IOException can never be thrown by the iteration.
		    // In this snippet, it can only be thrown by newDirectoryStream.
		    System.err.println(x);
		}
	}
}
