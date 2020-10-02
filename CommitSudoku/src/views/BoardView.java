package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.internal.dialogs.NewWizard;
import org.eclipse.ui.internal.handlers.WizardHandler.New;
import org.eclipse.wb.swt.SWTResourceManager;

import model.game.puzzle.Coordinate;
import util.Util;

public class BoardView extends Composite implements IBoardView{	
	private int btnSize;
	private int btnPadding = 5;
	private BoardTile[][] boardTiles = new BoardTile[9][9];
	private BoardTile selectedButton = boardTiles[0][0];
	
	private Composite subGrid1;
	private Composite subGrid2;
	private Composite subGrid3;
	private Composite subGrid4;
	private Composite subGrid5;
	private Composite subGrid6;
	private Composite subGrid7;
	private Composite subGrid8;
	private Composite subGrid9;
	private Composite[][] subGrids;

	public BoardView(Composite parent, int style) 
	{
		super(parent, style);
		GridLayout layout = new GridLayout(3, false);
		layout.makeColumnsEqualWidth = false;
		layout.verticalSpacing = 0;
		layout.horizontalSpacing = 0;
		layout.marginRight = 0;
		layout.marginLeft = 0;
		setLayout(layout);
		GridData mainData = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		setData(mainData);
	}

	private void buildGameBoard()
	{	
		btnSize = Math.floorDiv(getBounds().width, 9) - (btnPadding*2);
		for (int y = 0; y < 9; y++) {
			
			for (int x = 0; x < 9; x++) {
				/*if(y%3 == 0 && y != 0 && x == 0)
				{
					for (int i = 0; i < 11; i++) {
						Label horzSep = new Label(this, SWT.HORIZONTAL | SWT.SEPARATOR);
						GridData horzData = new GridData(SWT.LEFT, SWT.TOP, true, false, 11, 1);						
						horzSep.setData(horzData);
						horzSep.setSize(getBounds().width, 4);
						//horzSep.setBackground(new Color(null, 0,0,0));
					}
					
				}*/
				/*if(x%3 == 0 && x != 0) 
				{
					Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
					//label.setData(new GridData(SWT.CENTER, SWT.FILL, false, true, 1, 11));	
					GridData layoutData = new GridData();
					layoutData.heightHint = btnSize;
					label.setLayoutData(layoutData);*/
					
					//fillComp.setData(vertData);
					
					
					/*Label vertSep = new Label(this, SWT.VERTICAL | SWT.SEPARATOR);
					seperators.add(vertSep);
					vertSep.setData(vertData);
					vertSep.setForeground(new Color(null, 0,0,0));
					vertSep.setBackground(new Color(null, 0,0,0));*/
					/*if(y == 0)
					{
						Label vertSep = new Label(this, SWT.SEPARATOR);
						seperators.add(vertSep);
						vertSep.setData(vertData);
						vertSep.setBackground(new Color(null, 0,0,0));
					}
					else {
						Label fill = new Label(this, SWT.NONE);
					}*/
					
					
				
				BoardTile tile = new BoardTile(this, SWT.BORDER);
				tile.setSize(btnSize,btnSize);
				tile.setText("5");
				tile.setBackgroundColor(BoardTile.MAIN_TILE_COLOR);
				boardTiles[y][x] = tile;
			}
			
		}
		
	}
	
	public void buildBoard()
	{	
		btnSize = Math.floorDiv(getBounds().width, 9);
		for (int i = 0; i < 3; i++) 
		{
			for (int j = 0; j < 3; j++) {
				Composite comp = subGrids[i][j];
				for (int y = 0; y < 3; y++) {
					for (int x = 0; x < 3; x++) {
						BoardTile tile = new BoardTile(comp, SWT.BORDER);
						tile.setSize(btnSize,btnSize);
						tile.setText("5");
						tile.setBackgroundColor(BoardTile.MAIN_TILE_COLOR);
						GridData tileData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
						tile.setData(tileData);
						boardTiles[3*i+y][3*j+x] = tile;
					}
					
				}
				
			}				
		}
	}

	@Override
	public void init(int width, int height) 
	{
		setSize(width,height);
		initSubGrids();
		//buildGameBoard();
		buildBoard();
	}
	
	private void initSubGrids()
	{
		GridLayout subGridLayout = new GridLayout(3, false);
		subGridLayout.verticalSpacing = 1;
		subGridLayout.horizontalSpacing = 1;
		subGridLayout.marginHeight = 3;
		subGridLayout.marginWidth = 3;
		GridData subGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		
		subGrids = new Composite[3][3];
		subGrid1 = new Composite(this, SWT.BORDER);
		subGrid1.setLayout(subGridLayout);
		subGrid1.setData(subGridData);
		subGrid1.setBackground(new Color(null, 191, 191, 191));
		
		subGrid2 = new Composite(this, SWT.BORDER);
		subGrid2.setLayout(subGridLayout);
		subGrid2.setData(subGridData);
		subGrid2.setBackground(new Color(null, 191, 191, 191));
		
		subGrid3 = new Composite(this, SWT.BORDER);
		subGrid3.setLayout(subGridLayout);
		subGrid3.setData(subGridData);
		subGrid3.setBackground(new Color(null, 191, 191, 191));
		
		subGrid4 = new Composite(this, SWT.BORDER);
		subGrid4.setLayout(subGridLayout);
		subGrid4.setData(subGridData);
		subGrid4.setBackground(new Color(null, 191, 191, 191));
		
		subGrid5 = new Composite(this, SWT.BORDER);
		subGrid5.setLayout(subGridLayout);
		subGrid5.setData(subGridData);
		subGrid5.setBackground(new Color(null, 191, 191, 191));
		
		subGrid6 = new Composite(this, SWT.BORDER);
		subGrid6.setLayout(subGridLayout);
		subGrid6.setData(subGridData);
		subGrid6.setBackground(new Color(null, 191, 191, 191));
		
		subGrid7 = new Composite(this, SWT.BORDER);
		subGrid7.setLayout(subGridLayout);
		subGrid7.setData(subGridData);
		subGrid7.setBackground(new Color(null, 191, 191, 191));
		
		subGrid8 = new Composite(this, SWT.BORDER);
		subGrid8.setLayout(subGridLayout);
		subGrid8.setData(subGridData);
		subGrid8.setBackground(new Color(null, 191, 191, 191));
		
		subGrid9 = new Composite(this, SWT.BORDER);
		subGrid9.setLayout(subGridLayout);
		subGrid9.setData(subGridData);
		subGrid9.setBackground(new Color(null, 191, 191, 191));
		
		
		subGrids[0][0] = subGrid1;
		subGrids[0][1] = subGrid2;
		subGrids[0][2] = subGrid3;
		subGrids[1][0] = subGrid4;
		subGrids[1][1] = subGrid5;
		subGrids[1][2] = subGrid6;
		subGrids[2][0] = subGrid7;
		subGrids[2][1] = subGrid8;
		subGrids[2][2] = subGrid9;
	}
	
	public void resetTileNotes() 
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				boardTiles[y][x].toggleNoteText(false);				
			}
		}
	}
	
	

	@Override
	public BoardTile getButton(Coordinate coord) 
	{
		return boardTiles[coord.y][coord.x];
	}
	
	@Override
	public BoardTile getButton(int y, int x)
	{
		return boardTiles[y][x];
	}

	@Override
	public void setSelectedButton(Coordinate coord) 
	{
		selectedButton = boardTiles[coord.y][coord.x];
	}

	@Override
	public BoardTile getSelectedButton() 
	{
		return selectedButton;		
	}

	@Override
	public BoardTile[][] getGameBoard() 
	{
		return boardTiles;
	}

}
