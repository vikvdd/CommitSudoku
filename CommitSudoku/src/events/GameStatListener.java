package events;

public interface GameStatListener {
	public void onNumberCompleted(int number);
	public void onNumberUncompleted(int number);
	public void onPuzzleCompleted();
}
