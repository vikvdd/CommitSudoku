package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import events.ModelChangeObserver;
import model.ActionLog;
import model.Coordinate;
import model.GameModel;
import model.GameStatTracker;
import model.GameTime;
import model.NumTile;
import model.PuzzleAction;
import model.SudokuLogic;
import model.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;
import views.GameButtonsView;
import views.GameboardView;

public class GameboardController implements ModelChangeObserver
{
	
	private GameboardView view;
	private GameButtonsView btnView;
	private GameModel model;
	private GameStatTracker statTracker;
	private int selectedX;
	private int selectedY;
	List<Coordinate> highlightedNumbers;
	
	public GameboardController(GameModel model, GameboardView view, GameButtonsView buttonView)
	{
		this.model = model;
		this.view = view;
		this.btnView = buttonView;
		highlightedNumbers = new ArrayList<Coordinate>();
		selectedX = 0;
		selectedY = 0;
	}
	
	////////////////INIT FUNCTIONS////////////////
	
	public void init()
	{
		initModel();
		initBoardButtons();
		initGameButtons();
		fillGameboard();
		loadGame();
		GameTime.getInstance().start();
	}
	
	private void initModel()
	{
		SudokuPuzzle puzzle;
		try {
			puzzle = SaveManager.loadSudokuPuzzle(PuzzleSaveList.getInstance().getSaveList().get(0).getName());
			model.setGamePuzzle(puzzle);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(model.getPuzzle()==null) model.setGamePuzzle(SudokuLogic.generateRandomPuzzle());
		model.attach(this);
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
					} catch (IOException e) {
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
	
	//////////////////////ACTION FUNCTIONS//////////////////////////////////
	
	public void selectTileAction(Coordinate coordinate) {
		SudokuPuzzle puzzle = model.getPuzzle();
		if(puzzle.get()[selectedY][selectedX] == 0) view.getSelectedButton().setBackground(GameboardView.DEFAULT_TILE);
		selectedX = coordinate.x;
		selectedY = coordinate.y;
		Button btn = view.getButton(selectedY, selectedX);
		if(puzzle.get()[selectedY][selectedX] != 0)  btn.setBackground(GameboardView.MAIN_TILE);
		else btn.setBackground(GameboardView.SELECTED_TILE);
		view.setSelectedButton(btn);
	}
	
	private void numButtonAction(int val) throws IOException
	{
		SudokuPuzzle puzzle = model.getPuzzle();
		int[][] userPuz = Util.Clone2dArray(puzzle.getUserPuzzle());
		if(val == userPuz[selectedY][selectedX]) val = 0;
		PuzzleAction action = new PuzzleAction(new Coordinate(selectedX, selectedY), userPuz[selectedY][selectedX],val);
		ActionLog.getInstance().addAction(action);
		statTracker.addCoordinate(selectedY, selectedX, val);
		model.updateUserPuzzle(val, selectedY, selectedX);

		return;
	}
	
	
	//////////////////////BOARD FUNCTIONS///////////////////////////////
	
	private void loadGame()
	{
		statTracker = new GameStatTracker(model);
		fillGameboard();
	}
	
	private void updateGame()
	{
		fillGameboard();
	}
	
	private void highlightMatchingTiles()
	{
		
	}
	
	private void fillGameboard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				int[][] puzzle = model.getPuzzle().getUserPuzzle();
				int val = puzzle[y][x];
				puzzle[y][x] = 0;				
				NumTile tileType = findNumTileType(y, x);
				setButtonText(val, y, x, tileType);
			}	
		}
	}
	
	private void setButtonText(int val, int y, int x, NumTile tileType)
	{	
		Button btn = view.getButton(y,x);
		SudokuPuzzle puzzle = model.getPuzzle();
		btn.setBackground(GameboardView.DEFAULT_TILE);
		
		switch (tileType) {
		case Number:
			btn.setFont(GameboardView.DEFAULT_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);
			btn.setBackground(GameboardView.MAIN_TILE);
			btn.setText(puzzle.get()[y][x] + "");
			break;
		case Empty:
			btn.setText("");
			break;
		case ValidEntry:
			btn.setFont(GameboardView.ENTRY_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);					
			btn.setText(val + "");
			break;
		case InvalidEntry:
			btn.setFont(GameboardView.INVALID_FONT);
			btn.setForeground(GameboardView.INVALID_COLOR);				
			btn.setText(val + "");
			break;
		}		
	}
	
	//when val matches userpuzzle[y][x] it returns empty tile
	private NumTile findNumTileType(int y, int x)
	{
		int[][] puzzle = Util.Clone2dArray(model.getPuzzle().get());
		int[][] userPuz = model.getPuzzle().getUserPuzzle();
		if(puzzle[y][x] != 0) return NumTile.Number;
		else {
			if(isValidEntry(userPuz[y][x], y, x)) return NumTile.ValidEntry;
			else if(userPuz[y][x] == 0) return NumTile.Empty; 
		}
		return NumTile.InvalidEntry;
	}
	
	private boolean isValidEntry(int val, int y, int x)
	{
		int[][] puz = Util.Clone2dArray(model.getPuzzle().get());
		int[][] userPuz = model.getPuzzle().getUserPuzzle();
		if(puz[y][x] == 0)
		{
			userPuz[y][x] = 0;
			if(SudokuLogic.possible(userPuz, y, x, val))
				return SudokuLogic.possible(puz, y, x, val);
		}
		return false;
	}
	

	@Override
	public void update() {
		updateGame();		
	}

}
