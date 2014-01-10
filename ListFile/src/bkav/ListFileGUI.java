package bkav;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultListModel;
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

public class ListFileGUI {

	// TODO: Attributes
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
		initialize();
		list = null;
		listModel = new DefaultListModel<String>();
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
		lblPath.setBounds(10, 11, 46, 54);
		frmListFileGui.getContentPane().add(lblPath);

		textPath = new JTextField();
		textPath.setToolTipText("Enter path to list");
		textPath.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textPath.setBounds(66, 9, 200, 56);
		frmListFileGui.getContentPane().add(textPath);
		textPath.setColumns(10);

		listModel = new DefaultListModel<String>();

		listModel.addElement("");

		btnListUseStack = new JButton("List using Stack");
		btnListUseStack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO: Xử lí sự kiện click chuột vào nút "List using Stack"
				try {
					if (textPath.getText().equalsIgnoreCase(new String())) {
						JOptionPane.showMessageDialog(frmListFileGui,
								"Path cannot be null", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						listModel.removeAllElements();
						File f = new File(textPath.getText());
						JOptionPane.showMessageDialog(frmListFileGui,
								f.getAbsolutePath(), "Path",
								JOptionPane.INFORMATION_MESSAGE);

						list = ListFile.listFileUseStack(f);

						for (KNode<String> it = list; !it.isLast(); it = it
								.getNext()) {
							listModel.addElement(it.getNext().getData());

							listOfFile.setModel(listModel);
						}

					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(frmListFileGui,
							"Need run Java Applicaton as Administrator !",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnListUseStack.setToolTipText("Click to list");
		btnListUseStack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListUseStack.setBounds(276, 8, 178, 23);
		frmListFileGui.getContentPane().add(btnListUseStack);

		btnListUseQueue = new JButton("List using Queue");
		btnListUseQueue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO: Xử lí sự kiện click chuột vào nút "List using Queue"
				try {
					if (textPath.getText().equalsIgnoreCase(new String())) {
						JOptionPane.showMessageDialog(frmListFileGui,
								"Path cannot be null", "Error",
								JOptionPane.ERROR_MESSAGE);
					} else {
						listModel.removeAllElements();
						File f = new File(textPath.getText());
						JOptionPane.showMessageDialog(frmListFileGui,
								f.getAbsolutePath(), "Path",
								JOptionPane.INFORMATION_MESSAGE);

						list = ListFile.listFileUseQueue(f);

						for (KNode<String> it = list; !it.isLast(); it = it
								.getNext()) {
							listModel.addElement(it.getNext().getData());

							listOfFile.setModel(listModel);
						}

					}
				} catch (NullPointerException e) {
					JOptionPane.showMessageDialog(frmListFileGui,
							"Need run Java Applicaton as Administrator !",
							"Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnListUseQueue.setToolTipText("Click to list");
		btnListUseQueue.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnListUseQueue.setBounds(276, 42, 178, 23);
		frmListFileGui.getContentPane().add(btnListUseQueue);

		panel = new JPanel();
		panel.setBounds(10, 76, 444, 194);
		frmListFileGui.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		scrollPane = new JScrollPane();
		panel.add(scrollPane, "name_79550726745585");

		listOfFile = new JList<String>(listModel);
		scrollPane.setViewportView(listOfFile);

	}

}
