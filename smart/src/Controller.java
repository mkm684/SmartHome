import java.util.ArrayList;

public class Controller {

	private ArrayList<Room> rooms = new ArrayList<Room>();
	private ArrayList<DeviceGroup> groups = new ArrayList<DeviceGroup>();
	private Internal_control instance;
	
	Controller(){
		//groups.add(new DeviceGroup("Lights"));
		//groups.add(new DeviceGroup("Doors"));
		//groups.add(new DeviceGroup("Windows"));
	}
	
/////////////////////DEVICE IN ROOMS/////////////////////////////////////////////////	
	public void commandDevice(String room_name, String device , boolean command){
		for (Room temp: rooms){
			if (temp.get_name() == room_name){
				if(command)
					temp.turn_on(device);
			else
					temp.turn_off(device);
			}
		}
	}
	
	public void setTemprature(String room , double setpoint ){
		for (Room temp : this.rooms){
			if(temp.get_name() == room){
				temp.setRoom_temp(setpoint);
				break;
			}
		}
	}
	
	public void addRoom(String name, GreetingServer server ){
		// check if name exist
		for (Room temp : rooms){
			if (temp.get_name()==name){
				System.out.println("Error!! : this room does aleady exist");
				return;
			}
		}
		// if not create new room
		Room new_room = new Room(name , server);
		rooms.add(new_room);
	}
	
	public void deleteRoom(String name){
		for (Room temp : rooms){
			if (temp.get_name()==name){
				temp.remove_panel();
				rooms.remove(temp);
				return;
			}
		}
		System.out.println("Error!! : this room does aleady exist");
	}
	
	public void addDevice(String device_name, String room_name , String device_type ){
		Room temp_room =  null;
		for (Room temp: rooms){
			if(temp.get_name() == room_name){
				temp_room = temp;
				break;
			}
		}
	try {
		temp_room.addDevice(device_type, device_name );
	}catch(NullPointerException  e){
		if(temp_room == null)
		System.out.println("this room does not exist, you cannot add a device");
	}
	
	}
	
	public void deleteDevice(String device_name, String room_name ){
		for (Room temp: rooms){
			if(temp.get_name() == room_name){
				temp.removeDevice(device_name);
				break;
			}
		}
	}
	
//////////////////////////////////////DEVICES IN GROUPS/////////////////////////
	public void commandGroup(String Group_name, boolean command){
		DeviceGroup temp_group = null;
		for (DeviceGroup temp: groups){
			if (temp.get_name()== Group_name){
				temp_group=temp;
			}
		}
		try{
			if (command)
				temp_group.turn_on();
			else 
				temp_group.turn_off();
			
		}catch(NullPointerException  e){
			System.out.println("this room does not exist");
		}
	}
	
	public void addGroup(String group_name){
		// check if name exist
		for (DeviceGroup temp : groups){
			if (temp.get_name()==group_name){
				System.out.println("Error!! : this room does aleady exist");
				return;
			}
		}
		// if not create new room
		DeviceGroup new_group = new DeviceGroup(group_name);
		groups.add(new_group);
		
	}
	
	public void DeleteGroup(String group_name){
		for (DeviceGroup temp : groups){
			if (temp.get_name()==group_name){
				groups.remove(temp);
				return;
			}
		}
		System.out.println("Error!! : this room does aleady exist");
	}
	
	public void updateStatue(){
		
	}
	
	public void addDevice_to_group(String device_name, String room_name , String group_name ){
		try{
		Room temp_room =  null;
		for (Room temp: rooms){
			if(temp.get_name() == room_name)
				temp_room = temp;
				break;
		}
		
		Device temp_device= null;
		for (Device temp : temp_room.get_devices()){
			if(temp.get_name() == device_name){
				temp_device = temp;
				break;
			}
		}
		
		for (DeviceGroup temp : groups){
			if(temp.get_name() == group_name){
				temp.addDevice(temp_device);
				break;
			}
		}
		
		}catch(NullPointerException  e){
			System.out.println("this room does not exist");
		}
	}
	
	public void removeDevice(String device_name, String group_name ){
		DeviceGroup temp_group = null;
		for (DeviceGroup temp : groups){
			if(temp.get_name() == group_name){
				temp_group = temp;
				break;
			}
		}
		try {
			for (Device temp : temp_group.get_devices()){
				if(temp.get_name() == device_name){
					temp_group.removeDevice(temp);
					break;
				}
			}		
		}catch(NullPointerException  e){
			System.out.println("this group or device does not exist");
		}
		
}
}
