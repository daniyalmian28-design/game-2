
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
import java.awt.image.BufferStrategy;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^






public class BasicGameApp implements Runnable, KeyListener {

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


    public electing(){
        boolean selectScreen = true;

    }













    // Initialize your variables and construct your program objects here.
    public BasicGameApp() { // BasicGameApp constructor
        setUpGraphics();
        mario = new Mario(500,350,10,10,50,50);
        mario.name = "Mario Mario";
        mario.aliveimage = Toolkit.getDefaultToolkit().getImage("mariogame2.png");

        greninja = new Greninja(200,300,10,10,50,50);
        greninja.name = "Greninja Greninja";
        greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("greninjagame2.png");


        bowser = new  Bowser(600,100,7,7,100,100);
        Bowser.name = "Bowser Bowser";
        Bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("bowsergame2.png");

        background=Toolkit.getDefaultToolkit().getImage("supersmash.png");







        //variable and objects
        //create (construct) the objects needed for the game





    }
    // end BasicGameApp constructor

    public void moveThings() {
        //call the move() code for each object  -

    }

    //Paints things on the screen using bufferStrategy
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);



        //draw the images
        // Signature: drawImage(Image img, int x, int y, int width, int height, ImageObserver observer)

        g.drawImage(background,0,0,1000,700,null);
        g.drawImage(mario.aliveimage,mario.xpos,mario.ypos, mario.width, mario.height, null);
        if (mario.isalive == false) {
            mario.aliveimage = Toolkit.getDefaultToolkit().getImage("gravestone.png");
            ;
            mario.dx = 0;
            mario.dy = 0;
        }

        g.drawImage(bowser.aliveimage,bowser.xpos,bowser.ypos, bowser.width, bowser.height, null);
        if (bowser.isalive == false) {
            bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("gravestone.png");
            ;
            bowser.dx = 0;
            bowser.dy = 0;
        }

        g.drawImage(greninja.aliveimage,greninja.xpos,greninja.ypos, greninja.width, greninja.height, null);
        if (greninja.isalive == false) {
            greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("gravestone.png");
            ;
            greninja.dx = 0;
            greninja.dy = 0;
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
        Selecting
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

            }
            if (key==87){
                mario.up = true;
            }

            if (key == 83){
                mario.down = true;
            }

            if (key == 68){
                mario.left = true;
            }






    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 65){//a is pressed
            mario.right = false;
        }
        if (key==87){
            mario.up = false;
        }
        if (key == 83){
            mario.down = false;
        }
        if (key == 68){
            mario.left = false;
        }

    }
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
}
