import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.math.BigInteger;


///maina jeszcze nie ruszyłem - uzupełnie go adekwatnie do funkcji stąd
///dodam jeszcze jedno okno do wyświetlania klucza publicznego
///nie rozkminiłem nadal czy konwertowanie wszystkiego z tab byte na jakieś
///łańcuchy nie byłoby dobrym posunięciem
///chyba że ogarniemy jak wybierać który z 4 pierwiastków jest poprawny 

public class Rabin {

    BigInteger x1[], x2[], x3[], x4[];

    ///losowanie klucza prywatnego P,Q=3(mod 4)
    public int getP(){
        Random rand = new Random();
        int p=rand.nextInt(700)*4+3;
        return p;
    }
    ///wyznaczanie klucza publicznego N=P*Q
    public BigInteger getN(int p, int q){
        BigInteger tmp1, tmp2, n;
        tmp1=BigInteger.valueOf(p);
        tmp2=BigInteger.valueOf(q);
        n=tmp1.multiply(tmp2);
        return n;
    }
    ///szyfrowanie --> C=P^2(mod N)
    public BigInteger[] cipher(byte[] plain, BigInteger n){
        BigInteger[] ciphered = new BigInteger[plain.length];
        BigInteger temp;
        for(int i=0 ;i<plain.length ;i++)
        {
            int tmp;
            tmp=(plain[i]*plain[i]);
            temp=BigInteger.valueOf(tmp);
            System.out.print("zkwadratowany element tekstu jawnego : ");
            System.out.println(temp.mod(n));
            ciphered[i]=temp.mod(n);
        }
        return ciphered;
    }
    ///Algorytm euklidesa odnajdywania NWD
    ///wykorzystywany przy odszyfrowywaniu
    public int[] gcd(int a, int b) {
        //if (a == 0)
            //return b;

        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }
        int tab[]=new int[2];
        tab[0]=a;
        tab[1]=b;
        return tab;
    }
///ta kurwa jeszcze wymaga troche pracy
///te inty na koniec przekonwertuje do byte - przy obliczeniach na bajtach wypierdalało mi błąd
    public void decipher(BigInteger[] ciphered, BigInteger n, int p, int q){

        //byte[] deciphered = new byte[ciphered.length];
        BigInteger mp1[]=new BigInteger[ciphered.length];
        BigInteger mp2[]=new BigInteger[ciphered.length];
        BigInteger mq1[]=new BigInteger[ciphered.length];
        BigInteger mq2[]=new BigInteger[ciphered.length];
        BigInteger yp[]=new BigInteger[ciphered.length];
        BigInteger yq[]=new BigInteger[ciphered.length];
        BigInteger pom, pom2;
        int tmp[]=new int[2];
        ///4 pierwiastki kwadratowe z c(mod n)
        ///tylko jeden z nich zawiera zaszyfrowaną wiadomość
        BigInteger x1[]=new BigInteger[ciphered.length];
        BigInteger x2[]=new BigInteger[ciphered.length];
        BigInteger x3[]=new BigInteger[ciphered.length];
        BigInteger x4[]=new BigInteger[ciphered.length];

        for(int i=0 ;i<ciphered.length ;i++)
        {
///zabawa zaczyna się od obliczenia pierwiastków kwadratowych liczby c(mod p) i c(mod q)
///tzn mp=c^((p+1)/4)(mod p) i mq=c^((q+1)/4)(mod q)
            pom=ciphered[i].pow((p+1)/4);
            mp1[i]=pom.mod(BigInteger.valueOf(p));
            mp2[i]=BigInteger.valueOf(p).subtract(mp1[i]);
            pom2=ciphered[i].pow((q+1)/4);
            mq1[i]=pom2.mod(BigInteger.valueOf(q));
            mq2[i]=BigInteger.valueOf(q).subtract(mq1[i]);
///wykorzystujemy algo euklidesa żeby wyznaczyć liczby spełniające warunek yp*p+yq*q=1
            tmp=gcd(p,q);
            yp[i]=BigInteger.valueOf(tmp[0]);
            yq[i]=BigInteger.valueOf(tmp[1]);

            pom=yp[i].multiply(mq1[i]);
            pom2=yq[i].multiply(mp1[i]);
            x1[i]=pom.add(pom2).mod(n);

            pom=yp[i].multiply(mq2[i]);
            pom2=yq[i].multiply(mp1[i]);
            x2[i]=pom.add(pom2).mod(n);

            pom=yp[i].multiply(mq1[i]);
            pom2=yq[i].multiply(mp2[i]);
            x3[i]=pom.add(pom2).mod(n);

            pom=yp[i].multiply(mq2[i]);
            pom2=yq[i].multiply(mp2[i]);
            x4[i]=pom.add(pom2).mod(n);

        }
        this.x1=x1;
        this.x2=x2;
        this.x3=x3;
        this.x4=x4;
    }


    public byte[] convert(BigInteger x[]){
        byte[] converted = new byte[x.length];
        for(int i=0; i<x.length; i++){
            converted[i] = x[i].byteValue();
        }
        return converted;
    }


    public void saveToFile(byte[] cipheredText, String filePath, String filetoPath){
        Path path = Paths.get(filetoPath);
        try{
            Files.write(path, cipheredText);
        }
        catch (IOException e) {
            System.out.println("Exception Occurred:");
        }
    }

    public byte[] readFromFile(String filePath){
        File plik = new File(filePath);
        byte[] fileContent = new byte[(int) plik.length()];
        FileInputStream fin = null;
        try{
            fin = new FileInputStream(plik);
            fin.read(fileContent);
        }
        catch (Exception ae){
            System.out.println("Blad " + ae);
        }
        try {
            fin.close();
        }
        catch (Exception ea){
            System.out.println("Blad " + ea);
        }
        return fileContent;
    }

}
