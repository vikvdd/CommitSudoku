package tests;


import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import util.Util;
import views.BoardTile;

public class boardTileTest {
	public static void main(String[] args) {
		GridData data = new GridData();
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
		data.minimumHeight = 100;
		data.minimumWidth = 100;
		data.heightHint = 150;
		data.widthHint = 100;
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell = new Shell();
		shell.setText("CommitSudoku");
		shell.setBackground(new Color(null,209, 191, 174));
		shell.setData(data);
		GridLayout gridLayout = new GridLayout(9, false);
		gridLayout.makeColumnsEqualWidth = true;
		shell.setLayout(gridLayout);
		Composite comp = new Composite(shell, SWT.NORMAL);
		
		
		comp.setLayout(gridLayout);
		comp.setData(data);
		comp.setBounds(0,0,400,400);
		BoardTile tile = new BoardTile(comp, SWT.PUSH);
		BoardTile tile2 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile3 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile4 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile5 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile6 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile7 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile8 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile9 = new BoardTile(comp, SWT.PUSH);
		BoardTile tile10 = new BoardTile(comp, SWT.PUSH);
		
	
		
		
		tile.setData(data);
		
		
		
		
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}
}
