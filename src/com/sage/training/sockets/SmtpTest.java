package com.sage.training.sockets;

import java.io.*;
import java.net.*;

public class SmtpTest {
	public static void main(String[] args) {
		// declaration section:
		// smtpClient: our client socket
		// os: output stream
		// is: input stream
		        Socket smtpSocket = null;  
		        DataOutputStream os = null;
		        BufferedReader is = null;
		// Initialization section:
		// Try to open a socket on port 25
		// Try to open input and output streams
		        try {
		            smtpSocket = new Socket("localhost", 37403);
		            os = new DataOutputStream(smtpSocket.getOutputStream());
		            is = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
		        } catch (UnknownHostException e) {
		            System.err.println("Don't know about host: localhost");
		        } catch (IOException e) {
		            System.err.println("Couldn't get I/O for the connection to: localhost");
		        }
		// If everything has been initialized then we want to write some data
		// to the socket we have opened a connection to on port 25
		    if (smtpSocket != null && os != null && is != null) {
		            try {
		// The capital string before each colon has a special meaning to SMTP
		// you may want to read the SMTP specification, RFC1822/3
		        os.writeBytes("HELO\n");    
		                os.writeBytes("MAIL From: greg@doesthiswork.com\n");
		                os.writeBytes("RCPT To: greg@doesthiswork.com\n");
		                os.writeBytes("DATA\n");
		                os.writeBytes("From: greg@doesthiswork.com\n");
		                os.writeBytes("Subject: testing\n");
		                os.writeBytes("Hi there\n"); // message body
		                os.writeBytes("\n.\n");
		        os.writeBytes("QUIT");
		// keep on reading from/to the socket till we receive the "Ok" from SMTP,
		// once we received that then we want to break.
		                String responseLine;
		                while ((responseLine = is.readLine()) != null) {
		                    System.out.println("Server: " + responseLine);
		                    if (responseLine.indexOf("Ok") != -1) {
		                      break;
		                    }
		                }
		// clean up:
		// close the output stream
		// close the input stream
		// close the socket
		        os.close();
		                is.close();
		                smtpSocket.close();   
		            } catch (UnknownHostException e) {
		                System.err.println("Trying to connect to unknown host: " + e);
		            } catch (IOException e) {
		                System.err.println("IOException:  " + e);
		            }
		        }
		    } 
}
