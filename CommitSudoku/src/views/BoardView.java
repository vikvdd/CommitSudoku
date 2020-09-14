package views;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class BoardView extends Composite{

	public BoardView(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(1, false));
		int minBtnSize = 80;
		
		Composite composite = new Composite(this, SWT.NONE);
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite.widthHint = 792;
		composite.setLayoutData(gd_composite);
		composite.setLayout(new GridLayout(10, true));
		
		Button btnNewButton = new Button(composite, SWT.NONE);
		btnNewButton.setBounds(0, 0, minBtnSize, minBtnSize);
		btnNewButton.setText("New Button");
		
		Button btnNewButton_1 = new Button(composite, SWT.NONE);
		btnNewButton_1.setBounds(0, 0, minBtnSize, minBtnSize);
		btnNewButton_1.setText("New Button");
		
		Button btnNewButton_2 = new Button(composite, SWT.NONE);
		btnNewButton_2.setBounds(0, 0, minBtnSize, minBtnSize);
		btnNewButton_2.setText("New Button");
		
		Button btnNewButton_3 = new Button(composite, SWT.NONE);
		btnNewButton_3.setText("New Button");
		
		Button btnNewButton_4 = new Button(composite, SWT.NONE);
		btnNewButton_4.setText("New Button");
		
		Button btnNewButton_5 = new Button(composite, SWT.NONE);
		btnNewButton_5.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		btnNewButton_5.setText("New Button");
		
		Button btnNewButton_6 = new Button(composite, SWT.NONE);
		btnNewButton_6.setText("New Button");
		
		Button btnNewButton_7 = new Button(composite, SWT.NONE);
		btnNewButton_7.setText("New Button");
		
		Button btnNewButton_8 = new Button(composite, SWT.NONE);
		btnNewButton_8.setText("New Button");
		
		Button btnNewButton_9 = new Button(composite, SWT.NONE);
		btnNewButton_9.setText("New Button");
		
		Button btnNewButton_10 = new Button(composite, SWT.NONE);
		btnNewButton_10.setText("New Button");
		
		Button btnNewButton_11 = new Button(composite, SWT.NONE);
		btnNewButton_11.setText("New Button");
		
		Button btnNewButton_12 = new Button(composite, SWT.NONE);
		btnNewButton_12.setText("New Button");
		
		Button btnNewButton_13 = new Button(composite, SWT.NONE);
		btnNewButton_13.setText("New Button");
		
		Button btnNewButton_14 = new Button(composite, SWT.NONE);
		btnNewButton_14.setText("New Button");
		
		Button btnNewButton_15 = new Button(composite, SWT.NONE);
		btnNewButton_15.setText("New Button");
		
		Button btnNewButton_16 = new Button(composite, SWT.NONE);
		btnNewButton_16.setText("New Button");
		
		Button btnNewButton_17 = new Button(composite, SWT.NONE);
		btnNewButton_17.setText("New Button");
		
		Button btnNewButton_18 = new Button(composite, SWT.NONE);
		btnNewButton_18.setText("New Button");
		
		Button btnNewButton_19 = new Button(composite, SWT.NONE);
		btnNewButton_19.setText("New Button");
		// TODO Auto-generated constructor stub
	}
}
