package cats.chat.core.connection.data.type.impl;

import cats.chat.core.connection.data.type.TripleValueData;
import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 5:18 PM
 */
public class RegisterData extends TripleValueData<String, String, String> implements Serializable{

    public RegisterData(final String user, final String pass, final String name){
        super(REGISTER, user, pass, name);
    }

    public String user(){
        return first();
    }

    public String pass(){
        return second();
    }

    public String name(){
        return third();
    }
}
