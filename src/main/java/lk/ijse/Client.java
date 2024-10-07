package lk.ijse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connected to server.");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            // Thread for receiving messages from the server
            Thread serverListener = new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = dataInputStream.readUTF();
                        System.out.println("\nServer: " + serverMessage);
                        if (serverMessage.equalsIgnoreCase("exit")) {
                            System.out.println("Server has disconnected. Closing client...");
                            System.exit(0);
                        }
                        System.out.print("Enter message: ");
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            });
            serverListener.start();

            // Main thread for client input
            System.out.println("You can start sending messages.");
            while (true) {
                System.out.print("Enter message: ");
                String message = scanner.nextLine();

                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }

                // Wait for server's response
                System.out.println("Waiting for server's response...");
                String serverResponse = dataInputStream.readUTF();
                System.out.println("Server: " + serverResponse);
            }

            socket.close();
            System.out.println("Disconnected from server.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}