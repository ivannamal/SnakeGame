import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

public class ShopMenu {
	public static boolean choco_choosed = false;
	public static boolean mud_choosed = false;
	public static boolean lava_choosed = false;
	public static boolean madness_choosed = false;
	public static boolean inferno_choosed = false;
	public static boolean nano_choosed = false;
    private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private Image image5;
    private Image image6;
    private Image arrow;
    private RoundRectangle2D skin1 = new RoundRectangle2D.Double(75, 200, 250, 150, 40, 40);
    private RoundRectangle2D skin2 = new RoundRectangle2D.Double(375, 200, 250, 150, 40, 40);
    private RoundRectangle2D skin3 = new RoundRectangle2D.Double(675, 200, 250, 150, 40, 40);
    private RoundRectangle2D skin4 = new RoundRectangle2D.Double(75, 450, 250, 150, 40, 40);
    private RoundRectangle2D skin5 = new RoundRectangle2D.Double(375, 450, 250, 150, 40, 40);
    private RoundRectangle2D skin6 = new RoundRectangle2D.Double(675, 450, 250, 150, 40, 40);

    public ShopMenu() {
        // Load the image for the button background
    	arrow = Toolkit.getDefaultToolkit().getImage("arrow.png");
        image1 = Toolkit.getDefaultToolkit().getImage("choco_template.jpg");
        image2 = Toolkit.getDefaultToolkit().getImage("mud_template.png");
        image3 = Toolkit.getDefaultToolkit().getImage("lava_template.png"); 
        image4 = Toolkit.getDefaultToolkit().getImage("space_template.png"); 
        image5 = Toolkit.getDefaultToolkit().getImage("inf_template.jpg"); 
        image6 = Toolkit.getDefaultToolkit().getImage("nano_template.png"); 
        
    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(arrow, 25, 25, 80, 50, null);
        // Set the clipping area to a rounded rectangle for each skin
        g2d.setClip(skin1);
        g.drawImage(image1, (int) skin1.getX(), (int) skin1.getY(), (int) skin1.getWidth(), (int) skin1.getHeight(), null);
        g2d.setClip(null);

        g2d.setClip(skin2);
        g.drawImage(image2, (int) skin2.getX(), (int) skin2.getY(), (int) skin2.getWidth(), (int) skin2.getHeight(), null);
        g2d.setClip(null);

        g2d.setClip(skin3);
        g.drawImage(image3, (int) skin3.getX(), (int) skin3.getY(), (int) skin3.getWidth(), (int) skin3.getHeight(), null);
        g2d.setClip(null);

        g2d.setClip(skin4);
        g.drawImage(image4, (int) skin4.getX(), (int) skin4.getY(), (int) skin4.getWidth(), (int) skin4.getHeight(), null);
        g2d.setClip(null);

        g2d.setClip(skin5);
        g.drawImage(image5, (int) skin5.getX(), (int) skin5.getY(), (int) skin5.getWidth(), (int) skin5.getHeight(), null);
        g2d.setClip(null);

        g2d.setClip(skin6);
        g.drawImage(image6, (int) skin6.getX(), (int) skin6.getY(), (int) skin6.getWidth(), (int) skin6.getHeight(), null);
        g2d.setClip(null);

        Font fnt0 = new Font("Arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SKINS", 430, 100);
        Font fnt1 = new Font("Arial", Font.BOLD, 30);
        g.setFont(fnt1);
        drawStr(skin1,"CHOCO",choco_choosed,g);
        drawStr(skin2,"MUD",mud_choosed,g);
        drawStr(skin3,"LAVA",lava_choosed,g);
        drawStr(skin4,"MADNESS",madness_choosed,g);
        drawStr(skin5,"INFERNO",inferno_choosed,g);
        drawStr(skin6,"NANO",nano_choosed,g);

        g2d.setColor(Color.white);
        g2d.draw(skin1);
        g2d.draw(skin2);
        g2d.draw(skin3);
        g2d.draw(skin4);
        g2d.draw(skin5);
        g2d.draw(skin6);
    }
    public static void drawStr(RoundRectangle2D r,String name, boolean choosed, Graphics g) {
    	if(choosed) g.setColor(Color.red);
    	  g.drawString(name, (int) (r.getX() + 15), (int) (r.getY() - 15));
    	g.setColor(Color.white);
    }
}
