package main;

import java.io.File;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import controllers.BoardController;
import controllers.GamePanelController;
import model.game.SudokuGame;
import model.game.puzzle.SudokuLogic;
import model.game.puzzle.SudokuPuzzle;
import model.savesystem.GameDAO;
import model.savesystem.GameDataSaver;
import model.savesystem.IGameDAO;
import model.savesystem.PuzzleSaveList;
import model.savesystem.SaveManager;
import views.BoardView;
import views.GameButtonsView;
import views.GameboardView;
import views.IBoardView;
import views.PanelView;
import views.ViewScaleManager;

public class Main {
	public static void main(String[] args) 
	{
		init();
	}
	
	public static void init()
	{
		//INIT DISPLAY/WINDOW
		Display display = Display.getDefault();
		ViewScaleManager sm = new ViewScaleManager(1200, true);
		Shell shell = initWindow(sm.getWindowSize(), sm.getWindowSize()-220); 
		
		//INIT MODEL
		SudokuGame game = new SudokuGame();
		SaveManager saveManager = new SaveManager();
		IGameDAO gameDAO = new GameDAO();
		GameDataSaver dataSaver = new GameDataSaver(gameDAO, game);
		game.loadNewGame(tryLoadFirstPuzzle(gameDAO));
		
		//INIT VIEWS
		//IBoardView board = new GameboardView(shell, SWT.BORDER);
		IBoardView board = new BoardView(shell, SWT.NONE);
		board.init(sm.getPadding(), sm.getPadding(), sm.getBoardSize(), sm.getBoardSize());
		PanelView panelView = new PanelView(shell, SWT.NONE);
		panelView.init();
		GridData buttonsData = new GridData();
		buttonsData.horizontalAlignment = SWT.CENTER;
		GameButtonsView buttonsView = new GameButtonsView(shell, SWT.None);
		buttonsView.setLayoutData(buttonsData);
		buttonsView.init(sm.getBoardSize()/2-sm.getButtonViewSize()/2, sm.getBoardSize() + sm.getPadding()*4, sm.getButtonViewSize(), 60);
		
		//INIT CONTROLLERS
		initControllers(game, shell, board, buttonsView, panelView);
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	private static Shell initWindow(int width, int height)
	{
		Shell shell = new Shell();
		shell = new Shell();
		shell.setSize(width, height);
		shell.setText("CommitSudoku");
		shell.setBackground(new Color(null,209, 191, 174));
		GridLayout gridLayout = new GridLayout(2, false);
		GridData shellData = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		shell.setLayout(gridLayout);
		return shell;
	}
	
	private static void initControllers(SudokuGame game, Shell shell, IBoardView board, GameButtonsView buttonsView, PanelView panelView)
	{
		BoardController boardController = new BoardController(game, board, buttonsView);
		boardController.init();
		GamePanelController panelController = new GamePanelController(game, shell, panelView);
		panelController.init();
	}
	
	private static SudokuPuzzle tryLoadFirstPuzzle(IGameDAO dao)
	{
		SudokuPuzzle puzzle = SudokuLogic.generateRandomPuzzle();
		try {
			String puzName = PuzzleSaveList.getInstance().getListAsStrings()[0];
			puzzle = dao.loadPuzzle(new File(puzName + ".txt"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return puzzle;
	}
}
