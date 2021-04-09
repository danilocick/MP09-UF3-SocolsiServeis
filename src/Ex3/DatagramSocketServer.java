package Ex3;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class DatagramSocketServer {
    DatagramSocket socket;

    //mi ip 192.168.22.102;
    //puerto 5555;

    public static void main(String[] args) throws IOException {
        DatagramSocketServer datagramSocketServer = new DatagramSocketServer();
        datagramSocketServer.init(5555);
        datagramSocketServer.runServer();
    }

    public void init(int port) throws SocketException {
        socket = new DatagramSocket(port);
    }

    public void runServer() throws IOException {
        byte [] receivingData = new byte[1024];
        byte [] sendingData;
        InetAddress clientIP;
        int clientPort;

        //el servidor atén el port indefinidament
        while(true){
            //creació del paquet per rebre les dades
            DatagramPacket packet = new DatagramPacket(receivingData, 1024);
            //espera de les dades
            socket.receive(packet);
            //processament de les dades rebudes i obtenció de la resposta
            sendingData = processData(packet.getData(), packet.getLength());
            //obtenció de l'adreça del client
            clientIP = packet.getAddress();
            //obtenció del port del client
            clientPort = packet.getPort();
            //creació del paquet per enviar la resposta
            packet = new DatagramPacket(sendingData, sendingData.length,
                    clientIP, clientPort);
            //enviament de la resposta
            socket.send(packet);
        }
    }

    private byte[] processData(byte[] data, int length) {
        String mssg = new String(data, 0, length);

        System.out.println(mssg);

        if (mssg.equals("huevo")){
            return "Acertaste hijo de la gran puta, voy a buscar otra".getBytes(StandardCharsets.UTF_8);
        }else if (mssg.equals("repetir") || mssg.equals("Hola")){
            return "Vengo de padres cantores, pero yo no soy cantor. Llevo la ropa blanca y amarillo el corazón. ¿Quién soy?".getBytes(StandardCharsets.UTF_8);
        }else {
            return "fallaste boludo".getBytes(StandardCharsets.UTF_8);
        }
    }
}
