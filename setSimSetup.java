import java.lang.*;
import java.lang.String;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.io.*;
import java.lang.*;
import java.util.*;


public class setSimSetup extends Frame implements ActionListener
{
    TextField tf1;
    public setSimSetup()
    {
	Panel paneltxtfld = new Panel(new GridBagLayout());
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
	Label l2 = new Label("Seed Time:", Label.RIGHT);
	paneltxtfld.add(l2, gbc);
	
	tf1 = new TextField(30);
	gbc.gridwidth = GridBagConstraints.REMAINDER;
	paneltxtfld.add(tf1, gbc);
	
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
	
	setTitle("Initial Sim Setup");
	setBackground(Color.white);
	setSize (400, 200);
	pack();
	setResizable(false);
	this.setLocation(0,100);
	show ();
    }
    
    public void actionPerformed(ActionEvent event)
    {
	
	if(event.getActionCommand() == " - Set Values - ")
	    {
		String str;
		str = tf1.getText();
		try 
		    {
			Double d = new Double( str );
			M.SimSetup1[0] = d;
		    }
		catch (NumberFormatException e) {}
		//M.makeSimSetup1(d,0);
	    }
	
	if(event.getActionCommand() == " - Help - ")
	    {
		SimSetuphelp sha = new SimSetuphelp();
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
	setSimSetup simy = new setSimSetup();
    }
}






