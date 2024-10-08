package lk.ijse;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connected to server. Input 'exit' to disconnect.");

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);


            // Thread for receiving messages

            Thread receiveThread = new Thread(() -> {
                try {
                    while (true) {
                        String serverMessage = dataInputStream.readUTF();
                        if (serverMessage.equalsIgnoreCase("exit")) {
                            System.out.println("\nServer has disconnected.");
                            break;
                        }
                        System.out.print("\r" + ANSI_GREEN + "Server: " + serverMessage + ANSI_RESET + "\nYou: ");
                    }
                } catch (IOException e) {
                    if (true) {
                        System.out.println("Disconnected from server.");
                    }
                }
            });
            receiveThread.start();

            // Main thread for sending messages
            System.out.print("You: ");
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    dataOutputStream.writeUTF(message);
                    break;
                }
                dataOutputStream.writeUTF(message);
                dataOutputStream.flush();
                System.out.print("You: ");
            }

            System.out.println("Disconnecting from server...");
            socket.close();
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}