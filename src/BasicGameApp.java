import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//imports that we as a class downloaded into Java

public class BasicGameApp implements Runnable, KeyListener, MouseListener {
   //The variables that we use in the project are below

	final int WIDTH = 1000; //width of window
	final int HEIGHT = 700; //height of window
    int score = 0;

   //More variables declared
	public JFrame frame;
	public Canvas canvas;
    public JPanel panel;

    //Variables below for all of our photos to import
	public BufferStrategy bufferStrategy;
    public Image applePic;
    public Image apple2Pic;
    public Image apple3Pic;
    public Image BackgroundPic;
    public Image Featherpic;
    public Image snakepic;

   //These are the characters in the game. Each one has an object that is made for them.
	private apple1 apple;
    private apple2 apple2;
    private apple3 apple3;
    private Feather Feathery;
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
        apple2Pic = Toolkit.getDefaultToolkit().getImage("apple.png"); //load the picture
        apple3Pic = Toolkit.getDefaultToolkit().getImage("apple.png"); //load the picture
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("CheckeredBackground.png"); //load the picture
        Featherpic = Toolkit.getDefaultToolkit().getImage("apple.png"); //load the picture
        snakepic = Toolkit.getDefaultToolkit().getImage("snake.png"); //load the picture

        apple = new apple1(200,350); //creates chickenlittle object at that position (500,300)
        apple2 = new apple2(randx, randy); //AbbyM appears somewhere random every single time the go button is pressed
        apple3 = new apple3 (100,500); //creates Fish object at that position (100,500)
        Feathery = new Feather(400,200); //creates object at position shown
        Snakey = new Snake(10,10); //creates object at position shown

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
       if (apple2.isAlive) apple2.move();
       if (apple3.isAlive) apple3.move();
       if (Feathery.isAlive) Feathery.move();
        Snakey.move(); //always moves spaceship
        crashing(); //calls method

	}

    public void crashing () {
        //checks if any character hits the spaceship
        // makes it so that when each character hits the spaceship they die

        if (apple.isAlive && apple.hitbox.intersects(Snakey.hitbox)) {
            apple.dx = -apple.dx;
            apple.isAlive = false;
            score++;
        }
        if (apple2.isAlive && apple2.hitbox.intersects(Snakey.hitbox)){
            apple2.dx = -apple2.dx;
            apple2.isAlive = false;
            score++;
        }
        if (Feathery.isAlive && Feathery.hitbox.intersects(Snakey.hitbox)){
            Feathery.dx = -Feathery.dx;
            Feathery.isAlive = false;
            score++;
        }
        if (apple3.isAlive && apple3.hitbox.intersects(Snakey.hitbox)){
            apple3.dx = -apple3.dx;
            apple3.isAlive = false;
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
        g.drawImage(BackgroundPic, 0, 0,WIDTH, HEIGHT, null);


        //if objects are alive then draw their images, once they die make it so they dont show up on the screen
        if (apple.isAlive) {
            g.drawImage(applePic, apple.xpos, apple.ypos, apple.width, apple.height, null);
        }

        if (apple2.isAlive) {
            g.drawImage(apple2Pic, apple2.xpos, apple2.ypos, apple2.width, apple2.height, null);
        }
       if(apple3.isAlive) {
           g.drawImage(apple3Pic, apple3.xpos, apple3.ypos, apple3.width, apple3.height, null);
       }
       if(Feathery.isAlive) {
           g.drawImage(Featherpic, Feathery.xpos, Feathery.ypos, Feathery.width, Feathery.height, null);
       }


       //always draw the spaceship because it never dies

       g.drawImage(snakepic,Snakey.xpos, Snakey.ypos, Snakey.width, Snakey.height, null);


       //the line below tells us that if all of the objects/characters are dead (except spaceship) then a new image should flash on the screen

       if (apple.isAlive == false && apple2.isAlive ==false && apple3.isAlive == false && Feathery.isAlive == false){
           g.drawImage(Toolkit.getDefaultToolkit().getImage("GameOver.png"),0,0,1000,700, null);

       }




        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD,50));
        g.drawString("Score:"+ score, 20, 40);

        g.dispose();
		bufferStrategy.show();
	}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key typed"+e.getKeyCode());
        //up arrow is 38
        if(e.getKeyCode() == 38){
            System.out.println("pressed up arrow");
            Snakey.dy = -2;
        }
        if(e.getKeyCode() == 40){
            System.out.println("pressed down arrow");
            Snakey.dy = -10;
        }

        if(e.getKeyCode() == 37){
            System.out.println("pressed left arrow");
            Snakey.dx = -Math.abs(Snakey.dx);
        }

        if(e.getKeyCode() == 39){
            System.out.println("pressed right arrow");
            Snakey.dx = Math.abs(Snakey.dx);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

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