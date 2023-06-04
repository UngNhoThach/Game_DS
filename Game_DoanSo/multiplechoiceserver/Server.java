/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multiplechoiceserver;

import static Utils.Constant.PORT;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    // Server Socket
    static ServerSocket server;

    // Socket for client
    static Socket socket; //at server

    // Id of client
    static int id = 1;

    // A pair that a client belong to
    static Pair pair;
    
    public static Vector<Worker> workers = new Vector<>();

    // Collection of all socket connected to the server
    public static HashMap<Integer, Worker> workerMap = new HashMap();
    
    // Collection of all pair
    public static ArrayList<Pair> pairList = new ArrayList<>();
    
    public Server(int port) {
        int numThread = 10;
        ExecutorService executor = Executors.newFixedThreadPool(numThread);
        executor.execute(new ServerFunction());
        //    createkey(); 
        try {
            server = new ServerSocket(port);
            System.out.println("Server binding at port " + port);
            System.out.println("Waiting for client...");
            pairList.add(pair = new Pair());
            while (true) {
                socket = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                //ObjectOutput obOut = new ObjectOutputStream(socket.getOutputStream());
                //Worker client = new Worker(socket);
                Worker client = new Worker(id, socket, in, out);
                workerMap.put(id, client);
                workers.add(client);
                executor.execute(client);
                id++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    // join a worker to a pair when start a new  game
    public static void JoinAPair(Worker w) {
        for (Pair p : pairList) {
            if (!p.isFull()) {
                pair.AddClient(w);
                if (p.isFull()) {
                    p.setIsFull(true);
                }
                w.setPair(pair);
                return;
            }
        }
        pair = new Pair();
        pair.AddClient(w);
        w.setPair(pair);
        pairList.add(pair);
    }
    
    public static void main(String[] args) throws IOException {
        Server sv = new Server(PORT);
    }
    
}
