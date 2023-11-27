## How to Run

This guide provides instructions on how to run the Client and Server for the chatroom application. Ensure that you have Java installed on your system before proceeding.

### Prerequisites
- Java Development Kit (JDK) installed
- Source files for Client and Server located in the `src` folder

### Instructions

1. **Open Terminals:** 
   - Open two terminals: one for the server and the other for the client. If you want to connect multiple clients, open additional terminals for each client.

2. **Navigate to Source Directory:** 
   - In each terminal, navigate to the `src` folder where the server and client files are located.
   - Use the command: `cd path/to/src` (replace `path/to/src` with the actual path to your `src` folder).

3. **Run Server:**
   - In the terminal designated for the server, run the following command:
     ```
     java ChatroomServer.java
     ```
   - This starts the server process.

4. **Run Clients:**
   - In each client terminal, execute the following command:
     ```
     java ChatroomClient.java
     ```
   - Upon starting, the client will prompt you to enter the name of your chatroom. As per project requirements, the chatroom should be named 'first'.

5. **Join Chatroom:**
   - Each client should input the name of the chatroom when prompted. Once done, you will see a live stream of messages on all clients for each message sent from any client.

### Troubleshooting
- Ensure all commands are typed correctly.
- Verify that the JDK is properly installed and the `PATH` environment variable is set.
- Check that the server is running before attempting to connect clients.
