package views;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;

import javax.rmi.CORBA.Util;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Spinner;

public class TestWindow {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TestWindow window = new TestWindow();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		
		shell.setSize(1024, 1024);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite = new Composite(shell, SWT.BORDER);
		composite.setBackground(SWTResourceManager.getColor(192, 192, 192));
		
		Button btnNewButton = new Button(composite, SWT.BORDER);
		btnNewButton.setText("1");
		btnNewButton.setFont(SWTResourceManager.getFont("Myanmar Text", 9, SWT.NORMAL));
		btnNewButton.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton.setBounds(0, 0, 80, 80);
		System.out.println(btnNewButton.getBackground().getRed() +".." + btnNewButton.getBackground().getGreen() +".."+ btnNewButton.getBackground().getBlue());
		
		Button btnNewButton_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1.setBounds(80, 0, 80, 80);
		
		Button btnNewButton_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1.setBounds(246, 0, 80, 80);
		
		Button btnNewButton_2 = new Button(composite, SWT.BORDER);
		btnNewButton_2.setBounds(160, 0, 80, 80);
		
		Button btnNewButton_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2.setBounds(406, 0, 80, 80);
		
		Button btnNewButton_3 = new Button(composite, SWT.BORDER);
		btnNewButton_3.setBounds(326, 0, 80, 80);
		
		Button btnNewButton_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1.setBounds(652, 0, 80, 80);
		
		Button btnNewButton_3_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1.setBounds(572, 0, 80, 80);
		
		Button btnNewButton_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1.setBounds(492, 0, 80, 80);
		
		Label label = new Label(composite, SWT.SEPARATOR | SWT.VERTICAL);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label.setBounds(242, 0, 2, 733);
		
		Label label_1 = new Label(composite, SWT.SEPARATOR);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setEnabled(false);
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		label_1.setBounds(488, 0, 2, 733);
		
		Button btnNewButton_1_2_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1.setBounds(652, 80, 80, 80);
		
		Button btnNewButton_3_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1.setBounds(572, 80, 80, 80);
		
		Button btnNewButton_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1.setBounds(492, 80, 80, 80);
		
		Button btnNewButton_1_2_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2.setBounds(406, 80, 80, 80);
		
		Button btnNewButton_3_2 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2.setBounds(326, 80, 80, 80);
		
		Button btnNewButton_1_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2.setBounds(246, 80, 80, 80);
		
		Button btnNewButton_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1.setBounds(160, 80, 80, 80);
		
		Button btnNewButton_1_3 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3.setBounds(80, 80, 80, 80);
		
		Button btnNewButton_4 = new Button(composite, SWT.BORDER);
		btnNewButton_4.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4.setBounds(0, 80, 80, 80);
		
		Button btnNewButton_1_2_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1_1.setBounds(652, 160, 80, 80);
		
		Button btnNewButton_3_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1_1.setBounds(572, 160, 80, 80);
		
		Button btnNewButton_1_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1_1.setBounds(492, 160, 80, 80);
		
		Button btnNewButton_1_2_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2_1.setBounds(406, 160, 80, 80);
		
		Button btnNewButton_3_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2_1.setBounds(326, 160, 80, 80);
		
		Button btnNewButton_1_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2_1.setBounds(246, 160, 80, 80);
		
		Button btnNewButton_2_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1_1.setBounds(160, 160, 80, 80);
		
		Button btnNewButton_1_3_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3_1.setBounds(80, 160, 80, 80);
		
		Button btnNewButton_4_1 = new Button(composite, SWT.BORDER);
		btnNewButton_4_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4_1.setBounds(0, 160, 80, 80);
		
		Label label_2 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2.setBounds(0, 242, 732, 2);
		
		Button btnNewButton_2_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1_1_1.setBounds(160, 406, 80, 80);
		
		Button btnNewButton_1_3_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3_1_1.setBounds(80, 406, 80, 80);
		
		Button btnNewButton_4_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_4_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4_1_1.setBounds(0, 406, 80, 80);
		
		Button btnNewButton_4_2 = new Button(composite, SWT.BORDER);
		btnNewButton_4_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4_2.setBounds(0, 326, 80, 80);
		
		Button btnNewButton_1_3_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3_2.setBounds(80, 326, 80, 80);
		
		Button btnNewButton_2_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1_2.setBounds(160, 326, 80, 80);
		
		Button btnNewButton_2_2 = new Button(composite, SWT.BORDER);
		btnNewButton_2_2.setBounds(160, 246, 80, 80);
		
		Button btnNewButton_1_4 = new Button(composite, SWT.BORDER);
		btnNewButton_1_4.setBounds(80, 246, 80, 80);
		
		Button btnNewButton_5 = new Button(composite, SWT.BORDER);
		btnNewButton_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_5.setBounds(0, 246, 80, 80);
		
		Button btnNewButton_1_1_3 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_3.setBounds(246, 246, 80, 80);
		
		Button btnNewButton_1_1_2_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2_2.setBounds(246, 326, 80, 80);
		
		Button btnNewButton_1_1_2_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2_1_1.setBounds(246, 406, 80, 80);
		
		Button btnNewButton_3_2_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2_1_1.setBounds(326, 406, 80, 80);
		
		Button btnNewButton_3_2_2 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2_2.setBounds(326, 326, 80, 80);
		
		Button btnNewButton_3_3 = new Button(composite, SWT.BORDER);
		btnNewButton_3_3.setBounds(326, 246, 80, 80);
		
		Button btnNewButton_1_2_3 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_3.setBounds(406, 246, 80, 80);
		
		Button btnNewButton_1_2_2_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2_2.setBounds(406, 326, 80, 80);
		
		Button btnNewButton_1_2_2_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2_1_1.setBounds(406, 406, 80, 80);
		
		Button btnNewButton_1_1_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1_1_1.setBounds(492, 406, 80, 80);
		
		Button btnNewButton_1_1_1_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1_2.setBounds(492, 326, 80, 80);
		
		Button btnNewButton_1_1_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_2.setBounds(492, 246, 80, 80);
		
		Button btnNewButton_3_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_2.setBounds(572, 246, 80, 80);
		
		Button btnNewButton_3_1_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1_2.setBounds(572, 326, 80, 80);
		
		Button btnNewButton_3_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1_1_1.setBounds(572, 406, 80, 80);
		
		Button btnNewButton_1_2_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1_1_1.setBounds(652, 406, 80, 80);
		
		Button btnNewButton_1_2_1_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1_2.setBounds(652, 326, 80, 80);
		
		Button btnNewButton_1_2_1_2 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_2.setBounds(652, 246, 80, 80);
		
		Button btnNewButton_1_2_1_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1_1_1_1.setBounds(652, 653, 80, 80);
		
		Button btnNewButton_3_1_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1_1_1_1.setBounds(572, 653, 80, 80);
		
		Button btnNewButton_1_1_1_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1_1_1_1.setBounds(492, 653, 80, 80);
		
		Button btnNewButton_1_2_2_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2_1_1_1.setBounds(406, 653, 80, 80);
		
		Button btnNewButton_3_2_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2_1_1_1.setBounds(326, 653, 80, 80);
		
		Button btnNewButton_1_1_2_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2_1_1_1.setBounds(246, 653, 80, 80);
		
		Button btnNewButton_2_1_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1_1_1_1.setBounds(160, 653, 80, 80);
		
		Button btnNewButton_1_3_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3_1_1_1.setBounds(80, 653, 80, 80);
		
		Button btnNewButton_4_1_1_1 = new Button(composite, SWT.BORDER);
		btnNewButton_4_1_1_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4_1_1_1.setBounds(0, 653, 80, 80);
		
		Button btnNewButton_4_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_4_2_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_4_2_1.setBounds(0, 573, 80, 80);
		
		Button btnNewButton_1_3_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_3_2_1.setBounds(80, 573, 80, 80);
		
		Button btnNewButton_2_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_1_2_1.setBounds(160, 573, 80, 80);
		
		Button btnNewButton_1_1_2_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_2_2_1.setBounds(246, 573, 80, 80);
		
		Button btnNewButton_3_2_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_2_2_1.setBounds(326, 573, 80, 80);
		
		Button btnNewButton_1_2_2_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_2_2_1.setBounds(406, 573, 80, 80);
		
		Button btnNewButton_1_1_1_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_1_2_1.setBounds(492, 573, 80, 80);
		
		Button btnNewButton_3_1_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_1_2_1.setBounds(572, 573, 80, 80);
		
		Button btnNewButton_1_2_1_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_1_2_1.setBounds(652, 573, 80, 80);
		
		Button btnNewButton_1_2_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_1_2_1.setBounds(652, 493, 80, 80);
		
		Button btnNewButton_3_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_1_2_1.setBounds(572, 493, 80, 80);
		
		Button btnNewButton_1_1_1_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_1_2_1.setBounds(492, 493, 80, 80);
		
		Button btnNewButton_1_2_3_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_2_3_1.setBounds(406, 493, 80, 80);
		
		Button btnNewButton_3_3_1 = new Button(composite, SWT.BORDER);
		btnNewButton_3_3_1.setBounds(326, 493, 80, 80);
		
		Button btnNewButton_1_1_3_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_1_3_1.setBounds(246, 493, 80, 80);
		
		Button btnNewButton_2_2_1 = new Button(composite, SWT.BORDER);
		btnNewButton_2_2_1.setBounds(160, 493, 80, 80);
		
		Button btnNewButton_1_4_1 = new Button(composite, SWT.BORDER);
		btnNewButton_1_4_1.setBounds(80, 493, 80, 80);
		
		Button btnNewButton_5_1 = new Button(composite, SWT.BORDER);
		btnNewButton_5_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		btnNewButton_5_1.setBounds(0, 493, 80, 80);
		
		Label label_2_1 = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_2_1.setBounds(0, 488, 732, 2);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		
		Button btnNewButton_6 = new Button(composite_1, SWT.NONE);
		btnNewButton_6.setBounds(63, 10, 129, 45);
		btnNewButton_6.setText("Play Puzzle");
		Display device = Display.getCurrent();
		btnNewButton_6.setBackground(new Color(device, 10, 199, 19));
		
		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setBounds(10, 61, 232, 234);
		
		List list = new List(composite_2, SWT.BORDER);
		list.setBounds(0, 0, 232, 234);
		
		Spinner spinner = new Spinner(composite_1, SWT.BORDER);
		spinner.setBounds(38, 327, 47, 22);
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setAlignment(SWT.CENTER);
		lblNewLabel.setBounds(20, 306, 81, 15);
		lblNewLabel.setText("Max Solutions");
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setBounds(150, 306, 86, 15);
		lblNewLabel_1.setText("Empty Spaces");
		
		Button btnGeneratePuzzle = new Button(composite_1, SWT.NONE);
		btnGeneratePuzzle.setBounds(74, 367, 107, 39);
		btnGeneratePuzzle.setText("Generate Puzzle");
		
		Spinner spinner_1 = new Spinner(composite_1, SWT.BORDER);
		spinner_1.setMaximum(64);
		spinner_1.setMinimum(10);
		spinner_1.setSelection(40);
		spinner_1.setBounds(160, 327, 47, 22);
		
		Composite composite_3 = new Composite(shell, SWT.NONE);
		composite_3.setFont(SWTResourceManager.getFont("Segoe UI Symbol", 9, SWT.BOLD));
		
		Button btnNewButton_7 = new Button(composite_3, SWT.NONE);
		btnNewButton_7.setBounds(0, 0, 60, 60);
		btnNewButton_7.setText("1");
		
		Button btnNewButton_7_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_1.setText("2");
		btnNewButton_7_1.setBounds(66, 0, 60, 60);
		
		Button btnNewButton_7_2 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_2.setText("3");
		btnNewButton_7_2.setBounds(132, 0, 60, 60);
		
		Button btnNewButton_7_3 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_3.setText("4");
		btnNewButton_7_3.setBounds(198, 0, 60, 60);
		
		Button btnNewButton_7_4 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_4.setText("5");
		btnNewButton_7_4.setBounds(264, 0, 60, 60);
		
		Button btnNewButton_7_5 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_5.setText("6");
		btnNewButton_7_5.setBounds(329, 0, 60, 60);
		
		Button btnNewButton_7_6 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_6.setText("7");
		btnNewButton_7_6.setBounds(395, 0, 60, 60);
		
		Button btnNewButton_7_6_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_6_1.setText("8");
		btnNewButton_7_6_1.setBounds(461, 0, 60, 60);
		
		Button btnNewButton_7_6_1_1 = new Button(composite_3, SWT.NONE);
		btnNewButton_7_6_1_1.setText("9");
		btnNewButton_7_6_1_1.setBounds(527, 0, 60, 60);
		

	}
}
