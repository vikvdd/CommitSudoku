package events;

public interface GameListener {
	public void onGameStart();
	public void onGameEnd();
	public void onPuzzleChanged();
	public void onNumberEntry(int number);
	public void onPuzzleCompleted();
}
