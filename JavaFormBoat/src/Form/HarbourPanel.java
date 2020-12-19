package Form;

import java.awt.*;

import Boat.Boat;
import Boat.Harbour;
import Logics.IAdditional;
import Boat.Vehicle;

public class HarbourPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Harbour<Boat, IAdditional> harbour;

    public HarbourPanel(Harbour<Boat, IAdditional> harbour) {
        this.harbour = harbour;
    }

    public void paint(Graphics g) {
        super.paint( g );
        if (harbour != null) {
            harbour.Draw( g );
        }
    }

    public void setHarbour(Harbour<Boat, IAdditional> harbour) {
        this.harbour = harbour;
    }
}
