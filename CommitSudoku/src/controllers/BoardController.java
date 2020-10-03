package controllers;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.*;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import events.BoardTileListener;
import events.GameListener;
import events.GameStatListener;
import model.EntryType;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.SudokuLogic;
import views.BoardTile;
import views.*;

public class BoardController implements GameListener, GameStatListener{
	
	private SudokuGame game;
	private IBoardView view;
	private GameButtonsView btnView;
	private Coordinate selectedCoord;
	
	
	
	public BoardController(SudokuGame game, IBoardView view, GameButtonsView btnView)
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
		loadStartBoard();
		game.start(); 
	}
	
	private void initBoardButtons() {
		BoardTile[][] buttons = view.getGameBoard();
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);

				try {
					buttons[y][x].addBoardTileListener(new BoardTileListener() {
						
						@Override
						public void onTileMouseExit() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onTileMouseEnter() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onRightClick() {
							// TODO Auto-generated method stub
							
						}
						
						@Override
						public void onLeftClick() {
							// TODO Auto-generated method stub
							selectTileAction(coord);
						}
					});
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		setBoardButtonTileType(TileType.NORMAL);
	}
	
	private void initGameButtons()
	{
		Button[] buttons = btnView.getButtons();
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
	
	private void initModel()
	{
		game.addGameListener(this);
	}
	
	private void loadStartBoard() 
	{
		setBoardButtonTileType(TileType.NORMAL);
		toggleAllNumButtons(true);
		initBoardButtons();
	}
	
	@Override
	public void onGameStart(int[][] puzzleClone) {
		game.getStatTracker().addGameStatListener(this);
		fillGameboard(puzzleClone);
		loadStartBoard();
		clearTileNotes();
		selectedCoord = new Coordinate(0, 0);
		selectTileAction(selectedCoord);
	}

	@Override
	public void onGameEnd() {
		
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		game.start();
	}

	@Override
	public void onNumberEntry(Coordinate coord, int n) {
		updateBoardTile(coord, n);
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void onPuzzleSolved(int[][] solution) {
		//fillGameboard(solution);
	}
	
	@Override
	public void onNumberCompleted(int number) {
		try {
			Button btn = btnView.getButton(number-1);
			if(isBoardValid())
				btn.setEnabled(false);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void selectTileAction(Coordinate coord)
	{
		setBoardButtonTileType(TileType.NORMAL);
		selectedCoord = coord;
		view.setSelectedButton(selectedCoord);
		view.getSelectedButton().setBackgroundColor(BoardView.SELECTED_TILE);
		
		if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.FIXED) fixedTileClickAction(coord);
		else entryTileClickAction(coord);
		
		
		
		
	}
	
	private void fixedTileClickAction(Coordinate coord)
	{
		
		setMatchingNumbersTileType(TileType.HIGHLIGHTED, selectedCoord);
	}
	
	private void entryTileClickAction(Coordinate coord)
	{
		setHighlighedButtonsTileType(TileType.HIGHLIGHTED, selectedCoord);
	}
	
	private void numButtonAction(int num)
	{
		game.enterNumber(selectedCoord, num);
	}
	
	private void numButtonRightClickAction(int num)
	{
		if(view.getButton(selectedCoord).isNoteEnabled(num))
		{
			view.getButton(selectedCoord).setNoteText(num, false);
		}
		else
		{
			view.getButton(selectedCoord).setNoteText(num, true);
		}
	}
	
	
	private void toggleAllNumButtons(boolean isEnabled)
	{
		for (int i = 0; i < 10; i++) {
			toggleNumButton(isEnabled, i);
		}
	}
	
	private void toggleNumButton(boolean isEnabled, int number)
	{
		if(number > 0 && number < 10) btnView.getButton(number-1).setEnabled(isEnabled);
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
	
	private void updateBoardTile(Coordinate coord, int num)
	{
		EntryType entryType = game.getEntryType(coord, num);
		formatTileText(entryType, view.getButton(coord));
		updateBoardTileText(coord, num);
		updateTileNotes(coord, num);
	}
	
	private void formatTileText(EntryType entryType, BoardTile btn)
	{
		switch (entryType) {
		case FIXED:
			btn.setNumFont(BoardTile.DEFAULT_FONT);
			btn.setNumTextColor(BoardTile.DEFAULT_COLOR);
			break;
		case VALIDENTRY:
			btn.setNumFont(BoardTile.ENTRY_FONT);
			btn.setNumTextColor(BoardTile.DEFAULT_COLOR);					
			break;
		case INVALIDENTRY:
			btn.setNumFont(BoardTile.INVALID_FONT);
			btn.setNumTextColor(BoardTile.INVALID_COLOR);				
			break;
		}
	}
	
	private void updateBoardTileText(Coordinate coord, int n)
	{
		BoardTile btn = view.getButton(coord);
		String text = "";
		if(n != 0) 
		{
			text = n + "";
			btn.toggleNoteText(false);
		}
		btn.setText(text);
	}
	
	private void updateTileNotes(Coordinate coord, int num)
	{
		List<Coordinate> col = getColCoordsAtCoord(coord);
		List<Coordinate> row = getRowCoordsAtCoord(coord);
		List<Coordinate> sub = getSubGridCoordsAtCoord(coord);
		for (int i = 0; i < 8; i++) {
			try {
				view.getButton(col.get(i)).setNoteText(num, false);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				view.getButton(row.get(i)).setNoteText(num, false);
			} catch (Exception e) {
				// TODO: handle exception
			}
			try {
				view.getButton(sub.get(i)).setNoteText(num, false);
			} catch (Exception e) {
				// TODO: handle exception
			}			
		}
	}
	
	private void clearTileNotes()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				view.getButton(new Coordinate(x, y)).toggleNoteText(false);
			}
		}
	}
	
	private void setBoardButtonTileType(TileType tileType)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				try {
					setTileType(tileType, view.getButton(y, x));
				} catch (Exception e) {
				}
				
			}
		}
	}
	
	
	private void setHighlighedButtonsTileType(TileType tileType, Coordinate coord)
	{
		setButtonsTileType(tileType, getColCoordsAtCoord(coord));
		setButtonsTileType(tileType, getRowCoordsAtCoord(coord));
		setButtonsTileType(tileType, getSubGridCoordsAtCoord(coord));
	}
	
	private List<Coordinate> getRowCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		for (int x = 0; x < 9; x++) {
			if(coord.x != x) coords.add(new Coordinate(x, coord.y));
		}
		return coords;
	}
	
	private List<Coordinate> getColCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		for (int y = 0; y < 9; y++) {
			if(coord.y != y) coords.add(new Coordinate(coord.x, y));	
		}
		return coords;
	}
	
	private List<Coordinate> getSubGridCoordsAtCoord(Coordinate coord)
	{
		List<Coordinate> coords = new ArrayList<Coordinate>();
		Coordinate subCoord = SudokuLogic.getNearestSubGridCoordinate(coord);
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				if(coord.y != subCoord.y + y && coord.x != subCoord.x + x)
					coords.add(new Coordinate(x + subCoord.x, y + subCoord.y));
			}
		}
		return coords;
	}
	
	private void setButtonsTileType(TileType tileType, List<Coordinate> coords)
	{
		for(Coordinate coord : coords)
		{
			setTileType(tileType, view.getButton(coord));
		}
	}
	
	private void setMatchingNumbersTileType(TileType tileType, Coordinate coord)
	{
		List<Coordinate> coordinates = game.getAllCoordinatesOfN(game.getNumAtCoordinate(coord));
		for(int i = 0; i < coordinates.size(); i++)
		{
			setTileType(tileType, view.getButton(coordinates.get(i)));
		}
	}
	
	private void setTileType(TileType tileType, BoardTile btn)
	{
		switch (tileType) {
		case NORMAL:	
			btn.setBackgroundColor(BoardView.NORMAL_TILE);	
			break;
		case SELECTED:
			btn.setBackgroundColor(BoardView.SELECTED_TILE);
			break;
		case HIGHLIGHTED:
			btn.setBackgroundColor(BoardView.HIGHLIGHTED_TILE);
			break;
		}
	}
	
	private boolean isBoardValid()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.INVALIDENTRY) return false;
			}
		}
		return true;
	}
}
