import java.io.*;
import java.net.*;
import java.util.LinkedList;

class ServeOneJabber extends Thread {
	private Socket socket;
	private BufferedReader in;
	private PrintWriter out;
	private Autenticazione l;
	
	public ServeOneJabber(Socket s,Autenticazione l) throws IOException {
		socket = s;
		this.l=l;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				socket.getOutputStream())), true);
		start();
	}

	public void run() {
		try {
			while (true) {
				String dato;
				String utente;
				String str = in.readLine();
				if (Autenticazione.occupato(str)==false){
					utente= str;
					System.out.println("utente " + utente +  " connesso ");
					
					l.inserisci(utente,socket);
					out.println("ok");
					while(true){
						dato = in.readLine();
						if (dato.equals("END")||dato.equals("end")){
							
							out.println(dato);
							System.out.println(utente + " si è disconnesso.");
							l.togli(utente);
							break;
						}
						l.scrivi_mex(utente + " " + dato, socket);
						System.out.println(utente + " " + dato);
					}
				
				}
				
			else{
				System.out.println("username già utilizzato..impossibile accedere");
				socket.close();
			}
				
				break;
				
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
