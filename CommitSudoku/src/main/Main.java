package main;

import java.io.File;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import controllers.BoardController;
import controllers.GamePanelController;
import controllers.NumButtonController;
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
import views.LogoView;
import views.BoardViewBase;
import views.PanelView;
import views.View;
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
		
		
		
		Shell shell = new Shell();
		initWindow(shell);
		ViewScaleManager sm = new ViewScaleManager(shell.getDisplay().getPrimaryMonitor().getClientArea());
		
		//INIT MODEL
		SudokuGame game = new SudokuGame();
		SaveManager saveManager = new SaveManager();
		IGameDAO gameDAO = new GameDAO();
		GameDataSaver dataSaver = new GameDataSaver(gameDAO, game);
		game.loadNewGame(tryLoadFirstPuzzle(gameDAO));
		
		//INIT VIEWS
		BoardViewBase board = new BoardView(shell, SWT.NONE);
		board.setSize(sm.getBoardSize().getWidth(), sm.getBoardSize().getHeight());
		PanelView panelView = new PanelView(shell, SWT.NONE);
		panelView.init();
		GridData buttonsData = new GridData();
		buttonsData.horizontalAlignment = SWT.CENTER;
		GameButtonsView buttonsView = new GameButtonsView(shell, SWT.None);
		buttonsView.setLayoutData(buttonsData);
		buttonsView.init(sm.getButtonViewSize().getWidth(), sm.getButtonViewSize().getHeight());
		LogoView logoView = new LogoView(shell, SWT.NONE);
		logoView.init(display);
		
		//INIT CONTROLLERS
		initControllers(display, game, board, buttonsView, panelView);
		
		shell.open();
		shell.layout();
		
		final Point newSize = shell.computeSize(SWT.DEFAULT, SWT.DEFAULT, true); 
		shell.setSize(newSize);
		
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				shell.setVisible(false);
				game.end();
				shell.dispose();
			}
		});
		
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
	
	private static Shell initWindow(Shell shell)
	{
		shell.setSize(0,0);
		shell.setText("CommitSudoku");
		shell.setBackground(new Color(null,30, 59, 89));
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 0;
		GridData shellData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		shell.setLayout(gridLayout);
		return shell;
	}
	
	private static void initControllers(Display display, SudokuGame game, BoardViewBase board, GameButtonsView buttonsView, PanelView panelView)
	{
		GamePanelController panelController = new GamePanelController(display, game, panelView);
		panelController.init();
		NumButtonController buttonController = new NumButtonController(game, buttonsView);
		buttonController.init();
		BoardController boardController = new BoardController(game, board);
		boardController.init();
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
