package Form;

import java.awt.*;

import Boat.Boat;
import Boat.Harbour;
import Logics.IAdditional;

public class HarbourPanel extends Panel {
    private static final long serialVersionUID = 1L;

    private Harbour<Boat, IAdditional> harbour;

    public HarbourPanel(Harbour<Boat, IAdditional> park) {
        this.harbour = park;
    }

    public void paint(Graphics g) {
        super.paint( g );
        if (harbour != null) {
            harbour.Draw( g );
        }
    }
}
