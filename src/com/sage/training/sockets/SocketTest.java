package com.sage.training.sockets;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketTest {
	public static void main(String[] args) {
		// declaration section:
		// smtpClient: our client socket
		// os: output stream
		// is: input stream
		        Socket MyClient = null;  
		        DataOutputStream os = null;
		        BufferedReader is = null;
		        Scanner s = new Scanner(System.in);
		        boolean aSession = false;
		// Initialization section:
		// Try to open a socket on port 25
		// Try to open input and output streams
		        System.out.println("Enter Address");
		        String ip = s.nextLine();
		        System.out.println("Enter Port");
		        int port = Integer.parseInt(s.nextLine());
		        try {
		            MyClient = new Socket(ip, port);
		            os = new DataOutputStream(MyClient.getOutputStream());
		            is = new BufferedReader(new InputStreamReader(MyClient.getInputStream()));
		        } catch (UnknownHostException e) {
		            System.err.println("Don't know about host: " + ip);
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to: " + ip);
		        }
		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port 25
		    if (MyClient != null && os != null && is != null) {
		    	System.out.println("Connection to " + ip + ":" + port + " established!\n");
		    	aSession = true;
		    	System.out.println("Send Message: ");
		    	String message = s.nextLine();
		            try {
		            	os.writeBytes(message+"\n");
		                String responseLine;
		                while (aSession == true) {
		                	if ((responseLine = is.readLine()) != null) {
			                    System.out.println("Server: " + responseLine);
			                    if (responseLine.indexOf("Hello Back!") != -1) {
			                      break;
			                    }
		                	}
		                }
		// clean up:
		// close the output stream
		// close the input stream
		// close the socket
		                os.close();
		                is.close();
		                MyClient.close();   
		            } catch (UnknownHostException e) {
		                System.err.println("Trying to connect to unknown host: " + e);
		            } catch (IOException e) {
		                System.err.println("IOException:  " + e);
		            }
		        }
		    } 
}
