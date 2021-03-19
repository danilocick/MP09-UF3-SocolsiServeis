package Exercici1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {

    /*
    * Java proporciona varios contructores para crear objetos URL, veamos alguno de ellos:
    *
    * public URL(String spec)
    * Por ejemplo: URL direccion = new URL("http://www.it.uc3m.es");
    *
    * public URL(String protocol, String host, String file)
    * Por ejemplo: URL direccion = new URL("http","www.it.uc3m.es","index.html");
    *
    * public URL(String protocol, String host, int port, String file)
    * Por ejemplo: URL direccion = new URL("http","www.it.uc3m.es", 80, "index.html");
    *
    * */

    public static void main(String[] args) {
        URL url = null;
        try {


            Scanner sc = new Scanner(System.in);
            System.out.println("Insert url");
            String s = sc.nextLine();

            System.out.println("Insert tag");
            String mi = sc.nextLine();

            url = new URL(s);
            BufferedReader in = new BufferedReader(
            new InputStreamReader(url.openStream()));

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                if (inputLine.contains("<"+mi+">")){
                    System.out.println(inputLine);
                }
            in.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
