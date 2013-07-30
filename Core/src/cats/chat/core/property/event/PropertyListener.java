package cats.chat.core.property.event;

import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:37 AM
 */
public interface PropertyListener<T> extends Serializable{

    public void onChange(final T o, final T n);
}
