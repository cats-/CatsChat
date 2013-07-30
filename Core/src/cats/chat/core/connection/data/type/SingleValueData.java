package cats.chat.core.connection.data.type;

import cats.chat.core.connection.data.Data;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 2:23 AM
 */
public class SingleValueData<T> extends Data implements Serializable {

    private final T value;

    public SingleValueData(final byte opcode, final T value){
        super(opcode);
        this.value = value;
    }

    public T value(){
        return value;
    }
}
