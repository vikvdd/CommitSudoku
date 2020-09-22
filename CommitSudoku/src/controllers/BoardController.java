package controllers;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.Button;
import events.GameListener;
import model.EntryType;
import model.game.SudokuGame;
import model.game.puzzle.*;
import util.Util;
import views.GameButtonsView;
import views.GameboardView;
import views.TileType;

public class BoardController implements GameListener{
	
	private SudokuGame game;
	private GameboardView view;
	private GameButtonsView btnView;
	private Coordinate selectedCoord;
	
	
	public BoardController(SudokuGame game, GameboardView view, GameButtonsView btnView)
	{
		this.game = game;
		this.view = view;
		this.btnView = btnView;
	}
	
	public void init()
	{
		initModel();
		initBoardButtons();
		initGameButtons();
		game.loadNewGame(SudokuLogic.generateRandomPuzzle());
		selectedCoord = new Coordinate(0, 0);
		selectTileAction(selectedCoord);
	}

	@Override
	public void onGameStart(int[][] puzzleClone) {
		// TODO Auto-generated method stub
		fillGameboard(puzzleClone);
	}

	@Override
	public void onGameEnd() {
		
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		init();
	}

	@Override
	public void onNumberEntry(Coordinate coord, int n) {
		updateBoardTile(coord, n);
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
	
	private void initModel()
	{
		game.addGameListener(this);
	}
	
	private void selectTileAction(Coordinate coord)
	{
		if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.FIXED) return;
		setHighlighedButtonsTileType(TileType.NORMAL, selectedCoord);
		view.getSelectedButton().setBackground(GameboardView.NORMAL_TILE);
		selectedCoord = coord;
		
		setHighlighedButtonsTileType(TileType.HIGHLIGHTED, selectedCoord);
		view.setSelectedButton(view.getButton(selectedCoord));
		view.getSelectedButton().setBackground(GameboardView.SELECTED_TILE);
	}
	
	private void numButtonAction(int num)
	{
		game.enterNumber(selectedCoord, num);
	}
	
	private void fillGameboard(int[][] puz)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				updateBoardTile(coord, puz[y][x]);
			}	
		}
	}
	
	private void updateBoardTile(Coordinate coord, int n)
	{
		EntryType entryType = game.getEntryType(coord, n);
		formatTileText(entryType, view.getButton(coord));
		updateBoardTileText(coord, n);
	}
	
	private void formatTileText(EntryType entryType, Button btn)
	{
		switch (entryType) {
		case FIXED:
			btn.setFont(GameboardView.DEFAULT_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);
			break;
		case VALIDENTRY:
			btn.setFont(GameboardView.ENTRY_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);					
			break;
		case INVALIDENTRY:
			btn.setFont(GameboardView.INVALID_FONT);
			btn.setForeground(GameboardView.INVALID_COLOR);				
			break;
		}
	}
	
	private void updateBoardTileText(Coordinate coord, int n)
	{
		Button btn = view.getButton(coord);
		String text = "";
		if(n != 0) text = n + "";
		btn.setText(text);
	}
	
	private void setHighlighedButtonsTileType(TileType tileType, Coordinate coord)
	{
		setButtonColumnTileType(tileType, coord.x);
		setButtonRowTileType(tileType, coord.y);
		setButtonSubGridTileType(tileType, coord);
	}
	
	private void setButtonRowTileType(TileType tileType, int y)
	{
		for (int x = 0; x < 9; x++) {
			setTileType(tileType, view.getButton(y, x));
		}
	}
	
	private void setButtonColumnTileType(TileType tileType, int x)
	{
		for (int y = 0; y < 9; y++) {
			setTileType(tileType, view.getButton(y, x));
		}
	}
	
	private void setButtonSubGridTileType(TileType tileType, Coordinate coord)
	{
		Coordinate subGrid = getNearestSubGridCoordinates(coord);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				setTileType(tileType, view.getButton(subGrid.y + y, subGrid.x + x));
			}
		}
	}
	
	private void setTileType(TileType tileType, Button btn)
	{
		switch (tileType) {
		case NORMAL:	
			btn.setBackground(GameboardView.NORMAL_TILE);	
			break;
		case SELECTED:
			btn.setBackground(GameboardView.SELECTED_TILE);
			break;
		case HIGHLIGHTED:
			btn.setBackground(GameboardView.HIGHLIGHTED_TILE);
			break;
		}
	}
	
	private Coordinate getNearestSubGridCoordinates(Coordinate coord)
	{
		int y0 = Math.floorDiv(coord.y, 3) * 3;
		int x0 = Math.floorDiv(coord.x, 3) * 3;
		return new Coordinate(x0, y0);
	}

}
