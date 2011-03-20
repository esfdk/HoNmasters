package bosk.ovelser.ovelse7.ovelse75;

import java.io.BufferedWriter;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

/**
 * Class WriterFork is used to allow a user to write to two objects implementing the Writer interface at the same time.
 * 
 * @author Jakob Melnyk
 * @version Vers 1
 */
public class WriterFork {
	Writer a;
	Writer b;

	/**
	 * Constructor for class WriterFork
	 * @param writerA First writer to be written to.
	 * @param writerB Second writer på be written to.
	 */
	public WriterFork(Writer writerA, Writer writerB){
		a = writerA;
		b = writerB;
	}

	/**
	 * Write method for the WriterFork. Attempts to write on the two Writers given at the object's construction.
	 * 
	 * @param str The text the user wants to write to the two writers.
	 * @throws IOException If there is a problem writing.
	 */
	public void write(String str) throws IOException{
		a.write(str);
		a.close();
		b.write(str);
		b.close();
	}

	public static void main(String[] args) {
		File file = new File("test.txt");
		BufferedWriter con = new BufferedWriter(new OutputStreamWriter(System.out));

		try {
			file.createNewFile();
			FileWriter txt = new FileWriter(file);
			PrintWriter out = new PrintWriter(txt);
			WriterFork wf = new WriterFork(out, con);
			wf.write("Test of WriterFork class");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
