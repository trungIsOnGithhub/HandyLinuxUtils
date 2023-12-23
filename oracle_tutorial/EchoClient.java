import java.net.*;

public class EchoClient {
    public static final void clearTerminal() {
        final String operatingSystem = System.getProperty("os.name");

        if (operatingSystem.toLowerCase().contains("window")) {
            Runtime.getRuntime().exec("cls");
        }
        else {
            Runtime.getRuntime().exec("clear");
        }
    }

	public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        int portNumber = Integer.parseInt(args[0]);

        try (
            Socket kkSocket = new Socket(hostName, portNumber);

            PrintWriter out = new PrintWriter(kkSocket.getOutputStream(), true);

            BufferedReader in = new BufferedReader( new InputStreamReader( kkSocket.getInputStream() ) );
        ) {
            String fromServer = "", fromUser = "";
            BufferedReader stdIn = new BufferedReader( new InputStreamReader(System.in) );
 
            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);

                if ( fromServer.equals("Bye.") )
                    break;
                 
                fromUser = stdIn.readLine();

                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }

            clearTerminal();

            System.out.println("GOODBYE!!!");

        } catch (UnknownHostException e) {
            System.err.println("Don't know host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " +
                hostName);
            System.exit(1);
        }
	}
}