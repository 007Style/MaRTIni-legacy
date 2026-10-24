// this is a demo of a most rudimentary java based server 
// accepting socket connections and processing
// newline-separated commands from the client

import java.io.*;
import java.net.*;
import java.lang.*;

public class DemoServer {

    static char eofchars[] = { (char)4, '\n' };
    static String eof = new String(eofchars);

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
                    port = Integer.parseInt(argv[optind]);
                }
                else {
                    System.err.println(usage);
                    System.exit(1);
                }
            }
        } catch (NumberFormatException e) {
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
                BufferedReader in = new BufferedReader(
                                     new InputStreamReader(s.getInputStream()));

                OutputStream os = s.getOutputStream();

                doStuff(in, os);    // do the work for this client
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    // process the data on the given socket
    // here we don't care about sockets. We just have to read from an input
    // stream and write to an output stream.

    protected static void doStuff(BufferedReader ir, OutputStream os) {
        try {
            byte[] outbytes;

            while (true) {
                // get a line of input from the client
                String cmd = ir.readLine();

                if (cmd == null)
                    return;    // nothing more to read on this socket

                System.out.println("got command \"" + cmd + "\"");

                // now process the command and write the result back to the
                // client.

                if (cmd.equals("id")) {
                    outbytes = ("I'm the server\nMy name is Harry\n" + eof).getBytes();
                    os.write(outbytes, 0, outbytes.length);
                }
                else if (cmd.equals("sleep")) {
                    try {
                        java.lang.Thread.sleep(5000);
                        outbytes = ("I'm awake now\n" + eof).getBytes();
                        os.write(outbytes, 0, outbytes.length);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                else if (cmd.equals("who")) {
                    who_cmd(os);
                }
                else if (cmd.equals("hello")) {
                    outbytes = ("Hi there.\n" + eof).getBytes();
                    os.write(outbytes, 0, outbytes.length);
                }
		else if (cmd.equals("help")) {
                    outbytes = ("valid commands are: id who hello help sleep\n"
                                + eof).getBytes();
                    os.write(outbytes, 0, outbytes.length);
		}
                else {
                    outbytes = ("unimplemented command\n"+eof).getBytes();
                    os.write(outbytes, 0, outbytes.length);
                }
            }
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    // do the "who" command
    protected static void who_cmd(OutputStream os) {
        // run the who command and send the results to os
        byte[] outbytes;
        try {
            // run the Unix who command
            Process who = Runtime.getRuntime().exec("/usr/bin/who");
            InputStream is = who.getInputStream();
            final int bufsize = 4096;
            byte buf[] = new byte[bufsize];
            int nbytes;

            // read the results a buffer at a time (it would be
            // dumb to read line at a time unless we had some
            // need to count or deal with lines).

            while ((nbytes=is.read(buf)) != -1)
                os.write(buf, 0, nbytes);
            // write the end of output marker
            outbytes = eof.getBytes();
            os.write(outbytes, 0, outbytes.length);
            who.waitFor();
        } catch (Exception e) {
            try {
                outbytes = e.getMessage().getBytes();
                os.write(outbytes, 0, outbytes.length);
                outbytes = eof.getBytes();
                os.write(outbytes, 0, outbytes.length);
            } catch (Exception ex) {
                ex.printStackTrace();    // write failed, what can we do?
            }
        }
    }
}









