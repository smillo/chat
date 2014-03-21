import java.io.BufferedReader;
import java.io.IOException;


public class Ricevitore extends Thread{
	
	private BufferedReader buffer;

	public Ricevitore(BufferedReader buffer) throws IOException{
		
		this.buffer = buffer;
	}
	
	public void run(){
		
		
		while(true){
			try {
				String str = buffer.readLine();
				System.out.println(str);
			} catch (IOException e) {
				
			}
		}
	}
}
