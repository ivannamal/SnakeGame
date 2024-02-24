import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener{
	private Clip clip, clip2;
    static final int SCREEN_WIDTH = 1000;
    static final int SCREEN_HEIGHT = 650;
    static final int UNIT_SIZE = 50;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/(UNIT_SIZE*UNIT_SIZE);
    public static int DELAY = 300;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 6;
    int appleType;
    public static int applesEaten;
    private Image backgroundImage;
    int appleX;
    private Image backgroundImage2;
    private Image headImage;
    private Image orange;
    private Image apple;
    private Image grenade;
    int appleY;
    char direction = 'R';
    ArrayList<Rectangle> walls = new ArrayList<>();
    boolean running = false;
    Timer timer;
    Random random;
    private Menu menu;
    private ShopMenu shop_menu;
    private OptionsMenu options_menu;
    private EndMenu end_menu;
    private boolean gameStart = true;
    public static boolean isMenuOpen = false;
    public static boolean isMenu2Open = false;
    public static boolean isMenu3Open = false;
    public static boolean isMenu4Open = false;
    private BufferedImage bodyPartImage;
    private BufferedImage wallImage;
    public static String imgName = "cobra.jpg";
    public static int map_num;
    public static boolean generate = false;

    
    public static enum STATE{
    	MAIN_MENU,
    	GAME,
    	SHOP_MENU,
    	OPTIONS_MENU,
    	END_MENU
    };
    
    public static STATE State = STATE.MAIN_MENU;
    GamePanel(){
    	menu = new Menu();
    	shop_menu = new ShopMenu();
    	options_menu = new OptionsMenu();
    	end_menu = new EndMenu();
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        this.addMouseListener(new MouseInput());

        try {
            backgroundImage = ImageIO.read(new FileInputStream("omori-pixel-art-hd-wallpaper-preview.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            backgroundImage2 = ImageIO.read(new FileInputStream("g.jpg"));
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void startGame() { // Call the selectDifficulty() method
    	try {
    	    File soundFile = new File("effect1.wav");
    	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
    	    clip = AudioSystem.getClip();
    	    clip.open(audioInputStream);
    	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
    	    e.printStackTrace();
    	}
    	try {
    	    File soundFile = new File("finalEffect.wav");
    	    AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
    	    clip2 = AudioSystem.getClip();
    	    clip2.open(audioInputStream);
    	} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
    	    e.printStackTrace();
    	}
    	loadImage();
    	loadWallImage();
    	loadHeadImage();
    	loadAppleImage();
    	loadGrenadeImage();
    	loadOrangeImage();
        loadMap(map_num, generate);
        generateRandomWalls(45, generate);
        newApple();
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
    	drawBackground(g);
    	if(State == STATE.GAME) {
    		if(gameStart)
        		startGame();
        	gameStart = false;
        	
        	System.out.println(running);
    		super.paintComponent(g);
    		drawBackground2(g);
        	drawWalls(g, walls);
        	draw(g);
    	}else if(State ==STATE.MAIN_MENU) {
    		gameStart = true;
    		isMenu2Open = false;
    		isMenuOpen = true;
    		isMenu3Open = false;
    		isMenu4Open = false;
    		x[0] = 0;
    		y[0] = 0;
    		menu.render(g);
    		repaint();
    	}else if(State ==STATE.SHOP_MENU) {
    		isMenu2Open = true;
    		isMenuOpen = false;
    		isMenu3Open = false;
    		isMenu4Open = false;
    		shop_menu.render(g);
    		repaint();
    	}else if(State ==STATE.OPTIONS_MENU) {
    		isMenu2Open = false;
    		isMenuOpen = false;
    		isMenu3Open = true;
    		isMenu4Open = false;
    		options_menu.render(g);
    		repaint();
    	}else if(State == STATE.END_MENU) {
    		isMenu2Open = false;
    		isMenuOpen = false;
    		isMenu3Open = false;
    		isMenu4Open = true;
    		end_menu.render(g);
    		repaint();
    	}
    }

    private void drawWalls(Graphics g, ArrayList<Rectangle> walls) {
        for (Rectangle wall : walls) {
            g.fillRect(wall.x, wall.y, wall.width, wall.height);
            g.drawImage(wallImage, wall.x, wall.y, UNIT_SIZE, UNIT_SIZE, null);
        }
    }

    private void drawBackground(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
    }
    
    private void loadImage() {
        try {
            bodyPartImage = ImageIO.read(new FileInputStream(imgName));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadWallImage() {
        try {
            wallImage = ImageIO.read(new FileInputStream("lead.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadHeadImage() {
        try {
            headImage = ImageIO.read(new FileInputStream("head.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadAppleImage() {
        try {
            apple = ImageIO.read(new FileInputStream("apple.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadGrenadeImage() {
        try {
            grenade = ImageIO.read(new FileInputStream("grenade.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadOrangeImage() {
        try {
            orange = ImageIO.read(new FileInputStream("orange.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void drawBackground2(Graphics g) {
        g.drawImage(backgroundImage2, 0, 0, getWidth(), getHeight(), null);
    }
    public void draw(Graphics g) {
        if (running) {
            if (appleType == 0) {
            	g.drawImage(apple, appleX, appleY, UNIT_SIZE, UNIT_SIZE, null); // Normal value apple
            } else if (appleType == 1) {
            	g.drawImage(orange, appleX, appleY, UNIT_SIZE, UNIT_SIZE, null); // Medium value apple
            } else {
            	g.drawImage(grenade, appleX, appleY, UNIT_SIZE, UNIT_SIZE, null); // Highest value apple
            }

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                	g.drawImage(headImage, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
                } else {
                    // Draw the body part image
                    g.drawImage(bodyPartImage, x[i], y[i], UNIT_SIZE, UNIT_SIZE, null);
                }
            }

            g.setColor(Color.red);
            g.setFont(new Font("Arial", Font.BOLD, 40));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: " + applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2, g.getFont().getSize());
        } else {
            gameOver(g);
        }
    }

    public void newApple() {
        boolean validApplePosition = false;
        while (!validApplePosition) {
            appleX = random.nextInt((int)(SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
            appleY = random.nextInt((int)(SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

            // Check if the apple position overlaps with any walls or the snake's body
            boolean overlappingWall = walls.stream().anyMatch(wall -> wall.contains(appleX, appleY));
            boolean overlappingBody = Arrays.stream(x).skip(1).anyMatch(snakeX -> snakeX == appleX)
                    && Arrays.stream(y).skip(1).anyMatch(snakeY -> snakeY == appleY);

            if (!overlappingWall && !overlappingBody) {
                validApplePosition = true;
            }
        }

        // Generate the apple type
        appleType = random.nextInt(10); // Generates a random number from 0 to 9 for apple types
        if (appleType < 6) {
            appleType = 0;
        } else if (appleType < 9) {
            appleType = 1;
        } else {
            appleType = 2;
        }
    }


    public void move(){
        for(int i = bodyParts;i>0;i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        switch(direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }
    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
        	clip.setFramePosition(0); // Rewind the sound to the beginning
            clip.start(); // Play the sound
            if (appleType == 0) {
                applesEaten += 1; // Normal apple worth 1 point
            } else if (appleType == 1) {
                applesEaten += 3; // Medium value apple worth 3 points
            } else {
                applesEaten += 10; // Highest value apple worth 10 points
            }
            newApple();
            bodyParts++;
        }
    }
    private void generateRandomWalls(int count, boolean b) {
    	if(b) {
        walls.clear(); // Clear any existing walls

        for (int i = 0; i < count; i++) {
            int wallX = random.nextInt((SCREEN_WIDTH - 50) / UNIT_SIZE) * UNIT_SIZE + 50;
            int wallY = random.nextInt((SCREEN_HEIGHT - 50) / UNIT_SIZE) * UNIT_SIZE + 50;

            // Check if the wall overlaps with the snake's initial position or the apple
            if ((wallX == appleX && wallY == appleY) || (wallX == x[0] && wallY == y[0])) {
                i--;
                continue;
            }

            walls.add(new Rectangle(wallX, wallY, UNIT_SIZE, UNIT_SIZE));
        	}
    	}
    }


    public void checkCollisions() {
        //checks if head collides with body
        for(int i = bodyParts;i>0;i--) {
            if((x[0] == x[i])&& (y[0] == y[i])) {
                running = false;
              	clip2.setFramePosition(0); // Rewind the sound to the beginning
                clip2.start(); // Play the sound
                State = STATE.END_MENU;
                
            }
        }
        for (Rectangle wall : walls) {
            if (wall.intersects(x[0], y[0], UNIT_SIZE, UNIT_SIZE)) {
                running = false;
              	clip2.setFramePosition(0); // Rewind the sound to the beginning
                clip2.start(); // Play the sound
                State = STATE.END_MENU;
                break;
            }
        }
        //check if head touches left border
        if(x[0] < 0) {
            running = false;
          	clip2.setFramePosition(0); // Rewind the sound to the beginning
            clip2.start(); // Play the sound
            State = STATE.END_MENU;
        }
        //check if head touches right border
        if(x[0] > SCREEN_WIDTH) {
            running = false;
          	clip2.setFramePosition(0); // Rewind the sound to the beginning
            clip2.start(); // Play the sound
            State = STATE.END_MENU;
        }
        //check if head touches top border
        if(y[0] < 0) {
            running = false;
          	clip2.setFramePosition(0); // Rewind the sound to the beginning
            clip2.start(); 
            State = STATE.END_MENU;
        }
        //check if head touches bottom border
        if(y[0] > SCREEN_HEIGHT) {
            running = false;
          	clip2.setFramePosition(0); // Rewind the sound to the beginning
            clip2.start(); // Play the sound
            State = STATE.END_MENU;
        }

        if(!running) {
            timer.stop();
        }
    }
    public void gameOver(Graphics g) {
        
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
            }
        }
    }
    // Method to generate walls based on a map configuration
    private void generateMapWalls(int[][] map) {
        walls.clear(); // Clear any existing walls

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 1) {
                    int wallX = j * UNIT_SIZE;
                    int wallY = i * UNIT_SIZE;
                    walls.add(new Rectangle(wallX, wallY, UNIT_SIZE, UNIT_SIZE));
                }
            }
        }
    }

    private void loadMap(int mapIndex, boolean b) {
    	if(!b) {
        if (mapIndex >= 0 && mapIndex < maps.length) {
            generateMapWalls(maps[mapIndex]);
        } else {
            generateRandomWalls(30, true); // Fallback to generating random walls
        }
    }
}

    private final int[][][] maps = {
            {  // Map 1
                {0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1},
                {0,1,1,1,1,1,0,1,0,0,0,0,1,0,0,0,0,0,0,1},
                {0,0,0,0,0,1,0,1,1,1,0,0,1,1,1,1,0,0,1,1},
                {0,0,0,0,0,1,0,0,1,1,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0},
                {0,0,1,0,0,0,1,0,0,0,1,0,1,0,0,1,0,0,0,0},
                {0,0,1,1,0,0,1,0,1,0,1,1,1,0,0,1,1,1,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,0,0},
                {0,0,1,1,1,0,0,0,0,1,1,0,0,0,0,1,0,1,0,0},
                {0,0,0,0,1,0,0,1,0,1,0,0,0,0,0,1,1,1,0,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,0,1,1,0,0,0,0,0}
            },
            {  // Map 2
                {0,0,0,0,0,0,1,0,0,0,1,1,1,0,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,0,0,1,1,1,0,0,0,0,0,0,0,0,1,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,0},
                {0,0,0,1,0,1,0,0,1,0,0,1,1,1,0,1,0,0,1,0},
                {0,0,1,0,0,1,1,0,0,0,0,1,1,1,0,1,0,1,0,0},
                {0,0,1,0,0,0,1,0,0,0,0,1,1,1,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,0},
                {0,0,0,0,0,0,0,0,0,1,1,1,1,0,0,1,0,0,0,1},
                {0,0,0,0,1,1,0,0,0,0,0,0,0,0,0,1,1,0,0,0},
                {0,1,0,0,0,1,1,0,0,0,0,0,1,0,0,0,1,0,0,0},
                {1,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,1}
            },
            {  // Map 3
            	{0,0,0,0,0,0,0,0,0,1,0,0,1,0,0,0,0,0,0,0},
            	{1,0,0,0,0,0,1,0,0,1,0,0,1,0,0,1,1,1,0,0},
            	{1,0,0,1,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
            	{1,0,0,1,1,1,0,0,1,1,0,0,1,0,0,0,1,1,0,0},
            	{1,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0},
            	{0,0,0,0,0,0,1,0,1,0,0,1,1,0,0,0,1,1,0,0},
            	{0,0,0,1,0,0,1,0,0,1,0,0,1,1,0,0,0,0,0,0},
            	{0,1,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,0,0},
            	{0,0,0,1,1,0,0,1,0,0,1,0,0,0,0,0,0,0,0,0},
            	{0,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,0,0,1,0},
            	{0,0,0,1,0,0,1,0,1,0,0,1,1,1,1,0,0,1,1,0},
            	{1,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
            	{1,1,1,1,1,0,0,0,0,0,1,1,1,0,0,0,0,1,0,0}


            },
        };
}