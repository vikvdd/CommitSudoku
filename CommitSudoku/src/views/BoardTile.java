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
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.wb.swt.SWTResourceManager;

import util.Util;

public class BoardTile extends Composite{
	
	public static Color MAIN_COLOR = new Color(null, 255,255,255);
	public static Color HOVER_COLOR = new Color(null, 220,220,220);
	
	List<Composite> composites = new ArrayList<Composite>();
	Composite mainComp;
	Composite topHintComp;
	Composite numberComp;
	Composite bottomHintComp;
	Composite compClick;
	
	
	GridData mainData;
	
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

	public BoardTile(Composite parent, int style) {
		super(parent, style);
		
		mainData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		mainComp = new Composite(this, SWT.NONE);
		mainComp.setLayoutData(mainData);
		GridLayout gl_mainComp = new GridLayout(1, true);
		gl_mainComp.marginRight = 5;
		gl_mainComp.marginLeft = 5;
		gl_mainComp.marginTop = 5;
		gl_mainComp.marginBottom = 5;
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
		note1.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note1.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, true, false, 1, 1));
		note1.setText("1");
		
		note2 = new Label(topHintComp, SWT.NONE);
		note2.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note2.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note2.setText("2");
		
		note3 = new Label(topHintComp, SWT.NONE);
		note3.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note3.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note3.setText("3");
		
		note4 = new Label(topHintComp, SWT.NONE);
		note4.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note4.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note4.setText("4");
		
		note5 = new Label(topHintComp, SWT.NONE);
		note5.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note5.setLayoutData(new GridData(SWT.CENTER, SWT.TOP, false, false, 1, 1));
		note5.setText("5");
		
		numberComp = new Composite(mainComp, SWT.NONE);
		numberComp.setLayout(new GridLayout(1, false));
		numberComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		numLabel = new Label(numberComp, SWT.NONE);
		numLabel.setFont(SWTResourceManager.getFont("Segoe UI Black", 24, SWT.NORMAL));
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
		note6.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note6.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, true, false, 1, 1));
		note6.setText("6");
		
		note7 = new Label(bottomHintComp, SWT.NONE);
		note7.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note7.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note7.setText("7");
		
		note8 = new Label(bottomHintComp, SWT.NONE);
		note8.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note8.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note8.setText("8");
		
		note9 = new Label(bottomHintComp, SWT.NONE);
		note9.setFont(SWTResourceManager.getFont("Segoe UI Historic", 7, SWT.NORMAL));
		note9.setLayoutData(new GridData(SWT.CENTER, SWT.BOTTOM, false, false, 1, 1));
		note9.setText("9");
		noteLabels.addAll(Arrays.asList(note1,note2,note3,note4,note5,note6,note7,note8,note9));
	
		composites.addAll(Arrays.asList(mainComp, numberComp, topHintComp, bottomHintComp));
		
		setSize(100,100);
		setBackground(new Color(null, 0,0,0));
		initListeners();
	}
	
	public void setText(String text)
	{
		try {
			numLabel.setText(text);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void setNoteText(int num)
	{
		try {
			noteLabels.get(num-1).setText(num + "");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void setSize(int width, int height)
	{
		mainData.heightHint = height;
		mainData.widthHint = width;
		mainComp.setBounds(0,0,width,height);
	}
	
	public void setBackGround(Color color)
	{
		setBackground(color);
		mainComp.setBackground(color);
		topHintComp.setBackground(color);
		numberComp.setBackground(color);
		bottomHintComp.setBackground(color);
		numLabel.setBackground(color);
		for(Label lbl : noteLabels) lbl.setBackground(color);
	}
	
	public void initListeners()
	{
		for(Composite comp : composites)
		{
			comp.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					for (Control control : getChildren()) {
		                if (control == event.item)
		                    return;
		            }
					setBackGround(MAIN_COLOR);
				}
			});
			comp.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					
					setBackGround(HOVER_COLOR);
				}
			});
		}
		for(Control control : noteLabels)
		{
			control.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					for (Control control : getChildren()) {
		                if (control == event.item)
		                    return;
		            }
					setBackGround(MAIN_COLOR);
				}
			});
			control.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					
					setBackGround(HOVER_COLOR);
				}
			});
			numLabel.addListener(SWT.MouseExit, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					for (Control control : getChildren()) {
		                if (control == event.item)
		                    return;
		            }
					setBackGround(MAIN_COLOR);
				}
			});
			numLabel.addListener(SWT.MouseEnter, new Listener() {
				
				@Override
				public void handleEvent(Event event) {
					
					setBackGround(HOVER_COLOR);
				}
			});		
		}		
	}
}
