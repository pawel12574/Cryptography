/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsk1;

import static java.lang.Math.pow;
import java.util.Scanner;

public class BSK1 {

    //CESAR
    static char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
        'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z'};

    static int n = alphabet.length;

    static String cipher(String msg, int k0, int k1) { //abc 95, 87

        String s = "";  //String wyjściowy
        int length = msg.length();
        int c; //znak wyjściowy

        for (int i = 0; i < length; i++) {
            c = ((findIndex(msg.charAt(i))) * k1 + k0) % 26;       //c=(a*k1+k0) mod n
            s = s + alphabet[c] + "";
        }

        return s;
    }

    //CESAR
    static String decipher(String msg, int k0, int k1) {
        String s = "";   //String wyjściowy
        int index; //index toEnc
        int length = msg.length();
        double c; //znak wyjściowy
        int temp;
        double pt = k1;

        for (int i = 0; i < 10; i++) {
            pt = (pt * k1) % n;
        }

        for (int i = 0; i < length; i++) {
            index = findIndex(msg.charAt(i));
            //c = ((index)-k)%26;          
            // if(c<0)
            //     c=(26+c)%26;            //a = [c+(n-k0)]
            k0 = k0 % 26;
            c = (index + (n - k0)) * pt % n;
            temp = (int) c;
            s = s + alphabet[temp] + "";
        }

        return s;
    }

    static int findIndex(char c) {
        int x = 0;

        for (int i = 0; i < alphabet.length; i++) {
            if (c == alphabet[i]) {
                x = i;
            }
        }
        return x;
    }

    static String railfence(int n, String toEnc) { //"4", "ALA_MA_KOTA"

        String[][] toEncS = new String[n + 1][toEnc.length() + 1];
        int j = 0;
        int count = 0;
        for (int i = 0; i < toEnc.length(); i++) {
            if (count == 0) {
                j++;
            } else {
                j--;
            }

            if (j == 1) {
                count = 0;
            }
            if (j == n) {
                count = 1;
            }

            toEncS[j][i] = toEnc.charAt(i) + "";

        }

        String enc = "";

        for (int l = 1; l < n + 1; l++) {
            for (int i = 0; i < toEnc.length(); i++) {
                if (toEncS[l][i] != null) {
                    enc = enc + toEncS[l][i] + "";
                }
            }
        }

        System.out.println("Zaszyfrowany " + enc);

        return "";
    }

    static String railfenceDec(int n, String enc) {
        int j;
        int line = 0;
        int skip = 0;
        String[] pt = new String[enc.length()];
        int k = 0;
        for (line = 0; line < n - 1; line++) {
            skip = 2 * (n - line - 1);
            j = 0;
            for (int i = line; i < enc.length();) {
                pt[i] = enc.charAt(k++) + "";
                if ((line == 0) || (j % 2 == 0)) {
                    i += skip;
                } else {
                    i += 2 * (n - 1) - skip;
                }
                j++;
            }
        }

        System.out.print("Odszyfrowany ");

        for (int i = line; i < enc.length(); i += 2 * (n - 1)) {
            pt[i] = enc.charAt(k++) + "";
        }

        for (int i = 0; i < pt.length; i++) {
            System.out.print(pt[i]);
        }

        return "";

    }

    //MATRIX 
    static String matrixTransp(String toEnc, String key) {/// "alamakota"
        int d = key.length(); //było d=5 i key="34152"; 
        int x;

        x = toEnc.length() / d;
        if (x % d != 0) {
            x++;
        }
        String[][] matrix = new String[x][d];
        String out = "";
        int a = 0;
        for (int j = 0; j < x; j++) {

            for (int i = 0; i < d; i++) {
                if (a < toEnc.length()) {
                    matrix[j][i] = toEnc.charAt(a++) + "";
                }
            }
        }

        int dramat;
        String outTab[][] = new String[x][d];

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < d; j++) {

                dramat = key.charAt(j) - 49;
                if (matrix[i][j] != null) {
                    outTab[i][j] = matrix[i][dramat];
                } else {
                    outTab[i][j] = matrix[i][dramat];
                }
            }
        }

        for (int i = 0; i < x; i++) {

            for (int j = 0; j < d; j++) {
                if (outTab[i][j] != null) {
                    out = out + outTab[i][j];
                }

            }
        }

        return out;

    }
    //MATRIX  

    static String matrixDec(String toDec, String key) {

        int d = 5;
        int x;

        String out = "";

        x = toDec.length() / d;
        if (x % d != 0) {
            x++;
        }
        String[][] matrix = new String[x][d];
        int a = 0;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < d; j++) {
                if (a < toDec.length()) {
                    matrix[i][j] = toDec.charAt(a++) + "";
                }
            }
        }
        // 12345      34152
        //   34152     34152       34152     alama      amaal
        // crypt     ytcrp       alama     kota       tako
        // ograp     rpoga       kota
        // hy        hy
        String outTab[][] = new String[x][d];
        int dramat;

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < d; j++) {
                dramat = key.charAt(j) - 49;

                if (matrix[i][j] != null) {
                    outTab[i][dramat] = matrix[i][j];
                }
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < d; j++) {
                if (outTab[i][j] != null) {
                    out = out + outTab[i][j];
                }
            }
        }

        return out;
    }

    static String vigenere(String key, String toEnc) {   //"b","b"

        String enc = "";
        int length = toEnc.length();
        int[] index = new int[length];

        for (int i = 0; i < length; i++) {
            index[i] = findIndex(toEnc.charAt(i));//tablica indexów do zaszyfrowania
        }

        for (int i = 0; i < length; i++) {
            index[i] = (index[i] + findIndex(key.charAt(i % key.length()))) % 26;//index textu+klucza

            enc = enc + alphabet[index[i]];
        }

        System.out.println("Zaszyfrowany " + enc);//zaszyfrowany
        return "";
    }

    static String vigenereDec(String key, String enc) {

        String toDec = enc;
        int length = enc.length();
        int[] index = new int[length];
        String dec = "";

        for (int i = 0; i < toDec.length(); i++) {
            index[i] = findIndex(toDec.charAt(i));
        }

        for (int i = 0; i < toDec.length(); i++) {
            index[i] = (index[i] - findIndex(key.charAt(i % key.length()))) % 26;//index textu-klucza
            if (index[i] < 0) {
                index[i] = (26 + index[i]) % 26;
            }
            dec = dec + alphabet[index[i]];
        }

        return dec;
    }

    public static void main(String[] args) {
        int option = 0;
        Scanner sc = new Scanner(System.in);
        int k0, k1;
        String toEnc = "";
        String key = "";
        int k;
        System.out.println("1. Cesar szyfrowanie \n2. Cesar deszyfrowanie"
                + " \n3. Rail fence szyfrowanie \n4. Rail fence deszyfrowanie"
                + " \n5. Matrix szyfrowanie \n6. Matrix deszyfrowanie "
                + "\n7. Vigenere szyfrowanie \n8. Vigenere deszyfrowanie"
                + " \n9. Wyjście ");
        while (option != 9) {

            option = sc.nextInt();

            switch (option) {

                case 1: {
                    System.out.println("Cesar. Słowo do zaszyfrowania, klucz k0, klucz k1");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    System.out.println("klucz k0");
                    k0 = sc.nextInt();
                    System.out.println("klucz k1");
                    k1 = sc.nextInt();
                    System.out.println("Słowo zaszyfrowane " + cipher(toEnc, k0, k1));
                    break;
                }
                case 2: {
                    System.out.println("Cesar. Słowo do odszyfrowania, klucz k0, klucz k1 ");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    k0 = sc.nextInt();
                    k1 = sc.nextInt();
                    System.out.println("Słowo odszyfrowane " + decipher(toEnc, k0, k1));
                    break;
                }

                case 3: {
                    System.out.println("Rail fence. Podaj n, podaj słowo");

                    k = sc.nextInt();
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    System.out.println(railfence(k, toEnc));
                    break;
                }
                case 4: {
                    System.out.println("Rail fence. Podaj n, poda słowo");
                    k = sc.nextInt();
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    System.out.println(railfenceDec(k, toEnc));
                    break;
                }

                case 5: {
                    System.out.println("Matrix. Podaj słowo do zaszyfrowania oraz klucz");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    key = sc.nextLine();
                    System.out.println("Słowo zaszyfrowane " + matrixTransp(toEnc, key));
                    break;
                }
                case 6: {
                    System.out.println("Matrix. Podaj słowo do odszyfrowania oraz klucz");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    key = sc.nextLine();
                    System.out.println("Słowo odszyfrowane " + matrixDec(toEnc, key));
                    break;
                }

                case 7: {
                    System.out.println("Vigenere. Podaj słowo do zaszyfrowania i klucz");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    key = sc.nextLine();
                    System.out.println("Słowo zaszyfrowane " + vigenere(key, toEnc));
                    break;
                }
                case 8: {
                    System.out.println("Vigenere. Podaj słowo doodszyfrowania ");
                    toEnc = sc.nextLine();
                    toEnc = sc.nextLine();
                    key = sc.nextLine();
                    System.out.println("Słowo odszyfrowane " + vigenereDec(key, toEnc));
                    break;
                }

                default:
                    option = 9;

            }
        }

    }

}
