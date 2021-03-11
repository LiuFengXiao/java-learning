package bin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;


import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;

public class BluetoothServer implements Runnable {

    private LocalDevice local = null;

    private StreamConnection streamConnection = null;


    private InputStream inputStream;
    private OutputStream outputStream;

    private StreamConnectionNotifier notifier;

    private final static ExecutorService service = Executors.newCachedThreadPool();
    
    public String serverName;
    public String serverUUID;

    private OnServerListener mServerListener;
    
    public interface OnServerListener {
        void onConnected(InputStream inputStream, OutputStream outputStream);
        void onDisconnected();
        void onClose();
    }

    public BluetoothServer(String serverUUID, String serverName) {
        this.serverUUID = serverUUID;
        this.serverName = serverName;
    }
    
    public void start() {
        try {
            local = LocalDevice.getLocalDevice();
            if (!local.setDiscoverable(DiscoveryAgent.GIAC))
                System.out.println("");

            String url = "btspp://localhost:" +  serverUUID;// + ";name="+serverName;  
            notifier = (StreamConnectionNotifier)Connector.open(url);

            service.submit(this);
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
    
    public OnServerListener getServerListener() {
        return mServerListener;
    }

    public void setServerListener(OnServerListener mServerListener) {
        this.mServerListener = mServerListener;
    }

    @Override
    public void run() {
        try {
            streamConnection = notifier.acceptAndOpen();
            inputStream = streamConnection.openInputStream();
            outputStream = streamConnection.openOutputStream();
            
            if (mServerListener != null) {
                mServerListener.onConnected(inputStream, outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }
}