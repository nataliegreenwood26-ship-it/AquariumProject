import java.awt.*;
public class Snake {

    //Here's where I state which variables I am going to use.
    public String name;
    public int xpos;                //the x position
    public int ypos;                //the y position
    public int dx;                    //the speed of the object in the x direction
    public int dy;                    //the speed of the object in the y direction
    public int width;
    public int height;
    public boolean isAlive; //a boolean to denote if the object is alive or dead.
    public Rectangle hitbox; //created for collisions

 //Constructor
    public Snake (int pXpos, int pYpos) {
        xpos = pXpos;
        ypos = pYpos;
        dx = 4;
        dy = 0;
        width = 200;
        height = 200;
        isAlive = true;
        hitbox = new Rectangle(xpos, ypos, width, height);

    }

    //The move method.  Everytime this is run (or "called") the character's x position and y position change by dx and dy
    public void move() {
        xpos = xpos + dx;
        ypos = ypos + dy;

        //a wrapping effect is made below. this is because when the character goes off the screen the position is changed to the opposite side to make a wrapping.

        if (xpos > 1000) {
            xpos = -width;
        }

        if (xpos < -width) {
            xpos = 1000;
        }

        if(ypos > 700){
            ypos = -height;
        }

        if(ypos < -height){
            ypos = 700;
        }


        hitbox = new Rectangle(xpos, ypos, width, height);
    }
}












