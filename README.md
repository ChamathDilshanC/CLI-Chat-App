# Java Socket Chat Application

This project demonstrates a simple client-server chat application using Java sockets. It allows for bidirectional communication between a server and a client.

## Project Structure

The project consists of two main Java classes:

1. `Server.java`: Implements the server-side logic.
2. `Client.java`: Implements the client-side logic.

## Features

- Real-time messaging between server and client
- Alternating message flow (one side sends, then waits for a response)
- Graceful exit handling

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- An Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse (optional, but recommended)

## Setup and Running

### Using an IDE (IntelliJ IDEA)

1. Open the project in IntelliJ IDEA.
2. Set up a compound run configuration:
   - Go to "Run" > "Edit Configurations"
   - Click the "+" button and select "Compound"
   - Name it "Run Server then Client"
   - Add two application configurations:
     - One for `Server.java`
     - One for `Client.java`
   - Ensure the Server configuration is listed before the Client
3. Run the compound configuration to start both the server and client.

### Using Command Line

1. Open a terminal and navigate to the project directory.
2. Compile the Java files:
   ```
   javac Server.java Client.java
   ```
3. In one terminal window, start the server:
   ```
   java Server
   ```
4. In another terminal window, start the client:
   ```
   java Client
   ```

## Usage

1. The server will start and wait for a client connection.
2. The client will connect to the server.
3. The client can send the first message.
4. The server and client will then take turns sending messages.
5. Type "exit" in either the client or server console to end the session.

## Note

Ensure that the server is running before starting the client, as the client attempts to connect to a running server.

## Troubleshooting

- If you encounter "Address already in use" errors, ensure no other applications are using port 3000, or modify the port number in both Server.java and Client.java.
- If the client can't connect, verify that the server is running and that both are using the same port number.

## Contributing

Feel free to fork this project and submit pull requests with any enhancements.

## License

This project is open-source and available under the MIT License.
