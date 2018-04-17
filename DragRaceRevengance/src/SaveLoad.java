import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import java.io.Serializable;

public class SaveLoad {

	SaveLoad(){
		
	}

	public void Save(ArrayList<Car> list){
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("carList.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(list);
	         out.close();
	         fileOut.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	      }

	}
	
	public void Load () {
		 try {
	         FileInputStream fileIn = new FileInputStream("carList.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Car.list = (ArrayList<Car>) in.readObject();
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         i.printStackTrace();
	         return;
	      } catch (ClassNotFoundException c) {
	         System.out.println("Cars not found");
	         c.printStackTrace();
	         return;
	      }
	}

}