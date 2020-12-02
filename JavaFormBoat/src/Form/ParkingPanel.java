package Form;

import java.awt.Graphics;
import java.awt.Panel;
import Boat.Boat;
import Boat.Parking;

public class ParkingPanel extends Panel {

	private static final long serialVersionUID = 1L;

	private Parking<Boat> parking;
	
	public ParkingPanel (Parking<Boat> park){
		parking = park;
	}
	
	public void paint(Graphics g) {
        super.paint( g );
        if (parking != null)
        	parking.DrawMarking( g );
    }

    public void setBoat(Parking<Boat> park) {
        this.parking = park;
    }

}
