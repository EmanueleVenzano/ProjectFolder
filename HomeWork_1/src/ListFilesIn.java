import java.nio.file.*;
    
public class ListFilesIn {

    public static void main(String args[]) throws Exception {
	
	// Invoca il metodo statico "factory" della classe Paths
	// che crea un oggetto avente interfaccia "Path"
	Path dirPath = Paths.get(args[0]);

	// Apre un nuovo directory stream che consente di iterare
	// i file della directory (come oggetti a interfaccia Path)
	DirectoryStream<Path> dirStream = Files.newDirectoryStream(dirPath);

	for (Path entry: dirStream) {
	    // Stampa la entry e anche il nome file
	    // L'interfaccia Path ha molti metodi per "navigare"
	    // nel pathname
	    System.out.println(entry + " " + entry.getFileName());
	}
	
    }
}
