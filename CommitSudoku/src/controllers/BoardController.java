package controllers;

import java.util.ArrayList;
import java.util.List;
import events.BoardTileListener;
import events.GameListener;
import model.EntryType;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.Notes;
import views.BoardTile;
import views.*;

public class BoardController extends BaseController implements GameListener{
	
	BoardViewBase view;
	private List<Coordinate> selectedTiles = new ArrayList<Coordinate>();
		
	public BoardController(SudokuGame game, BoardViewBase view)
	{
		super(game);
		this.view = view;
	}
	
	@Override
	public void initModel() {
		
	}

	public void initView()
	{
		view.init();
	}
	
	@Override
	public void initController() {
		
	}
	
	@Override
	protected void initListeners()
	{
		super.initListeners();
		initBoardListeners();
	}
	
	private void initBoardListeners() {
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
		setBoardTileType(TileType.NORMAL);
	}

	@Override
	public void start() {
		game.start();
		
	}
	
	private void fillGameboard(int[][] puz)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Coordinate coord = new Coordinate(x, y);
				updateTile(coord, puz[y][x]);
			}	
		}
	}
	
	private void clearGameboard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				BoardTile tile = view.getButton(new Coordinate(x, y));
				tile.resetTile();
			}
		}
	}
	
	private void selectTileAction(Coordinate coord)
	{
		Coordinate selectCoord = game.getSelectedCoord();
		setTileType(TileType.NORMAL, selectedTiles);
		setTileType(TileType.NORMAL, selectCoord);
		
		game.setSelectedCoord(coord);
		view.setSelectedButton(coord);
		
		view.getSelectedButton().setBackgroundColor(BoardView.SELECTED_TILE);
		
		if(game.getEntryType(coord, game.getNumAtCoordinate(coord)) == EntryType.FIXED) 
		{
			selectAllTilesMatchingCoord(coord);
		}
		else 
		{
			selectAllOpposingTiles(coord);
		}
		
		setTileType(TileType.HIGHLIGHTED, selectedTiles);
	}
	
	private void selectAllTilesMatchingCoord(Coordinate coord)
	{
		selectedTiles = game.getAllCoordinatesOfN(game.getNumAtCoordinate(coord));
	}
	
	private void selectAllOpposingTiles(Coordinate coord)
	{
		selectedTiles = game.getColCoordsAtCoord(coord);
		selectedTiles.addAll(game.getRowCoordsAtCoord(coord));
		selectedTiles.addAll(game.getSubGridCoordsAtCoord(coord));
		setTileType(TileType.HIGHLIGHTED, selectedTiles);
	}
	
	private void updateTile(Coordinate coord, int num)
	{
		EntryType entryType = game.getEntryType(coord, num);
		updateTileFont(entryType, view.getButton(coord));
		updateTileText(coord, num);
		updateTileNotes(coord);
	}
	
	private void updateTileText(Coordinate coord, int n)
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
	
	private void updateTileNotes(List<Coordinate> coords)
	{
		for(Coordinate coord : coords)
		{
			Notes notes = game.getNotesAtCoordinate(coord);
			for(int i = 1; i <= 9; i++)
			{
				view.getButton(coord).setNoteText(i, notes.isNoteActive(i));
			}
		}
	}
	
	private void updateTileFont(EntryType entryType, BoardTile btn)
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
	
	private void setBoardTileType(TileType tileType)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				try {
					setTileType(tileType, new Coordinate(x, y));
				} catch (Exception e) {
				}
				
			}
		}
	}
	
	private void setTileType(TileType tileType, List<Coordinate> coords)
	{
		for(Coordinate coord : coords)
		{
			setTileType(tileType, coord);
		}
	}
	
	private void setTileType(TileType tileType, Coordinate coord)
	{
		BoardTile tile = view.getButton(coord);
		switch (tileType) {
		case NORMAL:	
			tile.setBackgroundColor(BoardView.NORMAL_TILE);	
			break;
		case SELECTED:
			tile.setBackgroundColor(BoardView.SELECTED_TILE);
			break;
		case HIGHLIGHTED:
			tile.setBackgroundColor(BoardView.HIGHLIGHTED_TILE);
			break;
		}
	}
	
	@Override
	public void onGameStart(int[][] puzzleClone) {
		fillGameboard(puzzleClone);
		selectTileAction(game.getSelectedCoord());
	}
	
	@Override 
	public void onGameEnd() {
		clearGameboard();
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String elapsedTime) {
		start();
	}

	@Override
	public void onNumberEntry(Coordinate coord, int n) {
		updateTile(coord, n);
		updateTileNotes(selectedTiles);
	}

	@Override
	public void onNoteEntry(int num) {
		// TODO Auto-generated method stub
		updateTileNotes(game.getSelectedCoord());
		updateTileNotes(selectedTiles);
	}

	
}
