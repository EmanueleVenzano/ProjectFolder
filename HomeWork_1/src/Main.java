import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
	public static void main (String[] args) throws Exception {
		Path dictPath = Paths.get(args[1]);
		Path tabPath;
		Path filePath = null;
		Path okPath = null;
		Path spamPath = null;
		Path outputPath = null;
		
		if (args[0] == "TEST") {
			tabPath = Paths.get(args[2]);
			filePath = Paths.get(args[3]);
		}else {
			okPath = Paths.get(args[2]);
			spamPath = Paths.get(args[3]);
			tabPath = Paths.get(args[4]);
			outputPath = Paths.get(args[5]);
		}
		Test testingMode = new Test (dictPath, tabPath, filePath);
		Train trainingMode = new Train (dictPath, tabPath, okPath, spamPath);
		TrainClass generateMode = new TrainClass (dictPath, tabPath, okPath, spamPath, outputPath, new Integer(args[6]).intValue(), new Integer(args[7]).intValue());
		switch (args[0]) {
		case ("TEST"):
			testingMode.analyze();
			break;
		case ("TRAIN"):
			trainingMode.createTable();
			break;
		case("GENERATE"):
			generateMode.createTable();
			break;
		default: return;
		}
	}
}