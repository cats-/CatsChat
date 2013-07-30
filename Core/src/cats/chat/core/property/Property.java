package cats.chat.core.property;

import cats.chat.core.property.event.PropertyListener;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:36 AM
 */
public class Property<T> implements Serializable {

    protected T value;

    protected final List<PropertyListener<T>> listeners;

    public Property(final T value){
        this.value = value;

        listeners = new LinkedList<>();
    }

    public Property(){
        this(null);
    }

    public void addListener(final PropertyListener<T> listener){
        listeners.add(listener);
    }

    public T get(){
        return value;
    }

    protected void fireChange(final T o, final T n){
        listeners.forEach(l -> l.onChange(o, n));
    }

    public void set(final T value){
        final T old = this.value;
        this.value = value;
        fireChange(old, value);
    }

    public void privateSet(final T value){
        this.value = value;
    }
}
