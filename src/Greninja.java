import java.awt.*;

public class Greninja {
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
    boolean readytoplay;
    boolean isalive = true;
    boolean up = false;
    boolean down = false;
    boolean left = false;
    boolean right = false;

    int currenthealth;
    int attack1;
    int attack2;
    int attackSpecial;

    public Greninja() {
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public Greninja(int xposInput, int yposInput, double dxInput, double dyInput, int widthInput, int heightInput) {
        this.xpos = xposInput;
        this.ypos = yposInput;
        this.dx = dxInput;
        this.dy = dyInput;
        this.width = widthInput;
        this.height = heightInput;
        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public void move() {
        if (this.up) {
            this.ypos -= (int)this.dy;
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

        if (this.xpos >= 1000) {
            this.dx = -this.dx;
        } else if (this.xpos <= 0) {
            this.dx = -this.dx;
        }

        if (this.ypos >= 700) {
            this.dy = -this.dy;
        } else if (this.ypos <= 0) {
            this.dy = -this.dy;
        }

        this.hitbox = new Rectangle(this.xpos, this.ypos, this.width, this.height);
    }

    public static void main(String[] args) {
    }
}
