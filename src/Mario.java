import java.awt.*;
//this is the code for mario projectile 1 - there is absolutely nothing unique about this code.
// it is standard movement, (dx dy) position, is alive, hitbox, etc- Daniyal
public class Mario {
    String name;
    Image aliveimage;
    Image deadimage;
    int xpos;
    int ypos;
    int speed;
    double dx;
    double dy;
    int width;
    int height;
    Rectangle hitbox;
    boolean isalive = true;
    boolean readytoplay;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;
    double gravity;
    int jump;


    int currenthealth;
    int attack1int;
    boolean attack1;
    int attack2int;
    boolean attack2;
    int attackSpecialint;
    boolean attackSpecial;


    public Mario() {
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public Mario(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput) {
        this.xpos = xposInput;
        this.ypos = yposInput;
        this.dx = dxInput;
        this.dy = dyInput;
        this.width = widthInput;
        this.height = heightInput;
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
        this.gravity = 0.9;
        this.jump = 14;
    }
////details how projectile moves using dx (horizontal speed) and dy (vertical movement)if (this.up) {
    public void move() {

            this.dy+=this.gravity; // google used to help with gravity, also used this website: https://www.w3schools.com/graphics/game_gravity.asp
        if (this.up) {
            if ( ypos>=290){
                this.dy=-this.jump;
            }

            this.up=false;
        }
        this.ypos += (int)this.dy;

        if (this.ypos >= 290){ //restricts ypos
            this.ypos=290;
            this.dy=0;

        }





        if (this.down) {
            this.ypos += (int)this.dy;
        }

        if (this.left) {
            this.xpos += (int)this.dx;
        }

        if (this.right) {
            this.xpos -= (int)this.dx;
        }

        if (this.xpos <= 200) {
            xpos = 200;
        } else if (this.xpos <= 0) { //restriction on xpos
            this.dx = -this.dx;
        }

        if (this.ypos >= 290) {
            ypos = 290;
        } else if (this.ypos <=290) {

        }

        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public static void main(String[] args) {
    }
}
