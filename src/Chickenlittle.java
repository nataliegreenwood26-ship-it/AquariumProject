import java.awt.*;
public class Chickenlittle {
    //name of the chickenlittle object class

    //VARIABLE DECLARATION SECTION
    //Here's where I state which variables I am going to use.
    public String name;
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the hero in the x direction
    public int dy;                    //the speed of the hero in the y direction
    public int width;                   //dimensions of the character object
    public int height;                  //dimensions of the character object
    public boolean isAlive; //a boolean to say if the hero is alive or dead.
    public Rectangle hitbox; //made for collisions


    // Constructor
    public Chickenlittle(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx =8;
        dy =4;
        width = 140;
        height = 140;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {
            xpos = xpos + dx;
            ypos = ypos + dy;
            hitbox = new Rectangle(xpos, ypos, width, height);

        // visual of the object bouncing off the walls by reversing direction (dx,dy) and looks like the character bounces.

            if (xpos < 0) {
                dx = -dx;
            }

            if (xpos >= 1000 - width) {
                dx = -dx;

            }
            if (ypos < 0) {
                dy = -dy;
            }

            if (ypos >= 700 - height) {
                dy = -dy;
            }

        }




    }






