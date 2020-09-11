package views;
import javax.annotation.Generated;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.wb.swt.SWTResourceManager;

import util.Util;

public class GamePanelView extends Composite{
	
	private static final int MARGINS = 4;
	public static final int PADDING = 10;
	
	private List gameList;
	private Button playButton;
	private Label maxSolLabel;
	private Label emptySpaceLabel;
	private Spinner maxSolutionsSpin;
	private Spinner emptySpaces;
	private Button generateBtn;
	private Button solveButton;
	
	
	public GamePanelView(Composite parent, int style) 
	{
		super(parent, style);
	}
	
	public void init(int x, int y, int width, int height)
	{
		setBounds(x, y, width, height);
		buildGameList();
		buildButtons();
	}
	
	private void buildGameList()
	{
		gameList = new List(this, SWT.BORDER |SWT.V_SCROLL);
		Util.print(this.getBounds().width + "");
		gameList.setBounds(PADDING,PADDING,getBounds().width-(PADDING*3),getBounds().height/5);
	}
	
	private void buildButtons()
	{
		playButton = new Button(this, SWT.PUSH);
		playButton.setText("Play Puzzle");
		playButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		playButton.setBounds(getBounds().width/2-60, getBounds().height/5 + (PADDING*2), 120, 50);
		playButton.setBackground(new Color(null, 74, 194, 76));
		
		Label horzSep1 = new Label(this, SWT.SEPARATOR);
		horzSep1.setBounds(PADDING, playButton.getBounds().y + playButton.getBounds().height + PADDING, getBounds().width-PADDING*2, 1);
		horzSep1.setBackground(new Color(null, 200,200,200));
		
		
		maxSolLabel = new Label(this, SWT.NORMAL);
		maxSolLabel.setText("Max Solutions");
		maxSolLabel.setBounds(getBounds().width/4-40 - maxSolLabel.getBounds().width, playButton.getBounds().y + playButton.getBounds().height + 30, 100,15);
		maxSolLabel.setAlignment(SWT.CENTER);
		
		emptySpaceLabel = new Label(this, SWT.NORMAL);
		emptySpaceLabel.setText("Empty Spaces");
		emptySpaceLabel.setBounds(getBounds().width - (getBounds().width/4) - 40-(emptySpaceLabel.getBounds().width/2), playButton.getBounds().y + playButton.getBounds().height + 30, 100,15);
		emptySpaceLabel.setAlignment(SWT.CENTER);
		
		maxSolutionsSpin = new Spinner(this, SWT.BORDER);
		maxSolutionsSpin.setBounds(getBounds().width/4-25 - maxSolutionsSpin.getBounds().width, playButton.getBounds().y + playButton.getBounds().height + 50, 50, 25);
		maxSolutionsSpin.setMaximum(100);
		maxSolutionsSpin.setMinimum(1);
		maxSolutionsSpin.setSelection(10);
		
		emptySpaces = new Spinner(this, SWT.BORDER);
		emptySpaces.setBounds(getBounds().width - (getBounds().width/4) - 25-(emptySpaces.getBounds().width/2), playButton.getBounds().y + playButton.getBounds().height + 50, 50, 25);
		emptySpaces.setMaximum(81);
		emptySpaces.setMinimum(10);
		emptySpaces.setSelection(50);
		
		generateBtn = new Button(this, SWT.PUSH);
		generateBtn.setBounds(getBounds().width/2-60, emptySpaces.getBounds().y + 40, 120, 50);
		generateBtn.setText("Generate Puzzle");
		generateBtn.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		
		Label horzSep2 = new Label(this, SWT.SEPARATOR);
		horzSep2.setBounds(PADDING, generateBtn.getBounds().y + playButton.getBounds().height + PADDING, getBounds().width-PADDING*2, 1);
		horzSep2.setBackground(new Color(null, 200,200,200));
		
		solveButton = new Button(this, SWT.PUSH);
		solveButton.setBounds(getBounds().width/2-60, horzSep2.getBounds().y + PADDING*2, 120, 50);
		solveButton.setBackground(new Color(null, 235,42,42));
		solveButton.setText("Solve");
		solveButton.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));

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
	
	public Button getGenerateButton()
	{
		return generateBtn;
	}
	
	public List getGameList()
	{
		return gameList;
	}
	
	public int getMaxSolutions()
	{
		return maxSolutionsSpin.getSelection();
	}
	
	public int getEmptySpaces()
	{
		return emptySpaces.getSelection();
	}
	
	public Button getSolveButton()
	{
		return solveButton;
	}
	
	private void addToList(String gameName)
	{
		gameList.add(gameName);
	}
	
}
