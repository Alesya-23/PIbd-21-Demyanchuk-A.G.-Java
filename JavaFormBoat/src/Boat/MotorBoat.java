package Boat;

import java.awt.Color;
import java.awt.Graphics;

import Logics.CicleMotors;
import Logics.IAdditional;
import Logics.RectangleMotors;
import Logics.TraungleMotors;

public class MotorBoat extends Boat implements Comparable<Boat> {

    LinkedList<Object> listMotorBoatProperties = new LinkedList<>();

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
        this.AddClass = AddClass;
        setiAdditional( AddClass );
        listMotorBoatProperties.add( MaxSpeed );
        listMotorBoatProperties.add( Weight );
        listMotorBoatProperties.add( MainColor );
        listMotorBoatProperties.add( boatWidth );
        listMotorBoatProperties.add( boatHeight );
        listMotorBoatProperties.add( DopColor );
        listMotorBoatProperties.add( SideLine );
        listMotorBoatProperties.add( Cabin );
        listMotorBoatProperties.add( Motors );
        listMotorBoatProperties.add( countMotors );
        listMotorBoatProperties.add( AddClass );
    }

    private void setiAdditional(int addClass) {
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

    /// Конструктор для загрузки с файла
    public MotorBoat(String info) {
        {
            String[] strs = info.split( String.valueOf( separator ) );
            if (strs.length == 9) {
                MaxSpeed = Integer.parseInt( strs[0] );
                Weight = Float.parseFloat( strs[1] );
                MainColor = Color.decode( strs[2] );
                DopColor = Color.decode( strs[3] );
                SideLine = Boolean.parseBoolean( strs[4] );
                Cabin = Boolean.parseBoolean( strs[5] );
                Motors = Boolean.parseBoolean( strs[6] );
                AddClass = Integer.parseInt( strs[7] );
                countMotors = Integer.parseInt( strs[8] );
                setiAdditional( AddClass );
                listMotorBoatProperties.add( MaxSpeed );
                listMotorBoatProperties.add( Weight );
                listMotorBoatProperties.add( MainColor );
                listMotorBoatProperties.add( boatWidth );
                listMotorBoatProperties.add( boatHeight );
                listMotorBoatProperties.add( DopColor );
                listMotorBoatProperties.add( SideLine );
                listMotorBoatProperties.add( Cabin );
                listMotorBoatProperties.add( Motors );
                listMotorBoatProperties.add( countMotors );
                listMotorBoatProperties.add( AddClass );
            }
        }
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

    public String ToString() {
        return super.ToString() + separator + DopColor.getRGB() + separator +
                SideLine + separator + Cabin + separator + Motors + separator + AddClass + separator + countMotors;
    }

    int compareColor(Color x, Color y) {
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

    @Override
    public int compareTo(Boat other) {
        if (other instanceof MotorBoat) {
            if (DopColor != ((MotorBoat) other).DopColor) {
                return compareColor( DopColor, ((MotorBoat) other).DopColor );
            }
            if ((SideLine) != ((MotorBoat) other).SideLine) {
                return Boolean.compare( SideLine, ((MotorBoat) other).SideLine );
            }
            if (Cabin != ((MotorBoat) other).Cabin) {
                return Boolean.compare( Cabin, ((MotorBoat) other).Cabin );
            }
            if (Motors != ((MotorBoat) other).Motors) {
                return Boolean.compare( Motors, ((MotorBoat) other).Motors );
            }
            if (countMotors != ((MotorBoat) other).countMotors) {
                return Integer.compare( countMotors, ((MotorBoat) other).countMotors );
            }
            if (AddClass != ((MotorBoat) other).AddClass) {
                return Integer.compare( AddClass, ((MotorBoat) other).AddClass );
            }
            return 0;
        }
        return super.compareTo( other );
    }

    public boolean equals(MotorBoat other) {
        if (other == null) {
            return false;
        }
        if (getClass().getName() != other.getClass().getName()) {
            return false;
        }
        if (DopColor != other.DopColor) {
            return false;
        }
        if (SideLine != other.SideLine) {
            return false;
        }
        if (Cabin != other.Cabin) {
            return false;
        }
        if (Motors != other.Motors) {
            return false;
        }
        if (AddClass != other.AddClass) {
            return false;
        }
        if (countMotors != other.countMotors) {
            return false;
        }
        return true;
    }

    /// <summary>
    /// Перегрузка метода от object
    /// </summary>
    /// <param name="obj"></param>
    /// <returns></returns>
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof MotorBoat)) {
            return false;
        } else {
            return equals( (MotorBoat) obj );
        }
    }

    public void printMotorBoatProperties() {
        for (Object ourMotorBoat : listMotorBoatProperties) {
            System.out.println( ourMotorBoat );
        }
    }
}
