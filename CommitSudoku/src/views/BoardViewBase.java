package views;

import org.eclipse.swt.widgets.Composite;
import model.game.puzzle.Coordinate;

public abstract class BoardViewBase extends View{
	
	public BoardViewBase(Composite parent, int style) {
		super(parent, style);
	}
	public abstract BoardTile getButton(Coordinate coord);
	public abstract BoardTile getButton(int y, int x);
	public abstract void setSelectedButton(Coordinate coord);
	public abstract BoardTile getSelectedButton();
	public abstract BoardTile[][] getGameBoard();
	public abstract void init();
	public abstract void updateComponentSizes();
	
	
}
