/*
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class demo_gui extends JFrame {

	static demo_gui frame;
	static JPanel topPanel;
	JTextField textfield;
	int x = 50;
	static GreetingClient receiver;
	private ArrayList<ImagePanel> images = new ArrayList<ImagePanel>();
	
	
	 //* Launch the application.
	 
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			//public void run() {
				try {
					receiver = new GreetingClient();
					receiver.run();
					initialize();
					frame.look_for_command();
					//demo_gui window = new demo_gui();
					//window.frame.setVisible(true);
					//frame = new demo_gui();
				} catch (Exception e) {
					e.printStackTrace();
				}
			//}
		});
	}

	
	// * Create the application.
	 
	public demo_gui() {
		//initialize();
	}
	
	public  void look_for_command(){
		String input_temp = null;
		String input = null;
		while(true){
			try {
				input = receiver.in.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("cannot read from control panel");
			}
			if (input_temp != input){
				String in = input;
				if (input.contains("created")){
					if (input.contains("D")){
						images.add(demo(in.subSequence(0, in.indexOf("D")).toString(),"Door"));	
					}else if (input.contains("L")){
						images.add(demo(in.subSequence(0, in.indexOf("L")).toString(),"Light"));	
					}else if (input.contains("W")){
						images.add(demo(in.subSequence(0, in.indexOf("W")).toString(),"Window"));	
					} 		
				}else {
					if (input.contains("on")){
						String name = in.subSequence(0, in.length()-2).toString();
						for (ImagePanel temp: images){
							if(temp.Device_name == name){
								temp.change_image(true);
							}
						}
					}else if ( input.contains("of")){
						String name = in.subSequence(0, in.length()-2).toString();
						for (ImagePanel temp: images){
							if(temp.Device_name == name){
								temp.change_image(false);
							}
						}
						
					}
				}
			}
			input_temp = input;
		}
	}

	
	// * Initialize the contents of the frame.
	 
	  static void initialize() {
		frame = new demo_gui();
		//frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.prepareUI();
		frame.pack();
		frame.setVisible(true);
		//frame.getContentPane().setLayout(null);
	}
	  
	private void prepareUI() {
		topPanel = new JPanel();
		textfield =new JTextField("Enter Name",7);
        topPanel.add(textfield);
		frame.getContentPane().add(topPanel);
		//frame.setVisible(true);
		//frame.repaint();
	}
	ImagePanel demo(String name , String type){
		ImagePanel panel = new ImagePanel(type,name);
        x=x+100;
        topPanel.add(panel);
        revalidate();
		//frame.setVisible(true);
        //frame.repaint();
		return panel;
	}
	
	
	public class ImagePanel extends JPanel{
		
	    private BufferedImage image;
	    String Device_type;
	    String Device_name;

	    public ImagePanel(String device_type , String device_name) {
	    	super();
	    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	    	this.setBounds(x, 103, 300, 200);
	    	Device_type = device_type;
	    	Device_name = device_name;
            //////Label////////////////////////////////////
            JLabel myLabel = new JLabel(device_type+" "+device_name);
            add(myLabel);
	        if(device_type == "Light"){
	            try {                
	  	          image = ImageIO.read(new File("bulb off.jpg"));
	  	       } catch (IOException ex) {
	  	            System.out.println("error in the bulb file");
	  	       }
           }else if (device_type == "Door"){
                try {                
        	        image = ImageIO.read(new File("doorclose.jpg"));
        	       } catch (IOException ex) {
        	            System.out.println("error in the bulb file");
        	       } 
            }else if (device_type == "Window"){
                try {                
        	        image = ImageIO.read(new File("windowclose.jpg"));
        	       } catch (IOException ex) {
        	            System.out.println("error in the bulb file");
        	       } 
            }
	    }
	    
	    
        public void change_image(boolean on_off){
        	if (Device_type == "Light"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("bulb on.jpg"));
	        	else
	        		image = ImageIO.read(new File("bulb off.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the bulb file");
	        	} 
        	}else if(Device_type == "Window"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("windowopen.jpg"));
	        	else
	        		image = ImageIO.read(new File("windowclose.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the window file");
	        	} 
        	}else if (Device_type == "Door"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("dooropen.jpg"));
	        	else
	        		image = ImageIO.read(new File("doorclose.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the Door file");
	        	} 
        	}
        	this.repaint(); 	
        }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	    }

	}

}*/

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import JavaDynUI.subPanel;
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class demo_gui extends JFrame {
 
    static demo_gui myFrame;
    JPanel topPanel;
	JTextField textfield;
	static GreetingClient receiver;
	private ArrayList<ImagePanel> rooms = new ArrayList<ImagePanel>();

	/////////////////////////////////Main Method/////////////////////////////////////
    public static void main(String[] args) throws Exception, InterruptedException {
    	SwingUtilities.invokeAndWait(() -> {
            try {
				createAndShowGUI();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    	receiver = new GreetingClient();
		receiver.run();
        myFrame.look_for_command();
    }
 
    private static void createAndShowGUI() throws IOException {
        myFrame = new demo_gui();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        myFrame.repaint();
        myFrame.setVisible(true);
    }
 
    private void prepareUI() {
        topPanel = new JPanel();
        topPanel.setBounds(100, 100, 700, 700);
        getContentPane().add(topPanel);
    }
    
    private ImagePanel newroom(String a){
    	ImagePanel temp = new ImagePanel(a); 
    	topPanel.add(temp);
        revalidate();
        return temp;
    }
    
	public  void look_for_command(){
		String input_temp = null;
		String input = null;
		while(true){
			try {
				input = receiver.in.readUTF();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("cannot read from control panel");
			}
			
			if (input_temp != input){
				String in = input;
				
				if (input.contains("created")){
					
					if(input.contains("R")){
						rooms.add(newroom(in.subSequence(0, in.indexOf("R")).toString()));	
					}else{
						String room_name = input.subSequence(0, in.indexOf("/")).toString();
						for (ImagePanel temp: rooms){
							if(temp.room_name.equals(room_name)){
									  if (input.contains("D")){
									temp.newdevice(in.subSequence(in.indexOf("/")+1, in.indexOf("D")).toString(),"Door");	
								}else if (input.contains("L")){
									temp.newdevice(in.subSequence(in.indexOf("/")+1, in.indexOf("L")).toString(),"Light");	
								}else if (input.contains("W")){
									temp.newdevice(in.subSequence(in.indexOf("/")+1, in.indexOf("W")).toString(),"Window");	
								}else if (input.contains("H")){
									temp.newdevice(in.subSequence(in.indexOf("/")+1, in.indexOf("H")).toString(),"Heater");	
								}
								break;
							}
						}
					}
				}else if(input.contains("removed")){
					
					if(input.contains("R")){
						String room_name = input.subSequence(0, in.indexOf("R")).toString();
						for (ImagePanel temp: rooms){
							if(temp.room_name.equals(room_name)){
								rooms.remove(temp);
								topPanel.remove(temp);
								topPanel.repaint();
								break;
							}
						}
					}else{
						String room_name = input.subSequence(0, in.indexOf("/")).toString();
						String name = in.subSequence(in.indexOf("/")+1, in.length()-7).toString();
						for (ImagePanel temp: rooms){
							if(temp.room_name.equals(room_name)){
								for (subSubPanel device_temp: temp.devices){
									if(device_temp.Device_name.equals(name)){
										temp.devices.remove(device_temp);
										temp.remove(device_temp);
										topPanel.repaint();
										break;
									}
								}
								break;
							}
						}
					}	
				}else {
					String room_name = input.subSequence(0, in.indexOf("/")).toString();
					String name = in.subSequence(in.indexOf("/")+1, in.length()-2).toString();
					for (ImagePanel room_temp: rooms){
						if(room_temp.room_name.equals(room_name)){
							for (subSubPanel device_temp: room_temp.devices){
								if(device_temp.Device_name.equals(name)){
									if (input.contains("on")){
										device_temp.change_image(true);
									}else if ( input.contains("of")){
										device_temp.change_image(false);
									}
									break;
								}
							}
							break;
						}
					}
				}
			}
			input_temp = input;
		}
	}
    
 
    private class ImagePanel extends JPanel { ///////////////ROOM/////////////////
         
    	ImagePanel me; //new room 
        boolean pressed;// mouse traker
        String room_name;
        private ArrayList<subSubPanel> devices = new ArrayList<subSubPanel>();
        
        public ImagePanel(String room_name) {
            super();
            me = this;
            me.setBackground(Color.gray);
            //me.setPreferredSize(new Dimension(200, 300));
            me.setLayout(new BoxLayout(me, BoxLayout.Y_AXIS));
            this.room_name = room_name;
			////////////////////room name label////////////////////////////////////
            JLabel myLabel = new JLabel(room_name);
            add(myLabel);  
        }
        
        /////////////////ADD Devices/////////////////////////////////////////////
        private subSubPanel newdevice(String name, String type){
        	subSubPanel temp = new subSubPanel(name,type); 
        	add(temp);
        	devices.add(temp);
            revalidate();
            return temp;
        }
    }
    
    private class subSubPanel extends JPanel {
        
        subSubPanel me;
	    private BufferedImage image;
	    String Device_type;
	    String Device_name;

 
        public subSubPanel(String device_name, String device_type) {
            super();
            me = this;
            Device_type = device_type;
            Device_name = device_name;
            //me.setLayout(new BoxLayout(me, BoxLayout.Y_AXIS));
            me.setPreferredSize(new Dimension(100, 150));
            me.setBackground(Color.white);
            /////////////////Label/////////////////////////
            JLabel myLabel = new JLabel(device_type+" "+device_name);
            add(myLabel);
            /// check box will be used if heater , window , light is added
            if(device_type.equals("Light")){
	            try {                
	  	          image = ImageIO.read(new File("bulb off.jpg"));
	  	       } catch (IOException ex) {
	  	            System.out.println("error in the bulb file");
	  	       }
           }else if (device_type.equals("Door")){
                try {                
        	        image = ImageIO.read(new File("doorclose.jpg"));
        	       } catch (IOException ex) {
        	            System.out.println("error in the door file");
        	       } 
            }else if (device_type.equals("Window")){
                try {                
        	        image = ImageIO.read(new File("windowclose.jpg"));
        	       } catch (IOException ex) {
        	            System.out.println("error in the window file");
        	       } 
            }else if (device_type.equals("Heater")){
                try {                
        	        image = ImageIO.read(new File("heater_off.jpg"));
        	       } catch (IOException ex) {
        	            System.out.println("error in the window file");
        	       } 
            }
            
                                  
        }

        public void change_image(boolean on_off){
        	if (Device_type == "Light"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("bulb on.jpg"));
	        	else
	        		image = ImageIO.read(new File("bulb off.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the bulb file");
	        	} 
        	}else if(Device_type == "Window"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("windowopen.jpg"));
	        	else
	        		image = ImageIO.read(new File("windowclose.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the window file");
	        	} 
        	}else if (Device_type == "Door"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("dooropen.jpg"));
	        	else
	        		image = ImageIO.read(new File("doorclose.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the Door file");
	        	} 
        	}else if (Device_type == "Heater"){
	        	try{
	        	if (on_off)
	        		image = ImageIO.read(new File("heater_on.jpg"));
	        	else
	        		image = ImageIO.read(new File("heater_off.jpg"));
	        	}catch (IOException ex) {
		            System.out.println("error in the Door file");
	        	} 
        	}
        	this.repaint(); 	
        }

	    @Override
	    protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	    }

    }
}
