package cats.chat.core.connection.data.type;

import cats.chat.core.connection.data.Data;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 2:19 AM
 */
public class TransportData extends Data implements Serializable{

    public TransportData(final byte opcode, final String from, final String to){
        super(opcode, from, to);
    }

    public String from(){
        return toString(0);
    }

    public String to(){
        return toString(1);
    }
}
