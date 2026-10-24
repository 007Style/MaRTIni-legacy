import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;

/**
 * The ColorBar class displays a color bar for color selection.
 */
class ColorBar {

/*
 * the top, left coordinate of the color bar
 */
int xpos, ypos;

/*
 * the width and height of the color bar
 */
int width, height;

/*
 * the current color selection index into the colors array
 */
int selectedColor = 3;

/*
 * the array of colors available for selection
 */
static Color colors[] = {
       Color.white, Color.gray, Color.red, Color.pink,
       Color.orange, Color.yellow, Color.green, Color.magenta,
       Color.cyan, Color.blue
};

/**
 * Create the color bar
 */
public ColorBar (int x, int y, int w, int h) {

       xpos = x;
       ypos = y;
       width = w;
       height = h; 
}

/**
 * Paint the color bar
 * @param g - destination graphics object
 */
void paint (Graphics g) {

       int x, y, i;
       int w, h;

       for (i=0; i<colors.length; i+=1) {
              w = width;
              h = height/colors.length;
              x = xpos;
              y = ypos + (i * h);
              g.setColor (Color.black);
              g.fillRect (x, y, w, h);
              if (i == selectedColor) {
                     x += 5;
                     y += 5;
                     w -= 10;
                     h -= 10;
              } else {
                     x += 1;
                     y += 1;
                     w -= 2;
                     h -= 2;
              }
              g.setColor (colors[i]);
              g.fillRect (x, y, w, h);
       }
}

/**
 * Check to see whether the mouse is inside a palette box.
 * If it is, set selectedColor and return true;
 *        otherwise, return false.
 * @param x, y - x and y position of mouse
 */
boolean inside (int x, int y) {

       int i, h;

       if (x < xpos || x > xpos+width) return false;
       if (y < ypos || y > ypos+height) return false; 

       h = height/colors.length;
       for (i=0; i<colors.length; i+=1) {
              if (y < ((i+1)*h+ypos)) {
                     selectedColor = i;
                     return true;
              }
       }
       return false;
}
}

/**
 * The Doodle applet implements a drawable surface
 * with a limited choice of colors to draw with.
 */
public class Doodle extends Frame {

/*
 * the maximum number of points that can be
 * saved in the xpoints, ypoints, and color arrays
 */
static final int MaxPoints = 1000;

/*
 * arrays to hold the points where the user draws
 */
int xpoints[] = new int[MaxPoints];
int ypoints[] = new int[MaxPoints]; 

/*
 * the color of each point
 */
int color[] = new int[MaxPoints];

/*
 * used to keep track of the previous mouse
 * click to avoid filling arrays with the
 * same point
 */
int lastx;
int lasty;

/*
 * the number of points in the arrays
 */
int npoints = 0;
ColorBar colorBar;
boolean inColorBar;

/**
 * Set the window title and create the menus
 * Create an instance of ColorBar
 */

    public void New_Action(ActionEvent event)
    {
        Doodle doodle = new Doodle();
    }

    public void Quit_Action(ActionEvent event)
    {
        System.exit(0);
    }

    class ActionListener1 implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            String str = event.getActionCommand();
            if (str.equals("New"))
                New_Action(event);
            else if (str.equals("Quit"))
                Quit_Action(event);
        }
    }

public Doodle () {

  buttons b = new buttons();
  add(b);

       setTitle ("Doodle");

       setBackground(Color.white);

       MenuBar menuBar = new MenuBar();
       Menu fileMenu = new Menu("File");
       fileMenu.add(new MenuItem("Open"));
       fileMenu.add(new MenuItem("New"));
       fileMenu.add(new MenuItem("Close"));
       fileMenu.add(new MenuItem("-"));
       fileMenu.add(new MenuItem("Quit"));
       menuBar.add (fileMenu);

       Menu editMenu = new Menu("Edit");
       editMenu.add(new MenuItem("Undo"));
       editMenu.add(new MenuItem("Cut"));
       editMenu.add(new MenuItem("Copy"));
       editMenu.add(new MenuItem("Paste"));
       editMenu.add(new MenuItem("-"));
       editMenu.add(new MenuItem("Clear"));
       menuBar.add (editMenu);
       setMenuBar (menuBar);

       colorBar = new ColorBar (10, 75, 30, 200); 

    WindowListener1 lWindow = new WindowListener1();
    addWindowListener(lWindow);

    MouseListener1 lMouse = new MouseListener1();
    addMouseListener(lMouse);

    MouseMotionListener1 lMouseMotion = new MouseMotionListener1();
    addMouseMotionListener(lMouseMotion);

    ActionListener1 lAction = new ActionListener1();
    fileMenu.addActionListener(lAction);

       setSize (410, 430);
       show ();
}

class WindowListener1 extends WindowAdapter
{
    public void windowClosing(WindowEvent event)
    {
        Window win = event.getWindow();
        win.setVisible(false);
        win.dispose();
        System.exit(0);
    }
}

class MouseMotionListener1 extends MouseMotionAdapter
{
    public void mouseDragged(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
              if (inColorBar) return;
              if ((x != lastx || y != lasty) && npoints < MaxPoints) {
                     lastx = x;
                     lasty = y;
                     xpoints[npoints] = x;
                     ypoints[npoints] = y;
                     color[npoints] = colorBar.selectedColor;
                     npoints += 1; 
                     repaint ();
              }
    }
}

class MouseListener1 extends MouseAdapter
{
    public void mousePressed(MouseEvent e)
    {
        int x = e.getX();
        int y = e.getY();
              if (colorBar.inside (x, y)) {
                     inColorBar = true;
                     repaint ();
                     return;
              }
              inColorBar = false;
              if (npoints < MaxPoints) {
                     lastx = x;
                     lasty = y;
                     xpoints[npoints] = x;
                     ypoints[npoints] = y;
                     color[npoints] = colorBar.selectedColor;
                     npoints += 1;
              }
              npoints = 0;
    }
}

/**
 * Redisplay the drawing space
 * @param g - destination graphics object
 */
public void update (Graphics g) {

       int i;

       for (i=0; i<npoints; i+=1) {
              g.setColor (colorBar.colors[color[i]]);
              g.fillOval (xpoints[i]-5, ypoints[i]-5, 10, 10);
       }
       colorBar.paint (g);
}

/**
 * Repaint the drawing space when required
 * @param g - destination graphics object
 */
public void paint (Graphics g) {

       update (g);
}

/**
 * The main method allows this class to be run as an application
 * @param args - command-line arguments
 */
public static void main (String args[]) {

       Doodle doodle = new Doodle ();
}

}

class buttons extends Panel
implements ActionListener
{
  Button Quit;
  Button Start;
  
  buttons()
  {
    Font    font = new Font ("Times", Font.BOLD, 14);
    Color   functionColor = new Color (255, 255, 0);
    Color   numberColor = new Color (0, 255, 255);
    Color   equalsColor = new Color (0, 255, 0);
    setFont (font);

    Start = new Button ("Start");
    add (Start);
    Start.setBackground (numberColor);
    Start.addActionListener(this);

    Quit = new Button ("Quit");
    add (Quit);
    Quit.setBackground (numberColor);
    Quit.addActionListener(this);
  }

  public void actionPerformed(ActionEvent event)
  {
    Object object = event.getSource();

    if(object == Quit)
    {
      System.exit(0);
    }

    if(object == Start)
    {
      setBackground(Color.red);
    }

  }
}
