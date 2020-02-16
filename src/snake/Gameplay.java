package snake;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	
	private int snakexLength[] = new int[750];
	private int snakeyLength[] = new int[750];
	
	private int foodx[]= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
						  525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	
	private int foody[]= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
			  			 525,550,575,600,625};
	
	private boolean left = false;
	private boolean right = false;
	private boolean down = false;
	private boolean up = false;
	
	private ImageIcon leftmouth;
	private ImageIcon rightmouth;
	private ImageIcon upmouth;
	private ImageIcon downmouth;
	private ImageIcon snakeimage;
	private ImageIcon titleimage;
	private ImageIcon food;
	
	private int lengthofsnake = 3;
	private int move=0;
	public int score=0;
	
	private Random random = new Random();
	private int xpos = random.nextInt(foodx.length);
	private int ypos = random.nextInt(foody.length);
	

	private Timer time;
	private int delay = 150;
	
	public Gameplay() {
		
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();
	}
	
	public void paint(Graphics g) {
		
		// draw image border
		g.setColor(Color.WHITE);
		g.drawRect(24, 10, 851, 55);
		
		// draw title image
		titleimage = new ImageIcon("snaketitle.jpg");
		titleimage.paintIcon(this, g, 25, 11);
		
		// draw border Game play
		g.setColor(Color.WHITE);
		g.drawRect(24, 74, 851, 577);
		
		// draw background Color
		g.setColor(Color.BLACK);
		g.fillRect(25, 75, 850, 575);
		
		// display Score
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 14));
		g.drawString("Score: "+score, 780, 30);
		
		// display Snake Length
		g.setColor(Color.white);
		g.setFont(new Font("arial", Font.BOLD, 14));
		g.drawString("Length: "+lengthofsnake, 780, 50);
		
		// default position of snake
		if(move == 0) {
			
			snakexLength[0] = 100; 
			snakexLength[1] = 75;
			snakexLength[2] = 50;
			
			snakeyLength[0] = 100; 
			snakeyLength[1] = 100;
			snakeyLength[2] = 100;
			
		}
		
		// default snake mouth 
		rightmouth = new ImageIcon("rightmouth.png");
		rightmouth.paintIcon(this, g, snakexLength[0], snakeyLength[0]);
		
		for(int i=0; i<lengthofsnake; i++) {
			
			if(right && i==0 ) {
				rightmouth = new ImageIcon("rightmouth.png");
				rightmouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
			if(left && i==0 ) {
				leftmouth = new ImageIcon("leftmouth.png");
				leftmouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
			if(up && i==0 ) {
				upmouth = new ImageIcon("upmouth.png");
				upmouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
			if(down && i==0 ) {
				downmouth = new ImageIcon("downmouth.png");
				downmouth.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
			
			if(i!=0) {
				snakeimage = new ImageIcon("snakeimage.png");
				snakeimage.paintIcon(this, g, snakexLength[i], snakeyLength[i]);
			}
		}
		
		food = new ImageIcon("enemy.png");
		food.paintIcon(this, g, foodx[xpos], foody[ypos]);
		
		if((foodx[xpos] == snakexLength[0]) && (foody[ypos] == snakeyLength[0])) {
			
			lengthofsnake++;
			score++;
			xpos = random.nextInt(34);
			ypos = random.nextInt(23);
			
		}
		
		for(int i=1; i<lengthofsnake; i++) {
			
			if(snakexLength[i] == snakexLength[0] && snakeyLength[i] == snakeyLength[0]) {
				
				right=false;
				left=false;
				up=false;
				down=false;

				g.setColor(Color.white);
				g.setFont(new Font("arial", Font.BOLD, 50));
				g.drawString("Game Over", 300, 300);
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press SpaceBar To Restart", 310, 340);
				
			}
			
		}
		
		if(lengthofsnake > 5) {
			delay = 100;
		}
		else if(lengthofsnake > 10) {
			delay = 50;
		}
		else if(lengthofsnake > 15) {
			delay=25;
		}
		else if(lengthofsnake > 20) {
			delay=10;
		}
			
			g.dispose();
			
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		time.start();
		if(right) {
			
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeyLength[i+1]=snakeyLength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				
				if(i==0) {
					snakexLength[i] = snakexLength[i] + 25;
				}
				else {
					snakexLength[i] = snakexLength[i-1];
				}
				if(snakexLength[i]>850) {
					snakexLength[i]=25;
				}
			}
			repaint();
			
		}
		if(left) {
			
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakeyLength[i+1]=snakeyLength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				
				if(i==0) {
					snakexLength[i] = snakexLength[i] - 25;
				}
				else {
					snakexLength[i] = snakexLength[i-1];
				}
				if(snakexLength[i] < 25) {
					snakexLength[i] = 850;
				}
			}
			repaint();
			
		}
		if(up) {
			
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakexLength[i+1]=snakexLength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				
				if(i==0) {
					snakeyLength[i] = snakeyLength[i] - 25;
				}
				else {
					snakeyLength[i] = snakeyLength[i-1];
				}
				if(snakeyLength[i] < 75) {
					snakeyLength[i] = 625;
				}
			}
			repaint();
			
		}
		if(down) {
			
			for(int i=lengthofsnake-1; i>=0; i--) {
				snakexLength[i+1]=snakexLength[i];
			}
			for(int i=lengthofsnake; i>=0; i--) {
				
				if(i==0) {
					snakeyLength[i] = snakeyLength[i] + 25;
				}
				else {
					snakeyLength[i] = snakeyLength[i-1];
				}
				if(snakeyLength[i] > 625) {
					snakeyLength[i] = 75;
				}
			}
			repaint();
			
		}
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			move=0;
			score=0;
			lengthofsnake=3;
			repaint();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			move++;
			right = true;
			
			if(!left) {
				right  = true;
			}
			else {
				right = false;
				left = true;
			}
			
			up = false;
			down = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			move++;
			left = true;
			
			if(!right) {
				left  = true;
			}
			else {
				left = false;
				right = true;
			}
			
			up = false;
			down = false;
			
		}
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			
			move++;
			up = true;
			
			if(!down) {
				up=true;
			}
			else {
				up=false;
				down=true;
			}
			
			left = false;
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			
			move++;
			down = true;
			
			if(!up) {
				down=true;
			}
			else {
				down=false;
				up=true;
			}
			
			left = false;
			right = false;
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
