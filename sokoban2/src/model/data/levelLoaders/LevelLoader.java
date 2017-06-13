package model.data.levelLoaders;

import java.io.InputStream;

import model.data.Level;

public interface LevelLoader {
	/**
	 * �. ���� ����� ���� ����� ������ ����� ���� ���� ��� ���� �� ����� �����
	 * �. �� �� ���� ������ ����� ���� ����� ���� ����� ��� ���� ����� ���� ���� ���� ����
	 * �. �� ���� ����� ���� ���� ����� ���� ������� ��� ���� ���� ��� �� ���� ����� ���� ����� �� �������, �� ��� ����� ���
	 * �. Input Stream is the superclass of all classes representing an input stream of bytes.
	 */
	public Level loadLevel(InputStream in);
}