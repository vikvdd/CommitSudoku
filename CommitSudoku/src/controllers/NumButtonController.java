package controllers;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import events.GameListener;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import views.GameButtonsView;

public class NumButtonController implements GameListener{
	SudokuGame game;
	GameButtonsView view;
	
	public NumButtonController(SudokuGame game, GameButtonsView view) {
		this.game = game;
		this.view = view;
	}
	
	public void init()
	{
		game.addGameListener(this);
		initGameButtons();
	}
	
	private void initGameButtons()
	{
		Button[] buttons = view.getButtons();
		for (int i = 0; i < 9; i++) {
			Button btn = buttons[i];
			btn.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent event) {
					try {
						numButtonAction(Integer.parseInt(btn.getText()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					
					
				}
			});
			btn.addListener(SWT.MenuDetect, new Listener() {
				
				@Override
				public void handleEvent(Event arg0) {
					
					try {
						numButtonRightClickAction(Integer.parseInt(btn.getText()));
					} catch (Exception e) {
						
					}
					
				}
			});
		}
	}
	
	private void numButtonAction(int num)
	{
		game.enterNumber(num);
	}
	
	private void numButtonRightClickAction(int num)
	{
		game.enterNote(num);
	}
	
	@Override
	public void onGameStart(int[][] puzzleClone) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNumberEntry(Coordinate coord, int number) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onNoteEntry(int num) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onPuzzleSolved(int[][] solution) {
		// TODO Auto-generated method stub
		
	}
	
}
