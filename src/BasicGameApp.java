
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
// Graphics Libraries

//ideas for the game
//uses either one human controlled + one automated or 2 human controlled - will do this using released etc et the beginning
//then, will allow to pick characters (maybe will make four each with different attributes)
//will set up a health bar, etc.
//collisions on the outside, jumping, etc.

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.Dimension;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^






public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;


    //Variable Definition Section
    //You can set their initial values too
    // Like Mario mario = new Mario(); //


    Mario mario = new Mario();
    Bowser bowser = new Bowser();
    Greninja greninja = new Greninja();
    Image background;

    boolean fight = false;


    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {// BasicGameApp constructor
        setUpGraphics();
        screenselect();


        //variable and objects
        //create (construct) the objects needed for the game

    }

    public void screenselect() {
        setUpGraphics();
        mario.isalive=true;
        mario = new Mario(600, 250, 0, 0, 200, 200);
        mario.name = "Mario Mario";
        mario.aliveimage = Toolkit.getDefaultToolkit().getImage("mariogame2.png");

        greninja.isalive=true;
        greninja = new Greninja(200, 250, 0, 0, 200, 200);
        greninja.name = "Greninja Greninja";
        greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("greninjagame2.png");

        bowser.isalive=false;

        background = Toolkit.getDefaultToolkit().getImage("selectscreen.jpg");

    }


    public void startFight(){
        fight = true;


        if (mario.readytoplay == true) {

            mario.xpos=200;
            mario.ypos=275;
            mario.dx=10;
            mario.dy=10;
            mario.width=50;
            mario.height=50;
            mario.name = "Mario Mario";
            mario.aliveimage = Toolkit.getDefaultToolkit().getImage("mariogame2.png");
            mario.currenthealth=3000;
            mario.attack1=100;
            mario.attack2=300;
            mario.attackSpecial=600;


            bowser = new Bowser(600, 230, 7, 7, 100, 100);
            Bowser.name = "Bowser Bowser";
            Bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("bowsergame2.png");
            bowser.currenthealth=5000;

            background = Toolkit.getDefaultToolkit().getImage("supersmash.png");
        }


        if (greninja.readytoplay == true) {

            greninja.xpos=200;
            greninja.ypos=275;
            greninja.dx=10;
            greninja.dy=10;
            greninja.width=50;
            greninja.height=50;
            greninja.name = "Greninja Greninja";
            greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("greninjagame2.png");
            greninja.currenthealth=1500;

            bowser = new Bowser(600, 230, 7, 7, 100, 100);
            Bowser.name = "Bowser Bowser";
            Bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("bowsergame2.png");
            bowser.currenthealth=5000;

            background = Toolkit.getDefaultToolkit().getImage("supersmash.png");

        }





    }

    public void healthbar(){

    }


    // end BasicGameApp constructor

    public void moveThings() {
        if (fight) {
            mario.move();
            greninja.move();
            bowser.move();
        }





        //call the move() code for each object  -

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);

        if (fight == true && mario.isalive==true){
            g.setColor(Color.red);
            g.fillRect(40,40,100,30);

            g.setColor(Color.green);
            g.fillRect(40,40, mario.currenthealth, 30);
        }

        if (fight == true && greninja.isalive==true){
            g.setColor(Color.blue);
            g.fillRect(40,40,100,30);

            g.setColor(Color.red);
            g.fillRect(40,40, greninja.currenthealth, 30);
        }

        if (fight == true ){
            g.setColor(Color.yellow);
            g.fillRect(40,40,100,30);

            g.setColor(Color.red);
            g.fillRect(40,40, bowser.currenthealth, 30);
        }


        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)

        g.drawImage(background,0,0,1000,700,null);

        if (fight == false){
            g.drawImage(mario.aliveimage,mario.xpos,mario.ypos, mario.width, mario.height, null);
            g.drawImage(greninja.aliveimage,greninja.xpos,greninja.ypos, greninja.width, greninja.height, null);
        }


       if (fight==true && mario.readytoplay== true){
           g.drawImage(mario.aliveimage,mario.xpos,mario.ypos, mario.width, mario.height, null);
       }
        if (fight==true && greninja.readytoplay== true){
            g.drawImage(greninja.aliveimage,greninja.xpos,greninja.ypos, greninja.width, greninja.height, null);
        }

        if (fight==true){
            g.drawImage(bowser.aliveimage,bowser.xpos,bowser.ypos, bowser.width, bowser.height, null);
        }








        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }














    //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
//vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv DON'T CHANGE! vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    //Declare the variables needed for the graphics
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;

    // PSVM: This is the code that runs first and automatically
    public static void main(String[] args) {
        BasicGameApp ex = new BasicGameApp();

        //creates a new instance of the game
        new Thread(ex).start();                 //creates a threads & starts up the code in the run( ) method
    }

    // main thread
    // this is the code that plays the game after you set things up
    public void run() {
        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
            healthbar();
        }
    }

    //Pauses or sleeps the computer for the amount specified in milliseconds
    public void pause(int time ) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
        }
    }

    private Image getImage(String filename){
        return Toolkit.getDefaultToolkit().getImage(filename);
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
        canvas.addMouseListener(this);
        canvas.addKeyListener(this);

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

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
            System.out.println(e.getKeyCode());
            int key = e.getKeyCode();

            if (key == 65){//a is pressed
                mario.right = true;
                greninja.right=true;

            }
            if (key==87){
                mario.up = true;
                greninja.up=true;
            }

            if (key == 83){
                mario.down = true;
                greninja.down=true;
            }

            if (key == 68){
                mario.left = true;
                greninja.left=true;
            }

            if (key == 73 && mario.isalive==true && fight == true){
                bowser.currenthealth=bowser.currenthealth- mario.attack1;
            }

            if (key == 79 && mario.isalive==true && fight == true) {
                bowser.currenthealth = bowser.currenthealth - mario.attack2;
            }
            if (key == 80 && mario.isalive==true && fight == true) {
                bowser.currenthealth = bowser.currenthealth - mario.attackSpecial;
            }

        if (key == 73 && greninja.isalive==true && fight == true){
            bowser.currenthealth=bowser.currenthealth- greninja.attack1;
        }

        if (key == 79 && greninja.isalive==true && fight == true) {
            bowser.currenthealth = bowser.currenthealth - greninja.attack2;
        }
        if (key == 80 && greninja.isalive==true && fight == true) {
            bowser.currenthealth = bowser.currenthealth - greninja.attackSpecial;
        }








    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 65){//a is pressed
            mario.right = false;
            greninja.right=false;
        }
        if (key==87){
            mario.up = false;
            greninja.up=false;
        }
        if (key == 83){
            mario.down = false;
            greninja.down=false;
        }
        if (key == 68){
            mario.left = false;
            greninja.left=false;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        if (fight == false) {
            if (mario.hitbox.contains(mouseX, mouseY)) {
                mario.readytoplay = true;
                greninja.readytoplay = false;
                startFight();
                System.out.println("Mario Selected");


            } else if (greninja.hitbox.contains(mouseX, mouseY)) {
                greninja.readytoplay = true;
                mario.readytoplay = false;
                startFight();
                System.out.println("Greninja Selected");

            }

        }


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


//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
