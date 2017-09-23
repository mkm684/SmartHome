
public class DevicesFactory {
	
	DevicesFactory(){
		
	}
	
	public Device createDevice(String device_type, String device_name, GreetingServer server, String room_name ){
	
			Device device = null;
			if(device_type == "Light" || device_type == "light"){
				 device = new Light(device_name , server, room_name); 
			}else if(device_type == "Window" || device_type == "window"){
				 device = new Window(device_name , server, room_name);
			}else if(device_type == "Heater" || device_type == "heater"){
				 device = new Heater(device_name , server, room_name) ;
			}else if(device_type == "Door" || device_type == "door"){
				 device = new Door(device_name , server, room_name );
			}else{
				 System.out.println("erorr in factory");
			}
			return device;
			
	}
	
	public Thermostate createThermostate(Room myroom){
		return new Thermostate(myroom);
	}
}
