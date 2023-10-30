import java.util.Scanner;

public class Encrypt {
    public static String temp = "";
    public static int k;

    // Encrypts text using a shift cipher
    public static String encrypt(String m, int k) {
        StringBuilder encrypto = new StringBuilder();
        for (int i = 0; i < m.length(); i++) {
            if (Character.isUpperCase(m.charAt(i))) {
                char ch = (char) (((int) m.charAt(i) + k - 65) % 26 + 65);
                encrypto.append(ch);
            } else {
                char ch = (char) (((int) m.charAt(i) + k - 97) % 26 + 97);
                encrypto.append(ch);
            }
        }

        temp = encrypto.toString();
        return encrypto.toString();
    }

    // Decryption
    // Decryption
public static String decrypt(String temp, int k) {
    StringBuilder decrypto = new StringBuilder();
    for (int i = 0; i < temp.length(); i++) {
        if (Character.isUpperCase(temp.charAt(i))) {
            char ch = (char) (((int) temp.charAt(i) - k - 65 + 26) % 26 + 65);
            decrypto.append(ch);
        } else {
            char ch = (char) (((int) temp.charAt(i) - k - 97 + 26) % 26 + 97);
            decrypto.append(ch);
        }
    }
    return decrypto.toString();
}


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a key");
        k = sc.nextInt();
        sc.nextLine(); // Consume the newline character left in the input buffer
        System.out.println("Enter the text");
        String m = sc.nextLine();

        System.out.println("Text: " + m);
        System.out.println("Key: " + k);
        String encryptedText = encrypt(m, k);
        System.out.println("Cipher: " + encryptedText);
        String decryptedText = decrypt(encryptedText, k);
        System.out.println("Decrypted: " + decryptedText);
    }
}
