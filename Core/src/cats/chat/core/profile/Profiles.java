package cats.chat.core.profile;

import cats.chat.core.connection.Connection;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 9:20 AM
 */
public class Profiles<T extends SafeProfile> extends LinkedList<T> implements Serializable{

    public T profile(final String user){
        return stream().filter(u -> u.equals(user)).findFirst().orElse(null);
    }

    public Profile profile(final Connection connection){
        return (Profile) stream().filter(u -> u instanceof Profile && ((Profile)u).connection != null && ((Profile)u).connection.equals(connection)).findFirst().orElse(null);
    }

}
