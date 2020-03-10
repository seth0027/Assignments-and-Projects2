/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;
import java.util.*;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {
private RandomGenerator r=RandomGenerator.getInstance();
private GRect board;
AudioClip bounceClip = MediaTools.loadAudioClip("bounce.au");
	private GRect paddle;
	private GOval ball;
	private GRect brick;
	private ArrayList<GRect> bricks=new ArrayList<>();
	private int counter;
	private double vx, vy;
	/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

	/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

	/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

	/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

	/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

	/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

	/** Separation between bricks */
	private static final int BRICK_SEP = 4;

	/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

	/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

	/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

	/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

	/** Number of turns */
	//private static final int NTURNS = 3;

	/* Method: init() */
	/** Sets up the Breakout program. */
	public void init() {
		/* You fill this in, along with any subsidiary methods */
		setUp();
		this.addMouseListeners();
	}
	

	/* Method: run() */
	/** Runs the Breakout program. */
	public void run() {
		/* You fill this in, along with any subsidiary methods */
		
		play();
	}
	public static void main(String[] args) {
		new Breakout().start(args);
	}
	
	public void play() {
		counter=NBRICK_ROWS*NBRICKS_PER_ROW;
		vy=3.0;
		vx = r.nextDouble(1.0, 3.0);
		if (r.nextBoolean(0.5)) vx = -vx;
		//int score=0;
		
		while(ball.getY()+BALL_RADIUS*2<HEIGHT || counter>0) {
			
			
			ball.move(vx, vy);
			this.pause(10);
			
			
			if(ball.getX()+BALL_RADIUS*2>WIDTH) {
				vx=-vx;
				
			}
			//if(ball.getY()+BALL_RADIUS*2>HEIGHT) {
				//vy=-vy;
			//}
			if(ball.getX()+BALL_RADIUS*2<4) {
				vx=-vx;
				
			}
			if(ball.getY()+BALL_RADIUS*2<4) {
				vy=-vy;
				
			}
			GObject collider=getCollidingObject();
			
			for(GRect b: bricks) {
			if(collider==b) {
				this.remove(b);
				--counter;
				
			}
			}
			GLabel l=null;
			if(counter==0) {
				bounceClip.play();
				l=new GLabel("You Won");
				l.setFont("SansSerrif-36");
				l.setLocation(WIDTH/2-60,HEIGHT/2);
				l.setColor(Color.WHITE);
				add(l);
				break;
			}
			else if(ball.getY()+BALL_RADIUS*2>HEIGHT) {
				bounceClip.play();
				l=new GLabel("You Lost");
				l.setFont("SansSerrif-36");
				l.setLocation(WIDTH/2-60, HEIGHT/2);
				l.setColor(Color.WHITE);
				add(l);
				
			}
		}
	}
	public GObject getCollidingObject() {
		if(this.getElementAt(ball.getX(),ball.getY())!=null && this.getElementAt(ball.getX(),ball.getY())!=board) {
			//vx=-vx;
			vy=-vy;
			bounceClip.play();
			return this.getElementAt(ball.getX(),ball.getY());
		}
		if(this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY())!=null && this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY())!=board) {
			//vx=-vx;
			vy=-vy;
			bounceClip.play();
			return this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY());
		}
		
		if(this.getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS)!=null && this.getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS)!=board) {
			//vx=-vx;
			vy=-vy;
			bounceClip.play();
			return this.getElementAt(ball.getX(),ball.getY()+2*BALL_RADIUS);
		}
		if(this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS)!=null && this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS)!=board) {
			//vx=-vx;
			vy=-vy;
			bounceClip.play();
			return this.getElementAt(ball.getX()+2*BALL_RADIUS,ball.getY()+2*BALL_RADIUS);
		}
		
		return null;
		
	}
	public void createBall() {
		ball=new GOval(APPLICATION_WIDTH/2,APPLICATION_HEIGHT/2,BALL_RADIUS*2,BALL_RADIUS*2);
		ball.setFilled(true);
		ball.setFillColor(Color.LIGHT_GRAY);
		add(ball);
		
	}
	public void setUp() {
		setUpBricks();
		createPaddle();
		createBall();
	}
	
	public void createPaddle(){
		 paddle=new GRect(APPLICATION_WIDTH/2-PADDLE_WIDTH,APPLICATION_HEIGHT-PADDLE_Y_OFFSET,PADDLE_WIDTH,PADDLE_HEIGHT);
		paddle.setFilled(true);
		paddle.setFillColor(Color.PINK);
		
		add(paddle);
		
	}
	
	public void setUpBricks() {
		this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		this.setBackground(Color.GRAY);
		 board=new GRect(WIDTH, HEIGHT);
		board.setFilled(true);
		board.setFillColor(Color.BLACK);
		add(board);
		
		for(int i=0; i<Breakout.NBRICK_ROWS;i++) {
			
			for(int j=0;j<Breakout.NBRICKS_PER_ROW;j++) {
				
					brick=new GRect(j*BRICK_WIDTH+4,BRICK_Y_OFFSET+i*BRICK_HEIGHT+4,BRICK_WIDTH,BRICK_HEIGHT);
					brick.setFilled(true);
					brick.setColor(Color.BLACK);
					int num=i;
					if(num>4) {
						num=num%5;
					}
					if(num==0) {
						brick.setFillColor(Color.RED);
					}
					if(num==1) {
						brick.setFillColor(Color.ORANGE);
					}
					if(num==2) {
						brick.setFillColor(Color.YELLOW);
					}
					if(num==3) {
						brick.setFillColor(Color.GREEN);
					}
					if(num==4) {
						brick.setFillColor(Color.CYAN);
					}
					bricks.add(brick);
					add(brick);
				
				
				
			}
			
			
			
		}
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getX()<WIDTH-PADDLE_WIDTH)
		paddle.setLocation(e.getX(),APPLICATION_HEIGHT-PADDLE_Y_OFFSET);
		
		
	}
	public void mouseClicked(MouseEvent e) {
		//play();
	}
}
