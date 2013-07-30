package cats.chat.core.connection.data;

import cats.chat.core.Constants;
import java.io.Serializable;
import java.util.Date;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:23 AM
 */
public class Data implements Serializable, Constants, Opcodes{

    protected final byte opcode;
    protected final Object[] values;

    private final Date time;

    public boolean sent;

    protected Data(final byte opcode, final Object... values){
        this.opcode = opcode;
        this.values = values;

        sent = false;

        time = new Date(System.currentTimeMillis());
    }

    public Date time(){
        return time;
    }

    public String timestamp(){
        return String.format("[%s]", TIME_FORMAT.format(time));
    }

    public byte opcode(){
        return opcode;
    }

    protected <T> T to(final Class<T> clazz, final int i){
        return clazz.cast(values[i]);
    }

    protected Byte toByte(final int i){
        return to(Byte.class, i);
    }

    protected Integer toInt(final int i){
        return to(Integer.class, i);
    }

    protected String toString(final int i){
        return to(String.class, i);
    }
}
