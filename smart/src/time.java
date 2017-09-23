import java.util.TimerTask;

public class time  extends TimerTask{
	
	Room r;
	Thermostate t;
	int count = 0;
	time(Room r, Thermostate t){
		this.r = r;
		this.t = t;
	}
	@Override
	public void run() {
		System.out.println("current_temp" + t.current_temp);
		if (t.current_temp >= t.getStatus()){
			for (Device temp: r.get_devices()){
				if (temp instanceof Heater){
					temp.turn_off();
				}
			}
			count++;
			if (count == 2){
			count =0;
			t.current_temp--;
			}
		}else{
			for (Device temp: r.get_devices()){
				if (temp instanceof Heater){
					temp.turn_on();
				}
			}
			t.current_temp++;
		}
	}

}
