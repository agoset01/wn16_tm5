
import java.io.BufferedReader;
import java.io.BufferedWriter;
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
//import java.io.ObjectOutput;
//import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.BitSet;
//import java.net.InetAddress;
import java.util.Random;
 
public class server{
 
    private static Socket socket;
    /*
    private static byte[] convertToBytes(Object object) throws IOException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutput out = new ObjectOutputStream(bos)) {
            out.writeObject(object);
            return bos.toByteArray();
        } 
    }
    */
    public static int strToInt( String str ){
        int i = 0;
        int num = 0;
        boolean isNeg = false;

        if (str.charAt(0) == '-') {
            isNeg = true;
            i = 1;
        }

        while( i < str.length()) {
            num *= 10;
            num += str.charAt(i++) - '0'; 
        }

        if (isNeg)
            num = -num;
        return num;
    }
 
    public static void main(String[] args){
    	
    	
    	String a=args[1];
    	
        try{
        	//Random rand = new Random();
        	//int random_integer;
        	int port=strToInt(a);
           
        
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server Started and waits for any number of clients to connect to it");
            
            //O server trexi sinexia logo tu while(true)
            while(true) {
            	//Diabazi to minkma apo to client
                socket = serverSocket.accept();
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                String number = br.readLine();
                
//            	take the millisecond before send
        		long start = System.currentTimeMillis();
        		long end = System.currentTimeMillis();

        		long counterSec =0;

        		int requestPerSecond = 0;

                
                
                
                //to minima paralambanete apo to client
                System.out.println("HELLO ");
                System.out.println("IP address : "+socket.getInetAddress().getHostName());
                System.out.println("Port : "+port);
                System.out.println("User Id : "+number);
                System.out.println();
                
                String returnMessage;
                try{
                    int numberInIntFormat = Integer.parseInt(number);
                    returnMessage = String.valueOf(numberInIntFormat) + "\n";
                }
               catch(NumberFormatException e){
                    //Input was not a number. Sending proper message back to client.
                    returnMessage = "Please send a proper number\n";
                }
 
                //Stelni tin apoantisi piso sto client.
                OutputStream os = socket.getOutputStream();
                OutputStreamWriter osw = new OutputStreamWriter(os);
                BufferedWriter bw = new BufferedWriter(osw);
                bw.write(returnMessage);
                //random_integer = rand.nextInt(2000-300) + 300;
              // byte [] ra= convertToBytes(random_integer);
              //String s = ra+"";
              
              int message_size = (int)(Math.random() * 1700000) + 300000;
				BitSet payload =new BitSet(message_size);

                
                //to minima stelnet sto client
                System.out.println("WELCOME");
                System.out.println("User id : "+returnMessage);
                System.out.println("Payload 1: "+message_size);
                System.out.println();
                
                
//            	request per second to count
    			requestPerSecond++;

    			end = System.currentTimeMillis();

    			//take one sec
    			counterSec = (end - start)/1000;

    			//	one sec pass
    			if(counterSec >= 1){
    				System.out.println("Server Throughput:"+requestPerSecond);

    				//	reset the counter and start time for throughput.
    				requestPerSecond=0;
    				start = System.currentTimeMillis();

    			}

                
                
                bw.flush();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally{
            try {
                socket.close();
            }
            catch(Exception e){}
        }
    
    }
}