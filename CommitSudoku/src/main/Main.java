package main;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import controllers.GamePanelController;
import controllers.GameboardController;
import model.GameModel;
import util.Util;
import views.GameButtonsView;
import views.GamePanelView;
import views.GameboardView;
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
		Shell shell = initWindow(sm.getWindowSize(), sm.getWindowSize());
		
		//INIT MODEL
		GameModel model = new GameModel();
		
		//INIT VIEWS
		Util.println("..." + sm.getBoardSize());
		GameboardView board = new GameboardView(shell, SWT.BORDER);
		board.init(sm.getPadding(), sm.getPadding(), sm.getBoardSize(), sm.getBoardSize());
		GamePanelView panelView = new GamePanelView(shell, SWT.BORDER);
		panelView.init(board.getBounds().width + sm.getPadding()*2, sm.getPadding(), sm.getPanelSize(), board.getBounds().height);
		GameButtonsView buttonsView = new GameButtonsView(shell, SWT.None);
		buttonsView.init(sm.getBoardSize()/2-sm.getButtonViewSize()/2, sm.getBoardSize() + sm.getPadding()*4, sm.getButtonViewSize(), 60);
		
		//INIT CONTROLLERS
		initControllers(model, shell, board, buttonsView, panelView);
		
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
		shell.setBackground(new Color(null,28, 28, 28));
		return shell;
	}
	
	private static void initControllers(GameModel model, Shell shell, GameboardView board, GameButtonsView buttonsView, GamePanelView panelView)
	{
		GameboardController boardController = new GameboardController(model, board, buttonsView);
		boardController.init();
		GamePanelController panelController = new GamePanelController(model, panelView);
		panelController.init();
	}
}
