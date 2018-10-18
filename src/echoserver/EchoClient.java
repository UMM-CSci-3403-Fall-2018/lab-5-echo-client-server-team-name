package echoserver;

import java.net.*;
import java.io.*;


public class EchoClient {
    public static final int portNumber = 6013;

    public static void main(String[] args) throws IOException {
        String server;
        // Use "127.0.0.1", i.e., localhost, if no server is specified.
        if (args.length == 0) {
            server = "127.0.0.1";
        } else {
            server = args[0];
        }

        try {
            // Connect to the server
            Socket socket = new Socket(server, portNumber);

            OutputStream output = socket.getOutputStream();
            InputStream keyboard = System.in;
            InputStream input = socket.getInputStream();
            int keyboardData;

            // Get data from the keyboard, write it to the server,
            // read from server and write it out
            while ((keyboardData = keyboard.read()) != -1) {
                output.write(keyboardData);
                int data = input.read();
                System.out.write(data);
            }


            // Close the socket when we're done reading from it
            socket.close();

            // Flush out the output so the system sends what it has 
            System.out.flush();

            // Provide some minimal error handling.
        } catch (ConnectException ce) {
            System.out.println("We were unable to connect to " + server);
            System.out.println("You should make sure the server is running.");
        } catch (IOException ioe) {
            System.out.println("We caught an unexpected exception");
            System.err.println(ioe);
        }
    }
}