package views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;
import events.BoardTileListener;
import util.TriggerDelay;
import util.Util;

public class BoardTile extends Composite{
	
	public static Color MAIN_TILE_COLOR = new Color(null, 255,255,255);
	public static Color HOVER_TILE_COLOR = new Color(null, 200,200,200);
	public static final Color DEFAULT_COLOR = new Color(null, 0,0,0);
	public static final Color INVALID_COLOR = new Color(null, 255,0,0);
	public static Font DEFAULT_FONT = SWTResourceManager.getFont("COURIER NEW GREEK", 23, SWT.BOLD);
	public static Font ENTRY_FONT = SWTResourceManager.getFont("COURIER NEW GREEK", 23, SWT.NORMAL);
	public static Font INVALID_FONT = SWTResourceManager.getFont("COURIER NEW GREEK", 23, SWT.NORMAL);
	public static Font NOTE_FONT = SWTResourceManager.getFont("COURIER NEW GREEK", 8, SWT.NORMAL);
	
	List<BoardTileListener> listeners = new ArrayList<BoardTileListener>();
	
	List<Composite> composites = new ArrayList<Composite>();
	Composite mainComp;
	Composite topHintComp;
	Composite numberComp;
	Composite bottomHintComp;
	Composite compClick;
	
	GridData gd_mainComp;
	
	Label numLabel;
	Label note1;
	Label note2;
	Label note3;
	Label note4;
	Label note5;
	Label note6;
	Label note7;
	Label note8;
	Label note9;
	List<Label> noteLabels = new ArrayList<Label>();
	
	private int width = 75;
	private int height = 75;
	private Color currentColor = MAIN_TILE_COLOR;
	private TriggerDelay clickDelay = new TriggerDelay(1);

	public void addBoardTileListener(BoardTileListener listener)
	{
		listeners.add(listener);
	}
	
	public void removeBoardTileListener(BoardTileListener listener)
	{
		listeners.remove(listener);
	}
	
	private void initFontSize()
	{
		int compSize = mainComp.getBounds().height;
		
		FontData[] noteFontData =  NOTE_FONT.getFontData();
		noteFontData[0].setHeight(Math.floorDiv(compSize, 9));
		NOTE_FONT = new Font(getDisplay(), noteFontData[0]);
		
		int numSize = Math.floorDiv(compSize,10)*3;
		FontData[] numFontData = DEFAULT_FONT.getFontData();
		numFontData[0].setHeight(numSize);
		DEFAULT_FONT = new Font(getDisplay(), numFontData[0]);
		
		FontData[] numFontData2 = ENTRY_FONT.getFontData();
		numFontData2[0].setHeight(numSize);
		ENTRY_FONT = new Font(getDisplay(), numFontData2[0]);
		
		FontData[] numFontData3 = INVALID_FONT.getFontData();
		numFontData3[0].setHeight(numSize);
		INVALID_FONT = new Font(getDisplay(), numFontData3[0]);
	}
	
	public void setText(String text)
	{
		try {
			numLabel.setText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void setNoteText(int num, boolean enabled)
	{
		try {
			noteLabels.get(num-1).setVisible(enabled);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setSize(int width, int height)
	{
		mainComp.setSize(width,height);
		gd_mainComp.widthHint = width;
		gd_mainComp.heightHint = height;
	}
	
	
	public void setNumTextColor(Color color)
	{
		numLabel.setForeground(color);
	}
	
	public void setBackgroundColor(Color color)
	{
		currentColor = color;
		super.setBackground(color);
		mainComp.setBackground(color);
		topHintComp.setBackground(color);
		numberComp.setBackground(color);
		bottomHintComp.setBackground(color);
		numLabel.setBackground(color);
		for(Label lbl : noteLabels) lbl.setBackground(color);
	}
	
	public void setHoverColor(Color color)
	{
		super.setBackground(color);
		mainComp.setBackground(color);
		topHintComp.setBackground(color);
		numberComp.setBackground(color);
		bottomHintComp.setBackground(color);
		numLabel.setBackground(color);
		for(Label lbl : noteLabels) lbl.setBackground(color);
	}
	
	public Color getBackgroundColor()
	{
		return currentColor;
	}
	
	public void setNumFont(Font font)
	{
		numLabel.setFont(font);
	}
	
	public String getText()
	{
		return numLabel.getText();
	}
	
	public String getNoteLabelText(int num)
	{
		if(num < 1 || num > 9) return "";
		return noteLabels.get(num-1).getText();
	}
	
	public void toggleNoteText(boolean enabled)
	{
		for(Label label : noteLabels)
		{
			try {
				setNoteText(Integer.parseInt(label.getText()), enabled);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	
	public void initListeners()
	{
		for(Composite comp : composites)
		{
			comp.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseExit(event);
				}
			});
			comp.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseEnter();
				}
			});
			comp.addListener(SWT.MouseDown, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseDown(event);
					
				}
			});
		}
		for(Control control : noteLabels)
		{
			control.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseExit(event);
				}
			});
			control.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					
					onMouseEnter();
				}
			});
			control.addListener(SWT.MouseDown, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseDown(event);
					
				}
			});
			numLabel.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseExit(event);
				}
			});
			numLabel.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					
					onMouseEnter();
				}
			});	
			numLabel.addListener(SWT.MouseDown, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					onMouseDown(event);
					
				}
			});
		}		
	}
	
	public boolean isNoteEnabled(int num)
	{
		if(noteLabels.get(num-1).isVisible()) return true;
		else return false;
	}
	
	private void onMouseEnter()
	{
		setHoverColor(HOVER_TILE_COLOR);
		notifyMouseEnter();
	}
	
	private void onMouseExit(Event event)
	{
		for (Control control : getChildren()) {
            if (control == event.item)
                return;
        }
		setBackgroundColor(currentColor);
		notifyMouseExit();
	}
	
	private void onMouseDown(Event event)
	{
		if(event.button == 1)
		{
			onLeftClick();
		}
		if(event.button == 3)
		{
			onRightClick();
		}
	}
	
	private void onLeftClick()
	{
		notifyLeftClick();
	}
	
	private void onRightClick()
	{
		notifyRightClick();
	}
	
	public BoardTile(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		mainComp = new Composite(this, SWT.NONE);
		gd_mainComp = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1);
		gd_mainComp.heightHint = 100;
		gd_mainComp.widthHint = 100;
		mainComp.setLayoutData(gd_mainComp);
		GridLayout gl_mainComp = new GridLayout(1, true);
		gl_mainComp.marginRight = 5;
		gl_mainComp.marginLeft = 5;
		gl_mainComp.marginTop = 0;
		gl_mainComp.marginBottom = 0;
		gl_mainComp.marginWidth = 0;
		gl_mainComp.marginHeight = 0;
		gl_mainComp.horizontalSpacing = 0;
		gl_mainComp.verticalSpacing = 0;
		mainComp.setLayout(gl_mainComp);

		
		topHintComp = new Composite(mainComp, SWT.NONE);
		topHintComp.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		GridLayout gl_composite = new GridLayout(5, true);
		gl_composite.marginWidth = 0;
		gl_composite.verticalSpacing = 0;
		gl_composite.marginHeight = 0;
		gl_composite.horizontalSpacing = 2;
		topHintComp.setLayout(gl_composite);
		
		note1 = new Label(topHintComp, SWT.NONE);
		note1.setFont(NOTE_FONT);
		note1.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, false, 1, 1));
		note1.setText("1");
		
		note2 = new Label(topHintComp, SWT.NONE);
		note2.setFont(NOTE_FONT);
		note2.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note2.setText("2");
		
		note3 = new Label(topHintComp, SWT.NONE);
		note3.setFont(NOTE_FONT);
		note3.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note3.setText("3");
		
		note4 = new Label(topHintComp, SWT.NONE);
		note4.setFont(NOTE_FONT);
		note4.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note4.setText("4");
		
		note5 = new Label(topHintComp, SWT.NONE);
		note5.setFont(NOTE_FONT);
		note5.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note5.setText("5");
		
		numberComp = new Composite(mainComp, SWT.NONE);
		GridLayout gl_numberComp = new GridLayout(1, true);
		gl_numberComp.horizontalSpacing = 0;
		gl_numberComp.verticalSpacing = 0;
		gl_numberComp.marginWidth = 0;
		gl_numberComp.marginHeight = 0;
		numberComp.setLayout(gl_numberComp);
		numberComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		numLabel = new Label(numberComp, SWT.NONE);
		numLabel.setFont(DEFAULT_FONT);
		numLabel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		numLabel.setText("8");
		GridData numData = new GridData(SWT.CENTER, SWT.FILL, true, true,1,1);
		numLabel.setData(numData);
		
		
		bottomHintComp = new Composite(mainComp, SWT.NONE);
		bottomHintComp.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, false, false, 1, 1));
		GridLayout gl_composite_1 = new GridLayout(4, true);
		gl_composite_1.marginWidth = 0;
		gl_composite_1.verticalSpacing = 0;
		gl_composite_1.marginHeight = 0;
		bottomHintComp.setLayout(gl_composite_1);
		
		note6 = new Label(bottomHintComp, SWT.NONE);
		note6.setFont(NOTE_FONT);
		note6.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, false, 1, 1));
		note6.setText("6");
		
		note7 = new Label(bottomHintComp, SWT.NONE);
		note7.setFont(NOTE_FONT);
		note7.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note7.setText("7");
		
		note8 = new Label(bottomHintComp, SWT.NONE);
		note8.setFont(NOTE_FONT);
		note8.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note8.setText("8");
		
		note9 = new Label(bottomHintComp, SWT.NONE);
		note9.setFont(NOTE_FONT);
		note9.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note9.setText("9");
		noteLabels.addAll(Arrays.asList(note1,note2,note3,note4,note5,note6,note7,note8,note9));
	
		composites.addAll(Arrays.asList(mainComp, numberComp, topHintComp, bottomHintComp));
		
		setSize(width,height);
		setBackgroundColor(MAIN_TILE_COLOR);
		initListeners();
		initFontSize();
		toggleNoteText(false);
	}
	
	private void notifyMouseEnter() {
		for(BoardTileListener l : listeners)
		{
			l.onTileMouseEnter();
		}
	}
	
	private void notifyMouseExit() {
		for(BoardTileListener l : listeners)
		{
			l.onTileMouseExit();
		}
	}
	
	private void notifyLeftClick() {
		for(BoardTileListener l : listeners)
		{
			l.onLeftClick();
		}
	}
	
	private void notifyRightClick() {
		for(BoardTileListener l : listeners)
		{
			l.onRightClick();
		}
	}
}
