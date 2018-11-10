import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Rabin szyfry = new Rabin();
        int p = szyfry.getP();
        int q = szyfry.getP();
        BigInteger n = szyfry.getN(p , q);
        System.out.println(p);
        System.out.println(q);
        System.out.println(n);
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj tekst do rozszyfrowania : ");
        String tekst = input.nextLine();
        BigInteger [] cipheredText = szyfry.cipher(tekst.getBytes(), n);
        //szyfry.decipher(cipheredText, n , p, q);
        for(int i=0; i<cipheredText.length; i++) System.out.println(cipheredText[i]);

        szyfry.decipher(cipheredText, n, p, q);
        byte[] uno;
        byte[] dos;
        byte[] tres;
        byte[] quatro;
        for(int i=0; i<szyfry.x1.length; i++){
            System.out.print("Wartosc x1 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x1[i]);
        }
        for(int i=0; i<szyfry.x3.length; i++){
            System.out.print("Wartosc x3 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x3[i]);
        }
        uno=szyfry.convert(szyfry.x1);
        dos=szyfry.convert(szyfry.x2);
        tres=szyfry.convert(szyfry.x3);
        quatro=szyfry.convert(szyfry.x4);
        String raz = new String(uno);
        String dwa = new String(dos);
        String trzy = new String(tres);
        String cztery = new String(quatro);
        System.out.println("rozwiazania");
        System.out.println(raz);
        System.out.println(dwa);
        System.out.println(trzy);
        System.out.println(cztery);
    }
}
