import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class DomainResolver {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("请输入域名 (输入 'exit' 退出): ");
            String domainName = scanner.nextLine();

            if (domainName.equalsIgnoreCase("exit")) {
                break;
            }

            try {
                String ipAddress = resolveDomain(domainName);
                System.out.println("IP地址: " + ipAddress);
            } catch (UnknownHostException e) {
                System.out.println("无法解析域名: " + domainName);
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    public static String resolveDomain(String domainName) throws UnknownHostException {
        InetAddress address = InetAddress.getByName(domainName);
        return address.getHostAddress();
    }
}
