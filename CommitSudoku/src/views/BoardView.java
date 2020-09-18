package views;

import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.JButton;

public class BoardView extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4231342799540984930L;
	
	private int _boardSize= 150;
	/**
	 * Create the panel.
	 * 
	 *
	 */
	public BoardView()
	{
		setLayout(new GridLayout(9, 9, 0, 0));		
		buildGameBoard();
	}
	
	public BoardView(int boardSize) {
		setBounds(100,100,boardSize,boardSize);
		setLayout(new GridLayout(9, 9, 0, 0));		
		buildGameBoard();
	}
	
	private void buildGameBoard()
	{
		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {
				JButton btn = new JButton("8");
				
				btn.setBackground(new Color(200, 210, 224));
				btn.setFocusPainted(false);
				add(btn);
			}
		}
	}

}
