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
        byte files[] = szyfry.readFromFile("/Users/pawelbialek/Desktop/Kot.jpg");
        BigInteger [] cipheredText = szyfry.cipher(files, n);
        //szyfry.decipher(cipheredText, n , p, q);
        for(int i=0; i<cipheredText.length; i++) System.out.println(cipheredText[i]);

        szyfry.decipher(cipheredText, n, p, q);
        byte[] uno;
        byte[] dos;
        byte[] tres;
        byte[] quatro;
        BigInteger[] correct = new BigInteger[szyfry.x1.length];
        BigInteger quaterthousand = BigInteger.valueOf(256);
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

        byte[] prawda = new byte[files.length];

        for(int i = 0; i < szyfry.x1.length; i++){          // Prawdopodobnie ten for nie dziala
            short pom = szyfry.x1[i].shortValue();            // ma on za zadanie podzielic naszego longa na dwa inty i sprawdzic
            byte dwa = (byte) (pom & 0xFF);           // który z 4 znków jest poprawny ( a będzie to ten w którym jeden
            byte jeden = (byte) ((pom>>8) & 0xFF);       // int bedzie samymi zerami tak jak przy szyfrowaniu ustalilismy)
            short pom1 = szyfry.x2[i].shortValue();
            byte dwa1 = (byte) (pom1 & 0xFF);
            byte jeden1 = (byte) ((pom1>>8) & 0xFF);
            short pom2 = szyfry.x3[i].shortValue();
            byte dwa2 = (byte) (pom2 & 0xFF);
            byte jeden2 = (byte) ((pom2>>8) & 0xFF);
            short pom3 = szyfry.x4[i].shortValue();
            byte dwa3 = (byte) (pom3 & 0xFF);
            byte jeden3 = (byte) ((pom3>>8) & 0xFF);
            if(jeden == 0x00) {
                char a = (char) dwa;
                prawda[i] = dwa;
                System.out.print(a);
            } else if (jeden1 == 0x00) {
                char b = (char) dwa1;
                prawda[i] = dwa1;
                System.out.print(b);
            } else if (jeden2 == 0x00) {
                //byte[] c = szyfry.intToByteArray(dwa2);
                char c = (char) dwa2;
                prawda[i] = dwa2;
                System.out.print(c);
            } else if (jeden3 == 0x00) {
                //byte[] d = szyfry.intToByteArray(dwa3);
                char d =(char) dwa3;
                prawda[i] = dwa3;
                System.out.print(d);
            }
        }








        for(int i=0; i<szyfry.x4.length; i++){
            if(szyfry.x1[i].compareTo(quaterthousand)==-1) correct[i]=szyfry.x1[i];
            else if(szyfry.x2[i].compareTo(quaterthousand)==-1) correct[i]=szyfry.x2[i];
            else if(szyfry.x3[i].compareTo(quaterthousand)==-1) correct[i]=szyfry.x3[i];
            else if(szyfry.x4[i].compareTo(quaterthousand)==-1) correct[i]=szyfry.x4[i];
        }
        //byte[] prawda = szyfry.convert(correct);
        szyfry.saveToFile(prawda, "/Users/pawelbialek/Desktop/Kot123.jpg");
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
