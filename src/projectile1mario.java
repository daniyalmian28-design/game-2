import java.awt.*;
//this is the code for mario projectile 1 - there is absolutely nothing unique about this code.
// it is standard movement, (dx dy) position, is alive, hitbox, etc- Daniyal
public class projectile1mario {
    String name;
    int xpos;
    int ypos;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    Image image;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    boolean isalive;

    public projectile1mario() {
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public projectile1mario(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput) {
        this.xpos = xposInput;
        this.ypos = yposInput;
        this.dx = dxInput;
        this.dy = dyInput;
        this.width = widthInput;
        this.height = heightInput;
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);

    }



    public void move() { //details how projectile moves using dx (horizontal speed) and dy (vertical movement)
        if (this.up) {
            this.ypos -= (int)this.dy;
        }

        if (this.down) {
            this.ypos += (int)this.dy;
        }

        if (this.left) {
            this.xpos -= (int)this.dx;
        }

        if (this.right) {
            this.xpos += (int)this.dx;
        }



        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height); //outlines the hitbox
    }

    public static void main(String[] args) {
    }
}





