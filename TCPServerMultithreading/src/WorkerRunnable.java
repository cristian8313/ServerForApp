import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;

/**

 */
public class WorkerRunnable implements Runnable{

    protected Socket clientSocket = null;
    protected String serverText   = null;
    protected TCPServerMultithreading server = null;

    public WorkerRunnable(TCPServerMultithreading server, Socket clientSocket, String serverText) {
        this.clientSocket = clientSocket;
        this.serverText   = serverText;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input  = clientSocket.getInputStream();
            OutputStream output = clientSocket.getOutputStream();
            long time = System.currentTimeMillis();
            output.write(("HTTP/1.1 200 OK\n\nWorkerRunnable: " +
            		this.serverText + " - " +
            		time +
            		"").getBytes());
            BufferedReader buffer = new BufferedReader(new InputStreamReader(input)); 
            while (!server.isStopped) {
            	String message = buffer.readLine();
            	if (!message.isEmpty()) {
            		System.out.println("recibio: " + message);
            	}
            }
            output.close();
            input.close();
            System.out.println("Request processed: " + time);
        } catch (IOException e) {
            //report exception somewhere.
            e.printStackTrace();
        }
    }
}