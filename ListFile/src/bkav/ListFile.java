package bkav;

import java.io.File;
import java.io.IOException;
import adt.*;

public class ListFile {

	public static KNode<String> listFile(File entry) {
		KNode<String> list = new KNode<String>();
		try {
			if (!entry.exists()) {
				System.out.println(entry.getName() + " not found!");
				return null;
			}
			KStack<File> s = new KStack<File>();
			File f = new File(entry.getCanonicalPath());
			s.push(f);
			while (!s.isEmpty()) {
				f = s.pop();
				list.add(f.getAbsolutePath());
				System.out.println(f.getAbsolutePath());
				if (f.isDirectory()) {
					String sarr[] = f.list();
					for (int i = 0; i < sarr.length; i++) {
						s.push(new File(f.getAbsolutePath(), sarr[i]));
						//System.out.println(s.top());
					}
				}
			}
		} catch (IOException e) {
			System.err.println("Error: " + e);
		} catch (FullStackException e) {
			System.err.println("Error: " + e);
		} catch (EmptyStackException e) {
			System.err.println("Error: " + e);
		}
		return list;
	}
}
