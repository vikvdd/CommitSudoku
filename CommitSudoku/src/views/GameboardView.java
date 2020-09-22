package views;
import java.util.Map;
import java.util.TreeMap;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import model.game.puzzle.Coordinate;

public class GameboardView extends Composite{
	
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
	private Button[][] gameboardBtns;
	private Map<String, Button> buttons;
	private Button selectedBtn;
	
	public GameboardView(Composite parent, int style) {
		super(parent, style);
		buttons = new TreeMap<>();
		gameboardBtns = new Button[9][9];
	}

	public void init(int x, int y, int width, int height) {
		setBackground(SWTResourceManager.getColor(110, 110, 110));
		setBounds(x,y,width,height);
		buildGameboard();
	}
	
	private void buildGameboard()
	{
		buildGameboardButtons();
		buildGameboardSeperators();
	}
	
	private void buildGameboardButtons()
	{
		btnSize = Math.floorDiv(getBounds().width, 9) - (BTN_PADDING*2);
		for (int y = 0; y < 9; y++) 
		{
			for (int x = 0; x < 9; x++) 
			{
				Button button = new Button(this, SWT.PUSH);
				button.setBackground(NORMAL_TILE);
				button.setBounds(x * (BTN_PADDING*2 + btnSize), y * (BTN_PADDING*2 + btnSize), btnSize, btnSize);
				button.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
				button.setText(0 + "");
				String key = "btn" + y + x;
				buttons.put(key, button);
				gameboardBtns[y][x] = button;
			}
		}
		setSelectedButton(gameboardBtns[0][0]);
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

	
	public void setSelectedButton(Button button)
	{	
		selectedBtn = button;
	}
	
	public Button getSelectedButton()
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
	
	public Button getButton(int y, int x)
	{
		return gameboardBtns[y][x];
	}
	
	public Button getButton(Coordinate coord)
	{
		return gameboardBtns[coord.y][coord.x];
	}
	
	public Map<String, Button> getBoardButtons()
	{
		return buttons;
	}
	
	public Button[][] getGameBoard()
	{
		return gameboardBtns;
	}
}
