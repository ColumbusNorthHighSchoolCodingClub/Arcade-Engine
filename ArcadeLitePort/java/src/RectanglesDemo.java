package src;
/**
 * Class ArcadeDemo
 * This class contains demos of many of the things you might
 * want to use to make an animated arcade game.
 * 
 * Adapted from the AppletAE demo from years past. 
 * 
 * 
 */


import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.applet.AudioClip;   
import java.util.ArrayList;

import com.arcadeengine.AnimationPanel;


public class RectanglesDemo extends AnimationPanel 
{

    //Constants
    //-------------------------------------------------------

    //Instance Variables
    //-------------------------------------------------------
    ArrayList<Projectile> laserArray = new ArrayList<Projectile>();
    ArrayList<PongBall> ballArray = new ArrayList<PongBall>();
    //Constructor
    //-------------------------------------------------------
    public RectanglesDemo()
    {   //Enter the name and width and height.  
        super("RectanglesDemo", 640, 480);
    }
       
    //The renderFrame method is the one which is called each time a frame is drawn.
    //-------------------------------------------------------
    public Graphics renderFrame(Graphics g) 
    {
        //Draw a ball that bounces around the screen.
        for(PongBall b : ballArray)
        {
            g.drawImage(ballImage,b.getX(),b.getY(),this);
            b.animate();
        }

        //Draw any 'lasers' that have been fired (spacebar).
        for(Projectile p : laserArray)
        {
            //Drawing element z from the array
            g.setColor(Color.YELLOW);
            g.fillRect(p.getX(), p.getY(), p.WIDTH ,p.HEIGHT);
            p.animate();
        }
        
        //Check for interactions
        for(int x=ballArray.size()-1; x>=0; x--)  //count down so the renumbering after removal won't affect us.
        {
            for(int z=laserArray.size()-1; z>=0; z--) //For each ball, we check each projectile.
            {
                if(x < ballArray.size() && z < laserArray.size()) //Make sure there's some left!
                {
                    if( ballArray.get(x).getRectangle().intersects(laserArray.get(z).getRectangle()) )
                    {
                        //They've overlapped, remove them both!
                        laserArray.remove(z);
                        ballArray.remove(x);
                    }
                }
            }
        }
        
        
        //General Text (Draw this last to make sure it's on top.)
        g.setColor(Color.GREEN);
        g.drawString("ArcadeEngine 2014", 10, 12);
        g.drawString("frame="+getFrameNumber(),200,12);
        
        return g;
    }//--end of renderFrame method--
    
    //-------------------------------------------------------
    //Respond to Mouse Events
    //-------------------------------------------------------
    public void mouseClicked(MouseEvent e)  
    { 
    }
    
    //-------------------------------------------------------
    //Respond to Keyboard Events
    //-------------------------------------------------------
    public void keyTyped(KeyEvent e) 
    {
        char c = e.getKeyChar();
            
        if(c==' ')
        {
            Projectile tempstar = new Projectile();
            tempstar.fireWeapon(mouseX,mouseY);           
            laserArray.add(tempstar);            
        }
        if(c=='b')
        {      
            ballArray.add(new PongBall());            
        }
        if(c=='f')
        	ballArray.clear();
    }

    
    
    //-------------------------------------------------------
    //Initialize Graphics
    //-------------------------------------------------------
//-----------------------------------------------------------------------
/*  Image section... 
 *  To add a new image to the program, do three things.
 *  1.  Make a declaration of the Image by name ...  Image imagename;
 *  2.  Actually make the image and store it in the same directory as the code.
 *  3.  Add a line into the initGraphics() function to load the file. 
//-----------------------------------------------------------------------*/
    Image ballImage;        
    Image starImage;
    
    public void initGraphics() 
    {      
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        ballImage = toolkit.getImage("ball.gif");
        starImage = toolkit.getImage("star.gif");
    } //--end of initGraphics()--
    
    //-------------------------------------------------------
    //Initialize Sounds
    //-------------------------------------------------------
//-----------------------------------------------------------------------
/*  Music section... 
 *  To add music clips to the program, do four things.
 *  1.  Make a declaration of the AudioClip by name ...  AudioClip clipname;
 *  2.  Actually make/get the .wav file and store it in the same directory as the code.
 *  3.  Add a line into the initMusic() function to load the clip. 
 *  4.  Use the play(), stop() and loop() functions as needed in your code.
//-----------------------------------------------------------------------*/
    AudioClip themeMusic;
    AudioClip bellSound;
    
    public void initMusic() 
    {
        themeMusic = loadClip("under.wav");
        bellSound = loadClip("ding.wav");
        themeMusic.loop(); //This would make it play!
    }

//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
}//--end of ArcadeDemo class--

