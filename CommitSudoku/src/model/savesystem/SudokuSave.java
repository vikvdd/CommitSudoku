package model.savesystem;
import java.io.Serializable;

import model.game.puzzle.Difficulty;

public class SudokuSave implements Serializable{
	
	private static final long serialVersionUID = -7069655433630032227L;
	private String name;
	private Difficulty difficulty;
	
	public SudokuSave(String _name, Difficulty _difficulty) {
		name = _name;
		difficulty = _difficulty;
	}
	
	public String getName()
	{
		return name;
	}
	
	public Difficulty getDifficulty()
	{
		return difficulty;
	}
}
