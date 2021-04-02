package day21.notes;

import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {
    @Test
    public void test01() throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        String hostAddress = inetAddress.getHostAddress();
        String hostName = inetAddress.getHostName();
        System.out.println("inetAddress = " + inetAddress);
        System.out.println("hostName = " + hostName);
        System.out.println("hostAddress = " + hostAddress);
    }
}
