import java.awt.Point;
import java.util.Scanner;


public class PlayfairCipher {
    // Length of digraph array
    private int length = 0;
    // Creates a matrix for Playfair cipher
    private String[][] table;


    // Main method to test Playfair method
    public static void main(String[] args) {
        new PlayfairCipher();
    }


    // Main run of the program, Playfair method
    // Constructor of the class
    private PlayfairCipher() {
        // Prompts the user for the keyword to use for encoding & creates tables
        System.out.print("Enter the key for playfair cipher: ");
        Scanner sc = new Scanner(System.in);
        String key = parseString(sc);
        while (key.equals(""))
            key = parseString(sc);
        table = this.cipherTable(key);


        // Prompts the user for the message to be encoded
        System.out.print("Enter the plaintext to be encipher: ");
        String input = parseString(sc);
        while (input.equals(""))
            input = parseString(sc);


        // Encodes and then decodes the encoded message
        String output = cipher(input);
        String decodedOutput = decode(output);


        // Output the results to the user
        keyTable(table);
        printResults(output, decodedOutput);
    }


    // Parses an input string to remove numbers, punctuation, replaces J's with I's, and makes the string all caps
    private String parseString(Scanner sc) {
        String parse = sc.nextLine();
        // Converts all the letters to upper case
        parse = parse.toUpperCase();
        // Replace anything that is not A-Z with an empty string
        parse = parse.replaceAll("[^A-Z]", "");
        // Replace the letter 'J' with 'I'
        parse = parse.replace("J", "I");
        return parse;
    }


    // Creates the cipher table based on the input string (already parsed)
    private String[][] cipherTable(String key) {
        // Creates a matrix of 5x5
        String[][] playfairTable = new String[5][5];
        String keyString = key + "ABCDEFGHIKLMNOPQRSTUVWXYZ";
       
        // Fill the string array with empty strings
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                playfairTable[i][j] = "";
            }
        }


        for (int k = 0; k < keyString.length(); k++) {
            boolean repeat = false;
            boolean used = false;


            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    if (playfairTable[i][j].equals("" + keyString.charAt(k))) {
                        repeat = true;
                    } else if (playfairTable[i][j].equals("") && !repeat && !used) {
                        playfairTable[i][j] = "" + keyString.charAt(k);
                        used = true;
                    }
                }
            }
        }
        return playfairTable;
    }


    // Cipher: takes input (all upper-case), encodes it, and returns the output
    private String cipher(String in) {
        length = (int) in.length() / 2 + in.length() % 2;


        // Insert 'X' between double-letter digraphs and redefine "length"
        for (int i = 0; i < (length - 1); i++) {
            if (in.charAt(2 * i) == in.charAt(2 * i + 1)) {
                in = new StringBuffer(in).insert(2 * i + 1, 'X').toString();
                length = (int) in.length() / 2 + in.length() % 2;
            }
        }


        // Makes the plaintext of even length and creates an array of digraphs
        String[] digraph = new String[length];
        for (int j = 0; j < length; j++) {
            if (j == (length - 1) && in.length() / 2 == (length - 1))
                // If not even, add 'X' at the end of the plaintext
                in = in + "X";
            digraph[j] = in.charAt(2 * j) + "" + in.charAt(2 * j + 1);
        }


        // Encodes the digraphs and returns the output
        String out = "";
        String[] encDigraphs = new String[length];
        encDigraphs = encodeDigraph(digraph);
        for (int k = 0; k < length; k++)
            out = out + encDigraphs[k];
        return out;
    }


    // Encryption logic: encodes the digraph input with the cipher's specifications
    private String[] encodeDigraph(String[] di) {
        String[] encipher = new String[length];
        for (int i = 0; i < length; i++) {
            char a = di[i].charAt(0);
            char b = di[i].charAt(1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();


            // Executes if the letters of the digraph appear in the same row
            if (r1 == r2) {
                c1 = (c1 + 1) % 5;
                c2 = (c2 + 1) % 5;
            }
            // Executes if the letters of the digraph appear in the same column
            else if (c1 == c2) {
                r1 = (r1 + 1) % 5;
                r2 = (r2 + 1) % 5;
            } else {
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            // Performs the table look-up and puts those values into the encoded array
            encipher[i] = table[r1][c1] + "" + table[r2][c2];
        }
        return encipher;
    }


    // Decryption logic: decodes the output given from the cipher and decode methods
    private String decode(String out) {
        String decoded = "";
        for (int i = 0; i < out.length() / 2; i++) {
            char a = out.charAt(2 * i);
            char b = out.charAt(2 * i + 1);
            int r1 = (int) getPoint(a).getX();
            int r2 = (int) getPoint(b).getX();
            int c1 = (int) getPoint(a).getY();
            int c2 = (int) getPoint(b).getY();


            if (r1 == r2) {
                c1 = (c1 + 4) % 5;
                c2 = (c2 + 4) % 5;
            } else if (c1 == c2) {
                r1 = (r1 + 4) % 5;
                r2 = (r2 + 4) % 5;
            } else {
                // Swapping logic
                int temp = c1;
                c1 = c2;
                c2 = temp;
            }
            decoded = decoded + table[r1][c1] + table[r2][c2];
        }
        // Returns the decoded message
        return decoded;
    }


    // Returns a point containing the row and column of the letter
    private Point getPoint(char c) {
        Point pt = new Point(0, 0);
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (c == table[i][j].charAt(0)) {
                    pt = new Point(i, j);
                }
            }
        }
        return pt;
    }


    // Function prints the key-table in matrix form for Playfair cipher
    private void keyTable(String[][] printTable) {
        System.out.println("Playfair Cipher Key Matrix: ");
        System.out.println();
        // Loop iterates for rows
        for (int i = 0; i < 5; i++) {
            // Loop iterates for columns
            for (int j = 0; j < 5; j++) {
                // Prints the key-table in matrix form
                System.out.print(printTable[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


    // Method that prints all the results
    private void printResults(String encipher, String dec) {
        System.out.print("Encrypted Message: ");
        // Prints the encrypted message
        System.out.println(encipher);
        System.out.println();
        System.out.print("Decrypted Message: ");
        // Prints the decrypted message
        System.out.println(dec);
    }
}
