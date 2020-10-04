package views;

import org.eclipse.swt.widgets.Composite;

public abstract class View extends Composite{
	
	public View(Composite parent, int style) {
		super(parent, style);
	}

	public abstract void init();
	
	public abstract void updateComponentSizes();
	
	public void setSize(int width, int height)
	{
		super.setSize(width, height);
		updateComponentSizes();
	}
}
