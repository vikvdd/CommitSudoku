package controllers;

import java.util.ArrayList;
import java.util.List;
import events.BoardTileListener;
import events.GameListener;
import events.GameStatListener;
import model.EntryType;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.Notes;
import util.Util;
import views.BoardTile;
import views.*;

public class BoardController implements GameListener, GameStatListener{
	
	private SudokuGame game;
	private IBoardView view;
	private List<Coordinate> highlightedTiles = new ArrayList<Coordinate>();
	
	
	public BoardController(SudokuGame game, IBoardView view)
	{
		this.game = game;
		this.view = view;

	}
	
	public void init()
	{
		initListeners();
		initBoardButtons();
		//initGameButtons();
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
	
	private void initListeners()
	{
		game.addGameListener(this);
	}
	
	private void loadStartBoard() 
	{
		setBoardButtonTileType(TileType.NORMAL);
		initBoardButtons();
	}
	
	
	private void selectTileAction(Coordinate coord)
	{
		Coordinate selectCoord = game.getSelectedCoord();
		setButtonsTileType(TileType.NORMAL, highlightedTiles);
		setTileType(TileType.NORMAL, view.getButton(selectCoord));
		game.setSelectedCoord(coord);
		view.setSelectedButton(coord);
		view.getSelectedButton().setBackgroundColor(BoardView.SELECTED_TILE);
		
		if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.FIXED) fixedTileClickAction(coord);
		else entryTileClickAction(coord);
	}
	
	private void fixedTileClickAction(Coordinate coord)
	{
		highlightedTiles = game.getAllCoordinatesOfN(game.getNumAtCoordinate(coord));
		setMatchingNumbersTileType(TileType.HIGHLIGHTED, highlightedTiles);
	}
	
	private void entryTileClickAction(Coordinate coord)
	{
		highlightedTiles = game.getColCoordsAtCoord(coord);
		highlightedTiles.addAll(game.getRowCoordsAtCoord(coord));
		highlightedTiles.addAll(game.getSubGridCoordsAtCoord(coord));
		setButtonsTileType(TileType.HIGHLIGHTED, highlightedTiles);
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
		updateAllTileNotes();
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
	
	private void updateTileNotes(Coordinate coord)
	{
		Notes notes = game.getNotesAtCoordinate(coord);
		for(int i = 1; i <= 9; i++)
		{			
			view.getButton(coord).setNoteText(i, notes.isNoteActive(i));
		}
	}
	
	private void updateGivenTileNotes(List<Coordinate> coords)
	{
		for(Coordinate coord : coords)
		{
			Notes notes = game.getNotesAtCoordinate(coord);
			for(int i = 1; i <= 9; i++)
			{
				Util.println(notes.isNoteActive(i) + "");
				view.getButton(coord).setNoteText(i, notes.isNoteActive(i));
			}
		}
	}
	
	private void updateAllTileNotes()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				Notes notes = game.getNotesAtCoordinate(coord);
				for(int i = 1; i <= 9; i++)
				{
					try {
						view.getButton(coord).setNoteText(i, notes.isNoteActive(i));
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			}
		}
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
	
	private void setButtonsTileType(TileType tileType, List<Coordinate> coords)
	{
		for(Coordinate coord : coords)
		{
			setTileType(tileType, view.getButton(coord));
		}
	}
	
	private void setMatchingNumbersTileType(TileType tileType, List<Coordinate> coords)
	{
		for(int i = 0; i < coords.size(); i++)
		{
			setTileType(tileType, view.getButton(coords.get(i)));
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
	
	@Override
	public void onGameStart(int[][] puzzleClone) {
		game.getStatTracker().addGameStatListener(this);
		fillGameboard(puzzleClone);
		loadStartBoard();
		selectTileAction(game.getSelectedCoord());
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
	public void onNoteEntry(int num) {
		// TODO Auto-generated method stub
		Util.println(num);
		updateTileNotes(game.getSelectedCoord());
		updateGivenTileNotes(highlightedTiles);
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
		
	}
}
