import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatroomClient {
    public static void main(String[] args) {
        try {
            // Establish connection with the server
            // 'localhost' indicates the server is on the same machine, and 54221 is the port number.
            Socket socket = new Socket("localhost",  54221);

            // Create a PrintWriter to send messages to the server
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Create a BufferedReader to receive messages from the server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Scanner to read user input from the console
            Scanner scanner = new Scanner(System.in);

            // Ask the user to enter the chat room name
            System.out.print("Enter the chat room name: ");
            String roomName = scanner.nextLine();
            out.println(roomName); // Send the room name to the server

            // Creating a new thread to handle receiving messages from the server
            Thread receiveThread = new Thread(() -> {
                try {
                    String message;
                    // Continuously read messages from the server and print them
                    while ((message = in.readLine()) != null) {
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            receiveThread.start(); // Start the thread to receive messages

            // Main loop for sending messages
            String message;
            while (true) {
                // Read user input and send it to the server
                message = scanner.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            // Handle any IO exceptions
            e.printStackTrace();
        }
    }
}

