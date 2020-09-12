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

public class PanelView extends Composite{

	private Composite gamePanel;
	private List gameList;
	private Button playButton;
	
	private Composite puzzleGenPanel;
	private Label totSolutionsLbl;
	private Spinner totSolutionsSpin;
	private Label emptySpacesLbl;
	private Spinner emptySpacesSpin;
	
	
	public PanelView(Composite parent, int style) {
		super(parent, style);
		//initGamePanel();
	}
	
	private void initGamePanel()
	{
		setBackground(new Color(null, 0,0,0));
		setBounds(400,500,500,500);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gamePanel = new Composite(this, SWT.NONE);
		//gameList.getBounds()
		gamePanel.setLayoutData(gridData);
		GridLayout gridLayout = new GridLayout();
		gamePanel.setLayout(gridLayout);
		gridLayout.numColumns = 2;
		gameList = new List(gamePanel, SWT.BORDER |SWT.V_SCROLL);
		gameList.setLayoutData(gridData);
		playButton = new Button(gamePanel, SWT.PUSH);
		
	}

}
