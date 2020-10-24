package Boat;

import java.awt.Color;
import java.awt.Graphics;

import Logics.CicleMotors;
import Logics.IAdditional;
import Logics.RectangleMotors;
import Logics.TraungleMotors;

public class MotorBoat extends Boat {
    public IAdditional iAdditional;

    public Color DopColor;
    /// <summary>
    /// Признак наличия боковых линий
    /// </summary>
    public boolean SideLine;

    // наличие кабины
    public boolean Cabin;

    // наличие мотора
    public boolean Motors;

    /// <summary>
    /// Конструктор

    public MotorBoat(int maxSpeed, float weight, Color mainColor, Color dopColor, boolean sideLine, boolean cabin,
                     boolean motors, int AddClass, int countMotors) {
        super( maxSpeed, weight, mainColor, 110, 60 );
        this.DopColor = dopColor;
        this.SideLine = sideLine;
        this.Cabin = cabin;
        this.Motors = motors;
        if (AddClass == 1)
            this.iAdditional = new CicleMotors( countMotors );
        if (AddClass == 2)
            this.iAdditional = new RectangleMotors( countMotors );
        if (AddClass == 3)
            this.iAdditional = new TraungleMotors( countMotors );
    }

    /// <summary>
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
}
