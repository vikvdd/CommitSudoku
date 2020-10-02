package views;
import java.util.Map;
import java.util.TreeMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import model.game.puzzle.Coordinate;

public class GameboardView extends Composite implements IBoardView{
	
	private static final int BTN_PADDING = 2;
	public static final Font DEFAULT_FONT = SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD);
	public static final Font ENTRY_FONT = SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL);
	public static final Font INVALID_FONT = SWTResourceManager.getFont("Segoe UI", 18, SWT.NORMAL);
	public static final Color DEFAULT_COLOR = new Color(null, 0,0,0);
	public static final Color INVALID_COLOR = new Color(null, 255,0,0);
	public static final Color HIGHLIGHTED_TILE = new Color(null, 178, 178, 178);
	public static final Color NORMAL_TILE = new Color(null, 220,220,220);
	public static final Color SELECTED_TILE = new Color(null, 164, 218, 237);
	
	private int btnSize;
	private BoardTile[][] gameboardBtns;
	private Map<String, BoardTile> buttons;
	private BoardTile selectedBtn;
	
	public GameboardView(Composite parent, int style) {
		super(parent, style);
		buttons = new TreeMap<>();
		gameboardBtns = new BoardTile[9][9];
	}

	public void init(int width, int height) {
		setBackground(SWTResourceManager.getColor(110, 110, 110));
		setSize(width,height);
		buildGameboard();
	}
	
	private void buildGameboard()
	{
		buildGameboardButtons();
		buildGameboardSeperators();
		setSelectedButton(gameboardBtns[0][0]);
		BoardTile tile = new BoardTile(this, SWT.BORDER);
		tile.setText("GOONLAD");
	}
	
	private void buildGameboardButtons()
	{
		btnSize = Math.floorDiv(getBounds().width, 9) - (BTN_PADDING*2);
		for (int y = 0; y < 9; y++) 
		{
			for (int x = 0; x < 9; x++) 
			{
				if(y%3 == 0) 
				{
					Label vertSep = new Label(this, SWT.NONE);
					GridData vertData = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 9);
					vertSep.setData(vertData);
				}
				BoardTile tile = new BoardTile(this, SWT.NONE);
				tile.setBackgroundColor(NORMAL_TILE);
				tile.setSize(btnSize, btnSize);
				String key = "btn" + y + x;
				buttons.put(key, tile);
				gameboardBtns[y][x] = tile;
			}
		}
	}
	
	private void buildGameboardSeperators()
	{
		Label vertSep1 = new Label(this, SWT.SEPARATOR);
		vertSep1.setBounds(((btnSize + BTN_PADDING * 2) * 3)-6, 0, 5, this.getBounds().height);
		vertSep1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label vertSep2 = new Label(this, SWT.SEPARATOR);
		vertSep2.setBounds(((btnSize + BTN_PADDING * 2) * 6)-6, 0, 5, this.getBounds().height);
		vertSep2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label horzSep1 = new Label(this, SWT.SEPARATOR);
		horzSep1.setBounds(0, ((btnSize + BTN_PADDING * 2) * 3)-4, this.getBounds().width, 4);
		horzSep1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label horzSep2 = new Label(this, SWT.SEPARATOR);
		horzSep2.setBounds(0, ((btnSize + BTN_PADDING * 2) * 6)-4, this.getBounds().width, 4);
		horzSep2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	}

	
	public void setSelectedButton(BoardTile tile)
	{	
		selectedBtn = tile;
	}
	
	@Override
	public void setSelectedButton(Coordinate coord) {
		// TODO Auto-generated method stub
		
	}
	
	public BoardTile getSelectedButton()
	{
		return selectedBtn;
	}
	
	public void fillGameboard(int[][] grid)
	{
		for (int y = 0; y < 9; y++) 
		{
			for (int x = 0; x < 9; x++) 
			{
				String key = "btn" + y + x;
				if(grid[y][x] == 0) buttons.get(key).setText("");
				else buttons.get(key).setText(grid[y][x] + "");
			}
		}
	}
	
	public void fillGameboardEntries(int[][] grid)
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				String key = "btn" + y + x;
				if(buttons.get(key).getText() == "")
				{
					if(grid[y][x] == 0) buttons.get(key).setText("");
					else buttons.get(key).setText(grid[y][x] + "");
				}
			}
		}
	}
	
	public BoardTile getButton(int y, int x)
	{
		return gameboardBtns[y][x];
	}
	
	public BoardTile getButton(Coordinate coord)
	{
		return gameboardBtns[coord.y][coord.x];
	}
	
	public Map<String, BoardTile> getBoardButtons()
	{
		return buttons;
	}
	
	public BoardTile[][] getGameBoard()
	{
		return gameboardBtns;
	}
}
