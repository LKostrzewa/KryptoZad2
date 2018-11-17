import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Rabin szyfry = new Rabin();
        BigInteger p = szyfry.getP();
        BigInteger q = szyfry.getP();
        BigInteger n = szyfry.getN(p , q);
        /*System.out.println(p);
        System.out.println(q);
        System.out.println(n);
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj tekst do rozszyfrowania : ");
        String tekst = input.nextLine();*/
        byte files[] = szyfry.readFromFile("C:\\Users\\Łukasz\\Desktop\\obrazek.png");
        BigInteger [] cipheredText = szyfry.cipher(files, n);
        //szyfry.decipher(cipheredText, n , p, q);
        for(int i=0; i<cipheredText.length; i++) System.out.println(cipheredText[i]);

        szyfry.decipher(cipheredText, n, p, q);
        byte[] uno;
        byte[] dos;
        byte[] tres;
        byte[] quatro;
        BigInteger[] correct = new BigInteger[szyfry.x1.length];
        BigInteger thousand = BigInteger.valueOf(1000);
        for(int i=0; i<szyfry.x1.length; i++){
            System.out.print("Wartosc x1 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x1[i]);
        }
        for(int i=0; i<szyfry.x2.length; i++){
            System.out.print("Wartosc x2 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x2[i]);
        }
        for(int i=0; i<szyfry.x3.length; i++){
            System.out.print("Wartosc x3 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x3[i]);
        }
        for(int i=0; i<szyfry.x4.length; i++){
            System.out.print("Wartosc x4 jako bigint : ");
            System.out.println(i);
            System.out.println(szyfry.x4[i]);
        }
        for(int i=0; i<szyfry.x4.length; i++){
            if(szyfry.x1[i].compareTo(thousand)==-1) correct[i]=szyfry.x1[i];
            else if(szyfry.x2[i].compareTo(thousand)==-1) correct[i]=szyfry.x2[i];
            else if(szyfry.x3[i].compareTo(thousand)==-1) correct[i]=szyfry.x3[i];
            else if(szyfry.x4[i].compareTo(thousand)==-1) correct[i]=szyfry.x4[i];
        }
        byte[] prawda = szyfry.convert(correct);
        szyfry.saveToFile(prawda, "C:\\Users\\Łukasz\\Desktop\\Odszyfrowany.png");
        /*uno=szyfry.convert(szyfry.x1);
        dos=szyfry.convert(szyfry.x2);
        tres=szyfry.convert(szyfry.x3);
        quatro=szyfry.convert(szyfry.x4);
        String raz = new String(uno);
        String dwa = new String(dos);
        String trzy = new String(tres);
        String cztery = new String(quatro);
        String dobrze = new String(prawda);
        System.out.println("rozwiazania");
        System.out.println(raz);
        System.out.println(dwa);
        System.out.println(trzy);
        System.out.println(cztery);
        System.out.println(dobrze);*/
    }
}
