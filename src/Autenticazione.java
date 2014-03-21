import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;
import java.util.*;



public class Autenticazione {

	static Broadcast<String> broad = new Broadcast<String>();
	static HashMap<String,Socket> lista = new HashMap<String,Socket>();

	public synchronized void  inserisci(String nome,Socket socket){
		lista.put(nome, socket);
		
	}
	public synchronized void togli(String utente){
		lista.remove(utente);
	}
	
	 public synchronized static boolean occupato(String user){
			return lista.containsKey(user);
		}
	 
	 public synchronized void manda(String mex){
			broad.send(mex);
		}
	 
	 public synchronized void scrivi_mex(String s, Socket sock) throws IOException{
			//JabberClientThread c;
			PrintWriter w;
			for(Map.Entry<String, Socket> a : lista.entrySet()){
				if(!a.getValue().equals(sock)){ 
					w =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(a.getValue().getOutputStream())), true);
					w.println(s);
				}
			}
		}}
