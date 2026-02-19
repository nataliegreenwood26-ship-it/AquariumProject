import java.awt.*;
public class AbbyMallard {

    //VARIABLE DECLARATION SECTION
        //Here's where I state which variables i am going to use.
        public String name;
        public int xpos;                //the x position
        public int ypos;                //the y position
        public int dx;                    //the speed of the object in the x direction
        public int dy;                    //the speed of the object in the y direction
        public int width;
        public int height;
        public boolean isAlive;
        public Rectangle hitbox; //made for collisions
        public boolean isCrashing;//a boolean to denote if the hero is alive or dead.


       //constructor
        public AbbyMallard(int pXpos, int pYpos) {
            xpos = pXpos;
            ypos = pYpos;
            dx =4;
            dy =6;
            width = 140;
            height = 140;
            isAlive = true;
            hitbox = new Rectangle(xpos,ypos,width,height);
            isCrashing = false;

        }

        //The move method.  Everytime this is run (or "called") the hero's x position and y position change by dx and dy
        public void move() {

                xpos = xpos + dx;
                ypos = ypos + dy;
                hitbox = new Rectangle(xpos, ypos, width, height);

                //created below for the visual of the object bouncing off the walls

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








