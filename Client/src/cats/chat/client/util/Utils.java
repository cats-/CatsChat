package cats.chat.client.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.image.BufferedImage;
import javax.swing.Box;
import javax.swing.ImageIcon;

/**
 * CatsChat
 * Josh
 * 25/07/13
 * 8:21 PM
 */
public final class Utils {

    private Utils(){}

    public static ImageIcon resize(final ImageIcon icon, final Dimension size){
        final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = image.createGraphics();
        g.drawImage(icon.getImage(), 0, 0, size.width, size.height, null);
        g.dispose();
        return new ImageIcon(image);
    }

    public static Component space(final Dimension size){
        return Box.createRigidArea(size);
    }

    public static ImageIcon createIcon(final Color color, final Dimension size){
        final Paint paint = new GradientPaint(0, 0, color, size.width, size.height, color.darker());
        final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = image.createGraphics();
        g.setPaint(paint);
        g.fillRect(0, 0, size.width, size.height);
        g.dispose();
        return new ImageIcon(image);
    }
}
