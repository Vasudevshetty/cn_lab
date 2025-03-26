package udp;

import java.net.*;

public class Server {
    public static void main(String[] args) throws Exception {
        try (DatagramSocket server = new DatagramSocket(3000)) {
            System.out.println("Server is running on port 3000");

            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                server.receive(request);

                String message = new String(request.getData());
                System.out.println("[Server]: Recieved message: " + message);

                InetAddress clientAddress = request.getAddress();
                int clientPort = request.getPort();

                String capitalized = message.toUpperCase();
                buffer = capitalized.getBytes();

                DatagramPacket response = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
                server.send(response);
            }
        }
    }
}
