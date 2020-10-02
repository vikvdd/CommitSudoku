package views;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import util.Util;

public class GameButtonsView extends Composite{
	
	private static final int MARGINS = 4;
	private static final int BTN_PADDING = 2;
	
	private int btnSize;
	private Button[] buttons;
	
	public GameButtonsView(Composite parent, int style) {
		super(parent, style);
		//buttons = new TreeMap<>();
		buttons = new Button[9];
	}
	
	public void init(int width, int height)
	{
		setSize(width,height);
		setBackground(new Color(null, 125, 125, 125));
		btnSize = Math.floorDiv(getBounds().width, 9) - (BTN_PADDING*2);
		BuildNumButtons();
	}
	
	private void BuildNumButtons()
	{
		for (int i = 0; i < 9; i++) {
			Button button = new Button(this, SWT.PUSH);
			button.setBounds(((2 * BTN_PADDING) + btnSize) * i + 2, BTN_PADDING, btnSize, this.getBounds().height-(BTN_PADDING*2));
			button.setText((i+1)+"");
			button.setBackground(new Color(null, 207, 207, 207));
			button.setFont(SWTResourceManager.getFont("DejaVu Sans Condensed", 13, SWT.BOLD));
			buttons[i] = button;
		}
	}
	
	public Button[] getButtons()
	{
		return buttons;
	}
	
	public Button getButton(int index)
	{
		return buttons[index];
	}
}
