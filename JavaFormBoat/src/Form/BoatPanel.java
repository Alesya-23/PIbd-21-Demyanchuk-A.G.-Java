import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoatPanel extends Panel implements ActionListener {
    private static final long serialVersionUID = 1L;
    Boat boat;
    Updated win;
    private boolean canDraw;

    public BoatPanel(Boat Boat, Updated window, boolean CanDraw) {
        win = window;
        boat = Boat;
        canDraw = CanDraw;
    }

    public boolean drawCan(boolean can) {
        canDraw = can;
        if (canDraw == false) {
            return false;
        } else return true;
    }

    public void paint(Graphics g) {
        super.paint( g );
        if (canDraw == true)
            boat.DrawTransport( g );

    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }
}
