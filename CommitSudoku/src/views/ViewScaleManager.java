package views;

import org.eclipse.swt.graphics.Rectangle;
import util.Util;

public class ViewScaleManager {
	private ViewDimension windowSize;
	private ViewDimension boardSize;
	private ViewDimension buttonViewSize;
	private ViewDimension panelSize;
	private int padding = 5;
	
	public ViewScaleManager(Rectangle rectangle) {
		ViewDimension dimension = new ViewDimension(rectangle.width, rectangle.height);
		windowSize = dimension;
		calculateCompositeDimensions(windowSize);
		
	}
	
	private void calculateCompositeDimensions(ViewDimension dimension)
	{
		int size = dimension.getHeight()-500;
		boardSize = new ViewDimension(size,size);	
		buttonViewSize = new ViewDimension((60*9)+(4*9),60);
		panelSize = new ViewDimension(250, boardSize.getHeight());
		windowSize = new ViewDimension(boardSize.getWidth() + panelSize.getWidth() + 150 , boardSize.getHeight() + buttonViewSize.getHeight() + 100);
	}
	
	public ViewDimension getWindowSize()
	{
		return windowSize;
	}
	
	public ViewDimension getBoardSize()
	{
		return boardSize;
	}
	
	public ViewDimension getButtonViewSize()
	{
		return buttonViewSize;
	}
	
	public ViewDimension getPanelSize()
	{
		return panelSize;
	}
	
	public int getPadding()
	{
		return padding;
	}
	
	
}
