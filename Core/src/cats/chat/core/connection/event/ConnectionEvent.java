package cats.chat.core.connection.event;

import cats.chat.core.connection.Connection;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 4:23 PM
 */
public class ConnectionEvent {

    private final Connection connection;

    public ConnectionEvent(final Connection connection){
        this.connection = connection;
    }

    public Connection connection(){
        return connection;
    }
}
