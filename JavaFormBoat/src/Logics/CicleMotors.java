package Logics;

import java.awt.Color;
import java.awt.Graphics;

import Enums.AdditionalEnumMotors;

public class CicleMotors implements IAdditional{
	private AdditionalEnumMotors addEnumMotors;

    public CicleMotors(int countMotors) {
        setCountMotors(countMotors);
    }

	@Override
	public void setCountMotors(int countMotors) {
    	addEnumMotors = AdditionalEnumMotors.definitionEnumMotors(countMotors);
	}
	
	public void drawMotors(Graphics g, Color dopColor, int xPos, int yPos) {
		switch (addEnumMotors) {
            case threeMotors: {
                g.setColor( dopColor );
                g.fillOval( xPos + 100,
                		yPos + 25, 10, 10 );
            }
            case twoMotors: {
                g.setColor( dopColor );
                g.fillOval( xPos + 100,
                		yPos + 16, 10, 10 );
            }
            case oneMotors: {
                g.setColor( dopColor );
                g.fillOval( xPos + 100,
                		yPos + 6, 10, 10 );
            }
        }
    }
}
