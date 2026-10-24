import java.io.*;
import java.net.*;

public class DemoClient {

    static int port = 2222;       // default server port
    static String host = "localhost";      // default server host

    // we use a line containing only this character as an end-of-record indicator
    static char eofchars[] = { (char)4 };
    static String eof = new String(eofchars);

    public static void main(String[] argv) {
        String usage = "usage: java DemoClient [-h host] [-p port] command ... " +
          "\n (default port=" + port +", host=" + host + ")";

        int optind = 0;  // command-line argument index
       
 try {
     // process command-line arguments
     for (optind = 0; optind < argv.length; ++optind) 
	 {
	     if (!argv[optind].substring(0,1).equals("-") ||
		 argv[optind].equals("--"))
		 break;    // end of options
	     if (argv[optind].equals("-p")) 
		 {   // -p port
		     if (++optind >= argv.length) 
			 {
			     System.err.println(usage);
			     System.exit(1);
			 }
		     port = (new Integer(argv[optind])).intValue();
		 }
	     else if (argv[optind].equals("-h")) 
		    {   // -h host
			if (++optind >= argv.length) 
			    {
				System.err.println(usage);
				System.exit(1);
			    }
			host = argv[optind];
		    }
	     else 
		 {
		     System.err.println(usage);
		     System.exit(1);
		 }
	 }
 }
 catch (NumberFormatException e) 
     {
	 System.err.println("bad number format");
	 System.exit(1);
     }
 // all remaining actions are considered to be tokens (or commands)
 // that we will send to the server one at a time over the same connection
 dowork(argv, optind);
    }

    


    // connect to the server and process the commands
    protected static void dowork(String argv[], int optind) {
        // now connect to the server
        try 
	    {
		// this creates a TCP/IP socket to the machine <host> on port <port>
		Socket s = new Socket(host, port);

		// we have a connection --
		// now get the OutPutStream so we can write to the socket
		OutputStream os = s.getOutputStream();

		// this enables us to read line-buffered results. See
		// the comments in DemoServer.java
		BufferedReader is = new BufferedReader(new InputStreamReader(s.getInputStream()));
		
		// send each remaining argument to the server as a command
		for (; optind < argv.length; ++optind) 
		    {
			// write the argument as a byte array terminated by a newline
			System.out.println("command: " + argv[optind]);
			byte[] cmd = (argv[optind]+"\n").getBytes();
			os.write(cmd, 0, cmd.length);    // write the bytes to the socket
			
			// now read the response. In this case we send a single
			// line as a response.
			System.out.println("response: ");
			while (true) 
			    {
				String response = is.readLine();
				if (response.equals(eof))
				    break;
				System.out.println(response);
			    }
		    }
		
		// no more arguments, close connection
		s.close();
	    }
        catch (UnknownHostException e) 
	    {
		System.err.println("unknown host: " + host);
		System.exit(1);
	    }
        catch (IOException e) 
	    {
		System.out.println(e.getMessage());
		e.printStackTrace();
	    }
    }
}

