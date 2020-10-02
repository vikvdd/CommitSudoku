package views;

import util.Util;

public class ViewScaleManager {
	private ViewDimension windowSize;
	private ViewDimension boardSize;
	private ViewDimension buttonViewSize;
	private ViewDimension panelSize;
	private int padding = 5;
	
	public ViewScaleManager(ViewDimension dimension) {
		windowSize = dimension;
		calculateCompositeDimensions(windowSize);
		
	}
	
	private void calculateCompositeDimensions(ViewDimension dimension)
	{
		boardSize = new ViewDimension(dimension.getHeight()-300, dimension.getHeight()-60);
		buttonViewSize = new ViewDimension((60*9)+(4*9), 60);
		panelSize = new ViewDimension(300, boardSize.getHeight());
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
