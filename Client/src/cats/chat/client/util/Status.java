package cats.chat.client.util;

import cats.chat.core.connection.data.Opcodes;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:23 PM
 */
public enum Status implements Opcodes, Constants{

    ONLINE(STATUS_ONLINE, "Online", Color.GREEN),
    BUSY(STATUS_BUSY, "Busy", Color.RED),
    AWAY(STATUS_AWAY, "Away", Color.ORANGE),
    OFFLINE(STATUS_OFFLINE, "Offline", Color.GRAY);

    private final byte opcode;
    private final String title;
    private final Color color;
    private final ImageIcon icon;

    private Status(final byte opcode, final String title, final Color color){
        this.opcode = opcode;
        this.title = title;
        this.color = color;

        icon = Utils.createIcon(color, STATUS_SIZE);
    }

    public byte opcode(){
        return opcode;
    }

    public String title(){
        return title;
    }

    public Color color(){
        return color;
    }

    public ImageIcon icon(){
        return icon;
    }

    public static Status getByOpcode(final byte opcode){
        for(final Status s : values())
            if(s.opcode == opcode)
                return s;
        return null;
    }
}
