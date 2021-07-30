package _02_Chat_Application;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class Client {
	private String ip;
	private int port;

	Socket connection;

	ObjectOutputStream os;
	ObjectInputStream is;

	public Client(String ip, int port) {
		this.ip = ip;
		this.port = port;
	}

	public void start(){
		try {

			connection = new Socket(ip, port);

			os = new ObjectOutputStream(connection.getOutputStream());
			is = new ObjectInputStream(connection.getInputStream());

			os.flush();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (connection.isConnected()) {
			try {
				ChatApp.addMsgToLog(is.readUTF());
				//System.out.println(is.readUTF());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void sendClick() {
		System.out.println("test");
		try {
			if (os != null) {
				//ChatApp.addMsgToLog(is.readUTF());
				os.writeUTF(ChatApp.currentChat);
				ChatApp.addMsgToLog(ChatApp.currentChat);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
