package bosk.ovelser.ovelse7.ovelse74;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class FileInfo {

	private File file;
	private FilenameFilter fnf;

	/**
	 * Constructor for class FileInfo
	 * @param f The File object info is wanted from.
	 */
	public FileInfo(File f) {
		file = f;
	}

	/**
	 * Constructor for class FileInfo with a filter included
	 * @param f The File object info is wanted from.
	 * @param s
	 */
	public FileInfo(File f, final String filter) {
		file = f;
		fnf = new FilenameFilter() {
			private Pattern pattern = Pattern.compile(filter);
			public boolean accept(File dir, String name) {
				return pattern.matcher(name).find();
			}
		};
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private String numberOfFilesInPath(File path){
		if(fnf == null){
			return path + " contains " + path.listFiles().length + " files";
		}
		else{
			return path + " contains " + path.listFiles(fnf).length + " files";
		}
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	private String sizeOfFilesInPath(File path){
		int size = 0;
		if(fnf == null){
			for(File b : path.listFiles()){
				size += b.length();
			}
		}
		else{
			for(File b : path.listFiles(fnf)){
				size += b.length();
			}
		}
		return " with a total size of " + size + " bytes.";
	}

	@Override
	public String toString(){
		return numberOfFilesInPath(file) + sizeOfFilesInPath(file);

	}

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args){
		FileInfo fi;
		if(args.length == 0){
			System.out.println("Need path as input");
			System.exit(1);
		}
		if(args.length >= 3){
			if(args[1].equalsIgnoreCase("-type")){
				fi = new FileInfo(new File(args[0]), args[2]);
			}
			else{
				fi = new FileInfo(new File(args[0]));
			}
		}
		else{
			fi = new FileInfo(new File(args[0]));
		}
		System.out.println(fi);
	}
}
