package Model.DB;

import java.util.LinkedList;
import java.util.List;

import javafx.collections.ObservableList;

public class SokobanDBManager {

	private DBManager dbm = DBManager.getInstance();

	public ObservableList getPlayersList(){
		return dbm.getListByQuery("from Players");
	}
	
	public ObservableList getUserNameList(){
		return dbm.getListByQuery("select s.name from Players s");
	}
	
	public ObservableList getList(String q){
		return dbm.getListByQuery(q);
	}

	public ObservableList getLevelList(){
		return dbm.getListByQuery("from Levels");
	}
	
	public ObservableList getLevelNameList(){
		return dbm.getListByQuery("select s.name from Levels s");
	}
	

	public ObservableList getScoreList(String playerName,String levelName, String orderBy, int maxResults){
		String q = "from ScoreList s where s.lName like '"+levelName+
				"' and s.uName like'"+playerName+"' order by s."+orderBy;//steps /time /lName
		return dbm.getListByQuery(q, maxResults);
		
	}

	public void addPlayer(String playerName){
		PlayerDB p = new PlayerDB(playerName);
		dbm.saveOrUpdate(p);
	}

	public void addLevel(String levelName,String map,int difficulty){
		LevelDB l = new LevelDB(levelName, map, difficulty);
		dbm.saveOrUpdate(l);
	}
	
	public void addScore(String userName,String levelName,int steps,int time){
		ScoreList sl = new ScoreList(userName, levelName, steps, time);
		dbm.saveOrUpdate(sl);
	}
	
	public void close() {
		dbm.close();
	}

}
