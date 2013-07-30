package cats.chat.client.handler;

import cats.chat.core.connection.data.Data;
import cats.chat.core.connection.event.DataEvent;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 9:07 PM
 */
public abstract class AbstractHandler<T extends Data>{

    public abstract void handle(final DataEvent e, final T data);
}
