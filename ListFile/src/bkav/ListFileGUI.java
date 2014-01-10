package bkav;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import adt.KNode;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.CardLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class ListFileGUI extends Thread {

	// TODO: Attributes
	private ListFileGUI listFileGui;
	private JFrame frmListFileGui;
	private JTextField textPath;
	private KNode<String> list;
	private DefaultListModel<String> listModel;
	private JLabel lblPath;
	private JButton btnListUseStack;
	private JButton btnListUseQueue;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JList<String> listOfFile;
	private JButton btnStop;
	private ListFile listFileThread;
	private JButton btnUpdateList;
	private JButton btnGetPath;
	private String path;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ListFileGUI window = new ListFileGUI();
					window.frmListFileGui.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ListFileGUI() {
		// TODO:
		listModel = new DefaultListModel<String>();
		initialize();
		list = new KNode<String>();
		listFileThread = new ListFile();
		listFileGui = this;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmListFileGui = new JFrame();
		frmListFileGui.setResizable(false);
		frmListFileGui.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent arg0) {

			}
		});
		frmListFileGui.setTitle("List File GUI");
		frmListFileGui.setBounds(100, 100, 480, 320);
		frmListFileGui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmListFileGui.getContentPane().setLayout(null);

		lblPath = new JLabel("Path");
		lblPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPath.setBounds(10, 11, 46, 23);
		frmListFileGui.getContentPane().add(lblPath);

		textPath = new JTextField();
		textPath.setToolTipText("Enter path to list");
		textPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPath.setBounds(66, 9, 163, 23);
		frmListFileGui.getContentPane().add(textPath);
		textPath.setColumns(10);

		//listModel = new DefaultListModel<String>();

		//listModel.addElement("");

		btnListUseStack = new JButton("List using Stack");
		btnListUseStack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO: Xử lí sự kiện click chuột vào nút "List using Stack"
				btnStop.setEnabled(true);
				btnListUseQueue.setEnabled(false);
				btnUpdateList.setEnabled(true);
				btnListUseStack.setEnabled(false);
				try {
					path = textPath.getText();
					if (path.equalsIgnoreCase(new String())) {
						JOptionPane.showMessageDialog(frmListFileGui,
								"Path cannot be null", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						listModel.removeAllElements();
						File f = new File(path);
						JOptionPane.showMessageDialog(frmListFileGui,
								f.getAbsolutePath(), "Path",
								JOptionPane.INFORMATION_MESSAGE);
						listFileThread = new ListFile();
						listFileThread.setArguments(listFileGui,
								path, true, list);
						listFileThread.start();
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(frmListFileGui,
							"Need run Java Applicaton as Administrator !\n"
									+ "Detail: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnListUseStack.setToolTipText("Click to list");
		btnListUseStack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListUseStack.setBounds(294, 8, 160, 23);
		frmListFileGui.getContentPane().add(btnListUseStack);

		btnListUseQueue = new JButton("List using Queue");
		btnListUseQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO: Xử lí sự kiện click chuột vào nút "List using Queue"

				btnStop.setEnabled(true);
				btnListUseQueue.setEnabled(false);
				btnUpdateList.setEnabled(true);
				btnListUseStack.setEnabled(false);
				try {
					path = textPath.getText();
					if (path.equalsIgnoreCase(new String())) {
						JOptionPane.showMessageDialog(frmListFileGui,
								"Path cannot be null", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						listModel.removeAllElements();
						File f = new File(path);
						JOptionPane.showMessageDialog(frmListFileGui,
								f.getAbsolutePath(), "Path",
								JOptionPane.INFORMATION_MESSAGE);
						listFileThread = new ListFile();
						listFileThread.setArguments(listFileGui,
								path, false, list);
						listFileThread.start();
					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(frmListFileGui,
							"Need run Java Applicaton as Administrator !\n"
									+ "Detail: " + e.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnListUseQueue.setToolTipText("Click to list");
		btnListUseQueue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListUseQueue.setBounds(294, 42, 160, 23);
		frmListFileGui.getContentPane().add(btnListUseQueue);

		panel = new JPanel();
		panel.setBounds(10, 76, 444, 194);
		frmListFileGui.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, "name_79550726745585");

		listOfFile = new JList<String>(listModel);
		scrollPane.setViewportView(listOfFile);

		btnStop = new JButton("Stop");
		btnStop.setEnabled(false);
		btnStop.addMouseListener(new MouseAdapter() {
			// TODO: Đoạn xử lí sự kiện click vào nút "Stop"
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listFileThread.stop();
				btnStop.setEnabled(false);
				btnListUseQueue.setEnabled(true);
				btnListUseStack.setEnabled(true);
				btnUpdateList.setEnabled(false);
				updateList();
			}
		});
		btnStop.setToolTipText("Click to stop");
		btnStop.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnStop.setBounds(66, 43, 80, 23);
		frmListFileGui.getContentPane().add(btnStop);

		btnUpdateList = new JButton("Update List");
		btnUpdateList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateList();
			}
		});
		btnUpdateList.setEnabled(false);
		btnUpdateList.setBounds(156, 43, 128, 23);
		frmListFileGui.getContentPane().add(btnUpdateList);
		
		btnGetPath = new JButton("...");
		btnGetPath.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser chooser = new JFileChooser();
				chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = chooser.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					path = chooser.getSelectedFile().getAbsolutePath();
					textPath.setText(path);
			    }
			}
		});
		btnGetPath.setBounds(239, 9, 45, 23);
		frmListFileGui.getContentPane().add(btnGetPath);

	}

	public void updateList() {
		listModel.removeAllElements();
		for (KNode<String> it = list; !it.isLast(); it = it.getNext()) {
			listModel.addElement(it.getNext().getData());
			listOfFile.setModel(listModel);
		}
	}
	
	public JFrame getForm() {
		return frmListFileGui;
	}
	
}
