import java.net.*;
import java.io.*;
import java.util.BitSet;

 
public class server {
	
    public static void main(String[] args) throws IOException {
    	 	
    if (args.length != 1) {
        System.err.println("Usage: java KKMultiServer <port number>");
        System.exit(1);
    }
 
        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) { 
            while (listening) {
            	long start = System.currentTimeMillis();    	
 	      		long end =System.currentTimeMillis();
 	      		long counterSec =0;
 	      		int requestPerSecond = 0; 
 	      		
 	      		Socket socket =serverSocket.accept();
 	      		ServerThread thread = new ServerThread(socket);
                    
 	      		thread.start();
 	      		requestPerSecond++;
 	      		end = System.currentTimeMillis();
	 	
	 	       	counterSec = (end - start)/1000;
	 	       	
	 	       	if(counterSec >= 1){
	 	       		System.out.println("Server Throughput:"+counterSec);
	 	       		requestPerSecond=0;
	 	       		start = System.currentTimeMillis();
	 	       		}
            	}
        	} catch (IOException e) {
        		System.err.println("Could not listen on port " + portNumber);
        		System.exit(-1);
        	}
    }
    
    
    
    public static class ServerThread extends Thread {
        private Socket socket = null;
        public ServerThread(Socket socket) {
             this.socket = socket;
        }
        String address;
        String port;
        public void run() {
        	while(true){		
	            try (
	                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            ) {

	                String line = "";
	                while((line =in.readLine())!=null){
	                	System.out.println(line);
	                	String toks[] = line.split(" ");
	                	 int message_size=(int)(Math.random()*2000)+300;
	 	                BitSet payload = new BitSet(message_size); 
	 	                out.println("WELCOME "+payload.size()+" id "+toks[3]);
	 					out.flush();

	                }
	                socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        }
    }

}
