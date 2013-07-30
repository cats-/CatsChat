package cats.chat.server.handler;

import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.data.Opcodes;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:31 AM
 */
public abstract class AbstractHandler<T extends Data> implements Opcodes{

    public abstract void handle(final DataEvent e, final T data);
}
