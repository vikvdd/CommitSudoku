package controllers;

import java.io.IOException;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import events.ModelChangeObserver;
import model.Coordinate;
import model.GameModel;
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
	private int selectedX;
	private int selectedY;
	
	public GameboardController(GameModel model, GameboardView view, GameButtonsView buttonView)
	{
		this.model = model;
		this.view = view;
		this.btnView = buttonView;
		selectedX = 0;
		selectedY = 0;
	}
	
	public void init()
	{
		initModel();
		initBoardButtons();
		initGameButtons();
		fillGameboard();
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
						// TODO Auto-generated method stub
						
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
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
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
		int[][] userPuz = puzzle.getUserPuzzle();
		if(val == userPuz[selectedY][selectedX]) val = 0;
		model.updateUserPuzzle(val, selectedY, selectedX);
		setButtonText(val, selectedY, selectedX);

		return;
	}
	
	private void fillGameboard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				setButtonText(model.getPuzzle().getUserPuzzle()[y][x], y, x);
			}	
		}
	}
	
	private void setButtonText(int val, int y, int x)
	{
		Button btn = view.getButton(y,x);
		SudokuPuzzle puzzle = model.getPuzzle();
		btn.setBackground(GameboardView.DEFAULT_TILE);
	
		if(puzzle.get()[y][x] != 0)
		{
			btn.setFont(GameboardView.DEFAULT_FONT);
			btn.setForeground(GameboardView.DEFAULT_COLOR);
			btn.setBackground(GameboardView.MAIN_TILE);
			btn.setText(puzzle.get()[y][x] + "");
			return;
		}
		else if(puzzle.get()[y][x] == 0 && puzzle.getUserPuzzle()[y][x] == 0) btn.setText("");
		else 
		{
			if(val != 0)
			{
				if(SudokuLogic.possible(puzzle.get(), y, x, val))
				{
					btn.setFont(GameboardView.ENTRY_FONT);
					btn.setForeground(GameboardView.DEFAULT_COLOR);					
					btn.setText(val + "");
				}
				else {
					btn.setFont(GameboardView.INVALID_FONT);
					btn.setForeground(GameboardView.INVALID_COLOR);				
					btn.setText(val + "");
				}
			}		
		}
		if(puzzle.get()[selectedY][selectedX] == 0 ) view.getSelectedButton().setBackground(GameboardView.SELECTED_TILE);
		
	}
	

	@Override
	public void update() {
		fillGameboard();		
	}

}
