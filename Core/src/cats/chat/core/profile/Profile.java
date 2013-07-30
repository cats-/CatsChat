package cats.chat.core.profile;

import cats.chat.core.connection.Connection;
import cats.chat.core.connection.data.Data;
import cats.chat.core.property.Property;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:19 AM
 */
public class Profile extends SafeProfile implements Serializable{

    public transient Connection connection;

    private final Property<String> pass;
    private final Profiles<SafeProfile> friends;
    private final LinkedList<Data> queue;

    public Profile(){
        pass = new Property<>();
        friends = new Profiles<>();
        queue = new LinkedList<>();
    }

    public SafeProfile createSafe(){
        final SafeProfile safe = new SafeProfile();
        safe.privateUpdate(this);
        safe.convoMap.clear();
        return safe;
    }

    public Property<String> pass(){
        return pass;
    }

    public Profiles<SafeProfile> friends(){
        return friends;
    }

    public LinkedList<Data> queue(){
        return queue;
    }
}
