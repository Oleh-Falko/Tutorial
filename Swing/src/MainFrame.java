

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import java.awt.Choice;
import javax.swing.JTextField;
import java.awt.TextField;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	private JTabbedPane tabbedPane;
	private JPanel panel, panel_1, panel_2;
	private JTextPane txtpnEnterUrl;
	private JPanel panel_3;
	private JScrollPane scrollPane;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmSave;
	private JMenuItem mntmExit;

	public MainFrame(){
		setTitle("Swing");
		setResizable(false);
		setLocationRelativeTo(null);
		setSize(680,540);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panel = new JPanel();
		panel.setBounds(10, 40, 644, 450);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 5, 624, 434);
		panel.add(tabbedPane);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Tab_1", null, panel_1, null);
		panel_1.setLayout(null);
		
		txtpnEnterUrl = new JTextPane();
		txtpnEnterUrl.setText("Enter URL...");
		txtpnEnterUrl.setBounds(10, 45, 599, 350);
		panel_1.add(txtpnEnterUrl);
		
		JButton btnNewButton = new JButton("Search...");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				URL url = null;
				try {
					url = new URL(txtpnEnterUrl.getText());
				} catch (MalformedURLException e) {
					JOptionPane.showMessageDialog(null, "URL not found or no Internet connection");
					e.printStackTrace();
				}
				Main.setImage(url);
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		panel_1.add(btnNewButton);
		
		JButton btnGetImage = new JButton("Get Image");
		btnGetImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int reply = chooser.showOpenDialog(null);
				if(reply==JFileChooser.APPROVE_OPTION){
					Main.setImage(chooser.getSelectedFile());
				}
			}
		});
		btnGetImage.setBounds(494, 11, 115, 23);
		panel_1.add(btnGetImage);
		
		panel_2 = new JPanel();
		tabbedPane.addTab("Tab_2", null, panel_2, null);
		panel_2.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 33, 599, 362);
		panel_2.add(scrollPane);
		
		panel_3 = new JPanel();
		scrollPane.setViewportView(panel_3);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("");
		panel_3.add(label);
		
		JButton btnView = new JButton("View");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Main.getImage()==null){
					return;
				}
				label.setIcon(new ImageIcon(Main.getImage()));
				label.updateUI();
			}
		});
		btnView.setBounds(10, 11, 89, 23);
		panel_2.add(btnView);
		
		Choice choice = new Choice();
		choice.setBounds(533, 14, 76, 20);
		panel_2.add(choice);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 674, 21);
		
		getContentPane().add(menuBar);
		
		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser();
				int reply = chooser.showSaveDialog(null);
				if(reply==JFileChooser.APPROVE_OPTION){
					Main.saveImage(chooser.getSelectedFile(), choice.getSelectedItem());
				}
			}
		});
		mnNewMenu.add(mntmSave);
		
		mnNewMenu_1 = new JMenu("Option");
		menuBar.add(mnNewMenu_1);
		
		mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnNewMenu_1.add(mntmExit);
		choice.add("png");
		choice.add("jpg");
		setVisible(true);
	}
}
