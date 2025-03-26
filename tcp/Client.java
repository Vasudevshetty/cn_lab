package tcp;

import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 3000);

        System.out.print("Enter the filename: ");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String filename = input.readLine();

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        out.println(filename);

        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line;
        while ((line = in.readLine()) != null)
            System.out.println(line);

        socket.close();
        in.close();
        out.close();
        input.close();
    }
}
