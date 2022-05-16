package p2p;

import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PeerThread extends Thread
{
	private static final Logger LOGGER = LoggerFactory.getLogger(PeerThread.class);
    private Socket socket;
    PeerReader peerReader;
    PeerWriter peerWriter;
    
    
    public PeerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
    	LOGGER.info("Got connection from " + socket.getInetAddress() + ".");
        peerReader = new PeerReader(socket);
        peerReader.start();
        peerWriter = new PeerWriter(socket);
        peerWriter.start();
    }

    public void send(String data)
    {
        if (peerWriter == null)
        {
        	LOGGER.error("Couldn't send " + data + " when outputThread is null");
        }
        else
        {
            peerWriter.write(data);
        }
    }
}