package bkav;

import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import adt.*;

public class ListFile extends Thread {

	private ListFileGUI listFileGui;
	private String path;
	private KStack<File> stack;
	private KQueue<File> queue;
	private KNode<String> list;
	private boolean option;

	public ListFile() {
		path = null;
		stack = null;
		queue = null;
	}

	public KNode<String> listFileUseStack(File entry)
			throws FileNotFoundException, FullStackException,
			EmptyStackException, IOException {
		if (!entry.exists()) {
			throw new FileNotFoundException(
					"Cannot list the path that isn't exist !");
		}

		stack = new KStack<File>();

		File f = new File(entry.getAbsolutePath());
		stack.push(f);
		while (!stack.isEmpty()) {
			f = stack.pop();
			list.add(f.getAbsolutePath());
			//listFileGui.updateList();
			System.out.println(f.getAbsolutePath());
			if (f.isDirectory()) {
				String sarr[] = f.list();
				if (sarr != null)
					for (int i = 0; i < sarr.length; i++) {
						stack.push(new File(f.getAbsolutePath(), sarr[i]));
					}
			}
		}
		return list;
	}

	public KNode<String> listFileUseQueue(File entry)
			throws FileNotFoundException, IOException, EmptyQueueException {
		if (!entry.exists()) {
			throw new FileNotFoundException(
					"Cannot list the path that isn't exist !");
		}
		queue = new KQueue<File>();
		File f = new File(entry.getCanonicalPath());
		queue.enQueue(f);
		while (!queue.isEmpty()) {
			//listFileGui.updateList();
			f = queue.deQueue();
			list.add(f.getAbsolutePath());
			System.out.println(f.getAbsolutePath());
			if (f.isDirectory()) {
				String sarr[] = f.list();
				if (sarr != null)
					for (int i = 0; i < sarr.length; i++) {
						queue.enQueue(new File(f.getAbsolutePath(), sarr[i]));
					}
			}
		}
		return list;
	}

	public void setArguments(ListFileGUI listFileGui, String path,
			boolean option, KNode<String> list) {
		this.path = path;
		this.list = list;
		this.option = option;
		this.listFileGui = listFileGui;
	}

	public <T> void listFile() throws FileNotFoundException,
			FullStackException, EmptyStackException, IOException,
			EmptyQueueException {
		File entry = new File(path);
		if (option == false) {
			JOptionPane.showMessageDialog(listFileGui.getForm(), "Use Queue",
					"Message", JOptionPane.WARNING_MESSAGE);
			listFileUseQueue(entry);
			return;
		} else {
			JOptionPane.showMessageDialog(listFileGui.getForm(), "Use Stack",
					"Message", JOptionPane.WARNING_MESSAGE);
			listFileUseStack(entry);
			return;
		}

	}

	public void run() {
		try {
			listFile();
		} catch (FileNotFoundException | FullStackException
				| EmptyStackException | IOException | EmptyQueueException e) {
			JOptionPane.showMessageDialog(listFileGui.getForm(),
					"Runtime error !" + "\nDetail: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
