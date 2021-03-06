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

public class GameboardView extends Composite{
	
	private static final int BTN_PADDING = 2;
	public static final Font DEFAULT_FONT = SWTResourceManager.getFont("Segoe UI", 14, SWT.BOLD);
	public static final Font ENTRY_FONT = SWTResourceManager.getFont("Segoe UI", 11, SWT.NORMAL);
	public static final Font INVALID_FONT = SWTResourceManager.getFont("Segoe UI", 11, SWT.BOLD | SWT.ITALIC);
	public static final Color DEFAULT_COLOR = new Color(null, 0,0,0);
	public static final Color INVALID_COLOR = new Color(null, 255,0,0);
	public static final Color DEFAULT_TILE = new Color(null, 230,230,230);
	public static final Color SELECTED_TILE = new Color(null, 250,250,250);
	
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
		setBackground(SWTResourceManager.getColor(192, 192, 192));
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
				button.setBackground(DEFAULT_TILE);
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
		vertSep1.setBounds(((btnSize + BTN_PADDING * 2) * 3)-5, 0, 4, this.getBounds().height);
		vertSep1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label vertSep2 = new Label(this, SWT.SEPARATOR);
		vertSep2.setBounds(((btnSize + BTN_PADDING * 2) * 6)-5, 0, 4, this.getBounds().height);
		vertSep2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label horzSep1 = new Label(this, SWT.SEPARATOR);
		horzSep1.setBounds(0, ((btnSize + BTN_PADDING * 2) * 3)-4, this.getBounds().width, 2);
		horzSep1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		Label horzSep2 = new Label(this, SWT.SEPARATOR);
		horzSep2.setBounds(0, ((btnSize + BTN_PADDING * 2) * 6)-4, this.getBounds().width, 2);
		horzSep2.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
	}
	
	public int test()
	{
		return buttons.size();
	}
	
	public void setSelectedButton(Button button)
	{
		if(selectedBtn != null) selectedBtn.setBackground(DEFAULT_TILE);		
		button.setBackground(SELECTED_TILE);
		
		selectedBtn = button;
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
	
	public Button getButton(String btnKey)
	{
		return buttons.get(btnKey);
	}
	
	public Button getButton(int y, int x)
	{
		return gameboardBtns[y][x];
	}
	
	public void setButtonText(String text, String btnKey)
	{
		buttons.get(btnKey).setText(text);
	}
	
	public void setButtonFont(Font font, String btnKey)
	{
		buttons.get(btnKey).setFont(font);
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
