import java.util.*;

public class DeviceGroup {
	
	private ArrayList<Device> devices = new ArrayList<Device>();
	private String name;
	
	DeviceGroup(String name){
		this.name = name;
	}
	
	public void addDevice(Device item){
		devices.add(item);
	}

	public void removeDevice(Device item){
		devices.remove(item);
	}
	
	public void turn_on(){
		for (Device temp : devices){
			temp.turn_on();
		}
	}
	
	public void turn_off(){
		for (Device temp : devices){
			temp.turn_off();
		}
		
	}
	
	public String get_name(){
		return name;
	}
	
	public ArrayList<Device> get_devices(){
		return devices;
		
	}

}
