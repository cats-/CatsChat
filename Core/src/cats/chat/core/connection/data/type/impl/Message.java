package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.TransportData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 2:09 AM
 */
public class Message extends TransportData implements Serializable{

    private final String message;

    public Message(final String from, final String to, final String message){
        super(MESSAGE, from, to);
        this.message = message;
    }

    public String message(){
        return message;
    }

    public String other(final String user){
        return user.equalsIgnoreCase(to()) ? from() : to();
    }
}
