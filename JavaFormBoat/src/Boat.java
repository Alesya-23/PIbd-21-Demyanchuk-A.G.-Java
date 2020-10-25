import java.awt.*;

class Boat {
    public AdditionalClass addClass;
    // Левая координата отрисовки лодки
    private static int _startPosX;
    /// Правая кооридната отрисовки лодки
    private static int _startPosY;
    /// Ширина окна отрисовки
    private int _pictureWidth;
    /// Высота окна отрисовки
    private int _pictureHeight;
    /// Ширина отрисовки лодки
    private final int boatWidth = 103;
    /// Высота отрисовки лодки
    private final int boatHeight = 42;
    /// Максимальная скорость
    public int MaxSpeed;
    /// Вес лодки
    public float Weight;
    /// Основной цвет корпуса
    public Color MainColor;
    ///Дополнительный цвет   (для полос)
    public Color DopColor;
    /// Признак наличия боковых линий
    public boolean SideLine;
    // наличие кабины
    public boolean Cabin;
    // наличие мотора
    public boolean Motors;

    public Boat(int maxSpeed, int weight, Color mainColor, Color dopColor, boolean sideLine,
                boolean cabin, boolean motors, int AddClass) {
        MaxSpeed = maxSpeed;
        Weight = weight;
        MainColor = mainColor;
        DopColor = dopColor;
        SideLine = sideLine;
        Cabin = cabin;
        Motors = motors;
        addClass = new AdditionalClass( AddClass );
    }

    public void SetPosition(int x, int y, int width, int height) {
        set_startPosX( x );
        set_startPosY( y );
        _pictureWidth = width;
        _pictureHeight = height;
    }

    ///Изменение направления перемещения
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо                 
            case Right:
                if (get_startPosX() + step < _pictureWidth - boatWidth) {
                    set_startPosX( (int) (get_startPosX() + step) );
                }
                break;
            //влево               
            case Left:
                if (get_startPosX() - step > 0) {
                    set_startPosX( (int) (get_startPosX() - step) );

                }
                break;
            //вверх              
            case Up:
                if (get_startPosY() - step > 0) {
                    set_startPosY( (int) (get_startPosY() - step) );
                }
                break;
            //вниз                 
            case Down:
                if (get_startPosY() + step < _pictureHeight - boatHeight) {
                    set_startPosY( (int) (get_startPosY() + step) );
                }
                break;
        }
    }

    /// Отрисовка лодки
    public void DrawTransport(Graphics g) {
        //корпус
        g.setColor( MainColor );
        g.fillOval( get_startPosX(), get_startPosY(), 100, 35 );
        g.drawLine( get_startPosX() + 20, get_startPosY(), get_startPosX() + 70, get_startPosY() );
        if (Cabin) {
            //кабина катера
            g.setColor( DopColor );
            g.fillOval( get_startPosX() + 15, get_startPosY() - 3, 70, 15 );
            g.fillOval( get_startPosX() + 25, get_startPosY() - 1, 40, 10 );
        }
        if (SideLine) {
            //линии
            g.setColor( java.awt.Color.white );
            g.drawLine( get_startPosX(), get_startPosY() + 20, get_startPosX() + 100, get_startPosY() + 20 );
            g.drawLine( get_startPosX(), get_startPosY() + 22, get_startPosX() + 100, get_startPosY() + 22 );
            g.drawLine( get_startPosX() + 20, get_startPosY(), get_startPosX() + 80, get_startPosY() );
        }
        if (Motors) {
            addClass.drawMotors( g, DopColor );
        }
    }

    public static int get_startPosX() {
        return _startPosX;
    }

    public void set_startPosX(int _startPosX) {
        Boat._startPosX = _startPosX;
    }

    public static int get_startPosY() {
        return _startPosY;
    }

    public void set_startPosY(int _startPosY) {
        Boat._startPosY = _startPosY;
    }
}
