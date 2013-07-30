package cats.chat.core.connection.data;

import java.io.Serializable;

/**
 * CatsChat
 * Josh
 * 22/07/13
 * 1:12 AM
 */
public interface Opcodes extends Serializable{

    byte MESSAGE = 0x0;

    byte CHANGE_STATUS = 0x1;
    byte STATUS_ONLINE = 0x0;
    byte STATUS_BUSY = 0x1;
    byte STATUS_AWAY = 0x2;
    byte STATUS_OFFLINE = 0x3;

    byte CHANGE_NAME = 0x4;
    byte CHANGE_PIC = 0x5;
    byte CHANGE_MOOD = 0x6;

    byte POPUP_MESSAGE = 0x7;

    byte ADD_USER = 0x8;
    byte REMOVE_USER = 0x9;

    byte REGISTER = 0x10;
    byte LOGIN = 0x11;

    byte ASSIGN_PROFILE = 0x12;
    byte ADD_PROFILE = 0x13;
    byte REMOVE_PROFILE = 0x14;
}
