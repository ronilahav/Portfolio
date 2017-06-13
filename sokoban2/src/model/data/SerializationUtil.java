package model.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class SerializationUtil {
	
	// deserialize to Object from given file
	public static Object deserialize(InputStream in) 
	{
		//ObjectInputStream ois;
		Object obj = null;
		try {
			ObjectInputStream ois = new ObjectInputStream(in);
			obj = ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	// serialize the given object and save it to file
	public static void serialize(Object obj, OutputStream out) 
	{
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			oos.writeObject(obj);
			out.close();
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}