import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.RoundRectangle2D;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;

public class EndMenu {
	private Image headImage;
    public RoundRectangle2D backBtn = new RoundRectangle2D.Double(370, 380, 300, 50, 10, 10);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SCORE: " + GamePanel.applesEaten, 405, 220);
        try {
            headImage = ImageIO.read(new FileInputStream("head.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(headImage, 460, 255, 100, 100, null);
        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("BACK TO MENU", (int) (backBtn.getX() + 35), (int) (backBtn.getY() + 35));

        g2d.draw(backBtn);
    }
}
