import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) throws IOException {
        // Create a socket with host name and port number
        Socket socket = new Socket("localhost", 8888);
        System.out.println("Connected to server: " + socket.getInetAddress());

        // Get the input stream and output stream of the socket
        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        // Create a data input stream to read data from the input stream
        DataInputStream dataInputStream = new DataInputStream(inputStream);

        // Loop until there is no more file to receive
        while (true) {
            try {
                // Read the file name length, file name and file size from the data input stream
                int fileNameLength = dataInputStream.readInt();
                byte[] fileNameBytes = new byte[fileNameLength];
                dataInputStream.readFully(fileNameBytes);
                String fileName = new String(fileNameBytes);
                long fileSize = dataInputStream.readLong();

                // Create a buffered output stream to write to a local file with the same name as received
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileName));

                // Create a byte buffer to store the file content
                byte[] buffer = new byte[1024];

                // Read from the data input stream and write to the buffered output stream until end of file
                int count;
                long total = 0;
                while (total < fileSize && (count = dataInputStream.read(buffer)) > 0) {
                    bufferedOutputStream.write(buffer, 0, count);
                    total += count;
                }

                // Flush the buffered output stream
                bufferedOutputStream.flush();

                // Close the buffered output stream
                bufferedOutputStream.close();

                // Print a message to indicate the file is received
                System.out.println("Received file: " + fileName + " (" + fileSize + " bytes)");
            } catch (EOFException e) {
                // Break out of the loop if end of stream is reached
                break;
            }
        }

        // Close the data input stream and the socket
        dataInputStream.close();
        socket.close();

        // Print a message to indicate the connection is closed
        System.out.println("Connection closed");
    }
}
