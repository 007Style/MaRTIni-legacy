import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.applet.Applet;

public class MaRTIni extends Applet
{
  MenuBar menuBar1 = new MenuBar();
  Menu menu1 = new Menu();
  MenuItem menuItem1 = new MenuItem();
  MenuItem menuItem2 = new MenuItem();
  MenuItem menuItem3 = new MenuItem();
  MenuItem menuItem4 = new MenuItem();
  MenuItem menuItem5 = new MenuItem();
  MenuItem menuItem6 = new MenuItem();
  MenuItem menuItem7 = new MenuItem();
  MenuItem menuItem8 = new MenuItem();
  MenuItem menuItem9 = new MenuItem();
  MenuItem menuItem10 = new MenuItem();
  TextField textField1 = new TextField();
  public void init()
  {
    setLayout(new BorderLayout());

  }

  public static void main (String args[])
  {
    Frame f = new Frame("MaRTIni");
    MaRTIni martini = new MaRTIni();

    martini.init();

    f.setSize(640, 480);
    f.add("Center", martini);
    f.show();
  }


  public MaRTIni() {
    try  {
      jbInit();
    }
    catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void jbInit() throws Exception {
    menuBar1.setFont(new Font("Dialog", 0, 12));
    menu1.setLabel("MaRTIni");
    menuItem1.setLabel("SimSetup");
    menuItem2.setLabel("Map");
    menuItem3.setLabel("Time");
    menuItem4.setLabel("Speed");
    menuItem5.setLabel("Statistics");
    menuItem6.setLabel("Power");
    menuItem7.setLabel("Propagation");
    menuItem8.setLabel("Resource");
    menuItem9.setLabel("Grid");
    menuItem10.setLabel("RadioChannel");
    textField1.setText("textField1");
    menuBar1.add(menu1);
    menu1.add(menuItem1);
    menu1.add(menuItem2);
    menu1.add(menuItem3);
    menu1.add(menuItem4);
    menu1.add(menuItem5);
    menu1.add(menuItem6);
    menu1.add(menuItem7);
    menu1.add(menuItem8);
    menu1.add(menuItem9);
    menu1.add(menuItem10);
    this.add(textField1, null);
  }
}









