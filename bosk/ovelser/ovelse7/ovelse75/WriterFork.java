package bosk.ovelser.ovelse7.ovelse75;

import java.io.BufferedWriter;
import java.io.Console;
import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class WriterFork {
	Writer a;
	Writer b;

	public WriterFork(Writer writerA, Writer writerB){
		a = writerA;
		b = writerB;
	}

	public void write(String str) throws IOException{
		a.write(str);
		a.close();
		b.write(str);
		b.close();
	}

	public static void main(String[] args) {
		File file = new File("test.txt");
		BufferedWriter con = new BufferedWriter(new OutputStreamWriter(System.out));
 
		if(con != null){
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

}
