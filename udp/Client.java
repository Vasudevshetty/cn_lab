package udp;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        DatagramSocket client = new DatagramSocket();
        InetAddress clientAddress = InetAddress.getByName("localhost");
        int clientPort = 3000;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter message to get capatilzed from server: ");
        String message = reader.readLine();

        byte[] buffer = new byte[1024];
        buffer = message.getBytes();

        DatagramPacket request = new DatagramPacket(buffer, buffer.length, clientAddress, clientPort);
        client.send(request);

        DatagramPacket response = new DatagramPacket(buffer, buffer.length);
        client.receive(response);

        String capitalized = new String(response.getData());
        System.out.println("[Client]: Recieved message: " + capitalized);
        client.close();
    }
}
