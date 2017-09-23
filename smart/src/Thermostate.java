import java.util.Timer;

public class Thermostate {

	private double setpoint ;
	public double current_temp;
	public int heaters_number;
	private Timer timer = new Timer();;
	
	Thermostate(Room myroom){
		heaters_number = 0 ;
		setpoint = 0 ;
		current_temp = 0 ;
		timer.scheduleAtFixedRate(new time(myroom, this), 0, 1000);	
	}
	
	public double getStatus(){
		return setpoint;
	}
	
	public void setStatus (double new_setpoint){
		setpoint = new_setpoint;
		
	}
	
	public void heater_added(){
		heaters_number++;
	}
	
	public void cancel_timer(){
		timer.cancel();
	}
}
