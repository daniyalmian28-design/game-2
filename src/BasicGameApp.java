
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
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Toolkit;
//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

//This is a supersmash inspired game. If I had had more time, I would have added controls for Greninja,
//but as it stands, only Mario is playable.Some Greninja code that I started with is in here, which
//might make reading through it slightly complicated - sorry about that. Please enjoy! - Daniyal




public class BasicGameApp implements Runnable, KeyListener, MouseListener {

    //Sets the width and height of the program window
    final int WIDTH = 1000;
    final int HEIGHT = 700;





    Mario mario = new Mario(); //Mario is one of the playable characters in this game (the only one at this time)
    Bowser bowser = new Bowser(); // Bowser is the villan of the game
    Greninja greninja = new Greninja(); // Greninja will be added in the future!
    projectile1mario Projectile1mario; // this is a projectile that gets launched during one of Mario's attacks - basic attack
    projectile2mario Projectile2mario; // this is a projectile that gets launched during one of Mario's attacks - back up attack
    projectileSpecialmario ProjectileSpecialmario; // this is a projectile that gets launched during one of Mario's attacks - it does the most damage
    ProjectileBowser projectileBowser;// this is a projectile that gets launched during one of Bowser's attacks. Each of the following bowser projectiles
                                        // is the same, they exist so that they can be fired off simultaneously
    ProjectileBowser projectileBowser2;
    ProjectileBowser projectileBowser3;
    ProjectileBowser projectileBowser4;
    ProjectileBowser projectileBowser5;
    ProjectileBowser projectileBowser6;
    Toolkit toolkit; // not specific to my game :)
    Timer timer; // I learned about timer timer through some online research (sited later on). It is used to schedule bowser's main attack on a 1 second basis.
    public String [] endquotes; // this is my array. It provides motivational quotes after the battle

    Image background; // background image
    int randomInt; //used several times - sometimes it is used to determine whether an attack is going to happen
                    // It is also used for my array
    boolean gameover; // boolean to define the end of the game (when one of the characters die)
    boolean greninjaclicked=false; // temporary boolean - used in code to help show thar greninja is not playable yet


    boolean fight = false; // helpful for the selection screen - indicates that the fight has not started


    // Initialize your variables and construct your program objects here.
    public BasicGameApp() {// BasicGameApp constructor
        setUpGraphics();
        screenselect();



        //variable and objects
        //create (construct) the objects needed for the game

    }


    public void screenselect() { //this method details the character selection process. Right now, you can only select mario,
                                // but nevertheless, the screen still appears to show what the game will eventually look like.
        setUpGraphics();
        mario.isalive = true;
        mario = new Mario(600, 250, 0, 0, 200, 200); // this is the mario that spawns during selection... he is larger than playable mario
        mario.name = "Mario Mario";
        mario.aliveimage = Toolkit.getDefaultToolkit().getImage("mariogame2.png");

        greninja.isalive = true;
        greninja = new Greninja(200, 250, 0, 0, 200, 200); // again, this is selection Greninja
        greninja.name = "Greninja Greninja";
        greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("greninjagame2.png");

        bowser.isalive = false;

        background = Toolkit.getDefaultToolkit().getImage("selectscreen.jpg");

    }


    public void startFight() { // this method triggers the fight based on which character was selected - it then spawns in
                                // the characters in their starting locations
        fight = true; // means fight will occur
        repeatingdoom(); // this is fun - it is the bowser attack method


        if (mario.readytoplay) { //if mario selected, mario spawns

            mario.xpos = 200;
            mario.ypos = 275;
            mario.dx = 5;
            mario.dy = 5;
            mario.width = 50;
            mario.height = 50;
            mario.name = "Mario Mario";
            mario.aliveimage = Toolkit.getDefaultToolkit().getImage("mariogame2.png");
            mario.currenthealth = 110;
            mario.attack1int = 5;
            mario.attack2int = 10;
            mario.attackSpecialint = 15;


            bowser = new Bowser(700, 230, 7, 7, 100, 100);
            bowser.name = "Bowser Bowser";
            bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("bowsergame2.png");
            bowser.currenthealth = 100;


            background = Toolkit.getDefaultToolkit().getImage("supersmash.png");
        }


        if (greninja.readytoplay) { //if greninja selected, greninja spawns - code is here but not playable yet

            greninja.xpos = 200;
            greninja.ypos = 275;
            greninja.dx = 10;
            greninja.dy = 10;
            greninja.width = 50;
            greninja.height = 50;
            greninja.name = "Greninja Greninja";
            greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("greninjagame2.png");
            greninja.currenthealth = 100;

            bowser = new Bowser(600, 230, 7, 7, 100, 100);
            bowser.name = "Bowser Bowser";
            bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("bowsergame2.png");
            bowser.currenthealth = 100;

            background = Toolkit.getDefaultToolkit().getImage("supersmash.png");
        }
    }


    public void checkCollision() { //check collision is hugely important for my game - it monitors all collisions between objects
                                    // this method specifically outlines what happens when projectiles intersect with a character for example.
        if (Projectile1mario != null) {

            if (Projectile1mario.hitbox.intersects(bowser.hitbox)) {
                bowser.currenthealth = bowser.currenthealth - mario.attack1int;
                Projectile1mario =null;
            }

        }
        if (Projectile2mario != null) {

            if (Projectile2mario.hitbox.intersects(bowser.hitbox) ) {
                bowser.currenthealth = bowser.currenthealth - mario.attack2int;
                Projectile2mario = null;
            }

        }
        if (ProjectileSpecialmario != null) {

            if (ProjectileSpecialmario.hitbox.intersects(bowser.hitbox)) {
                bowser.currenthealth = bowser.currenthealth - mario.attackSpecialint;
                ProjectileSpecialmario  = null;
            }

        }

        if ( projectileBowser!= null){
            if (projectileBowser.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser = null;
            }
        }
        if ( projectileBowser2!= null){
            if (projectileBowser2.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser2 = null;
            }
        }
        if ( projectileBowser3!= null){
            if (projectileBowser3.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser3 = null;
            }
        }
        if ( projectileBowser4!= null){
            if (projectileBowser4.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser4 = null;
            }
        }
        if ( projectileBowser5!= null){
            if (projectileBowser5.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser5 = null;
            }
        }
        if ( projectileBowser6!= null){
            if (projectileBowser6.hitbox.intersects(mario.hitbox)){
                mario.currenthealth=mario.currenthealth-10;
                projectileBowser6 = null;
            }
        }

        if (mario.hitbox.intersects(bowser.hitbox)) {
            mario.xpos = 200;
            mario.ypos = 0;
            mario.currenthealth = mario.currenthealth - 10;

        }

       if (fight)
       {        if (mario.currenthealth<=0 && bowser.isalive){
            mario.isalive=false;
            gameover = true;

           mario.aliveimage = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           greninja.aliveimage= Toolkit.getDefaultToolkit().getImage("invisible2.png");
          /* projectileBowser.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           projectileBowser2.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           projectileBowser3.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           projectileBowser4.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           projectileBowser5.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
           projectileBowser6.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");*/




           mario.dx = 0;
                mario.dy = 0;

        }
        if (bowser.currenthealth<=0 && mario.isalive) {
            fight = false;
            bowser.isalive = false;
            gameover = true;

            mario.aliveimage = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            bowser.aliveimage = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            greninja.aliveimage= Toolkit.getDefaultToolkit().getImage("invisible2.png");
           /* projectileBowser.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            projectileBowser2.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            projectileBowser3.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            projectileBowser4.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            projectileBowser5.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");
            projectileBowser6.image = Toolkit.getDefaultToolkit().getImage("invisible2.png");*/




            bowser.dx = 0;
                bowser.dy = 0;

        }

        }

    }


    // end BasicGameApp constructor

    public void moveThings() {
        if (fight) {
            mario.move();
            greninja.move();
            bowser.move();




            if (Projectile1mario != null){ // != null was notation I did not know. I asked my dad to help me think about how to write this code, and I also did some research: (https://www.upwork.com/resources/what-is-null-in-java)
            Projectile1mario.move(); // basically, this and the code below means that if the projectile is not null,it moves in its given direction

            }
            if (Projectile2mario != null){ //
                Projectile2mario.move();
                }

            if (ProjectileSpecialmario != null){
                ProjectileSpecialmario.move();
            }

            if (projectileBowser != null){ // same as above from Mario but instead for Bowser, all his many projectiles
                projectileBowser.move();
            }
            if (projectileBowser2 != null){
                projectileBowser2.move();
            }
            if (projectileBowser3 != null){
                projectileBowser3.move();
            }
            if (projectileBowser4 != null){
                projectileBowser4.move();
            }
            if (projectileBowser5 != null){
                projectileBowser5.move();
            }
            if (projectileBowser6 != null){
                projectileBowser6.move();
            }



        } // all objects in the code that move have move code here... some is repetetive so I did not give individual attention

    }

    //Paints things on the screen using bufferStrategy
    private void render() { //method for rendering graphics into the game - not much else to add
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.drawImage(background,0,0,1000,700,null);

        if (Projectile1mario != null) { // to reitierate, != null was notation I did not know until my dad helped me here is the source again- (https://www.upwork.com/resources/what-is-null-in-java)
            g.drawImage(Projectile1mario.image, Projectile1mario.xpos, Projectile1mario.ypos, Projectile1mario.width, Projectile1mario.height, null); //meaning if projectile is not null, it's position, size, etc are defined
        }
        if (Projectile2mario != null) {
            g.drawImage(Projectile2mario.image, Projectile2mario.xpos, Projectile2mario.ypos, Projectile2mario.width, Projectile2mario.height, null);
        }

        if (ProjectileSpecialmario != null) {
            g.drawImage(ProjectileSpecialmario.image, ProjectileSpecialmario.xpos, ProjectileSpecialmario.ypos, ProjectileSpecialmario.width, ProjectileSpecialmario.height, null);
        }

        if (projectileBowser != null&&!gameover){
            projectileBowser.move();
        }
        if (projectileBowser2 != null&&!gameover){
            g.drawImage(projectileBowser2.image, projectileBowser2.xpos,projectileBowser2.ypos,projectileBowser2.height,projectileBowser2.width, null);
        }
        if (projectileBowser3 != null&&!gameover){
            g.drawImage(projectileBowser3.image, projectileBowser3.xpos,projectileBowser3.ypos,projectileBowser3.height,projectileBowser3.width, null);
        }
        if (projectileBowser4 != null&&!gameover){
            g.drawImage(projectileBowser4.image, projectileBowser4.xpos,projectileBowser4.ypos,projectileBowser4.height,projectileBowser4.width, null);
        }
        if (projectileBowser5 != null&&!gameover){
            g.drawImage(projectileBowser5.image, projectileBowser5.xpos,projectileBowser5.ypos,projectileBowser5.height,projectileBowser5.width, null);
        }
        if (projectileBowser6 != null&&!gameover){
            g.drawImage(projectileBowser6.image, projectileBowser6.xpos,projectileBowser6.ypos,projectileBowser6.height,projectileBowser6.width, null);
        }


        if (!fight){ // if there is no fight, draw the main characters on spawn screen
            g.drawImage(mario.aliveimage,mario.xpos,mario.ypos, mario.width, mario.height, null);
            g.drawImage(greninja.aliveimage,greninja.xpos,greninja.ypos, greninja.width, greninja.height, null);

        }

        if (fight) { //this is some of my favorite code - it is specifically for healthbars


            if (mario.isalive && mario.readytoplay) { //if mario is the fighter - give a red background bar
                g.setColor(Color.red);
                g.fillRect(40, 40, 100, 30);



                g.setColor(Color.green); // then this bar decreases as mario loses health, fun effect
                g.fillRect(40, 40, mario.currenthealth, 30);
            }

            if (greninja.isalive & greninja.readytoplay) { //same is true for greninja (not yet)
                g.setColor(Color.blue);
                g.fillRect(40, 40, 100, 30);



                g.setColor(Color.red);
                g.fillRect(40, 40, greninja.currenthealth, 30);
            }

        }

        if (fight){ //this is for bowser, exists in every fight
            g.setColor(Color.yellow);
            g.fillRect(800,40,100,30);

            g.setColor(Color.red);
            g.fillRect(800,40, bowser.currenthealth, 30);
        }







       if (fight && mario.readytoplay){ //the below define mario, greninja, and bowser if they are selected to fight
           g.drawImage(mario.aliveimage,mario.xpos,mario.ypos, mario.width, mario.height, null);
       }
        if (fight && greninja.readytoplay){
            g.drawImage(greninja.aliveimage,greninja.xpos,greninja.ypos, greninja.width, greninja.height, null);
        }

        if (fight){
            g.drawImage(bowser.aliveimage,bowser.xpos,bowser.ypos, bowser.width, bowser.height, null);
        }
        if (gameover){ //this is code for if the game ends, and the screen it would take you too - my favorite part

           background = Toolkit.getDefaultToolkit().getImage("endscreen.png"); //new background
            Font myFont = new Font ("Times New Roman",1,100); //this is code I had to research - lets me have custom font: https://stackoverflow.com/questions/18249592/how-to-change-font-size-in-drawstring-java
            g.setFont (myFont);
            g.setColor(Color.RED); //assigns color to the font
            g.drawString("Game Over!",250,350); // helped me learn g.drawstring - https://stackoverflow.com/questions/28051336/jframe-how-to-display-text-without-jtextarea-jtextfield

            Font myFont2 = new Font ("Times New Roman",1,50); // this code is for my array, which shows motivational quotes at the end - which is why it appears in game over
            g.setFont (myFont2);
            g.drawString(endquotes[randomInt], 380,500);  // the array part specfically


            g.setColor(Color.black); //this covers up the health bars after the game ends - not sure how to make them go away
            g.fillRect(40, 40, 100, 30);
            g.setColor(Color.black);
            g.fillRect(800, 40, 100, 30);

        }
        if (greninjaclicked){ // temporary code to emphasize that Greninja is not ready!
            Font myFont = new Font ("Times New Roman",1,40); // https://stackoverflow.com/questions/18249592/how-to-change-font-size-in-drawstring-java
            g.setFont (myFont);
            g.setColor(Color.RED);
            g.drawString("feature in development!",250,500); // helped me learn g.drawstring - https://stackoverflow.com/questions/28051336/jframe-how-to-display-text-without-jtextarea-jtextfield


        }







        // Keep the code below at the end of render()
        g.dispose();
        bufferStrategy.show();
    }

    public void repeatingdoom(){  //this code I developed for bowsers unique attack which repeats on a given basis: //https://crunchify.com/java-timer-and-timertask-reminder-class-tutorials-example/
        toolkit = Toolkit.getDefaultToolkit();  // here is another link// https://stackoverflow.com/questions/9053383/creating-a-repeating-timer-reminder-in-java
        timer=new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                attack(); //details the specifics of bowser's attack

            }
        }, 1000,1200); //delay is initial time for first attack, period is time between attacks
    }

    public void attack(){ // this is all the code outlining the direction that Bowsers flaming logs go in... i alteded speed, and direction as seen below
        projectileBowser  = new ProjectileBowser(bowser.xpos, bowser.ypos, 10, 0, 60, 60);
        projectileBowser.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser.left = true;
        projectileBowser.down = false;
        projectileBowser.up=true;

        projectileBowser2 = new ProjectileBowser(bowser.xpos, bowser.ypos, 10, 5, 60, 60);
        projectileBowser2.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser2.left = true;
        projectileBowser2.down = false;
        projectileBowser2.up=true;

        projectileBowser3 = new ProjectileBowser(bowser.xpos, bowser.ypos, 17, 0, 60, 60);
        projectileBowser3.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser3.left = true;
        projectileBowser3.down = false;
        projectileBowser3.up=true;

        projectileBowser4 = new ProjectileBowser(bowser.xpos, 290, 10, 20, 60, 60);
        projectileBowser4.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser4.left = true;
        projectileBowser4.down = false;
        projectileBowser4.up=false;


        projectileBowser5 = new ProjectileBowser(bowser.xpos, bowser.ypos, 10, 2, 60, 60);
        projectileBowser5.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser5.left = true;
        projectileBowser5.down = false;
        projectileBowser5.up=true;


        projectileBowser6 = new ProjectileBowser(bowser.xpos, bowser.ypos, 10, 3, 60, 60);
        projectileBowser6.image = Toolkit.getDefaultToolkit().getImage("flaminglog.png");
        projectileBowser6.left = true;
        projectileBowser6.down = false;
        projectileBowser6.up=true;






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
    public void run() { // i initially put my array in the gameover section of render, but that caused it to glitch. I actually only
                        // need the array to run once then show up later so I put it here to avoid the glitch. I think the problem was with while true
                        // and that it would cause the array to loop infinitely. this caused it to constantly change the quote on the screen.

        endquotes = new String [4]; // this array shows different motivational quotes.
        endquotes[0]="Good Game";
        endquotes[1]="well played";
        endquotes[2]="nice fight";
        endquotes[3]= "rematch?";
        randomInt = (int)(Math.random()*4);

        //for the moment we will loop things forever.
        while (true) {
            moveThings();  //move all the game objects
            render();  // paint the graphics
            pause(10); // sleep for 10 ms
            checkCollision();
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
        frame = new JFrame("Application Tem" +
                "plate");   //Create the program window or frame.  Names it.

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


        /*if (gameover){ -- was trying to figure this out but it was getting pretty complicated
        I would like to learn this at some point though: https://www.youtube.com/watch?v=FR2UptJyaSM
            label=true;
            JLabel label = new JLabel();
            label.setText(" winner " );
            label.setHorizontalTextPosition(JLabel.CENTER);
            label.setForeground(Color.RED);
            label.setFont(new Font("Super Pixel",Font.BOLD,30));
            label.setBackground(Color.BLACK);
            label.setOpaque(true);
            label.setBounds(0,0,1000,700);
            frame.add(label);


        }*/







    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) { //these are standard movement keys for characters directions
            System.out.println(e.getKeyCode());
            int key = e.getKeyCode();

            if (key == 65){//a is pressed
                mario.right = true;
                greninja.right=true;

            }
            if (key==87 && mario.ypos==290){
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

            if (key == 73 && mario.isalive && fight){ // this is for attack1. the reason fight is added here is so that mario cannot attack on loading screen - this is basically the same as the code below for other attacks
                boolean attack1successful;


                randomInt = (int)(Math.random()*2); // this is to add a degree of difficulty to the game. if random int isn't 0 the attack fails. you can see the same for the other attacks below
                if (randomInt == 0) {
                     attack1successful = true;
                }
                else {
                    attack1successful = false;

                }

                if (attack1successful){
                    Projectile1mario = new projectile1mario(mario.xpos, mario.ypos, 10, 10, 30, 60);
                    Projectile1mario.image = Toolkit.getDefaultToolkit().getImage("marioattack1.png");
                    Projectile1mario.right=true;


                }


            }

            if (key == 79 && mario.isalive && fight) { //different attack, see overview above
                boolean attack2successful;

                randomInt = (int)(Math.random()*2);
                if (randomInt == 0) {
                    attack2successful = true;
                }
                else {
                    attack2successful = false;

                }

                if (attack2successful){
                    Projectile2mario = new projectile2mario(mario.xpos, mario.ypos, 10, 10, 30, 60);
                    Projectile2mario.image = Toolkit.getDefaultToolkit().getImage("fireball.png");
                    Projectile2mario.right=true;



                }



            }
            if (key == 80 && mario.isalive && fight) { //again, differeny attack see overview above.
                boolean attackSpecialsuccessful;

                randomInt = (int)(Math.random()*2);
                if (randomInt == 0) {
                    attackSpecialsuccessful = true;
                }
                else {
                    attackSpecialsuccessful = false;

                }

                if (attackSpecialsuccessful){
                    ProjectileSpecialmario = new projectileSpecialmario(mario.xpos, mario.ypos-30, 7, 7, 50, 90);
                    ProjectileSpecialmario.image = Toolkit.getDefaultToolkit().getImage("special attack.png");
                    ProjectileSpecialmario.right=true;



                }
            }




        /*if (key == 73 && greninja.isalive && fight){
            bowser.currenthealth=bowser.currenthealth- greninja.attack1;
        }

        if (key == 79 && greninja.isalive && fight) {
            bowser.currenthealth = bowser.currenthealth - greninja.attack2;
        }
        if (key == 80 && greninja.isalive && fight) {
            bowser.currenthealth = bowser.currenthealth - greninja.attackSpecial;
        }*/








    }

    @Override
    public void keyReleased(KeyEvent e) { //basically this shows that when you release the key, the character
                                            // stops doing what it was doing in pressed
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

        if (!fight && !gameover) { //if click on mario, mario fights
            if (mario.hitbox.contains(mouseX, mouseY)) {
                greninjaclicked=false;
                mario.readytoplay = true;
                greninja.readytoplay = false;
                startFight();
                System.out.println("Mario Selected");


            } else if (greninja.hitbox.contains(mouseX, mouseY)) { //if click on greninja right now, a red x appears stopping you because he isnt ready yet
                greninjaclicked=true;
                greninja.aliveimage = Toolkit.getDefaultToolkit().getImage("block.jpg");








                // greninja.readytoplay = true;
               // mario.readytoplay = false;
                //startFight();
              //  System.out.println("Greninja Selected");

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
