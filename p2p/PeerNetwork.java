package p2p;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PeerNetwork extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerNetwork.class);
	
    private int port;
    private boolean runFlag = true;
    
    List<PeerThread> peerThreads;
    List<String> peers;

    
    public PeerNetwork() {
        this.port = 8015;
        this.peerThreads = new ArrayList<PeerThread>();
        this.peers = new ArrayList<String>();
    }
   
    public PeerNetwork(int port) {
    	this.port = port;
    	this.peerThreads = new ArrayList<PeerThread>();
    	this.peers = new ArrayList<String>();
    }

   
    public void connect(String host, int port){
    	Socket socket =null;
    	try {
    		socket = new Socket();
    		socket.connect(new InetSocketAddress(host,port),10000); 
//    		socket.setSoTimeout(10000);
			String remoteHost = socket.getInetAddress().getHostAddress();
			int remotePort = socket.getPort();
			LOGGER.info("socket " + remoteHost + ":" + remotePort + " connected.");
			peers.add(remoteHost + ":" + remotePort);
			PeerThread pt = new PeerThread(socket);
			peerThreads.add(pt);
			pt.start();
		} catch (IOException e) {
			LOGGER.warn("socket " + host +":"+port+ " can't connect.",e);
		}
    }

    @Override
    public void run() {
        try {
            ServerSocket listenSocket = new ServerSocket(port);
            while (runFlag) 
            {
            	PeerThread peerThread = new PeerThread(listenSocket.accept());
                peerThreads.add(peerThread);
                peerThread.start();
            }
            listenSocket.close();
        } catch (Exception e) {
           LOGGER.error("{}",e);
        }
    }

    public void broadcast(String data) {
        for (PeerThread pt: peerThreads) {
        	LOGGER.info("Sent:: " + data);
            if( pt!=null){
            	pt.send(data);
            }
        }
    }
}