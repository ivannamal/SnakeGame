import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class Menu {

    public RoundRectangle2D playButton = new RoundRectangle2D.Double(430, 200, 150, 50, 10, 10);
    public RoundRectangle2D optionsButton = new RoundRectangle2D.Double(430, 300, 150, 50, 10, 10);
    public RoundRectangle2D shopButton = new RoundRectangle2D.Double(430, 400, 150, 50, 10, 10);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Font fnt0 = new Font("arial", Font.BOLD, 50);
        g.setFont(fnt0);
        g.setColor(Color.white);
        g.drawString("SNAKE GAME", 330, 100);

        Font fnt1 = new Font("arial", Font.BOLD, 30);
        g.setFont(fnt1);
        g.drawString("Play", (int) (playButton.getX() + 42), (int) (playButton.getY() + 35));
        g.drawString("Options", (int) (optionsButton.getX() + 20), (int) (optionsButton.getY() + 35));
        g.drawString("Skins", (int) (shopButton.getX() + 37), (int) (shopButton.getY() + 35));

        g2d.draw(playButton);
        g2d.draw(optionsButton);
        g2d.draw(shopButton);
    }
}
