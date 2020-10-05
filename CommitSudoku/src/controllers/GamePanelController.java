package controllers;

import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import events.GameListener;
import events.TimeListener;
import model.game.SudokuGame;
import model.game.puzzle.Coordinate;
import model.game.puzzle.Difficulty;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import util.Util;
import views.PanelView;

public class GamePanelController extends BaseController implements GameListener, TimeListener
{
	private SudokuGame game;
	private PanelView view;
	private Display display;
	private int currentSolution;
	
	public GamePanelController(Display display, SudokuGame game, PanelView view)
	{
		super(game);
		this.display = display;
		this.game = game;
		this.view = view;
		currentSolution = 0;
	}
	
	@Override
	protected void initModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void initView() {
		// TODO Auto-generated method stub
		updateGameTitles(game.getPuzzleName(), game.getPuzzleDifficulty());
	}

	@Override
	protected void initController() {
		// TODO Auto-generated method stub
		initGameList();
	}

	@Override
	public void start() {
		
		
	}
		
	private void initGameList()
	{
		view.updateGameList(PuzzleSaveList.getInstance().getListAsStrings());
	}
	
	@Override
	protected void initListeners()
	{
		super.initListeners();
		game.addGameListener(this);
		game.getGameTime().addTimeListener(this);
		Util.println("listeners");
		
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
		view.getResetButton().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				resetButtonAction();
				
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
			game.end();
			PuzzleSaveList saveList = PuzzleSaveList.getInstance();
			SudokuPuzzle puzzle = SaveManager.loadPuzzle(PuzzleSaveList.getInstance().getSelectedSave().getName());		
			game.loadNewGame(puzzle);	
		} catch (Exception e) {
			Util.println("No puzzle was selected.");
		}
		
	}
	
	private void deleteButtonAction()
	{
		try {
			SaveManager.deletePuzzle(view.getGameList().getItem(view.getGameList().getSelectionIndex()));
			PuzzleSaveList saveList = PuzzleSaveList.getInstance();
			saveList.refresh();
			initGameList();
			
		} catch (Exception e) {
			Util.println("Failed to delete puzzle.");
		}
	}
	
	private void savePuzzleAction()
	{
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
		game.end();
		savePuzzleAction();
		game.loadNewGame(SudokuLogic.generateRandomPuzzle(emptySpaces, maxSolutions));
	}
	
	private void solvePuzzleAction()
	{
		game.solve();
	}
	
	private void resetButtonAction()
	{
		game.reset();
	}
	
	private void updateGameTime()
	{
		if(!display.isDisposed())
		{
			long elapsed = game.getGameTime().getTime();
			long second = (elapsed / 1000) % 60;
			long minute = (elapsed / (1000 * 60)) % 60;
			String time = String.format("%02d:%02d", minute, second);
			view.getTimeLabel().setText(time);
		}
	}
	
	private void updateGameTitles(String name, Difficulty difficulty)
	{
		
		try {
			view.getPuzzleNameLbl().setText(name + "");
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
		
		game.getGameTime().addTimeListener(this);
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
	public void onNoteEntry(int num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleCompleted() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPuzzleSolved(int[][] solution) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void onStart() {
		Runnable timer = new Runnable() {
			
			@Override
			public void run() {
				updateGameTime();
				if(!display.isDisposed())
					display.timerExec(500, this);
			}
		};
		display.timerExec(500, timer);
		
	}

	@Override
	public void onStop() {
		
		
	}	
}
