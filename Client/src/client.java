
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
import java.net.InetAddress;
import java.net.Socket;
import java.util.BitSet;
import java.util.Random;
 
public class client{
 
	
    private static Socket socket;
 
    /*private static byte[] convertToBytes(Object object) throws IOException {
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
    
    public static void main(String args[]){
    	
    	String b=args[1];
    	String a=args[2];
    	
    	
        try{
        	Random rand = new Random();
        	//int random_integer;
            String host = "localhost";
            int port=strToInt(a);
            
            InetAddress address = InetAddress.getByName(host);
            socket = new Socket(address, port);
       
            
            //Stelni to minima sto server
            OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
            int n;
            n=rand.nextInt(300-1)+1;
           
            
            String number =Integer.toString(n);
            String sendMessage = number + "\n";
            bw.write(sendMessage);
            bw.flush();
            
         
            long start = System.currentTimeMillis();
            System.out.println("start"+start);
            //To minima stelenete sto server
            System.out.println("HELLO");
            System.out.println("IP address : "+b);
            System.out.println("Port : "+port);
            System.out.println("User Id : "+sendMessage);
            System.out.println();
            
            
            
            //Perni tin apantisi apo to server
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String message = br.readLine();
            //random_integer = rand.nextInt(2000-300) + 300;
            //byte [] ra= convertToBytes(random_integer);
            //String s = ra+"";
            
            int message_size = (int)(Math.random() * 1700000) + 300000;
			BitSet payload =new BitSet(message_size);

			
            
            //to minima paralifthike apo to server
            System.out.println("WELCOME");
            System.out.println("User Id : "+message);
            System.out.println("Payload 2: "+payload);
            System.out.println();
         // reveive the msg from server
    		long end = System.currentTimeMillis();
    		System.out.println("end"+end);
    		System.out.println("Communication Latency : " + ((end - start)));
            
           
    		

            
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
        finally{
            //Klinume to socket
            try{
                socket.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
       
        
        
    }
}