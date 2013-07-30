package cats.chat.server.util;

import cats.chat.server.Server;
import java.util.HashMap;
import java.util.Map;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 7:57 PM
 */
public final class CredentialsValidator {

    private interface Validator{
        public String validate(final String value, final int length);
    }

    public static final byte NAME = 0x0;
    public static final byte USER = 0x1;
    public static final byte PASS = 0x2;

    private static final Validator NAME_VALIDATOR = (v, l) ->  l >= 1 && l <= 60 ? null : "Name must be between 1 and 60 (inclusive) characters long";
    private static final Validator USER_VALIDATOR = (v, l) -> {
        if(Server.instance().profiles().profile(v) != null)
            return "That user already exists";
        if(l < 4)
            return "User must be atleast 4 characters long";
        for(final char c : v.toCharArray())
            if(!Character.isLetterOrDigit(c))
                return "User cannot contain any symbols";
        return null;
    };
    private static final Validator PASS_VALIDATOR = (v, l) -> {
        if(l < 6)
            return "Pass must be atleast 6 characters long";
        for(final char c : v.toCharArray())
            if(!Character.isLetterOrDigit(c))
                return "Pass cannot contain any symbols";
        return null;
    };

    private static final Map<Byte, Validator> VALIDATORS = new HashMap<>();

    static{
        VALIDATORS.put(NAME, NAME_VALIDATOR);
        VALIDATORS.put(USER, USER_VALIDATOR);
        VALIDATORS.put(PASS, PASS_VALIDATOR);
    }

    private CredentialsValidator(){}

    public static String validate(final byte id, final String string){
        return VALIDATORS.get(id).validate(string, string.length());
    }

}
