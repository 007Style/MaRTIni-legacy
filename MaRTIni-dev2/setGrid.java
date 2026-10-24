import java.lang.*;
import java.lang.String;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;


public class setGrid extends Frame implements ActionListener
{
  Panel paneltxtfld = new Panel(new GridBagLayout());
  public setGrid()
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

    TextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10, tf11, tf12, tf13, tf14;
    
    gbc.gridwidth = 1;
    Label l2 = new Label("Dimension:", Label.RIGHT);
    paneltxtfld.add(l2, gbc);
   
    tf2 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf2, gbc);
    
    gbc.gridwidth = 1;
    Label l3 = new Label("NoBlock:", Label.RIGHT);
    paneltxtfld.add(l3,gbc);
    
    tf3 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf3, gbc);
    
    gbc.gridwidth = 1;
    Label l4 = new Label("DistRes:", Label.RIGHT);
    paneltxtfld.add(l4, gbc);
   
    tf4 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf4, gbc);
    
    gbc.gridwidth = 1;
    Label l5 = new Label("BlockSize:", Label.RIGHT);
    paneltxtfld.add(l5, gbc);
    
    tf5 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf5, gbc);

    gbc.gridwidth = 1;
    Label l1 = new Label("CellRadius:", Label.RIGHT);
    paneltxtfld.add(l1,gbc);
    
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    tf1 = new TextField(30);
    paneltxtfld.add(tf1, gbc);
    
    gbc.gridwidth = 1;
    Label l6 = new Label("TtlLength = ", Label.CENTER);
    paneltxtfld.add(l6, gbc);
    
    tf6 = new TextField(" NoBlock * BlockSize", 30);
    tf6.setEditable(false);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf6, gbc);
    
    gbc.gridwidth = 1;
    Label l7 = new Label("MaxCoord = ", Label.RIGHT);
    paneltxtfld.add(l7, gbc);
    
    tf7 = new TextField("TtlLength - DistRes", 30);
    tf7.setEditable(false);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf7, gbc);
    
    gbc.gridwidth = 1;
    Label l8 = new Label("PointPBlck = ", Label.RIGHT);
    paneltxtfld.add(l8, gbc);
    
    tf8 = new TextField("BlockSize / DistRes", 30);
    tf8.setEditable(false);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf8, gbc);
      
    gbc.gridwidth = 1;
    Label l9 = new Label("TtlPoints = ", Label.RIGHT);
    paneltxtfld.add(l9, gbc);
    
    tf9 = new TextField("NoBlock * PointPBlck", 30);
    tf9.setEditable(false);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf9, gbc);
      
    gbc.gridwidth = 1;
    Label l10 = new Label("NumPoints:", Label.RIGHT);
    paneltxtfld.add(l10, gbc);

    tf10 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf10, gbc);
    
    gbc.gridwidth = 1;
    Label l11 = new Label("EAST:", Label.RIGHT);
    paneltxtfld.add(l11, gbc);

    tf11 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf11, gbc);
        
    gbc.gridwidth = 1;
    Label l12 = new Label("SOUTH:", Label.RIGHT);
    paneltxtfld.add(l12, gbc);
    
    tf12 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf12, gbc);
        
    gbc.gridwidth = 1;
    Label l13 = new Label("WEST:", Label.RIGHT);
    paneltxtfld.add(l13, gbc);

    tf13 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf13, gbc);
        
    gbc.gridwidth = 1;
    Label l14 = new Label("NORTH:", Label.RIGHT);
    paneltxtfld.add(l14, gbc);

    tf14 = new TextField(30);
    gbc.gridwidth = GridBagConstraints.REMAINDER;
    paneltxtfld.add(tf14, gbc);
    
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
    
    setTitle("Grid Settings");
    setBackground(Color.white);
    setSize (400, 200);
    pack();
    setResizable(false);
    this.setLocation(240,340);
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
	Gridhelp griddy = new Gridhelp();
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
      setGrid gridy = new setGrid();
  }
}



