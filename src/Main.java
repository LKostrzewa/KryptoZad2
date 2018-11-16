import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Rabin szyfry = new Rabin();
        BigInteger p = szyfry.getP();
        BigInteger q = szyfry.getP();
        BigInteger n = szyfry.getN(p , q);
        System.out.println(p);
        System.out.println(q);
        System.out.println(n);
        Scanner input = new Scanner(System.in);
        System.out.println("Podaj tekst do rozszyfrowania : ");
        String tekst = input.nextLine();
        byte [] tym = tekst.getBytes();                 // Zamiana tekstu na tablice byte
        //int[] tym2 = new int[tym.length];

        for(int i=0;i<tym.length; i++){                     // podgladam se tablice
            System.out.println(tym[i]);
        }

        IntBuffer intBuf =                                  // --- od tąd
                ByteBuffer.wrap(tym)
                        .order(ByteOrder.BIG_ENDIAN)
                        .asIntBuffer();
        int[] tym2 = new int[intBuf.remaining()];
        intBuf.get(tym2);                                   // do tąd jest pirdolnik z neta co zamienia to na tablice int
                                                            // to gowno dziala, wczesniej mialem zrobione samemu i tez dzialalo jkbc
        for(int i=0;i<tym2.length; i++){
            System.out.println(tym2[i]);                    // i tu se jom wyswietlam ( dla malych tekstow wysliwetla sie jako jedna liczba)
        }

        /*for(int i=0; i<tym.length; i+=4) {
            System.out.println(tym[i]);
            System.out.println(tym[i+1]);
            System.out.println(tym[i+2]);
            System.out.println(tym[i+3]);
            tym2[i] = tym[i] << 24 | (tym[i+1] & 0xFF) << 16 | (tym[i+2] & 0xFF) << 8 | (tym[i+3] & 0xFF);
            //tym2[i] = (tym[i]<<24) & 0xff000000 | (tym[i+1]<<16) & 0x00ff0000 | (tym[i+2]<<8) & 0x0000ff00 | (tym[i+3]<<0) & 0x000000ff;
            System.out.println(tym2[i] & 0xFFFFFFFF);
//1685418081
        }*/
        BigInteger [] cipheredText = szyfry.cipher(tym2, n);
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

        /*for(int i = 0; i < szyfry.x1.length; i++){
                short pom = szyfry.x1[i].shortValue();
                byte jeden = (byte) (pom & 0xFF);
                byte dwa = (byte) ((pom>>8) & 0xFF);
            short pom1 = szyfry.x2[i].shortValue();
            byte jeden1 = (byte) (pom1 & 0xFF);
            byte dwa1 = (byte) ((pom1>>8) & 0xFF);
            short pom2 = szyfry.x3[i].shortValue();
            byte jeden2 = (byte) (pom2 & 0xFF);
            byte dwa2 = (byte) ((pom2>>8) & 0xFF);
            short pom3 = szyfry.x4[i].shortValue();
            byte jeden3 = (byte) (pom3 & 0xFF);
            byte dwa3 = (byte) ((pom3>>8) & 0xFF);
                if(jeden == 0x00){
                    char a = (char) dwa;
                    //System.out.print(dwa+" ");
                    System.out.print(a+" ");
                }else if(jeden1 == 0x00){
                    char b = (char) dwa1;
                    //System.out.print(dwa1+" ");
                    System.out.print(b+" ");
                }else if(jeden2 == 0x00){
                    char c = (char) dwa2;
                    //System.out.print(dwa2+" ");
                    System.out.print(c+" ");
                }else if(jeden3 == 0x00) {
                    char d = (char) dwa3;
                    //System.out.print(dwa3 + " ");
                    System.out.print(d+ " ");
                }
        }*/


        for(int i = 0; i < szyfry.x1.length; i++){          // Prawdopodobnie ten for nie dziala
            long pom = szyfry.x1[i].longValue();            // ma on za zadanie podzielic naszego longa na dwa inty i sprawdzic
            int jeden = (int) (pom & 0xFFFFFFFF);           // który z 4 znków jest poprawny ( a będzie to ten w którym jeden
            int dwa = (int) ((pom>>32) & 0xFFFFFFFF);       // int bedzie samymi zerami tak jak przy szyfrowaniu ustalilismy)
            long pom1 = szyfry.x2[i].longValue();
            int jeden1 = (int) (pom1 & 0xFFFFFFFF);
            int dwa1 = (int) ((pom1>>32) & 0xFFFFFFFF);
            long pom2 = szyfry.x3[i].longValue();
            int jeden2 = (int) (pom2 & 0xFFFFFFFF);
            int dwa2 = (int) ((pom2>>32) & 0xFFFFFFFF);
            long pom3 = szyfry.x4[i].longValue();
            int jeden3 = (int) (pom3 & 0xFFFFFFFF);
            int dwa3 = (int) ((pom3>>32) & 0xFFFFFFFF);
                if(jeden == 0x00000000) {
                    byte[] a = szyfry.intToByteArray(dwa);
                    String aa = new String(a);
                    System.out.print(aa);
                } else if (jeden1 == 0x00000000) {
                    byte[] b = szyfry.intToByteArray(dwa1);
                    String bb = new String(b);
                    System.out.print(bb);
                } else if (jeden2 == 0x00000000) {
                    byte[] c = szyfry.intToByteArray(dwa2);
                    String cc = new String(c);
                    System.out.print(cc);
                } else if (jeden3 == 0x00000000) {
                    byte[] d = szyfry.intToByteArray(dwa3);
                    String dd = new String(d);
                    System.out.print(dd);
                }
            }
        uno=szyfry.convert(szyfry.x1);
        dos=szyfry.convert(szyfry.x2);
        tres=szyfry.convert(szyfry.x3);
        quatro=szyfry.convert(szyfry.x4);
        System.out.println(uno);
        System.out.println(dos);
        System.out.println(tres);
        System.out.println(quatro);
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
