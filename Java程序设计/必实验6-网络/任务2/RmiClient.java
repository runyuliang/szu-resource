package RMI;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class RmiClient {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 8080);
        Message hello = (Message) registry.lookup("hello");
        System.out.println(hello.sendMessage());
    }
}



