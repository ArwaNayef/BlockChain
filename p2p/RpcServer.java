package p2p;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RpcServer extends Thread
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RpcServer.class);
    private int port;
    private boolean runFlag = true;

    List<RpcThread> rpcThreads;

  
    public RpcServer()
    {
        this.port = 8016;
        this.rpcThreads = new ArrayList<RpcThread>();
    }

   
    public RpcServer(int port)
    {
        this.port = port;
        this.rpcThreads = new ArrayList<RpcThread>();
    }

    @Override
    public void run()
    {
        try
        {
            ServerSocket socket = new ServerSocket(port);
            while (runFlag)
            {
            	RpcThread thread = new RpcThread(socket.accept());
                rpcThreads.add(thread);
                thread.start();
            }
            socket.close();
        } catch (Exception e){
        	LOGGER.error("rpc error in port:" + port,e);
        }
    }
}