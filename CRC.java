import java.util.*;

public class CRC {
    public String divide(char[] divident, char[] divisor) {
        int n = divident.length, m = divisor.length;

        for (int i = 0; i < n; i++) {
            if (divident[i] == '1') {
                if (n - i < m)
                    break;

                for (int j = 0; j < m; j++)
                    divident[i + j] = divisor[j] == '0' ? divident[i + j] : divident[i + j] == '1' ? '0' : '1';
            }
        }

        return new String(divident).substring(n - m + 1);
    }

    public String encode(String data, String key) {
        return data + divide((data + "0".repeat(key.length() - 1)).toCharArray(), key.toCharArray());
    }

    public boolean decode(String encodedData, String key) {
        return divide(encodedData.toCharArray(), key.toCharArray()).contains("1");
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter binary key: ");
        String key = sc.next();
        System.out.print("Enter binary data: ");
        String data = sc.next();

        CRC crc = new CRC();
        String encoded = crc.encode(data, key);
        System.out.println("Encoded data: " + encoded);

        System.out.println();

        System.out.print("Enter Encoded data: ");
        encoded = sc.next();

        sc.close();
        boolean errorred = crc.decode(encoded, key);
        System.out.println(errorred ? "Data contains Error" : "Data is Error free");
    }
}
