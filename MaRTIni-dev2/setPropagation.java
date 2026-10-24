import java.lang.*;
import java.lang.String;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class setPropagation extends Frame implements ActionListener
{
  Panel paneltxtfld = new Panel(new GridBagLayout());
  public setPropagation()
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

    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12;
    
    gbc.gridwidth = 1;
    Label l2 = new Label("PropCoeff:", Label.RIGHT);
    paneltxtfld.add(l2, gbc);
   
    tf2 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf2, gbc);
    
    gbc.gridwidth = 1;
    Label l3 = new Label("MANHATTAN:", Label.RIGHT);
    paneltxtfld.add(l3,gbc);
    
    tf3 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf3, gbc);
    
    gbc.gridwidth = 1;
    Label l4 = new Label("Px:", Label.RIGHT);
    paneltxtfld.add(l4, gbc);
   
    tf4 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf4, gbc);
    
    gbc.gridwidth = 1;
    Label l5 = new Label("N1:", Label.RIGHT);
    paneltxtfld.add(l5, gbc);
    
    tf5 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf5, gbc);

    gbc.gridwidth = 1;
    Label l1 = new Label("N2:", Label.RIGHT);
    paneltxtfld.add(l1,gbc);
    
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    tf1 = new TextField(30);
    paneltxtfld.add(tf1, gbc);

    
    gbc.gridwidth = 1;
    Label l6 = new Label("Ny:", Label.RIGHT);
    paneltxtfld.add(l6, gbc);
    
    tf6 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf6, gbc);
    
    gbc.gridwidth = 1;
    Label l7 = new Label("Xb:", Label.RIGHT);
    paneltxtfld.add(l7, gbc);

    tf7 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf7, gbc);
    
    
    gbc.gridwidth = 1;
    Label l8 = new Label("Y0:", Label.RIGHT);
    paneltxtfld.add(l8, gbc);

    tf8 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf8, gbc);
        
    gbc.gridwidth = 1;
    Label l9 = new Label("Y1:", Label.RIGHT);
    paneltxtfld.add(l9, gbc);

    tf9 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf9, gbc);
        
    gbc.gridwidth = 1;
    Label l10 = new Label("Y2:", Label.RIGHT);
    paneltxtfld.add(l10, gbc);

    tf10 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf10, gbc);
    
    gbc.gridwidth = 1;
    Label l11 = new Label("Py:", Label.RIGHT);
    paneltxtfld.add(l11, gbc);

    tf11 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf11, gbc);
        
    gbc.gridwidth = 1;
    Label l12 = new Label("Yb:", Label.RIGHT);
    paneltxtfld.add(l12, gbc);

    tf12 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf12, gbc);
    
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
    
    setTitle("Propagation Settings");
    setBackground(Color.white);
    setSize (400, 200);
    pack();
    setResizable(false);
    this.setLocation(180,280);
    show ();
  }
  
  public void actionPerformed(ActionEvent event)
  {
    
    if(event.getActionCommand() == " - Set Values - ")
      {
	setBackground(Color.blue);
      }
    
    if(event.getActionCommand() == " - Help - ")
      {
	Propagationhelp propy = new Propagationhelp();
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
    setPropagation propy = new setPropagation();
  }
}



