package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.SingleValueData;
import cats.chat.core.profile.SafeProfile;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 23/07/13
 * 11:19 AM
 */
public class AddProfileData extends SingleValueData<SafeProfile> implements Serializable {

    public AddProfileData(final SafeProfile profile){
        super(ADD_PROFILE, profile);
    }

    public SafeProfile profile(){
        return value();
    }
}
