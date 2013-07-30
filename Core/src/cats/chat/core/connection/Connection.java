package cats.chat.core.connection;

import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.event.ConnectionEvent;
import cats.chat.core.connection.event.ConnectionListener;
import cats.chat.core.connection.event.DataEvent;
import cats.chat.core.connection.event.DataListener;
import cats.chat.core.connection.event.Listener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:07 AM
 */
public class Connection extends Thread implements Runnable{

    private final Socket socket;
    private final ObjectOutputStream output;
    private final ObjectInputStream input;

    private final List<Listener> listeners;

    public Connection(final Socket socket) throws IOException {
        this.socket = socket;

        listeners = new LinkedList<>();

        output = new ObjectOutputStream(socket.getOutputStream());
        output.flush();

        input = new ObjectInputStream(socket.getInputStream());

        setPriority(MAX_PRIORITY);
        start();
    }

    public void addListener(final Listener l){
        listeners.add(l);
    }

    public void removeListener(final Listener l){
        listeners.remove(l);
    }

    private void fireDataReceived(final Data d){
        listeners.stream().filter(l -> l instanceof DataListener).forEach(l -> ((DataListener)l).onDataReceived(new DataEvent(this, d)));
    }

    private void fireConnectionClosed(){
        listeners.stream().filter(l -> l instanceof ConnectionListener).forEach(l -> ((ConnectionListener)l).onConnectionClosed(new ConnectionEvent(this)));
    }

    public void run(){
        while(socket.isConnected()){
            try{
                final Data d = (Data)input.readObject();
                fireDataReceived(d);
            }catch(Exception ex){
                ex.printStackTrace();
                close();
                break;
            }
        }
        fireConnectionClosed();
    }

    public boolean send(final Data data){
        try{
            output.writeObject(data);
            output.flush();
            return data.sent = true;
        }catch(IOException ex){
            ex.printStackTrace();
            return data.sent = false;
        }
    }

    public boolean close(){
        try{
            output.close();
            input.close();
            socket.close();
        }catch(IOException ex){
            ex.printStackTrace();
        }finally{
            return socket.isClosed();
        }
    }

    public boolean connected(){
        return socket.isConnected();
    }
}

