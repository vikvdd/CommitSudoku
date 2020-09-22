package events;

import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;

public interface GameListener {
	public void onGameStart(int[][] puzzleClone);
	public void onGameEnd();
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime);
	public void onNumberEntry(Coordinate coord, int number);
	public void onPuzzleCompleted();
}
