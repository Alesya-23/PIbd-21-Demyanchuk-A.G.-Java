import java.awt.Color;
import java.awt.Graphics;

public class AdditionalClass {
    private AdditionalEnumMotors addEnumMotors;
    public int countMotors;

    public AdditionalClass(int CountMotors) {
        this.countMotors = CountMotors;
    }

    public void definitionEnumMotors() {
        if (countMotors == 1) {
            addEnumMotors = addEnumMotors.oneMotors;
        }
        if (countMotors == 2) {
            addEnumMotors = addEnumMotors.twoMotors;
        }
        if (countMotors == 3) {
            addEnumMotors = addEnumMotors.threeMotors;
        }
    }

    void drawMotors(Graphics g, Color dopColor) {
        definitionEnumMotors();
        switch (addEnumMotors) {
            case threeMotors: {
                g.setColor( dopColor );
                g.fillRect( Boat.get_startPosX() + 100,
                        Boat.get_startPosY() + 22, 5, 5 );
            }
            case twoMotors: {
                g.setColor( dopColor );
                g.fillRect( Boat.get_startPosX() + 100,
                        Boat.get_startPosY() + 16, 5, 5 );
            }
            case oneMotors: {
                g.setColor( dopColor );
                g.fillRect( Boat.get_startPosX() + 100,
                        Boat.get_startPosY() + 10, 5, 5 );
            }
        }
    }
}
