package ask1_425;
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
                  Socket socket =serverSocket.accept();
                  ServerThread thread = new ServerThread(socket);
                  thread.start();
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
        		long start = System.currentTimeMillis();
        		long end = System.currentTimeMillis();

        		long counterSec =0;

        		int requestPerSecond = 0;
	            try (
	                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	                BufferedReader in = new BufferedReader(
	                    new InputStreamReader(
	                        socket.getInputStream()));
	            ) {
	                String inputLine, outputLine;
	                String line = "";
	                while((line =in.readLine())!=null){
	                	System.out.println(line);
	                	String toks[] = line.split(" ");
	                	String ip=toks[3];
	                	 int message_size=(int)(Math.random()*2000)+300;
	 	                BitSet payload = new BitSet(message_size);
	 	                
	 	                out.println("WELCOME "+payload.size()+" id "+toks[3]);
//	 					request per second to count
	 					requestPerSecond++;

	 					end = System.currentTimeMillis();

	 					//take one sec
	 					counterSec = (end - start)/1000;

	 					//	one sec pass
	 					if(counterSec >= 1){
	 						System.out.println(" counterPerSec:"+requestPerSecond);

	 						//	reset the counter and start time for throughput.
	 						requestPerSecond=0;
	 						start = System.currentTimeMillis();

	 					}
	 					out.flush();

	                }
	               
	                /*
					requestPerSecond++;
					counterSec=(end-start)/1000;
					if(counterSec>=1){
						
					}*/
					
					
	                socket.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
        }
    }

}
