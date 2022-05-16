package p2p;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PeerReader extends Thread {
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerReader.class);
	
    private Socket socket;

    private ArrayList<String> receivedData = new ArrayList<String>();

  
    public PeerReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String input;
            while ((input = in.readLine()) != null) {
                receivedData.add(input);
            }
        } catch (Exception e) {
        	LOGGER.info("Peer " + socket.getInetAddress() + " disconnected.",e);
        }
    }

    
    public List<String> readData() {
        ArrayList<String> inputBuffer = new ArrayList<String>(receivedData);
        receivedData.clear(); //clear 'buffer'
        return inputBuffer;
    }
}