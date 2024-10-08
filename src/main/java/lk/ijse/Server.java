package lk.ijse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server Started. Waiting for client...");

            Socket socket = serverSocket.accept();
            System.out.println("Client Connected. Input 'exit' to disconnect.");

            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            // Thread for receiving messages
            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String clientMessage = dataInputStream.readUTF();
                        if (clientMessage.equalsIgnoreCase("exit")) {
                            System.out.println("\nClient has disconnected.");
                            break;
                        }
                        System.out.print("\r" + ANSI_YELLOW + "Client: " + clientMessage + ANSI_RESET + "\nServer: ");
                    }
                } catch (IOException e) {
                    if (true) {
                        System.out.println("Client disconnected.");
                    }
                }
            });
            receiveThread.start();

            // Main thread for sending messages
            System.out.print("Server: ");
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    dataOutputStream.writeUTF(message);
                    break;
                }
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
                System.out.print("Server: ");
            }

            System.out.println("Shutting down server...");
            socket.close();
            serverSocket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}