package Model;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import Model.Actions.ConsoleLevelDisplayer;
import Model.Actions.LoadLevel;
import Model.Actions.MySokobanPolicy;
import Model.Actions.Policy;
import Model.Actions.SaveLevel;
import Model.DB.DBManager;
import Model.DB.LevelDB;
import Model.DB.PlayerDB;
import Model.DB.ScoreList;
import Model.DB.SokobanDBManager;
import Model.Data.Level;
import antlr.collections.impl.LList;
import javafx.collections.ObservableList;


public class SokobanModel extends Observable implements Model,Observer{
	private Level lvl = new Level();
	private LoadLevel ll = new LoadLevel();
	private Policy policy = new MySokobanPolicy();
	private SaveLevel sl = new SaveLevel();
	private ConsoleLevelDisplayer lvlDisp = new ConsoleLevelDisplayer();
	private LinkedList<String> ls;
	private SokobanDBManager dbm;
	
	public SokobanModel(){
		
	}
	@Override
	public SokobanDBManager getManager() {
		return dbm = new SokobanDBManager();
	}
	public SokobanModel(Level lvl){
		this.lvl=lvl;
	}
	
	public void setLevel(Level lvl){
		this.lvl = lvl;
	}
	public Level getLevel(){
		return this.lvl;
	}
	@Override
	public void load(LinkedList<String> arg){
		this.lvl.stopTimer();
		this.lvl.setStepCounter(0);
		ll.load(lvl,arg.removeFirst());
		levelToPaint("-1");//-1 level loaded. no move has made.
		this.lvl.addObserver(this);
	}
	@Override
	public void save(LinkedList<String> arg){
		sl.save(arg.removeFirst(), this.lvl);
	}
	public void display(){
		this.lvlDisp.display(lvl);
	}
	
	public void levelToPaint(String param){
		ls = new LinkedList<String>();
		ls.add("paint");
		ls.add(this.lvlDisp.getStringFromLevel(lvl).toString());//Level to print as a String.
		ls.add(param);//Move number.
		ls.add(this.lvl.getPlayer().getPos());//send the location of the player.
		ls.add(""+this.lvl.getPlayer().getDispChar());//Display char of player.
		ls.add(this.lvl.getLastMovedBox().getPos());//Send the position of the last moved box.
		ls.add(""+this.lvl.getLastMovedBox().getDispChar());
		setChanged();
		notifyObservers(ls);
	}

	@Override
	public void move(LinkedList<String> arg){//arg used as direction.
		if(lvl!=null){
		int moveNumber;
		moveNumber=policy.move(lvl, arg.removeFirst());
		if(moveNumber>0){//if movement happened.
			this.lvl.increaseStepCounterBy(1);//Add 1 step.
			if(this.lvl.getBoxOnDest()==this.lvl.getDestNumber()){//check if completed
				ls = new LinkedList<String>();
				ls.add("completed");
				ls.add(String.valueOf(this.lvl.getStepCounter()));
				setChanged();
				notifyObservers(ls);
			}
		}
		
		levelToPaint(String.valueOf(moveNumber));//Redraw
		}
	}
	
	public int getLevelStepCounter(){
		return this.lvl.getStepCounter();
	}

	@Override
	public void update(Observable o, Object arg) {
		ls=new LinkedList<String>();
		String[] s = ((String)arg).split(":");
		ls.add(s[0]);
		ls.add(s[1]);
		setChanged();
		notifyObservers(ls);
	}
	
	public void sendList(LinkedList<String> args){//players,levels,scores
		String s = args.removeFirst();
		ObservableList<String> ol;
		ls = new LinkedList<>();
		ls.add("showlist");//cmd
		if(s.equals("players")){
			ls.add("players");//table type
			ol=dbm.getPlayersList();
			for(String str:ol)//table data
				ls.add(str);
		}else if(s.equals("levels")){
			ls.add("levels");
			ol=dbm.getLevelList();
			for(String str:ol)
				ls.add(str);
		}else if(s.equals("scores")){
			ls.add("scores");
			
			String pName="%";
			String lName="%";
			String orderBy="steps";
			int maxResults = 15;
			if(args.size()==3){
				pName=args.removeFirst();
				lName=args.removeFirst();
				orderBy=args.removeFirst();
			}
				
			int maxResult;
			ol=dbm.getScoreList(pName, lName, orderBy, maxResults);
			for(String str:ol)
				ls.add(str);
		}
		setChanged();
		notifyObservers(ls);
	}
	
	

}
