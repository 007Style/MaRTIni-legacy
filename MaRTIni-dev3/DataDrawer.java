import java.awt.*;

public interface DataDrawer {

	public void setDisplay(DataDisplay parent);

	public void draw(Graphics g, Dimension d);
	
	public boolean handleClick(Point p, Dimension d);
		
}