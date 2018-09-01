package lineregression.zl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class IOUtil {

	public static BufferedReader getReader(String path) throws FileNotFoundException {

		return new BufferedReader(new FileReader(path));
	}
}
