package views;

import util.Util;

public class ViewScaleManager {
	private int windowSize;
	private int boardSize;
	private int buttonViewSize;
	private int panelSize;
	private int padding;
	
	public ViewScaleManager(int _windowSize, boolean adjustResToFit) {
		calculateCompositeDimensions(_windowSize);
		padding = 10;
		if(adjustResToFit) adjustResToFit();
		Util.println(windowSize + ":" + boardSize + ":" + buttonViewSize + ":" + panelSize + ":");
	}
	
	private void calculateCompositeDimensions(int _windowSize)
	{
		this.windowSize = _windowSize;
		float brdSize = ((float)windowSize-((float)padding*2.0F)) * (6.0F/10.0F);
		Util.print(brdSize + "");
		boardSize = (int)brdSize;
		float pnlSize = ((float)windowSize-(float)padding*1.0F) * (4.0F/10.0F);
		panelSize =  (int)pnlSize; 
		float btnViewSize = (float)boardSize * (4.0F/5.0F);
		float btnSize = (btnViewSize-((float)padding*17.0F))/9;
		buttonViewSize = (int)btnSize *9 + (padding*17);
	}
	
	private void adjustResToFit()
	{
		windowSize = boardSize + panelSize + (padding*5);
	}
	
	public int getWindowSize()
	{
		return windowSize;
	}
	
	public int getBoardSize()
	{
		return boardSize;
	}
	
	public int getButtonViewSize()
	{
		return buttonViewSize;
	}
	
	public int getPanelSize()
	{
		return panelSize;
	}
	
	public int getPadding()
	{
		return padding;
	}
	
	
}
