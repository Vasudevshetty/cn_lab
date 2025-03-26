package tcp;

import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(3000);
        System.out.println("Server is running on port 3000");

        Socket socket = server.accept();
        System.out.println("Client connected");

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String filename = in.readLine();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String line;
        while ((line = reader.readLine()) != null)
            out.println(line);

        socket.close();
        server.close();
        in.close();
        out.close();
        reader.close();
    }
}
