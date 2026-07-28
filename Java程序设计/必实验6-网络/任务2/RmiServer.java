package RMI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


class SerMeg extends UnicastRemoteObject implements Message {

    private String message;

    public SerMeg(String message) throws RemoteException {
        this.message = message;
    }

    @Override
    public String sendMessage() {
        System.out.println("Server has sent the message to the client.");
        return message;
    }
}


public class RmiServer {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(8080);
            Message hello = new SerMeg("Server says: Hello world!");
            registry.bind("hello", hello);
            System.out.println("Successfully registered.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
