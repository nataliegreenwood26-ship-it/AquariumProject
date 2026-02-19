import java.awt.*;
public class Feather {

    //VARIABLE DECLARATION SECTION
    //Here's where I state which variables I am going to use.
    public String name;
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the object in the x direction
    public int dy;                    //the speed of the object in the y direction
    public int width;
    public int height;
    public boolean isAlive;//a boolean to denote if the object is alive or dead.
    public Rectangle hitbox; //made for collisions
    public boolean isCrashing;

    //constructor
    public Feather(int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 4;
        dy = 4;
        width = 140;
        height = 140;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);

        isCrashing = false;

    }

    //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
    public void move() {

            xpos = xpos + dx;
            ypos = ypos + dy;
            hitbox = new Rectangle(xpos, ypos, width, height);

            //made below to show the character bouncing off the walls of the border

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









