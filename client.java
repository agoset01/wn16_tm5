package ask1_425;

import java.io.*;
import java.net.*;

public class client {
	public static void main(String[] args) throws IOException {
         
        if (args.length != 2) {
            System.err.println(
                "Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }
 
        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);
        
        for(int i = 0; i< 5;i++){
        	User user = new User(new Socket(hostName,portNumber), i);
        	new Thread(user).start();
        }
        
       
      
    }

	static class User implements Runnable {
		public User(){
			
		}
		Socket socket;
		int id;

		public void setId(int id) {
			this.id = id;
		}

		public User(Socket socket, int id) {
			this.socket = socket;
			this.id = id;
		}

		@Override
		public void run() {

			try {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
				String fromServer;
				String fromUser;
				
				long start=System.currentTimeMillis();
				//System.out.println("start"+start);
				out.println("HELLO "+socket.getLocalAddress().getHostAddress()+" "+socket.getLocalPort()+" "+id);
				
				 
				out.flush();
				long end=System.currentTimeMillis();
				 //System.out.println("end"+end);
	            System.out.println("Communication Latency:"+((end-start)));
				
				 String inputLine;
	                String line = "";
	                while((line =in.readLine())!=null){
	                	System.out.println(line);
	                }
	                
	               
	               
	                
			} catch (UnknownHostException e) {
				System.err.println("Don't know about host " + socket.getInetAddress().getHostName());
				System.exit(1);
			} catch (IOException e) {
				System.err.println("Couldn't get I/O for the connection to " + socket.getInetAddress().getHostName());
				System.exit(1);
			}
		}

	}
}
