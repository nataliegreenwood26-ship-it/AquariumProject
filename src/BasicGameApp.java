import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
//imports that we as a class downloaded into Java

public class BasicGameApp implements Runnable {
   //The variables that we use in the project are below

	final int WIDTH = 1000; //width of window
	final int HEIGHT = 700; //height of window

   //More variables declared
	public JFrame frame;
	public Canvas canvas;
    public JPanel panel;

    //Variables below for all of our photos to import
	public BufferStrategy bufferStrategy;
	public Image chickenlittlePic;
    public Image AbbyMallardPic;
    public Image FishPic;
    public Image BackgroundPic;
    public Image Featherpic;
    public Image spaceshippic;

   //These are the characters in the game. Each one has an object that is made for them.
	private Chickenlittle chicklil;
    private AbbyMallard AbbyM;
    private Fish Fishy;
    private Feather Feathery;
    private Spaceship Spaceshippy;

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
		chickenlittlePic = Toolkit.getDefaultToolkit().getImage("chickenlittle.png"); //load the picture
        AbbyMallardPic = Toolkit.getDefaultToolkit().getImage("AbbyMallard.png"); //load the picture
        FishPic = Toolkit.getDefaultToolkit().getImage("Fish.png"); //load the picture
        BackgroundPic = Toolkit.getDefaultToolkit().getImage("Fence.png"); //load the picture
        Featherpic = Toolkit.getDefaultToolkit().getImage("Feather.png"); //load the picture
        spaceshippic = Toolkit.getDefaultToolkit().getImage("Spaceship.png"); //load the picture

        chicklil = new Chickenlittle(500,350); //creates chickenlittle object at that position (500,300)
        AbbyM = new AbbyMallard(randx, randy); //AbbyM appears somewhere random every single time the go button is pressed
        Fishy = new Fish (100,500); //creates Fish object at that position (100,500)
        Feathery = new Feather(400,200); //creates object at position shown
        Spaceshippy = new Spaceship(10,10); //creates object at position shown

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
        if (chicklil.isAlive) chicklil.move();
       if (AbbyM.isAlive) AbbyM.move();
       if (Fishy.isAlive) Fishy.move();
       if (Feathery.isAlive) Feathery.move();
        Spaceshippy.move(); //always moves spaceship
        crashing(); //calls method

	}

    public void crashing () {
        //checks if any character hits the spaceship
        // makes it so that when each character hits the spaceship they die

        if (chicklil.isAlive && chicklil.hitbox.intersects(Spaceshippy.hitbox)) {
            chicklil.dx = -chicklil.dx;
            chicklil.isAlive = false;
        }
        if (AbbyM.isAlive && AbbyM.hitbox.intersects(Spaceshippy.hitbox)){
            AbbyM.dx = -AbbyM.dx;
            AbbyM.isAlive = false;
        }
        if (Feathery.isAlive && Feathery.hitbox.intersects(Spaceshippy.hitbox)){
            Feathery.dx = -Feathery.dx;
            Feathery.isAlive = false;
        }
        if (Fishy.isAlive && Fishy.hitbox.intersects(Spaceshippy.hitbox)){
            Fishy.dx = -Fishy.dx;
            Fishy.isAlive = false;
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
        if (chicklil.isAlive) {
            g.drawImage(chickenlittlePic, chicklil.xpos, chicklil.ypos, chicklil.width, chicklil.height, null);
        }

        if (AbbyM.isAlive) {
            g.drawImage(AbbyMallardPic, AbbyM.xpos, AbbyM.ypos, AbbyM.width, AbbyM.height, null);
        }
       if(Fishy.isAlive) {
           g.drawImage(FishPic, Fishy.xpos, Fishy.ypos, Fishy.width, Fishy.height, null);
       }
       if(Feathery.isAlive) {
           g.drawImage(Featherpic, Feathery.xpos, Feathery.ypos, Feathery.width, Feathery.height, null);
       }


       //always draw the spaceship because it never dies

       g.drawImage(spaceshippic,Spaceshippy.xpos, Spaceshippy.ypos, Spaceshippy.width, Spaceshippy.height, null);


       //the line below tells us that if all of the objects/characters are dead (except spaceship) then a new image should flash on the screen

       if (chicklil.isAlive == false && AbbyM.isAlive ==false && Fishy.isAlive == false && Feathery.isAlive == false){
           g.drawImage(Toolkit.getDefaultToolkit().getImage("Alien.png"),0,0,1000,700, null);

       }



        g.dispose();

		bufferStrategy.show();
	}
}