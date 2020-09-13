package controllers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import events.SaveObserver;
import model.GameModel;
import model.SudokuLogic;
import model.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;
import views.GamePanelView;
import views.PanelView;

public class GamePanelController
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
	}
		
	
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
	
	private void savePuzzleAction()
	{
		SaveManager.saveSudokuPuzzle(model.getPuzzle(), true);
		PuzzleSaveList.getInstance().refresh();
		initGameList();
	}
	
	private void updateGameTitles()
	{
		SudokuPuzzle puzzle = model.getPuzzle();
		view.getPuzzleNameLbl().setText(puzzle.getName());
		view.getDifficultyLbl().setText(puzzle.getDifficulty().name());
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
}
