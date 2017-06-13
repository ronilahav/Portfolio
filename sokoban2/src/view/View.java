package view;

import javafx.scene.media.MediaPlayer;

public interface View {
	public void isCurrentLevelNull(boolean isNull);
	public void setLevelData(char[][] levelData);
	public void isLevelComplete(boolean isComplete);
	public void startTimer ();
	public void stopTimer();
	public void setSteps(int steps);
	public int getCount();
	public void setCount(int count);
	public void setOut(String out);
	public MediaPlayer getMp();
}