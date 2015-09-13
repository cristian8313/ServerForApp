
public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TCPServerMultithreading server = new TCPServerMultithreading(5454);
		new Thread(server).start();

		/*try {
		    Thread.sleep(60 * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println("Stopping Server");
		server.stop();*/

	}

}
