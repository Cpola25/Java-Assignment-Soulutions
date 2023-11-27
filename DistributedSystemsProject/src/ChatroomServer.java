import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatroomServer{
    
    // This defines the Server's listening port
    // If in use either free the terminal or change this value to a free one in both the server and client code
 
    private static final int PORT = 54221;
    // Map that will hold all chat rooms. 
    private static Map<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Chat Server is running on port " + PORT);

            // Server will run indefinitely and accept incoming client connections
            while (true) {
                // Accepts a new client connection
                Socket clientSocket = serverSocket.accept();
                // Starts a new thread for each client
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            // Handle exceptions related to IO
            e.printStackTrace();
        }
    }

    // Inner that will handle client communication
    private static class ClientHandler extends Thread {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try {
                // Initializes communication channels with the client
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Read the name of the chat room from client
                String roomName = in.readLine();
                // Create a new chat room if it doesn't exist
                if (!chatRooms.containsKey(roomName)) {
                    chatRooms.put(roomName, new ChatRoom(roomName));
                }

                // Get the chat room and add this client to it
                ChatRoom chatRoom = chatRooms.get(roomName);
                chatRoom.addClient(out);

                // Process messages from this client
                String message;
                while ((message = in.readLine()) != null) {
                    // Broadcast received messages to all clients in the room
                    chatRoom.broadcast(message);
                }

                // Clean up when client leaves
                chatRoom.removeClient(out);

                // Remove chat room if empty
                if (chatRoom.isEmpty()) {
                    chatRooms.remove(roomName);
                }

                // Close client socket
                clientSocket.close();
            } catch (IOException e) {
                // Handle exceptions
                e.printStackTrace();
            }
        }
    }

    // Inner class that represents a chatroom
    private static class ChatRoom {
        private String name;
        // List of clients in this chat room. Using thread-safe list.
        private List<PrintWriter> clients = new CopyOnWriteArrayList<>();

        public ChatRoom(String name) {
            this.name = name;
        }

        // Adds a new client to the chat room
        public void addClient(PrintWriter client) {
            clients.add(client);
        }

        // Removes a client from the chat room
        public void removeClient(PrintWriter client) {
            clients.remove(client);
        }

        // Checks if the chat room is empty
        public boolean isEmpty() {
            return clients.isEmpty();
        }

        // Sends a message to all clients in the chat room
        public void broadcast(String message) {
            for (PrintWriter client : clients) {
                client.println(message);
            }
        }
    }
}
