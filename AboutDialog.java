import layer.*;
import java.awt.*;
import javax.swing.*;

public class AboutDialog extends JDialog {
    
    String[] text = {"WINLAB: Wireless Information Network Laboratory", 
		     "Rutgers University", 
		     "", "Programmed by:", 
		     "    Roland Wunderlich", "    Ramnath Ravindran", 
		     "", "", 
		     "GIF image file encoding Copyright(c) 1996,1998", 
		     "by Jef Poskanzer <jef@acme.com>. All rights reserved.",  
		     "", "", 
		     "Graphing capabilities Copyright(c) 1995-1999",
		     "The Regents of the University of California.", 
		     "All rights reserved."};
    
    AboutDialog(Frame owner) {
	super(owner, "About MaRTIni", true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	
	Layer[] layers = new Layer[2];
	layers[0] = new ImageLayer("About background", 
				   Utilities.getImage("martinibk.jpg"));
	layers[1] = new TextLayer("About text", text);
	
	getContentPane().add(new LayerDisplay("About datum.viewer", 
					      640.0, 480.0, layers, false));

	pack();
	setLocationRelativeTo(owner);
	show();
    }
    
}
