package TCP;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ThreadSevidorAdivina implements Runnable {
    /* Thread que gestiona la comunicació de SrvTcPAdivina.java i un cllient ClientTcpAdivina.java */

    Socket clientSocket = null;
    ObjectInputStream in;
    ObjectOutputStream out;
    List<Integer> msgEntrant, msgSortint;
    boolean acabat;

    public ThreadSevidorAdivina(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        acabat = false;
        in = new ObjectInputStream(clientSocket.getInputStream());
        out= new ObjectOutputStream(clientSocket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while(!acabat) {

                msgSortint = generaResposta(msgEntrant);

                out.writeObject(msgSortint);
                out.flush();
            }
        }catch(IOException e){
            System.out.println(e.getLocalizedMessage());
        }
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Integer> generaResposta(List<Integer> en) {
        List<Integer> listaOrdenada;
        Collections.sort(en);
        listaOrdenada = en;
        return listaOrdenada;
    }

}
