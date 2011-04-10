package bosk.ovelse07.ovelse74;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class FileInfo {

	private File file;
	private FilenameFilter fnf;

	/**
	 * Constructor for class FileInfo
	 * 
	 * @param f The File object info is wanted from.
	 */
	public FileInfo(File f) {
		file = f;
	}

	/**
	 * Constructor for class FileInfo with a filter included
	 * 
	 * @param f The File object info is wanted from.
	 * @param s The String the FileInfo should look for in its directory.
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
	 * @return
	 */
	private ArrayList<File> listFiles(){
		ArrayList<File> temp = new ArrayList<File>();
		if(fnf != null){
			for(File f : file.listFiles(fnf)){
				if(!f.isDirectory()){
					temp.add(f);
				}
			}
		}
		else{
			for(File f : file.listFiles()){
				if(!f.isDirectory()){
					temp.add(f);
				}
			}
		}
		return temp;
	}

	/**
	 * If the FileInfo object was not constructed with a filter, it will check all files.
	 *  
	 * @param path A File object containing the path of the directory to be checked.
	 * @return A String with info about the number of files in the path.
	 */
	private String numberOfFilesInPath(File path){
		return path + " contains " + listFiles().size() + " files";
	}

	/**
	 * If the FileInfo object was not constructed with a filter, it will check all files.
	 * 
	 * @param path A File object containing the path of the directory to be checked.
	 * @return A String with info about the size of the files in the path.
	 */
	private String sizeOfFilesInPath(File path){
		int size = 0;
		for(File b : listFiles()){
			size += b.length();
		}
		return " with a total size of " + size + " bytes.";
	}

	/*'
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
