package events;

import model.game.puzzle.Coordinate;

public interface GameListener {
	public void onGameStart();
	public void onGameEnd();
	public void onPuzzleChanged();
	public void onNumberEntry(Coordinate coord);
	public void onPuzzleCompleted();
}
