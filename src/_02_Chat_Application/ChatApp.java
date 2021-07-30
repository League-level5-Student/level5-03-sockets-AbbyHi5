package _02_Chat_Application;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * Using the Click_Chat example, write an application that allows a server computer to chat with a client computer.
 */

public class ChatApp extends JFrame {

	public static String currentChat;
	JButton send = new JButton("SEND");
	JTextField textEnter = new JTextField("Enter Text Here");
	JPanel panel = new JPanel();
	public static JLabel chatLog = new JLabel("Log:");

	Server server;
	Client client;

	public static void main(String[] args) {
		new ChatApp();
	}

	public ChatApp() {

		int response = JOptionPane.showConfirmDialog(null, "Are you hosting?", "Chat App", JOptionPane.YES_NO_OPTION);
		if (response == JOptionPane.YES_OPTION) {
			server = new Server(8080);
			setTitle("SERVER");
			JOptionPane.showMessageDialog(null,
					"Server started at: " + server.getIPAddress() + "\nPort: " + server.getPort());
			send.addActionListener((e) -> {
				currentChat = textEnter.getText();
				server.sendClick();
			});
			panel.add(textEnter);
			textEnter.setPreferredSize(new Dimension(200, 25));
			panel.add(send);
			panel.add(chatLog);
			add(panel);
			setVisible(true);
			setSize(400, 300);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			server.start();

		} else {
			setTitle("CLIENT");
			String ipStr = JOptionPane.showInputDialog("Enter the IP Address");
			String prtStr = JOptionPane.showInputDialog("Enter the port number");
			int port = Integer.parseInt(prtStr);
			client = new Client(ipStr, port);
			send.addActionListener((e) -> {
				client.sendClick();
			});
			panel.add(textEnter);
			textEnter.setPreferredSize(new Dimension(200, 25));
			panel.add(send);
			panel.add(chatLog);
			add(panel);
			setVisible(true);
			setSize(400, 300);
			pack();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			client.start();
		}

	}
	
	public static void addMsgToLog(String msg) {
		chatLog.setText(chatLog.getText() + msg);
	}
}
