package controllers;

import java.awt.Desktop.Action;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import events.EventObserver;
import events.SaveObserver;
import model.ActionLog;
import model.GameModel;
import model.GameTime;
import model.PuzzleAction;
import model.SudokuLogic;
import model.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;
import views.GamePanelView;
import views.PanelView;

public class GamePanelController implements EventObserver
{
	private GameModel model;
	private PanelView view;
	private Shell shell;
	private int currentSolution;
	
	public GamePanelController(GameModel model, Shell shell, PanelView view)
	{
		this.model = model;
		this.view = view;
		this.shell = shell;
		currentSolution = 0;
	}
	
	public void init()
	{
		initGameList();		
		initListeners();
		updateGameTitles();
	}
	
	private void initGameList()
	{
		view.updateGameList(PuzzleSaveList.getInstance().getListAsStrings());
		Util.println(PuzzleSaveList.getInstance().getSaveList().size()+"");
	}
	
	private void initListeners()
	{
		GameTime.getInstance().attach(this);
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				savePuzzleAction();
				shell.setVisible(false);
				
			}
		});
		
		view.getPlayButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				playButtonAction();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.getDeleteButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				deleteButtonAction();	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.getGenerateBtn().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generatePuzzleAction(view.getEmptySpaces(), view.getMaxSolutions());
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.getSolveBtn().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				solvePuzzleAction();
				savePuzzleAction();	
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.getUndoButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				undoButtonAction();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		view.getRedoButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				redoButtonAction();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}
	
	//////////////////////////ACTION FUNCTIONS///////////////////////////
		
	
	private void playButtonAction()
	{
		try {
			updateGameState();
			PuzzleSaveList saveList = PuzzleSaveList.getInstance();
			SudokuPuzzle puzzle = SaveManager.loadSudokuPuzzle(PuzzleSaveList.getInstance().getSelectedSave().getName());			
			model.setGamePuzzle(puzzle);
			updateGameTitles();
			
		} catch (Exception e) {
			Util.println("No puzzle was selected.");
		}
		
	}
	
	private void deleteButtonAction()
	{
		try {
			SaveManager.deleteSudokuPuzzle(view.getGameList().getItem(view.getGameList().getSelectionIndex()));
			PuzzleSaveList saveList = PuzzleSaveList.getInstance();
			saveList.refresh();
			initGameList();
			
		} catch (Exception e) {
			Util.println("Failed to delete puzzle.");
		}
	}
	
	private void savePuzzleAction()
	{
		SaveManager.saveSudokuPuzzle(model.getPuzzle(), true);
		PuzzleSaveList.getInstance().refresh();
		initGameList();
	}
	
	private void undoButtonAction()
	{
		PuzzleAction move = ActionLog.getInstance().getAction();
		if(move == null) {
			
			return;
		}
		model.updateUserPuzzle(move.getOldValue(), move.getCoordinate().y, move.getCoordinate().x);
		ActionLog.getInstance().undoAction();
	}
	
	private void redoButtonAction()
	{
		PuzzleAction move = ActionLog.getInstance().getAction();
		ActionLog.getInstance().redoAction();
		Util.println(move.getCoordinate().y + ":" + move.getCoordinate().x + "...." + move.getOldValue() + ".." + move.getNewValue());
		model.updateUserPuzzle(move.getNewValue(), move.getCoordinate().y, move.getCoordinate().x);
		
		return;
	}
	
	private void updateGameTime()
	{
		view.getTimeLabel().setText(GameTime.getInstance().getTime().toString());
	}
	
	private void updateGameTitles()
	{
		try {
			SudokuPuzzle puzzle = model.getPuzzle();
			view.getPuzzleNameLbl().setText(puzzle.getName());
			view.getDifficultyLbl().setText(puzzle.getDifficulty().name());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	private void updateGameState()
	{
		PuzzleSaveList saveList = PuzzleSaveList.getInstance();
		saveList.setSelectedSave(saveList.getSaveList().get(view.getGameList().getSelectionIndex()));
		savePuzzleAction();
	}
	
	private void generatePuzzleAction(int emptySpaces, int maxSolutions)
	{
		savePuzzleAction();
		model.setGamePuzzle(SudokuLogic.generateRandomPuzzle(emptySpaces, maxSolutions));
	}
	
	private void solvePuzzleAction()
	{
		SudokuLogic.solve(model.getPuzzle());
		int[][] userPuz = model.getPuzzle().getSolution(currentSolution);
		model.setUserPuzzle(userPuz);
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				Util.print(model.getPuzzle().getUserPuzzle()[y][x]+"");
			}
		}
	}

	@Override
	public void update() {
		updateGameTime();
	}
}
