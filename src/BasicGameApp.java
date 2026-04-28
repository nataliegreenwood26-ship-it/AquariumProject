import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import java.lang.reflect.Array;
import javax.swing.JFrame;
import javax.swing.JPanel;
//imports that we as a class downloaded into Java

public class BasicGameApp implements Runnable, KeyListener, MouseListener {
   //The variables that we use in the project are below

	final int WIDTH = 1000; //width of window
	final int HEIGHT = 700; //height of window
    int score = 0; //score of the game


    boolean[] squished = {false, false, false, false}; //array for squished fruit
    //0 = apple, 1 = orange, 2 = lemon, 3 = blueberry


   //More variables declared
	public JFrame frame;
	public Canvas canvas;
    public JPanel panel;

    //Variables below for all of our photos to import
	public BufferStrategy bufferStrategy;
    public Image applePic;
    public Image orangepic;
    public Image lemonpic;
    public Image BackgroundPic;
    public Image blueberrypic;
    public Image snakepic;

   //These are the characters/fruits in the game. Each one has an object that is made for them.
	private apple apple;
    private orange orange;
    private lemon lemon;
    private blueberry blueberry;
    private Snake Snakey;


   //This is the code that runs first when pressing run
	public static void main(String[] args) {
		BasicGameApp ex = new BasicGameApp();  //creates a new instance of the game
		new Thread(ex).start(); //creates a thread & starts up the code in the run( ) method
	}

   // Constructor Method Below
   // Make the objects, and define variables from above.
	public BasicGameApp() {
        setUpGraphics();

        int randx =

        randx = (int)(Math.random()*999)+ 1; // random position within the width of the window

        int randy = (int)(Math.random()*699)+1; //random position within the height of the window


      //variable and objects
		applePic = Toolkit.getDefaultToolkit().getImage("apple.png"); //load the picture
        orangepic = Toolkit.getDefaultToolkit().getImage("orange.png"); //load the picture
        lemonpic = Toolkit.getDefaultToolkit().getImage("lemon.png"); //load the picture
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("CheckeredBackground.png"); //load the picture
        blueberrypic = Toolkit.getDefaultToolkit().getImage("blueberry.png"); //load the picture
        snakepic = Toolkit.getDefaultToolkit().getImage("snake.png"); //load the picture

        apple = new apple(200,350); //creates apple object at that position (500,300)
        orange = new orange(randx, randy); //the orange appears somewhere random every single time the go button is pressed
        lemon = new lemon (100,500); //creates lemon object at that position (100,500)
        blueberry = new blueberry(400,200); //creates blueberry object at position shown
        Snakey = new Snake(10,10); //creates snake object at position shown

    }

   // looping method
	public void run() {
		while (true) {

         moveThings();  //move all the game objects
         render();  // draws all the objects
         pause(20); // waits
		}
	}

	public void moveThings()
	{
      //calls the move( ) code from the other object classes
        //checks if each character is alive, if alive calls their move() method
        if (apple.isAlive) apple.move();
       if (orange.isAlive) orange.move();
       if (lemon.isAlive) lemon.move();
       if (blueberry.isAlive) blueberry.move();
        Snakey.move(); //always moves snake
        crashing(); //calls method

	}

    public void crashing () {
        //checks if any of the fruit hit the snake
        // makes it so that when each fruit hits the snake they die, and points are added

        if (apple.isAlive && apple.hitbox.intersects(Snakey.hitbox)) {
            apple.dx = -apple.dx;
            apple.isAlive = false;
            score++;
        }
        if (orange.isAlive && orange.hitbox.intersects(Snakey.hitbox)){
            orange.dx = -orange.dx;
            orange.isAlive = false;
            score++;
        }
        if (blueberry.isAlive && blueberry.hitbox.intersects(Snakey.hitbox)){
            blueberry.dx = -blueberry.dx;
            blueberry.isAlive = false;
            score++;
        }
        if (lemon.isAlive && lemon.hitbox.intersects(Snakey.hitbox)){
            lemon.dx = -lemon.dx;
            lemon.isAlive = false;
            score++;
        }

    }

   //controls the game speed
   public void pause(int time ){
   		//sleep
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {

			}
   }

   //Graphics setup method
   private void setUpGraphics() {
      frame = new JFrame("Application Template");   //Create the program window or frame.  Names it.
   
      panel = (JPanel) frame.getContentPane();  //sets up a JPanel which is what goes in the frame
      panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));  //sizes the JPanel
      panel.setLayout(null);   //set the layout
   
      // creates a canvas which is a blank rectangular area of the screen onto which the application can draw
      // and trap input events (Mouse and Keyboard events)
      canvas = new Canvas();
       canvas.addKeyListener(this);
       //set canvas as the mouselistener
       canvas.addMouseListener(this);


      canvas.setBounds(0, 0, WIDTH, HEIGHT);
      canvas.setIgnoreRepaint(true);
   
      panel.add(canvas);  // adds the canvas to the panel.
   
      // frame operations
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //makes the frame close and exit nicely
      frame.pack();  //adjusts the frame and its contents so the sizes are at their default or larger
      frame.setResizable(false);   //makes it so the frame cannot be resized
      frame.setVisible(true);      //IMPORTANT!!!  if the frame is not set to visible it will not appear on the screen!
      
      // sets up things so the screen displays images nicely.
      canvas.createBufferStrategy(2);
      bufferStrategy = canvas.getBufferStrategy();
      canvas.requestFocus();
      System.out.println("DONE graphic setup");
   
   }


	//paints things on the screen using bufferStrategy
	private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        //draws the image of the background
        g.drawImage(BackgroundPic, 0, 0, WIDTH, HEIGHT, null);


        //if fruits are alive then draw their images, once they die make it so they dont show up on the screen
        //always draw the snake because it never dies

        g.drawImage(snakepic, Snakey.xpos, Snakey.ypos, Snakey.width, Snakey.height, null);


        //the line below tells us that if all of the objects/fruits are dead (except snake) then a new image should flash on the screen (game over)

        if (apple.isAlive == false && orange.isAlive == false && lemon.isAlive == false && blueberry.isAlive == false) {
            g.drawImage(Toolkit.getDefaultToolkit().getImage("gameover.png"), 0, 0, 1000, 700, null);

        }



        //the lines below tell to draw the fruits flat if they are alive but squished
        if (apple.isAlive) {
            if (squished[0]) { //array used here from above

                g.drawImage(applePic, apple.xpos, apple.ypos + apple.height * 2 / 3, apple.width, apple.height / 3, null); //squishes the fruit
            } else {
                g.drawImage(applePic, apple.xpos, apple.ypos, apple.width, apple.height, null);
            }
        }

        if (orange.isAlive) {
            if (squished[1]) { //array used here
                g.drawImage(orangepic, orange.xpos, orange.ypos + orange.height * 2 / 3, orange.width, orange.height / 3, null); //squishes the fruit
            } else {
                g.drawImage(orangepic, orange.xpos, orange.ypos, orange.width, orange.height, null);
            }
        }

        if (lemon.isAlive) {
            if (squished[2]) {
                g.drawImage(lemonpic, lemon.xpos, lemon.ypos + lemon.height * 2 / 3, lemon.width, lemon.height / 3, null); //squishes the fruit
            } else {
                g.drawImage(lemonpic, lemon.xpos, lemon.ypos, lemon.width, lemon.height, null);
            }
        }


            if (blueberry.isAlive) {
                if (squished[3]) {
                    g.drawImage(blueberrypic, blueberry.xpos, blueberry.ypos + blueberry.height * 2 / 3, blueberry.width, blueberry.height / 3, null); //squishes the fruit
                } else {
                    g.drawImage(blueberrypic, blueberry.xpos, blueberry.ypos, blueberry.width, blueberry.height, null);
                }
            }

                g.setColor(Color.BLACK);
                g.setFont(new Font("Arial", Font.BOLD, 50));
                g.drawString("Score:" + score, 20, 40);

                g.dispose();
                bufferStrategy.show();
            }


            public void resetgame(){ //this method is for when enter is clicked, how to reset the game

                //bring all fruits back to life
                apple.isAlive = true;
                orange.isAlive = true;
                lemon.isAlive = true;
                blueberry.isAlive = true;

                //unsquish all fruits
                for(int x = 0; x < squished.length; x++) {
                    squished[x] = false;
                }


                //make the fruits return to their starting positions
                apple.xpos = 200;
                apple.ypos = 100;
                lemon.xpos = 100;
                lemon.ypos = 500;
                blueberry.xpos = 400;
                blueberry.ypos = 200;
                orange.xpos = (int)(Math.random()*999) + 1;
                orange.ypos = (int)(Math.random()*699) + 1;



                //reset score back to 0
                score = 0;


            }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        //below is for the arrows to control the snake
        System.out.println("Key typed"+e.getKeyCode());
        //up arrow is 38
        if(e.getKeyCode() == 38){
            System.out.println("pressed up arrow");
            Snakey.dy = -1;
        }
        if(e.getKeyCode() == 40){
            System.out.println("pressed down arrow");
            Snakey.dy = 1;
        }

        if(e.getKeyCode() == 37){
            System.out.println("pressed left arrow");
            Snakey.dx = -Math.abs(Snakey.dx);
        }

        if(e.getKeyCode() == 39){
            System.out.println("pressed right arrow");
            Snakey.dx = Math.abs(Snakey.dx);
        }

        //below is for when to press enter to reset the game, go to the reset method above
        if(e.getKeyCode() == 10){
            if(!apple.isAlive && !orange.isAlive && !lemon.isAlive && !blueberry.isAlive){
                resetgame();
            }
        }


    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        //below is for the fruits to get squished when the mouse clicks it
int mx = e.getX();
int my = e.getY();


//Intersection #2, if you click on the fruit then you squish it
        if(apple.isAlive && apple.hitbox.contains(mx,my)){
            squished[0] = true; //when you click the box you squish the fruit
            score += 5; //add five points to the score if you can squish them
        }

        //same for the other fruits
        if(orange.isAlive && orange.hitbox.contains(mx,my)){
            squished[1] = true;
            score += 5;
        }
        if(lemon.isAlive && lemon.hitbox.contains(mx,my)){
            squished[2] = true;
            score += 5;
        }
        if(blueberry.isAlive && blueberry.hitbox.contains(mx,my)){
            squished[3] = true;
            score += 5;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}