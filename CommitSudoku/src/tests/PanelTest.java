package tests;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import views.PanelView;

public class PanelTest {
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell = new Shell();
		shell.setSize(350, 1024);
		shell.setText("CommitSudoku");
		shell.setBackground(new Color(null,209, 191, 174));
		GridLayout gridTing = new GridLayout(2, false);
		GridData data = new GridData();
		data.grabExcessHorizontalSpace = true;
		data.horizontalAlignment = SWT.CENTER;
		shell.setLayout(gridTing);
		Button btn = new Button(shell, SWT.PUSH);
		btn.setBounds(10,10, 50,50);
		
		
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
