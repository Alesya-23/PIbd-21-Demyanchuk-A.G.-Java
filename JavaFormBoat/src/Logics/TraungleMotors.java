package Logics;

import java.awt.Color;
import java.awt.Graphics;

import Boat.Boat;
import Boat.MotorBoat;
import Enums.AdditionalEnumMotors;

public class TraungleMotors implements IAdditional {
    private AdditionalEnumMotors addEnumMotors;

    public TraungleMotors(int CountMotors) {
        setCountMotors( CountMotors );
    }

    @Override
    public void setCountMotors(int countMotors) {
        addEnumMotors = AdditionalEnumMotors.definitionEnumMotors( countMotors );
    }

    public void traungleMotors(Graphics g, int xPos, int yPos, int size) {
        g.drawLine( xPos, yPos, xPos + size, yPos - 2 * size );
        g.drawLine( xPos + size, yPos - 2 * size, xPos + 3 * size, yPos );
        g.drawLine( xPos + 3 * size, yPos, xPos, yPos );
        g.fillOval( xPos, yPos - size, 2 * size - size / 4, 2 * size - size / 4 );
    }

    @Override
    public void drawMotors(Graphics g, Color dopColor, int xPos, int yPos) {
        switch (addEnumMotors) {
            case threeMotors: {
                g.setColor( dopColor );
                traungleMotors( g, xPos + 100,
                        yPos + 22, 5 );
            }
            case twoMotors: {
                g.setColor( dopColor );
                traungleMotors( g, xPos + 100,
                        yPos + 16, 5 );
            }
            case oneMotors: {
                g.setColor( dopColor );
                traungleMotors( g, xPos + 100,
                        yPos + 10, 5 );
            }
        }
    }
}
