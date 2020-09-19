package controllers;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import events.GameListener;
import model.EntryType;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import views.GameButtonsView;
import views.GameboardView;

public class BoardController implements GameListener{
	
	private SudokuGame game;
	private GameboardView view;
	private GameButtonsView btnView;
	private Coordinate selectedCoord;
	
	
	public BoardController(SudokuGame game, GameboardView view)
	{
		
	}
	
	private void init()
	{
		initBoardButtons();
		initGameButtons();
		game.start();
	}

	@Override
	public void onGameStart() {
		// TODO Auto-generated method stub
		fillGameboard();
	}

	@Override
	public void onGameEnd() {
		
	}

	@Override
	public void onPuzzleChanged() {
		init();
	}

	@Override
	public void onNumberEntry(Coordinate coord) {
		updateBoardTile(coord);
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
	
	private void initBoardButtons() {
		Button[][] buttons = view.getGameBoard();
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				buttons[y][x].addSelectionListener(new SelectionListener() {
					
					@Override
					public void widgetSelected(SelectionEvent arg0) {
						selectTileAction(coord);
					}
					
					@Override
					public void widgetDefaultSelected(SelectionEvent arg0) {
						
					}
				});
			}
		}
	}
	
	private void initGameButtons()
	{
		Button[] buttons = btnView.getButtons();
		for (int i = 0; i < 9; i++) {
			Button btn = buttons[i];
			btn.addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					try {
						numButtonAction(Integer.parseInt(btn.getText()));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent arg0) {
					// TODO Auto-generated method stub
					
				}
			});
		}
	}
	
	private void selectTileAction(Coordinate coord)
	{
		if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.Fixed) return;
		view.getSelectedButton().setBackground(GameboardView.DEFAULT_TILE);
		selectedCoord = coord;
		view.setSelectedButton(view.getButton(selectedCoord));
		view.getSelectedButton().setBackground(GameboardView.SELECTED_TILE);
		
	}
	
	private void numButtonAction(int num)
	{
		game.enterNumber(selectedCoord, num);
	}
	
	private void fillGameboard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				updateBoardTile(coord);
			}	
		}
	}
	
	private void updateBoardTile(Coordinate coord)
	{
		int num = game.getNumAtCoordinate(coord);
		EntryType entryType = game.getEntryType(coord, num);
		formatBoardTile(entryType, view.getButton(coord));
		updateBoardTileText(coord);
	}
	
	private void formatBoardTile(EntryType entryType, Button btn)
	{
		switch (entryType) {
		case Fixed:
			btn.setFont(GameboardView.DEFAULT_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);
			btn.setBackground(GameboardView.MAIN_TILE);
			break;
		case Empty:
			break;
		case ValidEntry:
			btn.setFont(GameboardView.ENTRY_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);					
			break;
		case InvalidEntry:
			btn.setFont(GameboardView.INVALID_FONT);
			btn.setForeground(GameboardView.INVALID_COLOR);				
			break;
		}		
	}
	
	private void updateBoardTileText(Coordinate coord)
	{
		Button btn = view.getButton(coord);
		btn.setText(game.getNumAtCoordinate(coord) + "");
	}
}
