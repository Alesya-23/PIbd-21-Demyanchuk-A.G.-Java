package Boat;

import java.awt.*;
import java.util.Comparator;

public class BoatComparer implements Comparator<Vehicle> {
    public int compare(Vehicle x, Vehicle y) {
        if (x.getClass().getName() != y.getClass().getName()) {
            return (x.getClass().getName()).compareTo( y.getClass().getName() );
        } else {
            if (x instanceof Boat && y instanceof Boat) {
                return ComparerBoat( (Boat) x, (Boat) y );
            } else if (x instanceof MotorBoat && y instanceof MotorBoat) {
                return ComparerMotorBoat( (MotorBoat) x, (MotorBoat) y );
            }
        }
        return 0;
    }

    private int ComparerBoat(Boat x, Boat y) {
        if (x.MaxSpeed != y.MaxSpeed) {
            return Integer.compare( x.MaxSpeed, y.MaxSpeed );
        }
        if (x.Weight != y.Weight) {
            return Float.compare( x.Weight, y.Weight );
        }
        if (x.MainColor != y.MainColor) {
            return compareColor( x.MainColor, y.MainColor );
        }
        return 0;
    }

    private int ComparerMotorBoat(MotorBoat x, MotorBoat y) {
        var res = ComparerBoat( x, y );
        if (res != 0) {
            return res;
        }
        if (x.DopColor != y.DopColor) {
            return compareColor( x.DopColor, y.DopColor );
        }
        if (x.SideLine != y.SideLine) {
            return Boolean.compare( x.SideLine, y.SideLine );
        }
        if (x.Cabin != y.Cabin) {
            return Boolean.compare( x.Cabin, y.Cabin );
        }
        if (x.Motors != y.Motors) {
            return Boolean.compare( x.Motors, y.Motors );
        }
        if (x.AddClass != y.AddClass) {
            return Integer.compare( x.AddClass, y.AddClass );
        }
        if (x.countMotors != y.countMotors) {
            return Integer.compare( x.countMotors, y.countMotors );
        }
        return 0;
    }

    private int compareColor(Color x, Color y) {
        if (x.getRed() != y.getRed()) {
            return (x.getRed() - y.getRed());
        }
        if (x.getGreen() != y.getGreen()) {
            return (x.getGreen() - y.getGreen());
        }
        if (x.getBlue() != y.getBlue()) {
            return (x.getBlue() - y.getBlue());
        }
        return 0;
    }
}
