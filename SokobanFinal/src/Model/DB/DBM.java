package Model.DB;

import java.io.Serializable;
import java.util.List;

import javafx.collections.ObservableList;

public interface DBM {
	public void saveOrUpdate(DB data);
	//public <T> void deleteData(Class<T> c, Serializable sKey);
	public <T> ObservableList<T> getListByQuery(String q);
	public <T> List<T> getListByQuery(String q, int maxResults);
}
