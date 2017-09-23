import java.io.IOException;
import java.util.ArrayList;

public class Room {
	
	private ArrayList<Device> devices = new ArrayList<Device>();
	private Thermostate room_thermostate = null;
	private DevicesFactory factory;
	private String name;
	GreetingServer server ;
	
	Room(String name , GreetingServer server ){
		this.name = name;
		factory = new DevicesFactory();
		this.server = server;
		try {
			server.out.writeUTF(name+"R"+"created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addDevice(String device_type , String device_name ){
		try{
			
		Device new_device = factory.createDevice(device_type, device_name , server, name);
		
		if(new_device instanceof Heater){
			if (room_thermostate == null)
				room_thermostate = factory.createThermostate(this);
				room_thermostate.heater_added();
		}
			devices.add(new_device);
		}catch(NullPointerException  e){

				System.out.println("error could not add device");
		}
	}
	
	public void removeDevice(String device_name){
		for (Device temp : devices){
			if(temp.get_name() == device_name){
				if (temp instanceof Heater){
					room_thermostate.heaters_number--;
					if (room_thermostate.heaters_number == 0){
					room_thermostate.cancel_timer();
					room_thermostate = null;
					}
				}
				temp.remove_panel();
				devices.remove(temp);
				break;
			}
		}
	}
		
	public void turn_on(String device_name){
		for (Device temp : devices){
			if(temp.get_name() == device_name){
				temp.turn_on();
				break;
			}
		}
	}
	
	public void turn_off(String device_name){
		for (Device temp : devices){
			if(temp.get_name() == device_name){
				temp.turn_off();
				break;
			}
		}
	}	
	
	public void setRoom_temp(double setpoint){
		room_thermostate.setStatus(setpoint);	
		System.out.println(name + " temprature is : " + setpoint);
	}
	
	public void remove_panel() {
		try {
			server.out.writeUTF(name+"R"+"removed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public String get_name(){
		return name;
	}
	
	public ArrayList<Device> get_devices(){
		return devices;
	}
	
}
