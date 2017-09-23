import java.io.IOException;

public class Light implements Device {
	
	private String name;
	private String room_name;
	private boolean  on_or_off;
	GreetingServer server;
	
	Light(String name , GreetingServer server , String room_name){
		this.name = name;
		this.room_name = room_name;
		on_or_off = false;
		this.server = server;
		try {
			server.out.writeUTF(room_name+"/"+name+"L"+"created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void turn_on(){
		on_or_off = true;
		System.out.println(name + "light is on");
		try {
			server.out.writeUTF(room_name+"/"+name+"on");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void turn_off(){
		on_or_off = false;
		System.out.println(name +"light is off");
		try {
			server.out.writeUTF(room_name+"/"+name+"of");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean get_status(){
		return this.on_or_off;
	}
	
	public String get_name(){
		return name;
	}
	
	public void remove_panel(){
		try {
			server.out.writeUTF(room_name+"/"+name+"removed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}