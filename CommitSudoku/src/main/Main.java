package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import controllers.BoardController;
import controllers.GamePanelController;
import controllers.GameboardController;
import model.GameModel;
import model.game.GameDataSaver;
import model.game.SudokuGame;
import util.Util;
import views.GameButtonsView;
import views.GameboardView;
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
		ViewScaleManager sm = new ViewScaleManager(1024, true);
		Shell shell = initWindow(sm.getWindowSize(), sm.getWindowSize()-120); 
		
		//INIT MODEL
		GameModel model = new GameModel();
		SudokuGame game = new SudokuGame();
		
		//INIT VIEWS
		GameboardView board = new GameboardView(shell, SWT.BORDER);
		board.init(sm.getPadding(), sm.getPadding(), sm.getBoardSize()-5, sm.getBoardSize()-5);
		PanelView panelView = new PanelView(shell, SWT.NONE);
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
	
	private static void initControllers(SudokuGame game, Shell shell, GameboardView board, GameButtonsView buttonsView, PanelView panelView)
	{
		/*GameboardController boardController = new GameboardController(game, board, buttonsView);
		boardController.init();*/
		BoardController boardController = new BoardController(game, board, buttonsView);
		boardController.init();
		GamePanelController panelController = new GamePanelController(game, shell, panelView);
		panelController.init();
	}
	
	private static void initSaveSystem()
	{
		
	}
}
