import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.RoundRectangle2D;

public class OptionsMenu {

	
	public static boolean map1_choosed = true;
	public static boolean map2_choosed = false;
	public static boolean map3_choosed = false;
	
	public static boolean btn1_choosed = true;
	public static boolean btn2_choosed = false;
	public static boolean btn3_choosed = false;
	
	public static boolean randomBtn_choosed = false;
	
	private Image arrow;
	private Image image1;
    private Image image2;
    private Image image3;
    private Image image4;
    private RoundRectangle2D map1 = new RoundRectangle2D.Double(75, 400, 250, 150, 10, 10);
    private RoundRectangle2D map2 = new RoundRectangle2D.Double(375, 400, 250, 150, 10, 10);
    private RoundRectangle2D map3 = new RoundRectangle2D.Double(675, 400, 250, 150, 10, 10);
    
    private RoundRectangle2D btn1 = new RoundRectangle2D.Double(250, 200, 160, 40, 10, 10);
    private RoundRectangle2D btn2 = new RoundRectangle2D.Double(435, 200, 160, 40, 10, 10);
    private RoundRectangle2D btn3 = new RoundRectangle2D.Double(620, 200, 160, 40, 10, 10);
    
    private RoundRectangle2D randomBtn = new RoundRectangle2D.Double(320, 585, 360, 40, 10, 10);
	
	    public OptionsMenu() {
	        // Load the image for the button background
	    	arrow = Toolkit.getDefaultToolkit().getImage("arrow.png");
	    	image1 = Toolkit.getDefaultToolkit().getImage("map1.png");
	        image2 = Toolkit.getDefaultToolkit().getImage("map2.png");
	        image3 = Toolkit.getDefaultToolkit().getImage("map3.png"); 
	        image4 = Toolkit.getDefaultToolkit().getImage("btn.png"); 
	    
	    }

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.drawImage(arrow, 25, 25, 80, 50, null);
        g2d.setClip(map1);
        g.drawImage(image1, (int) map1.getX(), (int) map1.getY(), (int) map1.getWidth(), (int) map1.getHeight(), null);
        g2d.setClip(null);
        
        g2d.setClip(map2);
        g.drawImage(image2, (int) map2.getX(), (int) map2.getY(), (int) map2.getWidth(), (int) map2.getHeight(), null);
        g2d.setClip(null);
        
        g2d.setClip(map3);
        g.drawImage(image3, (int) map3.getX(), (int) map3.getY(), (int) map3.getWidth(), (int) map3.getHeight(), null);
        g2d.setClip(null);
        
        g2d.setClip(randomBtn);
        g.drawImage(image4, (int) randomBtn.getX(), (int) randomBtn.getY(), (int) randomBtn.getWidth(), (int) randomBtn.getHeight(), null);
        g2d.setClip(null);
        
        Font fnt0 = new Font("Arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("OPTIONS", 400, 100);
        Font fnt1 = new Font("Arial", Font.BOLD, 30);
        g.setFont(fnt1);
        drawStr(map1,"MAP 1",map1_choosed,g);
        drawStr(map2,"MAP 2",map2_choosed,g);
        drawStr(map3,"MAP 3",map3_choosed,g);
        
        drawStr2(btn1,"SLOW",btn1_choosed,g,35);
        drawStr2(btn2,"MEDIUM",btn2_choosed,g,20);
        drawStr2(btn3,"FAST",btn3_choosed,g,43);
        
        drawStr2(randomBtn,"RANDOM GENERATION",randomBtn_choosed,g,10);
        
        g2d.setColor(Color.white);
        g2d.draw(map1);
        g2d.draw(map2);
        g2d.draw(map3);
        
        g2d.draw(btn1);
        g2d.draw(btn2);
        g2d.draw(btn3);
        g2d.draw(randomBtn);
    }
    public static void drawStr(RoundRectangle2D r,String name, boolean choosed, Graphics g) {
    	if(choosed) g.setColor(Color.red);
    	  g.drawString(name, (int) (r.getX() + 15), (int) (r.getY() - 15));
    	g.setColor(Color.white);
    }
    public static void drawStr2(RoundRectangle2D r,String name, boolean choosed, Graphics g, int x) {
    	if(choosed) g.setColor(Color.red);
    	  g.drawString(name, (int) (r.getX() + x), (int) (r.getY() + 30));
    	g.setColor(Color.white);
    }
}

