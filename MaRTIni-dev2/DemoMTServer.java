// this is a demo of a most rudimentary java based server 
// accepting socket connections and processing
// newline-separated commands from the client

import java.io.*;
import java.net.*;
import java.lang.*;
import MTServerStuff;

public class DemoMTServer {

    public static void main(String[] argv) {
        int port = 2222;
        String usage = "usage: java DemoServer [-p port] (default=" + port +")";

        // process command line arguments
        try {
            for (int optind = 0; optind < argv.length; ++optind) {
                if (argv[optind].equals("-p")) {    // -p port
                    if (++optind >= argv.length) {
                        System.err.println(usage);
                        System.exit(1);
                    }
                    port = (new Integer(argv[optind])).intValue();
                }
                else {
                    System.err.println(usage);
                    System.exit(1);
                }
            }
        }
        catch (NumberFormatException e) {
            System.err.println("bad number format");
            System.exit(1);
        }

        try {
            // the ServerSocket class allows us to create a socket,
            // bind it to a given port number for accepting TCP/IP connections
            // and set the queue length to a given value (we use 5)
            ServerSocket ss = new ServerSocket(port, 5);

            while (true) {
                System.out.println("waiting for a connection on port " + port);
                Socket s = ss.accept();    // wait for a connection
                System.out.println("got a connection from " + s.getInetAddress());

                // We want to read data a line at a time in this program.
                // A socket connection has no record delimiters so we
                // cannot count on a read returning data with the same
                // boundaries as the client writes. The client puts a newline
                // after every command. A BufferedReader allows us to read
                // characters and process newline boundaries. The
                // BufferedReader requires a Reader as input, so we convert
                // the socket InputStream to a Reader with InputStreamReader

                BufferedReader is = new BufferedReader(
                                     new InputStreamReader(s.getInputStream()));

                OutputStream os = s.getOutputStream();    // output to socket goes here
                MTServerStuff p = new MTServerStuff(is, os);    // create a thread
                p.start();    // run it
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
