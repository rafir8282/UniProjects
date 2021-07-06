import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
	private static final long serialVersionUID = 1L;
	private final int B_WIDTH = 300;
	private final int B_HEIGHT = 300;
	private final int DOT_SIZE = 10;
	private final int RAND_POS = 29;
	private final int DELAY = 140;
	private SnakeLinkedList snake = new SnakeLinkedList();
	private Color[] colours = {Color.BLUE, Color.YELLOW, Color.GREEN};
	private int dots;
	private int apple_x;
	private int apple_y;
	private boolean leftDirection = false;
	private boolean rightDirection = true;
	private boolean upDirection = false;
	private boolean downDirection = false;
	private boolean inGame = true;
	private Timer timer;
	private Image apple;
	
	public Board() {
		initBoard();
	}
	
	private void initBoard() {
		addKeyListener(new TAdapter());
		setBackground(Color.BLACK);
		setFocusable(true);
		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		loadImages();
		initGame();
	}
	
	private void loadImages() {
		apple = new ImageIcon("res/apple.png").getImage();
	}
	
	private void initGame() {
		dots = 3;
		
		for (int z = 0; z < dots; z++) {
			if (z == 0) {
				snake.addHead(50, 50, Color.RED);
			} else {
				snake.addJoint(50 - z*DOT_SIZE, 50, colours[z%3]);
			}
		}
		
		locateApple();
		new Timer(DELAY, this).start();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}
	
	private void doDrawing(Graphics g) {
		if (inGame) {
			g.drawImage(apple, apple_x, apple_y, this);
			
			for (int z = 0; z < dots; z++) {
				if (z == 0) {
					SnakeNode head = snake.getHead();
					
					g.setColor(head.getColor());
					g.fillOval(head.getX(), head.getY(), DOT_SIZE, DOT_SIZE);
				} else {
					SnakeNode joint = snake.getJoint(z);
					
					g.setColor(joint.getColor());
					g.fillOval(joint.getX(), joint.getY(), DOT_SIZE, DOT_SIZE);
				}
			}
			
			Toolkit.getDefaultToolkit().sync();
		} else {
			gameOver(g);
		}
	}
	
	private void gameOver(Graphics g) {
		String msg = "Game Over";
		Font small = new Font("Helvetica", Font.BOLD, 14);
		
		g.setColor(Color.WHITE);
		g.setFont(small);
		g.drawString(msg, (B_WIDTH - getFontMetrics(small).stringWidth(msg))/2, B_HEIGHT/2);
	}
	
	private void checkApple() {
		if ((snake.getHead().getX() == apple_x) && (snake.getHead().getY() == apple_y)) {
			for (int z = 0; z < colours.length; z++) {	
				snake.addTail(colours[z]);
			}
			
            dots++;
            locateApple();
		}
	}
	
	private void checkCollision() {
		for (int z = dots; z > 0; z--) {
			if ((z > 4) && (snake.getHead().getX() == snake.getJoint(z).getX()) && (snake.getHead().getY() == snake.getJoint(z).getY())) {
				inGame = false;
			}
		}
		
		if (snake.getHead().getY() >= B_HEIGHT) {
			inGame = false;
		}
		
		if (snake.getHead().getY() < 0) {
			inGame = false;
		}
		
		if (snake.getHead().getX() >= B_WIDTH) {
			inGame = false;
		}
		
		if (snake.getHead().getX() < 0) {
			inGame = false;
		}
		
		if (!inGame) {
			try {
				timer.stop();
			} catch (NullPointerException e) {
				
			}
		}
	}
	
	private void locateApple() {
		int r;
		
		r = (int)(Math.random()*RAND_POS);
		apple_x = r*DOT_SIZE;
		r = (int)(Math.random()*RAND_POS);
        apple_y = r*DOT_SIZE;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (inGame) {
			checkApple();
			checkCollision();
            snake.getJoint(1);
            snake.snakeMove(dots - 1, leftDirection, rightDirection, upDirection, downDirection, DOT_SIZE);
		}
		
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			
			if ((key == KeyEvent.VK_LEFT) && (!rightDirection)) {
				leftDirection = true;
				upDirection = false;
				downDirection = false;
			}
			
			if ((key == KeyEvent.VK_RIGHT) && (!leftDirection)) {
				rightDirection = true;
				upDirection = false;
				downDirection = false;
			}
			
			if ((key == KeyEvent.VK_UP) && (!downDirection)) {
				upDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
			
			if ((key == KeyEvent.VK_DOWN) && (!upDirection)) {
				downDirection = true;
				rightDirection = false;
				leftDirection = false;
			}
		}
	}
}