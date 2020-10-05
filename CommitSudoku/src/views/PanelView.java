package views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.internal.handlers.RestartWorkbenchHandler;
import org.eclipse.ui.internal.handlers.WizardHandler.New;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class PanelView extends View{

	private Composite gamePanel;
	private List gameList;
	private Button playButton;
	
	private Label lblTotalSolutions;
	private Spinner totalSolutionsSpin;
	private Label lblemptySpaces;
	private Spinner emptySpacesSpin;
	private Composite compositePuzzleGen;
	private Composite composite;
	private Label lblPuzzle;
	private Label lblDifficultyTitle;
	private Label lblPuzzleName;
	private Label lbldifficulty;
	private Label lblTime;
	private Label lblDateCompletedTitle;
	private Label lblDateCompleted;
	private Button generateBtn;
	private Composite composite_1;
	private Label label_1;
	private Composite composite_2;
	private Composite composite_3;
	private Button btnSolve;
	private Button undoButton;
	private Button redoButton;
	private Button resetButton;
	
	public static Color primaryColor = new Color(null,  220,220,220);
	public static Color secondaryColor = new Color(null, 220,220,220);
	public static Color highlightColor = new Color(null, 176, 209, 217);
	private Button deleteBtn;
	
	private int width = 250;
	
	public PanelView(Composite parent, int style) {
		super(parent, style);
	}
	
	

	@Override
	public void updateComponentSizes() {
		
	}
	
	public List getGameList()
	{
		return gameList;
	}
	
	public void updateGameList(String[] newList)
	{	
		gameList.removeAll();
		if(newList.length > 0)
		{
			for(String str : newList)
			{
				if(str!=null)
					gameList.add(str);
			}
		}
	}
	
	public Button getPlayButton()
	{
		return playButton;
	}
	
	public Button getDeleteButton()
	{
		return deleteBtn;
	}
	
	public Label getTimeLabel()
	{
		return lblTime;
	}
	
	public Label getPuzzleNameLbl()
	{
		return lblPuzzleName;
	}
	
	public Label getDifficultyLbl()
	{
		return lbldifficulty;
	}
	
	public Label getDateCompLbl()
	{
		return lblDateCompleted;
	}
	
	public Button getGenerateBtn()
	{
		return generateBtn;
	}
	
	public int getMaxSolutions()
	{
		return totalSolutionsSpin.getSelection();
	}
	
	public int getEmptySpaces()
	{
		return emptySpacesSpin.getSelection();
	}
	
	public Button getSolveBtn()
	{
		return btnSolve;
	}
	
	public Button getUndoButton()
	{
		return undoButton;
	}
	
	public Button getRedoButton()
	{
		return redoButton;
	}
	
	public Button getResetButton()
	{
		return resetButton;
	}
	
	@Override
	public void init() {
		GridLayout gridLayout = new GridLayout(1, false);
		gridLayout.verticalSpacing = 10;
		gridLayout.marginTop = 10;
		gridLayout.marginWidth = 10;
		gridLayout.horizontalSpacing = 1;
		GridData gridData = new GridData(SWT.LEFT, SWT.FILL, true, false);
		gridData.widthHint = width;
		gridData.minimumWidth = width;
		gridData.heightHint = SWT.DEFAULT;
		setLayoutData(gridData);
		
		setLayout(gridLayout);
		setLayoutData(gridData);
		setBackground(primaryColor);
		
		composite_2 = new Composite(this, SWT.NONE);
		GridLayout gl_composite_2 = new GridLayout(2, true);
		gl_composite_2.verticalSpacing = 10;
		composite_2.setLayout(gl_composite_2);
		composite_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite_2.setBackground(primaryColor);
		
		composite_3 = new Composite(composite_2, SWT.NONE);
		composite_3.setBackground(secondaryColor);
		GridLayout gl_composite_3 = new GridLayout(2, true);
		gl_composite_3.verticalSpacing = 3;
		composite_3.setLayout(gl_composite_3);
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_composite_3.heightHint = 87;
		composite_3.setLayoutData(gd_composite_3);
		
		lblTime = new Label(composite_3, SWT.NONE);
		lblTime.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		lblTime.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.BOLD));
		lblTime.setBackground(secondaryColor);
		lblTime.setText("000:000");
		
		lblPuzzle = new Label(composite_3, SWT.NONE);
		lblPuzzle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPuzzle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblPuzzle.setText("Puzzle Name:");
		lblPuzzle.setBackground(secondaryColor);
		
		lblPuzzleName = new Label(composite_3, SWT.NONE);
		lblPuzzleName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lblPuzzleName.setText("ExamplePuzzle");
		lblPuzzleName.setBackground(secondaryColor);
		
		lblDifficultyTitle = new Label(composite_3, SWT.NONE);
		lblDifficultyTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDifficultyTitle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDifficultyTitle.setText("Difficulty:");
		lblDifficultyTitle.setBackground(secondaryColor);
		
		lbldifficulty = new Label(composite_3, SWT.NONE);
		lbldifficulty.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		lbldifficulty.setText("HARD AF");
		lbldifficulty.setBackground(secondaryColor);
		
		lblDateCompletedTitle = new Label(composite_3, SWT.NONE);
		lblDateCompletedTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDateCompletedTitle.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		lblDateCompletedTitle.setText("Date Completed:");
		lblDateCompletedTitle.setBackground(secondaryColor);
		
		lblDateCompleted = new Label(composite_3, SWT.NONE);
		lblDateCompleted.setText("-");
		lblDateCompleted.setBackground(secondaryColor);
		lblDateCompleted.setData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		
		gameList = new List(composite_2, SWT.BORDER | SWT.V_SCROLL);
		GridData gd_gameList = new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1);
		gd_gameList.heightHint = 200;
		gameList.setLayoutData(gd_gameList);
		gameList.setItems(new String[] {});
		gameList.setBackground(highlightColor);
		
		playButton = new Button(composite_2, SWT.NONE);
		GridData gd_playButton = new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1);
		gd_playButton.heightHint = 40;
		gd_playButton.minimumWidth = 100;
		gd_playButton.minimumHeight = 40;
		playButton.setLayoutData(gd_playButton);
		playButton.setText("Play");
		playButton.setBackground(new Color(null, 74, 194, 76));
		
		deleteBtn = new Button(composite_2, SWT.NONE);
		GridData gd_btnNewButton = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_btnNewButton.heightHint = 40;
		gd_btnNewButton.widthHint = 100;
		gd_btnNewButton.minimumWidth = 100;
		gd_btnNewButton.minimumHeight = 40;
		deleteBtn.setLayoutData(gd_btnNewButton);
		deleteBtn.setText("Delete");
		deleteBtn.setBackground(new Color(null, 235, 59, 59));

		
		composite = new Composite(this, SWT.NONE);
		GridLayout gl_composite = new GridLayout(2, false);
		gl_composite.marginBottom = 5;
		gl_composite.verticalSpacing = 10;
		composite.setLayout(gl_composite);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		composite.setBackground(primaryColor);
		
		/////////////////PUZZLE GEN COMPOSITE///////////////////
		
		compositePuzzleGen = new Composite(this, SWT.NONE);
		GridLayout gl_compositePuzzleGen = new GridLayout(2, true);
		gl_compositePuzzleGen.marginBottom = 10;
		compositePuzzleGen.setLayout(gl_compositePuzzleGen);
		compositePuzzleGen.setLayoutData(new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1));
		compositePuzzleGen.setBackground(primaryColor);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		label.setBounds(0, 0, 64, 2);
		
		lblTotalSolutions = new Label(compositePuzzleGen, SWT.NONE);
		lblTotalSolutions.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblTotalSolutions.setBounds(0, 0, 77, 15);
		lblTotalSolutions.setText("Total Solutions");
		lblTotalSolutions.setBackground(primaryColor);
		
		lblemptySpaces = new Label(compositePuzzleGen, SWT.NONE);
		lblemptySpaces.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblemptySpaces.setBounds(0, 0, 73, 15);
		lblemptySpaces.setText("Empty Spaces");
		lblemptySpaces.setBackground(primaryColor);
		
		totalSolutionsSpin = new Spinner(compositePuzzleGen, SWT.BORDER);
		totalSolutionsSpin.setMaximum(1000);
		totalSolutionsSpin.setMinimum(1);
		totalSolutionsSpin.setSelection(10);
		totalSolutionsSpin.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		totalSolutionsSpin.setBounds(0, 0, 47, 22);
		totalSolutionsSpin.setBackground(highlightColor);
		
		emptySpacesSpin = new Spinner(compositePuzzleGen, SWT.BORDER);
		emptySpacesSpin.setMaximum(81);
		emptySpacesSpin.setSelection(40);
		emptySpacesSpin.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		emptySpacesSpin.setBounds(0, 0, 47, 22);
		emptySpacesSpin.setBackground(highlightColor);
		
		
		generateBtn = new Button(compositePuzzleGen, SWT.NONE);
		GridData gd_generateBtn = new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1);
		gd_generateBtn.heightHint = 40;
		generateBtn.setLayoutData(gd_generateBtn);
		generateBtn.setText("Generate Puzzle");
		
		composite_1 = new Composite(this, SWT.NONE);
		composite_1.setLayout(new GridLayout(2, true));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite_1.setBackground(primaryColor);
		
		label_1 = new Label(composite_1, SWT.SEPARATOR | SWT.HORIZONTAL | SWT.CENTER);
		label_1.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false, 2, 1));
		label_1.setSize(64, 2);
		label_1.setBackground(primaryColor);
		
		btnSolve = new Button(composite_1, SWT.NONE);
		btnSolve.setText("Solve");
		
		undoButton = new Button(composite_1, SWT.NONE);
		undoButton.setText("Undo");
		
		redoButton = new Button(composite_1, SWT.NONE);
		redoButton.setText("Redo");
		
		resetButton = new Button(composite_1, SWT.NONE);
		resetButton.setText("Reset");
		//new Label(composite_1, SWT.NONE);
		
	}
}
