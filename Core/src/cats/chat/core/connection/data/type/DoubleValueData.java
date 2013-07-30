package cats.chat.core.connection.data.type;

import cats.chat.core.connection.data.Data;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:03 PM
 */
public class DoubleValueData<F, S> extends Data implements Serializable{

    private final F first;
    private final S second;

    public DoubleValueData(final byte opcode, final F first, final S second){
        super(opcode);
        this.first = first;
        this.second = second;
    }

    public F first(){
        return first;
    }

    public S second(){
        return second;
    }
}
