package cats.chat.core.connection.data.type;

import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:19 PM
 */
public class TripleValueData<F, S, T> extends DoubleValueData<F, S> implements Serializable{

    private final T third;

    public TripleValueData(final byte opcode, final F first, final S second, final T third){
        super(opcode, first, second);
        this.third = third;
    }

    public T third(){
        return third;
    }
}
