package bkav;

import java.io.File;
import java.io.IOException;
import adt.KNode;

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
		KNode<String> listOfText = new KNode<String>();
		
		for (long i = 1; i <= 5; i++) {
			listOfText.add("This is " + i);
		}
		listOfText.addAfter(4, "Xâu này phải ở vị trí số 5");

		listOfText.setData(4, "Hoàng Văn Khoa");
		System.out.println("Danh sách: ");
		for (KNode<?> it = listOfText; !it.isLast(); it = it.getNext()) {
			System.out.println("[" + it.getNext().getIndex() + ":"
					+ it.getNext().getData() + "]");
		}
		System.out.println("Size before take = " + listOfText.size());

		System.out.println("Thử nghiệm: ");
		for (; listOfText.get(1) != null; ) {
			long index = listOfText.get(1).getIndex();
			String str = listOfText.takeLast();
			System.out.println("[" + index + ":"
					+ str + "]");
		}

		System.out.println("Size after take = " + listOfText.size());
		System.out.println();

	}

}
