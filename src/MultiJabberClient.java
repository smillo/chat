import java.io.*;
import java.net.*;

public class MultiJabberClient {
	

	public static void main(String[] args) throws IOException,
			InterruptedException {
		InetAddress addr = InetAddress.getByName(null);
	
		
				new JabberClientThread(addr,"Client 2", MultiJabberServer.list );
			
			Thread.currentThread().sleep(100);
		
	}}

