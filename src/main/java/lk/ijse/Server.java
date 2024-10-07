package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server Started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Main thread for server input and receiving messages
            System.out.println("Waiting for client's message...");
            while (true) {
                String clientMessage = dataInputStream.readUTF();
                System.out.println("\nClient: " + clientMessage);
                if (clientMessage.equalsIgnoreCase("exit")) {
                    System.out.println("Client has disconnected. Shutting down server...");
                    break;
                }

                System.out.print("Enter message: ");
                String serverMessage = scanner.nextLine();

                dataOutputStream.writeUTF(serverMessage);
                dataOutputStream.flush();

                if (serverMessage.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("Waiting for client's message...");
            }

            socket.close();
            serverSocket.close();
            System.out.println("Server Stopped.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}