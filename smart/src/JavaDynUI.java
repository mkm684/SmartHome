import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.util.ArrayList;

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
 
/**
 *
 * @web http://java-buddy.blogspot.com/
 */
public class JavaDynUI extends JFrame {
 
    static JavaDynUI myFrame;
    JPanel topPanel;

	JTextField textfield;
	
	

	
	////// controller and server 
	Controller controlpanel = new Controller();	
	static GreetingServer newserver ;

	/////////////////////////////////Main Method
    public static void main(String[] args) {
    	int port = Integer.parseInt("5000");
    	SwingUtilities.invokeLater(() -> {
            try {
				createAndShowGUI();
		    	 newserver = new GreetingServer(port);
		    	 newserver.run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
 
    private static void createAndShowGUI() throws IOException {
        myFrame = new JavaDynUI();
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.prepareUI();
        myFrame.pack();
        myFrame.setVisible(true);
    }
 
    private void prepareUI() {
    	
        topPanel = new JPanel();
        topPanel.setBounds(100, 100, 700, 700);
        
        JButton buttonAdd = new JButton("Add Room");
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	topPanel.add(new subPanel(topPanel));
                //myFrame.repaint();
                revalidate();
            }
        });
        topPanel.add(buttonAdd);
        
        textfield =new JTextField("Enter Name",7);
        topPanel.add(textfield);
        /*JButton buttonRemoveAll = new JButton("Remove All");
        buttonRemoveAll.addActionListener(new ActionListener() {
 
            @Override
            public void actionPerformed(ActionEvent e) {
                topPanel.removeAll();
                //myFrame.pack();
                revalidate();
            }
        });*/
 
       // getContentPane().add(mainPanel, BorderLayout.CENTER);
       // getContentPane().add(buttonAdd, BorderLayout.NORTH);
        getContentPane().add(topPanel);
        //getContentPane().add(buttonRemoveAll, BorderLayout.SOUTH);
		//getContentPane().add(textfield, BorderLayout);
    }
 
    private class subPanel extends JPanel { ///////////////ROOM/////////////////
         
        subPanel me; //new room 
        boolean pressed;// mouse traker
        String devCh;
        boolean thermostate_created = false;
 
        public subPanel(JPanel panel) {
            super();
            me = this;
            me.setBackground(Color.gray);
            me.setBounds(76, 103, 300, 200);
            me.setLayout(new BoxLayout(me, BoxLayout.Y_AXIS));
            
           /////////////drag and drop feature/////////////////////////////
            me.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					if(pressed)
					me.setLocation(e.getX(), e.getY());
					panel.repaint();
				}
			});
			me.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					pressed= true;
				}
				@Override
				public void mouseReleased(MouseEvent e) {
					pressed= false;
				}
			});
			
			////////////////////room name label////////////////////////////////////
            JLabel myLabel = new JLabel(textfield.getText());
            controlpanel.addRoom(myLabel.getText(),newserver);
            add(myLabel);
            /////////////////////////////new test feild to add devices///////////////
            JTextField textfield1 = new JTextField("",7);
            textfield.setSize(40, 20);
            me.add(textfield1);
            ///////////////////////////////////// drop list of devices ////////////////////////
        	String[] dev = {"Light", "Door", "Heater", "Window"};
        	JComboBox devices = new JComboBox(dev);
            me.add(devices);
            /////////////////////////////////////////remove room///////////////////////////////
            JButton myButtonRemoveMe = new JButton("Remove Room");
            myButtonRemoveMe.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                	controlpanel.deleteRoom(myLabel.getText());
                    me.getParent().remove(me);
                    myFrame.repaint();
                }
            });
            add(myButtonRemoveMe);
            
            //////////////////////////////////////////add device////////////////////////////////////////////////////
            JButton action = new JButton("Add Device");
            action.addActionListener(new ActionListener() {
     
                @Override
                public void actionPerformed(ActionEvent e) {
                	devCh = (String) devices.getSelectedItem();
                	if (devCh == "Heater" && thermostate_created == false ){
                		thermostate_created = true;
                        me.add(new subSubPanel(devCh, textfield1.getText(),myLabel.getText() ));
                        textfield1.setText(" ");
                        revalidate();
                	}
                	else if (devCh == "Heater" && thermostate_created == true){
                		System.out.println("heater is already created in "+myLabel.getText() );
                	}
                	else{
                    me.add(new subSubPanel(devCh, textfield1.getText(),myLabel.getText() ));
                    textfield1.setText(" ");
                    revalidate();
                	}
                }
            });
            add(action);
        }
    }
    
    private class subSubPanel extends JPanel {
        
        subSubPanel me;
 
        public subSubPanel(String device_type, String device_name, String room_name) {
            super();
            me = this;
            me.setBackground(Color.white);
            
            //////Label////////////////////////////////////
            JLabel myLabel = new JLabel(device_type+" "+device_name);
            add(myLabel);
            
            /// check box will be used if heater , window , light is added
            Checkbox checkbox = new Checkbox("");
    		JSlider slider = new JSlider(0,30);
    		slider.setValue(0);
    		
    		/////////////////////slider.set
    		JLabel temprature_label = new JLabel("");
            if (device_type == "Light" || device_type == "Window" || device_type == "Door"){
            	checkbox.addItemListener(new ItemListener() {
        			public void itemStateChanged(ItemEvent e) {
        				if (checkbox.getState()){
        	            	controlpanel.commandDevice(room_name, device_name, true);
        	            }
        	            else{
        	            	controlpanel.commandDevice(room_name, device_name, false);
        	            }
        			}
        		});
            	add(checkbox);
            }else{
            	add(temprature_label);
            	slider.addChangeListener(new ChangeListener() {
        			public void stateChanged(ChangeEvent e) {
        				temprature_label.setText(Integer.toString(slider.getValue())+" C");
        				controlpanel.setTemprature(room_name, slider.getValue());
        			}
        		});
            	add(slider);
            }	
            controlpanel.addDevice(device_name, room_name, device_type);
            
            /////////////////////remove button
            JButton myButtonRemoveMe = new JButton("Remove Device");
            myButtonRemoveMe.addActionListener(new ActionListener(){
 
                @Override
                public void actionPerformed(ActionEvent e) {
                	controlpanel.deleteDevice(device_name, room_name);
                    me.getParent().remove(me);
                    topPanel.repaint();
                }
            });
            add(myButtonRemoveMe);
                                  
        }
    }
}