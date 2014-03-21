import java.io.*;
import java.net.*;

class JabberClientThread extends Thread {
	private BufferedReader b;
	static  String str;
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Autenticazione lista;
	Ricevitore ricev;
	

	
	public JabberClientThread(InetAddress addr,String nome,Autenticazione lista) {
		str=nome;
		this.lista = lista;
		System.out.println("Making client " + nome);
		
		b = new BufferedReader(new InputStreamReader(System.in));
		try {
			socket = new Socket(addr, MultiJabberServer.PORT);
		} catch (IOException e) {
			System.err.println("Socket failed ");
		}
		try {
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			start();
		} catch (IOException e) {
			try {
				socket.close();
			} catch (IOException e2) {
				System.err.println("Socket not closed");
			}
		}
	}

	public void run() {

		try {
			System.out.println("nome: ");
			String nome = b.readLine();
			out.println(nome);
			String a= in.readLine();
			
			if( a.equals("ok") && a!=null ){
				System.out.println(a);
				ricev = new Ricevitore(in);
				ricev.start();
				while(true){
				String messaggio=b.readLine();
				if(messaggio.equals(""))
					continue;
			if(messaggio.equals("END")|| messaggio.equals("end")){
				System.out.println("chiusura...arrivederci!");
				ricev = new Ricevitore(in);
				ricev.start();
				out.println(messaggio);
				break;}
			out.println(messaggio);
			}}
				else{
					System.out.println("utente non autorizzato");
				
				}
			
		} catch (IOException e) {
			System.err.println("IO Exception");
		} finally { 
			try {
				socket.close();
			} catch (IOException e) {
				System.err.println("Socket not closed");
			}
			
		}
	}
}
