package views;

import java.util.Map;

import model.game.puzzle.Coordinate;

public interface IBoardView {
	public void init(int x, int y, int width, int height);
	public BoardTile getButton(Coordinate coord);
	public BoardTile getButton(int y, int x);
	public void setSelectedButton(Coordinate coord);
	public BoardTile getSelectedButton();
	public BoardTile[][] getGameBoard();
}
