package Boat;

import java.awt.Graphics;

import Enums.Direction;

import java.awt.Color;

public class Boat extends Vehicle {
    /// Ширина отрисовки лодки
    protected int boatWidth = 103;
    /// Высота отрисовки лодки
    protected int boatHeight = 42;
    /// Разделитель для записи информации по объекту в файл
    protected char separator = ';';

    public Boat() {
    }

    public Boat(int maxSpeed, float weight, Color mainColor) {
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
    }

    /// Конструктор для загрузки с файла
    public Boat(String info) {
        String[] strs = info.split( String.valueOf( separator ) );
        if (strs.length == 3) {
            MaxSpeed = Integer.parseInt( strs[0] );
            Weight = Float.parseFloat( strs[1] );
            MainColor = Color.decode( strs[2] );
        }
    }

    protected Boat(int maxSpeed, float weight, Color mainColor, int boatWidth, int
            boatHeight) {
        this.MaxSpeed = maxSpeed;
        this.Weight = weight;
        this.MainColor = mainColor;
        this.boatWidth = boatWidth;
        this.boatHeight = boatHeight;
    }

    @Override
    public void MoveTransport(Direction direction) {
        float step = MaxSpeed * 100 / Weight;
        switch (direction) {
            // вправо
            case Right:
                if (_startPosX + step < _pictureWidth - boatWidth) {
                    _startPosX = ((int) (_startPosX + step));
                }
                break;
            //влево
            case Left:
                if (_startPosX - step > 0) {
                    _startPosX = ((int) (_startPosX - step));
                }
                break;
            //вверх
            case Up:
                if (_startPosY - step > 0) {
                    _startPosY = ((int) (_startPosY - step));
                }
                break;
            //вниз
            case Down:
                if (_startPosY + step < _pictureHeight - boatHeight) {
                    _startPosY = ((int) (_startPosY + step));
                }
                break;
        }
    }

    /// Отрисовка лодки
    @Override
    public void DrawTransport(Graphics g) {
        //корпус
        g.setColor( MainColor );
        g.fillOval( _startPosX, _startPosY, 100, 35 );
        g.setColor( Color.WHITE );
        g.drawLine( _startPosX, _startPosY + 22, _startPosX + 100, _startPosY + 22 );
    }

    public String ToString() {
        return "" + MaxSpeed + separator + Weight + separator + MainColor.getRGB();
    }
}
