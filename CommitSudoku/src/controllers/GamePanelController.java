package controllers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import events.GameListener;
import model.game.GameTime;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;
import views.PanelView;

public class GamePanelController implements GameListener
{
	private SudokuGame game;
	private PanelView view;
	private Shell shell;
	private int currentSolution;
	
	public GamePanelController(SudokuGame game, Shell shell, PanelView view)
	{
		this.game = game;
		this.view = view;
		this.shell = shell;
		currentSolution = 0;
	}
	
	public void init()
	{
		initGameList();		
		initListeners();
	}
	
	private void initGameList()
	{
		view.updateGameList(PuzzleSaveList.getInstance().getListAsStrings());
		Util.println(PuzzleSaveList.getInstance().getSaveList().size()+"");
	}
	
	private void initListeners()
	{
		game.addGameListener(this);
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
			game.loadNewGame(puzzle);	
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
		//SaveManager.saveSudokuPuzzle(game.getPuzzle(), true);
		PuzzleSaveList.getInstance().refresh();
		initGameList();
	}
	
	private void undoButtonAction()
	{
		game.undoAction();
	}
	
	private void redoButtonAction()
	{
		game.redoAction();
	}
	
	private void generatePuzzleAction(int emptySpaces, int maxSolutions)
	{
		savePuzzleAction();
		game.loadNewGame(SudokuLogic.generateRandomPuzzle(emptySpaces, maxSolutions));
	}
	
	private void solvePuzzleAction()
	{
		game.solve();
	}
	
	private void updateGameTime()
	{
		view.getTimeLabel().setText(GameTime.getInstance().getTime().toString());
	}
	
	private void updateGameTitles(String name, Difficulty difficulty)
	{
		try {
			view.getPuzzleNameLbl().setText(Util.formatStringToTitle(name));
			view.getDifficultyLbl().setText(Util.capitalizeFirstLetter(difficulty.name()));
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

	@Override
	public void onGameStart(int[][] puzzleClone) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onGameEnd() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleChanged(String name, Difficulty difficulty, String gameTime) {
		updateGameTitles(name, difficulty);
	}

	@Override
	public void onNumberEntry(Coordinate coord, int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}
}
