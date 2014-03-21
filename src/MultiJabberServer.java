import java.io.*;
import java.net.*;
import java.util.LinkedList;

public class MultiJabberServer {
	static final int PORT = 8080;
	 static Autenticazione list = new Autenticazione();
	

	public static void main(String[] args) throws IOException {
		ServerSocket s = new ServerSocket(PORT);
		System.out.println("Server Started");
		try {
			while (true) {
				
				Socket socket = s.accept();
				
				try {
					new ServeOneJabber(socket,list);
				} catch (IOException e) {
					
					socket.close();
				}
			}
		} finally {
			s.close();
		}
	}
}
