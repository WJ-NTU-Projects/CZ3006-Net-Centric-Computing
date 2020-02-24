import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPServer {
    static DatagramSocket socket;
    static final int PORT = 17;
    
    public static void main(String args[]) {
        try {
            System.out.println("Server address = " + InetAddress.getLocalHost().getHostAddress());
            socket = new DatagramSocket(PORT);
        } catch (UnknownHostException | SocketException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        while (true) {
            try {
                byte[] buffer = new byte[1024];
                DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
                socket.receive(requestPacket);
                String data = new String(requestPacket.getData());
                System.out.println("Received data: " + data.trim());
                System.out.println();
                InetAddress clientAddress = requestPacket.getAddress();
                int clientPort = requestPacket.getPort();
                
                byte[] reply = "Random quote of the day here!".getBytes();
                DatagramPacket replyPacket = new DatagramPacket(reply, reply.length, clientAddress, clientPort);
                socket.send(replyPacket);
            } catch (IOException e) {               
                System.out.println("IOError: " + e.getMessage());
            }
        }
    }
}
