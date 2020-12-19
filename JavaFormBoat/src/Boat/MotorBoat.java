package Boat;

import java.awt.Color;
import java.awt.Graphics;

import Logics.CicleMotors;
import Logics.IAdditional;
import Logics.RectangleMotors;
import Logics.TraungleMotors;

public class MotorBoat extends Boat {
    private IAdditional iAdditional;

    private Color DopColor;

    /// Признак наличия боковых линий
    private boolean SideLine;

    // наличие кабины
    private boolean Cabin;

    // наличие мотора
    private boolean Motors;

    private int addClass;

    private int countMotors;

    /// Конструктор
    public MotorBoat(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean sideLine, boolean cabin,
                     boolean motors, int AddClass, int countMotors) {
        super( maxSpeed, weight, mainColor, 110, 60 );
        this.DopColor = dopColor;
        this.SideLine = sideLine;
        this.Cabin = cabin;
        this.Motors = motors;
        this.countMotors = countMotors;
        if (AddClass == 1)
            this.iAdditional = new CicleMotors();
        iAdditional.setCountMotors( countMotors );
        if (AddClass == 2)
            this.iAdditional = new RectangleMotors();
        iAdditional.setCountMotors( countMotors );
        if (AddClass == 3)
            this.iAdditional = new TraungleMotors();
        iAdditional.setCountMotors( countMotors );
    }

    @Override
    public void DrawTransport(Graphics g) {
        super.DrawTransport( g );
        //корпус
        if (Cabin) {
            //кабина катера
            g.setColor( DopColor );
            g.fillOval( _startPosX + 15, _startPosY - 3, 70, 15 );
            g.fillOval( _startPosX + 25, _startPosY - 1, 40, 10 );
        }

        if (SideLine) {
            //линии
            g.setColor( java.awt.Color.white );
            g.drawLine( _startPosX, _startPosY + 20, _startPosX + 100, _startPosY + 20 );
            g.drawLine( _startPosX, _startPosY + 22, _startPosX + 100, _startPosY + 22 );
            g.drawLine( _startPosX + 20, _startPosY, _startPosX + 80, _startPosY );
        }
        if (Motors) {
            iAdditional.drawMotors( g, DopColor, _startPosX, _startPosY );
        }
    }

    public int getAddClass() {
        return addClass;
    }

    public int getCountMotors() {
        return countMotors;
    }

    public void SetDopColor(Color color) {
        DopColor = color;
    }

    public void SetMotors(IAdditional AddClass) {
        this.iAdditional = AddClass;
        iAdditional.setCountMotors( countMotors );
    }

    public void SetCountMotors(int countMotors) {
        this.countMotors = countMotors;
    }
}
