package Form;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Boat.Boat;
import Boat.ITransportBoat;
import Boat.MotorBoat;
import Logics.Updated;

public class BoatPanel extends Panel {
    private static final long serialVersionUID = 1L;
    private Boat boat;

    public BoatPanel(Boat boat) {
        this.boat = boat;
    }

    public void paint(Graphics g) {
        super.paint( g );
        if (boat != null)
            boat.DrawTransport( g );
    }

    public void setBoat(Boat boat) {
        this.boat = boat;
    }
}
