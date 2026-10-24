import java.lang.*;
import java.lang.String;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;



public class setMap extends Frame implements ActionListener
{
    Panel paneltxtfld = new Panel(new GridBagLayout());
    TextField tf1, tf2;
    Checkbox check1, check2;
 public setMap()
  {
    setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    Panel panelbut = new Panel();

    gbc.gridwidth = GridBagConstraints.REMAINDER;
    gbc.fill = GridBagConstraints.HORIZONTAL;
    gbc.weightx = 1.0;

    add(panelbut, gbc);

    gbc.fill = GridBagConstraints.BOTH;
    gbc.weighty = 1.0;

    add(paneltxtfld, gbc);

    gbc.fill = GridBagConstraints.NONE;
    gbc.weightx = 0.0;
    gbc.weighty = 0.0;
    
    gbc.gridwidth = 1;
    Label l1 = new Label("Scale:", Label.RIGHT);
    paneltxtfld.add(l1,gbc);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    
    tf1 = new TextField(30);
    paneltxtfld.add(tf1, gbc);
    
    gbc.gridwidth = 1;
    Label l2 = new Label("Object Scale:", Label.RIGHT);
    paneltxtfld.add(l2, gbc);
   
    tf2 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf2, gbc);
    
    gbc.gridwidth = 1;
    Label l3 = new Label("Base Colors:", Label.RIGHT);
    paneltxtfld.add(l3);
    
    Checkbox check1 = new Checkbox("Check for True, otherwise False.");
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(check1, gbc);
    
    gbc.gridwidth = 1;
    Label l4 = new Label("Base Lines: ", Label.RIGHT);
    paneltxtfld.add(l4, gbc);
    Checkbox check2 = new Checkbox("Check for true, otherwise False.");
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(check2, gbc);
    
 
    Button setval = new Button(" - Set Values - ");
    Button help = new Button(" - Help - ");
    Button close = new Button(" - Close - ");

    Font    font = new Font ("Times", Font.BOLD, 14);
    Color   numberColor = new Color (50, 200, 255);
    Color   equalsColor = new Color (120, 160, 170);
    setFont (font);

    panelbut.add(setval);
    //does crap here setval.setBackground(red);
    setval.setBackground(numberColor);
    setval.addActionListener(this);

    panelbut.add(help);
    help.setBackground(equalsColor);
    help.addActionListener(this);

    panelbut.add(close);
    close.setBackground(numberColor);
    close.addActionListener(this);

    WindowListener2 lWindow = new WindowListener2();
    addWindowListener(lWindow);

    setTitle("Map Settings");
    setBackground(Color.white);
    setSize (400, 200);
    pack();
    setResizable(false);
    this.setLocation(30,130);
    show ();
  }


  public void actionPerformed(ActionEvent event)
  {

    if(event.getActionCommand() == " - Set Values - ")
      {
	  String str;
	  str = tf1.getText();
	  Double d1 = new Double( str ); // new Double( prim );
	  // double prim = d1.doubleValue();
	  M.Map2[0] = d1;
	  str = tf2.getText();
	  Double d2 = new Double( str );
	  M.Map2[1] = d2;
	  Double d3 = new Double( 1.0 );
	  Double d4 = new Double( 0.0 );
	  boolean question;
	  question = check1.getState();
	  if(question == true)
	      M.Map2[2] = d3;
	  else
	      M.Map2[2] = d4;
	  question = check2.getState();
	  if(question == true)
	      M.Map2[3] = d3;
	  else
	      M.Map2[3] = d4;
      }

    if(event.getActionCommand() == " - Help - ")
      {
	Maphelp mapy = new Maphelp();
      }
    
    if(event.getActionCommand() == " - Close - ")
      {
	this.dispose();
      }
  }
  
  class WindowListener2 extends WindowAdapter
  {
    public void windowClosing(WindowEvent event)
    {
      Window win = event.getWindow();
      win.setVisible(false);
      win.dispose();
      //System.exit(0);
    }
  }
  
  public static void main (String args[])
  {  
    setMap map = new setMap();
  }
}
