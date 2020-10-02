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
import views.BoardView;

public class boardTileTest {
	public static void main(String[] args) {
		
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell = new Shell();
		GridLayout gridLayout = new GridLayout(9, false);
		gridLayout.makeColumnsEqualWidth = true;
		GridData data = new GridData();
		data.horizontalAlignment = SWT.FILL;
		data.verticalAlignment = SWT.FILL;
		data.grabExcessHorizontalSpace = true;
		
		shell.setLayout(gridLayout);
		shell.setText("CommitSudoku");
		shell.setBackground(new Color(null,209, 191, 174));
		shell.setData(data);
		shell.setSize(1024,1024);
		BoardView boardView = new BoardView(shell, SWT.BORDER);
		
		
		
		
		
		
		
		
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
