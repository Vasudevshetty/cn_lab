import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RSA {
    BigInteger publicKey, privateKey, mod;

    public void getKeys(int bitlen) {
        Random r = new Random();
        BigInteger p = BigInteger.probablePrime(bitlen, r);
        BigInteger q = BigInteger.probablePrime(bitlen, r);
        mod = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        publicKey = BigInteger.probablePrime(bitlen / 2, r);
        while (phi.gcd(publicKey).compareTo(BigInteger.ONE) > 0 || publicKey.compareTo(phi) >= 0)
            publicKey.add(BigInteger.ONE);
        privateKey = publicKey.modInverse(phi);
    }

    public void displayKeys() {
        System.out.println("Public Key: " + publicKey);
        System.out.println("Private Key: " + privateKey);
        System.out.println("Modulus: " + mod);
    }

    public BigInteger encrypt(BigInteger message) {
        return message.modPow(publicKey, mod);
    }

    public BigInteger decrypt(BigInteger cipherText) {
        return cipherText.modPow(privateKey, mod);
    }

    public static void main(String[] args) {
        RSA rsa = new RSA();
        rsa.getKeys(512);
        rsa.displayKeys();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message to be encrypted: ");
        BigInteger message = new BigInteger(sc.nextLine());

        BigInteger cipherText = rsa.encrypt(message);
        System.out.println("Encrypted message: " + cipherText);
        System.out.println("Decrypted message: " + rsa.decrypt(cipherText));

        sc.close();
    }
}
