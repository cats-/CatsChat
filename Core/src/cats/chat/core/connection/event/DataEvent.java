package cats.chat.core.connection.event;

import cats.chat.core.connection.Connection;
import cats.chat.core.connection.data.Data;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 4:24 PM
 */
public class DataEvent extends ConnectionEvent{

    private final Data data;

    public DataEvent(final Connection connection, final Data data){
        super(connection);
        this.data = data;
    }

    public Data data(){
        return data;
    }

    public boolean reply(final Data data){
        return connection().send(data);
    }
}
