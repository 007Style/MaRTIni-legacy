import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

public class SimSetup //extends Applet
{
 
  public void init()
  {
    setLayout(new BorderLayout());

  }

  public static void main (String args[])
  {
    Frame f = new Frame("SimSetup");
    SimSetup s = new SimSetup();

    s.init();

    f.setSize(640, 480);
    f.add("Center", simSetup);
    f.show();
  }


  public SimSetup()
  {
  }

}
