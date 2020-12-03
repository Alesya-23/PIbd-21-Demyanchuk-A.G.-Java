package Logics;

import java.awt.Color;
import java.awt.Graphics;

import Enums.AdditionalEnumMotors;

public class RectangleMotors implements IAdditional {
    private AdditionalEnumMotors addEnumMotors;
    public int countMotors;

    public RectangleMotors(int CountMotors) {
        setCountMotors( CountMotors );
    }

    @Override
    public void setCountMotors(int countMotors) {
        addEnumMotors = AdditionalEnumMotors.definitionEnumMotors( countMotors );
    }

    public void drawMotors(Graphics g, Color dopColor, int xPos, int yPos) {
        switch (addEnumMotors) {
            case threeMotors: {
                g.setColor( dopColor );
                g.fillRect( xPos + 100,
                        yPos + 22, 5, 5 );
            }
            case twoMotors: {
                g.setColor( dopColor );
                g.fillRect( xPos + 100,
                        yPos + 16, 5, 5 );
            }
            case oneMotors: {
                g.setColor( dopColor );
                g.fillRect( xPos + 100,
                        yPos + 10, 5, 5 );
            }
        }
    }
}
