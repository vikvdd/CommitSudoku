package views;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import model.game.puzzle.Coordinate;
import util.Util;

public class BoardView extends BoardViewBase{	
	public static final Color HIGHLIGHTED_TILE = new Color(null, 178, 178, 178);
	public static final Color NORMAL_TILE = new Color(null, 220,220,220);
	public static final Color SELECTED_TILE = new Color(null, 164, 218, 237);
	public static final Color SUBGRID_COLOR = new Color(null, 28, 34, 41);
	
	private int btnSize;
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
		
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);
		GridData mainData = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		setData(mainData);
	}
	
	@Override
	public void init() {
		setBackground(new Color(null, 57,73,89));
		updateComponentSizes();
	}

	@Override
	public void updateComponentSizes() {
		Control[] controls = getChildren();
		int size = controls.length;
		
		for (int i = 0; i < size; i++) {
			controls[i].dispose();
		}
		Util.println(getChildren().length);
		
		updateBoard();
	}
	
	public void updateBoard()
	{
		initSubGrids();
		buildBoard();
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
						BoardTile tile = new BoardTile(comp, SWT.NONE);
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
	
	private void initSubGrids()
	{
		GridLayout subGridLayout = new GridLayout(3, false);
		subGridLayout.verticalSpacing = 1;
		subGridLayout.horizontalSpacing = 1;
		subGridLayout.marginHeight = 2;
		subGridLayout.marginWidth = 2;
		GridData subGridData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		
		subGrids = new Composite[9][9];
		
		subGrids = new Composite[3][3];
		subGrid1 = new Composite(this, SWT.NONE);
		subGrid1.setLayout(subGridLayout);
		subGrid1.setData(subGridData);
		subGrid1.setBackground(SUBGRID_COLOR);
		
		subGrid2 = new Composite(this, SWT.NONE);
		subGrid2.setLayout(subGridLayout);
		subGrid2.setData(subGridData);
		subGrid2.setBackground(SUBGRID_COLOR);
		
		subGrid3 = new Composite(this, SWT.NONE);
		subGrid3.setLayout(subGridLayout);
		subGrid3.setData(subGridData);
		subGrid3.setBackground(SUBGRID_COLOR);
		
		subGrid4 = new Composite(this, SWT.NONE);
		subGrid4.setLayout(subGridLayout);
		subGrid4.setData(subGridData);
		subGrid4.setBackground(SUBGRID_COLOR);
		
		subGrid5 = new Composite(this, SWT.NONE);
		subGrid5.setLayout(subGridLayout);
		subGrid5.setData(subGridData);
		subGrid5.setBackground(SUBGRID_COLOR);
		
		subGrid6 = new Composite(this, SWT.NONE);
		subGrid6.setLayout(subGridLayout);
		subGrid6.setData(subGridData);
		subGrid6.setBackground(SUBGRID_COLOR);
		
		subGrid7 = new Composite(this, SWT.NONE);
		subGrid7.setLayout(subGridLayout);
		subGrid7.setData(subGridData);
		subGrid7.setBackground(SUBGRID_COLOR);
		
		subGrid8 = new Composite(this, SWT.NONE);
		subGrid8.setLayout(subGridLayout);
		subGrid8.setData(subGridData);
		subGrid8.setBackground(SUBGRID_COLOR);
		
		subGrid9 = new Composite(this, SWT.NONE);
		subGrid9.setLayout(subGridLayout);
		subGrid9.setData(subGridData);
		subGrid9.setBackground(SUBGRID_COLOR);
		
		
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
