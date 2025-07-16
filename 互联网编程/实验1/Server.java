import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) throws IOException {
        // Create a server socket on port 8888
        ServerSocket serverSocket = new ServerSocket(8888);
        System.out.println("Server started on port 8888");

        // Accept a connection from a client
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected: " + clientSocket.getInetAddress());

        // Get the input stream and output stream of the client socket
        InputStream inputStream = clientSocket.getInputStream();
        OutputStream outputStream = clientSocket.getOutputStream();

        // Create a data output stream to write data to the output stream
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

        // Define an array of files to send
        File[] files = {new File("/Users/lry/Documents/file1.txt"), new File("/Users/lry/Documents/file2.png"), new File("/Users/lry/Documents/file3.pdf")};

        // Loop through the files and send them one by one
        for (File file : files) {
            // Get the file name and size
            String fileName = file.getName();
            long fileSize = file.length();

            // Write the file name length, file name and file size to the data output stream
            dataOutputStream.writeInt(fileName.length());
            dataOutputStream.writeBytes(fileName);
            dataOutputStream.writeLong(fileSize);

            // Create a buffered input stream to read from the file
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));

            // Create a byte buffer to store the file content
            byte[] buffer = new byte[1024];

            // Read from the file and write to the data output stream until end of file
            int count;
            while ((count = bufferedInputStream.read(buffer)) > 0) {
                dataOutputStream.write(buffer, 0, count);
            }

            // Flush the data output stream
            dataOutputStream.flush();

            // Close the buffered input stream
            bufferedInputStream.close();

            // Print a message to indicate the file is sent
            System.out.println("Sent file: " + fileName + " (" + fileSize + " bytes)");
        }

        // Close the data output stream and the client socket
        dataOutputStream.close();
        clientSocket.close();

        // Print a message to indicate the connection is closed
        System.out.println("Connection closed");

        // Close the server socket
        serverSocket.close();
    }
}