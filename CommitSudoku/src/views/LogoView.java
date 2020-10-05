package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;


import util.Util;

public class LogoView extends Composite{

	Label logo = new Label(this, SWT.BORDER);
	Image image = new Image(getDisplay(), 150,70);
	
	public LogoView(Composite parent, int style) {
		super(parent, style);
	}
	
	public void init(Display display)
	{
		GridLayout layout = new GridLayout(1, false);
		layout.marginTop = 0;
		layout.marginWidth=0;
		layout.marginHeight=0;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		setLayout(layout);
		GridData data = new GridData(SWT.RIGHT, SWT.TOP, false, true, 1, 1);
		setBackground(new Color(null, 0,0,0,0));
		try {
			image = new Image(display, "SudokuLogo.jpg");
			image = resize(image, 200, 50);
			//image = new Image(display, LogoView.class.getResourceAsStream("SudokuLogo"));
			
		} catch (Exception e) {
			Util.println("Could not find image");
		}
		/*Canvas c = new Canvas (this, SWT.TRANSPARENT);
		c.setData(data);
		c.setLayout(layout);
		c.addPaintListener(
		    new PaintListener(){
		        public void paintControl(PaintEvent e) 
		        {
		            e.gc.drawImage(image, 0, 0);
		        }
		    }
		);*/
		setData(data);
		logo.setData(data);
		logo.setImage(image);
		logo.setBackground(new Color(null,0,0,0,0));		
	}
	
	private Image resize(Image image, int width, int height) {
		Image scaled = new Image(Display.getDefault(), width, height);
		GC gc = new GC(scaled);
		gc.setAntialias(SWT.ON);
		gc.setInterpolation(SWT.HIGH);
		gc.drawImage(image, 0, 0,
		image.getBounds().width, image.getBounds().height,
		0, 0, width, height);
		gc.dispose();
		image.dispose(); // don't forget about me!
		return scaled;
		}

}
