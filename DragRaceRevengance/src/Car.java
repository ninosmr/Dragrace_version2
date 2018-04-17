import java.io.Serializable;
import java.util.ArrayList;

public class Car implements Serializable{
	static ArrayList<Car> list = new ArrayList<Car>();
	String Name = null;
	String Brand = null;
	int weight = 0;
	int torque = 0;
	int RPM = 0;
	int WD = 0;

	int Tires = 0;
	
	public Car() {
		
	}
	
	public Car (String Name, String Brand, int weight, int torque, int RPM, int WD, int Tires) {
		this.Name = Name;
		this.Brand = Brand;
		this.weight = weight;
		this.torque = torque;
		this.RPM = RPM;
		this.WD = WD;
		this.Tires = Tires;
	}
	
	public String getName() {
		return this.Brand+" "+this.Name;
	}

}