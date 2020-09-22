package events;

public interface GameStatListener {
	public void onNumberCompleted(int number);
	public void onPuzzleCompleted();
}
