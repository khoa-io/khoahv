package bkav;

import java.io.File;
import java.io.IOException;
import adt.*;

public class ListFile {

	public static KNode<String> listFileUseStack(File entry)
			throws FileNotFoundException, FullStackException, EmptyStackException, IOException {
		KNode<String> list = new KNode<String>();
			if (!entry.exists()) {
				throw new FileNotFoundException(
						"Cannot list the path that isn't exist !");
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
						// System.out.println(s.top());
					}
				}
			}
		return list;
	}

	public static KNode<String> listFileUseQueue(File entry)
			throws FileNotFoundException, IOException, EmptyQueueException {
		KNode<String> list = new KNode<String>();
			if (!entry.exists()) {
				throw new FileNotFoundException(
						"Cannot list the path that isn't exist !");
			}
			KQueue<File> q = new KQueue<File>();
			File f = new File(entry.getCanonicalPath());
			q.enQueue(f);
			while (!q.isEmpty()) {
				f = q.deQueue();
				list.add(f.getAbsolutePath());
				System.out.println(f.getAbsolutePath());
				if (f.isDirectory()) {
					String sarr[] = f.list();
					for (int i = 0; i < sarr.length; i++) {
						q.enQueue(new File(f.getAbsolutePath(), sarr[i]));
						// System.out.println(s.top());
					}
				}
			}
		return list;
	}
}
