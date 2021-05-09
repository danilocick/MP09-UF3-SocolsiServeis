package Exercicis_TCP_UCP_multicast.Exercici_UDP_multicast;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ClientVelocimetre {
    MulticastSocket socket;
    InetAddress multicastIP;
    int port;
    List<Integer> list = new ArrayList<>();

    public void init(String strIp, int portValue) throws SocketException,IOException {
        multicastIP = InetAddress.getByName(strIp);
        port = portValue;
        socket = new MulticastSocket(port);
    }

    public void runClient() throws IOException{
        DatagramPacket packet;
        byte [] receivedData = new byte[1024];
        boolean continueRunning = true;

        //activem la subscripció
        socket.joinGroup(multicastIP);

        //el client atén el port fins que decideix finalitzar
        while(continueRunning){
            //creació del paquet per rebre les dades
            packet = new DatagramPacket(receivedData, 1024);
            //espera de les dades, màxim 5 segons
            socket.setSoTimeout(5000);
            try{
                //espera de les dades
                socket.receive(packet);
                //processament de les dades rebudes i obtenció de la resposta
                getData(packet.getData(), packet.getLength());
            }catch(SocketTimeoutException e){
                e.printStackTrace();
            }
        }

        //es cancel·la la subscripció
        socket.leaveGroup(multicastIP);
    }

    private void getData(byte[] data, int length) {
        int s = ByteBuffer.wrap(data).getInt();

        list.add(s);
        if (list.size() == 5){
            int m = list.get(0) +list.get(1) +list.get(2) +list.get(3) +list.get(4) / 5;
            System.out.println("la mitjana es: " + m);
            list.clear();
        }
    }

    public static void main(String[] args) {
        ClientVelocimetre c = new ClientVelocimetre();
        try {
            c.init("224.0.10.54",5557);
            c.runClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
