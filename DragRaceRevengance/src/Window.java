import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Window extends JFrame implements ActionListener {

	/* ---------- INITIALIZATIONS ---------- */
	static Car winningCar = new Car();
	static double WinningTime = 0;
	SaveLoad SL = new SaveLoad();
	enum Wheels { Front , Rear , All }
	enum Tires { Street, Race, Drag }
	JFrame menu = new JFrame();
	Car def = new Car();
	Car second = new Car();
	Car temp1 = new Car();
	Car temp2 = new Car();
	String[] carList = new String[Car.list.size()];
	DecimalFormat df = new DecimalFormat("##.##");
	String NameOfCar;
	String MakerOfCar;
	int WheelsDrivenByCar = 0;
	int TiresOnCar = 0;
	int WeightOfCar = 1;
	int RPMOfCar = 1;
	int TorqueOfCar = 1;
	
	//startscreen:
	
	JButton start;
	JLabel background;
	ImageIcon image1;
	JLabel StartMenuBackground;
	
	//carselect:
	
	JButton startRace;
	JButton customWindow;
	JComboBox<String> carName;
	JComboBox<String> carName2;
	ImageIcon image2;
	JLabel carSelectHeader;
	
	//customcar:
	ImageIcon image3;
	JButton startRaceCust;
	JLabel curbWeight;
	JLabel revLimit;
	JLabel peakTorque;
	JLabel wheelDrive;
	JLabel tireType;
	JLabel versus;
	JLabel customCarBackground;
	JTextField weight;
	JTextField rpm;
	JTextField torque;
	JTextField Name;
	JTextField Manufacturer;
	JComboBox tires;
	JComboBox wheelsDriven;
	JComboBox otherCar;
	JComboBox carName3;
	
	//race
	JLabel winnerName;
	JLabel winnerTime;
	ImageIcon image4;
	JLabel raceBackground;
	
	//other
	JPanel container;
	JPanel startScreen;
	JPanel carSelect;
	JPanel customCar;
	JPanel race;
	
	
	/* ----------CONSTRUCTOR---------- */
	public Window() {
	for (int i = 0; i<Car.list.size(); i++) {
		carList[i] = Car.list.get(i).getName();
	}
	df.setRoundingMode(RoundingMode.DOWN);
	CardLayout card = new CardLayout();
	this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setTitle("BenchRacer Simulator 2017");
	this.setSize(1280,720);
	container = new JPanel();
	container.setLayout(card);
	startScreen = new JPanel();
	carSelect = new JPanel();
	this.add(container);
	customCar = new JPanel();
	race = new JPanel();



	container.add(startScreen, "start");
	container.add(carSelect, "select");
	container.add(customCar,"custom");
	container.add(race, "race");

	
	/*---------- startScreen ----------*/
	startScreen.setLayout(null);
	start = new JButton("Start");
	image1 = new ImageIcon("images/pic.jpg");
	StartMenuBackground = new JLabel(image1);
	StartMenuBackground.setBounds(0,0,1280,720);
	startScreen.add(start);
	start.setBounds(560,500,160,60);
	startScreen.add(StartMenuBackground);
	start.setFont(new Font("Helvetica", Font.BOLD, 55));
	start.setForeground(Color.ORANGE);
	start.setOpaque(false);
	start.setContentAreaFilled(false);
	start.setBorderPainted(false);
	start.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			card.show(container, "select");
		}
		
	});
	

	
	/*---------- carSelect ----------*/
	carSelect.setLayout(null);
	startRace = new JButton("Start Race!");
	customWindow = new JButton ("Custom");
	carName = new JComboBox(carList);
	carName.setEditable(false);
	carName2 = new JComboBox(carList);
	carName2.setEditable(false);
	image2 = new ImageIcon("images/pic1.jpg");
	carSelectHeader = new JLabel(image2);
	
	/*-----   positioning   -----*/
	carSelectHeader.setBounds(0,0,1280,720);
	startRace.setBounds(542,200,203,62);
	startRace.setFont(new Font("Impact", Font.BOLD, 32));
	startRace.setForeground(Color.ORANGE);
	startRace.setBackground(Color.WHITE);
	customWindow.setBounds(542,637,203,41);
	customWindow.setFont(new Font("Impact", Font.BOLD, 32));
	customWindow.setForeground(Color.ORANGE);
	customWindow.setBackground(Color.WHITE);
	carName.setBounds(202,200,301,41);
	carName2.setBounds(784,200,301,41);
	
	customWindow.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			card.show(container, "custom");
			
		}
		
	});
	startRace.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
		
			int temp3 = carName.getSelectedIndex();
			int temp4 = carName2.getSelectedIndex();
			temp1 = Car.list.get(temp3);
			temp2 = Car.list.get(temp4);

			winningCar = RaceTheCars(temp1, temp2);
			card.show(container, "race");
			
		}
		
	});
	
	/*-----   adding components   -----*/
	
	carSelect.add(startRace);
	carSelect.add(customWindow);
	carSelect.add(carName);
	carSelect.add(carName2);
	carSelect.add(carSelectHeader);
	
	/*---------- customCar ----------*/
	customCar.setLayout(null);
	Name = new JTextField();
	Manufacturer = new JTextField();
	image3 = new ImageIcon("images/pic2.jpg");
	customCarBackground = new JLabel(image3);
	carName3 = new JComboBox(carList);
	carName3.setEditable(false);
	versus = new JLabel("Versus:");
	startRaceCust = new JButton("Race!");
	weight = new JTextField();
	rpm = new JTextField();
	torque = new JTextField();
	tires = new JComboBox(Tires.values());
	wheelsDriven = new JComboBox(Wheels.values());
	startRaceCust.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			int i = carName3.getSelectedIndex();
			second = Car.list.get(i);
			NameOfCar = Name.getText();
			MakerOfCar = Manufacturer.getText();
			WeightOfCar = Integer.parseInt(weight.getText());
			TorqueOfCar = Integer.parseInt(torque.getText());
			RPMOfCar = Integer.parseInt(rpm.getText());
			WheelsDrivenByCar = wheelsDriven.getSelectedIndex();
			TiresOnCar = tires.getSelectedIndex();
			
			Custom(NameOfCar, MakerOfCar, WeightOfCar, TorqueOfCar, RPMOfCar, WheelsDrivenByCar, TiresOnCar, second);
			card.show(container, "race");
		}


		
	});
	
	/*-----   positioning   -----*/
	customCarBackground.setBounds(0, 0, 1280, 720);
	rpm.setBounds(98,157,103,30);
	torque.setBounds(148,237,103,30);
	weight.setBounds(197,317,103,30);
	Name.setBounds(248,398,103,30);
	Manufacturer.setBounds(297,476,103,30);
	startRaceCust.setBounds(992,138,208,92);
	startRaceCust.setFont(new Font("Helonia", Font.BOLD, 60));
	startRaceCust.setForeground(Color.WHITE);
	startRaceCust.setBackground(Color.GRAY);
	wheelsDriven.setBounds(246,157,103,30);
	tires.setBounds(294,237,136,29);
	carName3.setBounds(994,456,203,48);
	/*-----   adding components   -----*/
	customCar.add(Name);
	customCar.add(Manufacturer);
	customCar.add(rpm);
	customCar.add(torque);
	customCar.add(weight);
	customCar.add(tires);
	customCar.add(wheelsDriven);
	customCar.add(startRaceCust);
	customCar.add(carName3);
	customCar.add(customCarBackground);
	
	
	
	/*---------- race ----------*/
	race.setLayout(null);
	image4 = new ImageIcon("images/pic3.jpg");
	raceBackground = new JLabel(image4);
	winnerName = new JLabel();
	winnerName.setFont(new Font("Helvetica", Font.BOLD, 32));
	winnerTime = new JLabel();
	winnerTime.setFont(new Font("Helvetica", Font.BOLD, 32));
	
	
	/*-----   positioning   -----*/
	raceBackground.setBounds(0, 0, 1280, 720);
	winnerName.setBounds(880, 410, 370, 60);
	winnerTime.setBounds(830, 567, 100, 60);

	
	/*-----   adding components   -----*/
	
	race.add(winnerName);
	race.add(winnerTime);
	race.add(raceBackground);
	
	/*---------- other stuff ----------*/

	this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {	
	}
	
	public Car Custom (String Name, String Manufacturer, int weight, int torque, int rpm, int wheelsDriven, int tires, Car c2) {
		Car CustCar = new Car(Name, Manufacturer, weight, torque, rpm, wheelsDriven, tires);
		c2 = RaceTheCars(CustCar, c2);
		Car.list.add(CustCar);
		SL.Save(Car.list);
		return c2;
		
	}
	public Car RaceTheCars (Car c1, Car c2){

		double Time1 = CalcSpeed(c1);
		double Time2 = CalcSpeed(c2);
		if (Time1<Time2) {
			winnerName.setText(String.valueOf(c1.getName()));
			WinningTime = Math.round(Time1*100.0)/100.0;
			winnerTime.setText(String.valueOf(WinningTime));
			winnerTime.setBackground(Color.RED);
			
			return c1;}
		if (Time2<Time1) {
			winnerName.setText(String.valueOf(c2.getName()));
			WinningTime = Math.round(Time2*100.0)/100.0;
			winnerTime.setText(String.valueOf(WinningTime));
			return c2;}
		else 
			return def;
	}
	
	public double CalcSpeed(Car c) {
		
		double accel = 0;
		int WGT = c.weight;
		int TRQ = c.torque;
		int REV = c.RPM;
		int WD = c.WD;
		int cartires = c.Tires;
		double HP = (TRQ*REV)/5252;
			if (WD ==0 ||WD == 1) 
			HP = HP*.90;
			
			else if (WD == 2) 
			HP = HP*.80;
		accel = 1.356*(24*(HP/WGT));
			if (WD ==0) 
			accel = accel*.7;
			
			else if (WD == 1) 
			accel = accel*.85;
			
			else if(WD == 2) 
			accel = accel*1;
			
			if (cartires ==0) 
			accel = accel*.7;
			
			else if (cartires == 1) 
			accel = accel*.9;
			
			else if(cartires == 2) 
			accel = accel*1;
			System.out.println(accel);
			double qmTime = Math.sqrt((804)/accel);
		return qmTime;
	}
	

}
