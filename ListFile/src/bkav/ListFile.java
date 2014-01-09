package bkav;

import java.io.File;
import java.io.IOException;
import adt.*;

public class ListFile {

	public static long listFile(File entry) {
		long numberOfFiles = 0;
		try {
			if (!entry.exists()) {
				System.out.println(entry.getName() + " not found!");
			}
			if (entry.isFile()) {
				System.out.println(entry.getCanonicalPath());
			} else if (entry.isDirectory()) {

			}
		} catch (IOException e) {
			System.err.println("Error: " + e);
		}
		return numberOfFiles;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
}

