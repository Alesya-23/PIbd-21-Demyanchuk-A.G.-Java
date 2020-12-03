package Boat;

import Logics.IAdditional;

import java.awt.Color;
import java.awt.Graphics;

public class Harbour<T extends ITransportBoat, D extends IAdditional> {
    /// Массив объектов, которые храним
    public Object[] _places;
    /// Ширина окна отрисовки
    private int pictureWidth;
    /// Высота окна отрисовки
    private int pictureHeight;
    /// Размер парковочного места (ширина)
    private int _placeSizeWidth = 210;
    /// Размер парковочного места (высота)
    private int _placeSizeHeight = 80;

    /// Конструктор
    @SuppressWarnings("unchecked")
    public Harbour(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = new Object[width * height];
        for (int i = 0; i < width * height; i++) {
            _places[i] = null;
        }
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    /// Перегрузка оператора сложения
    /// Логика действия: на гавань добавляется лодка
    public boolean add(T boat) {
        int margin = 10;
        int x = 0;
        int y = 0;
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] == null) {
                int placesWidth = pictureWidth / _placeSizeWidth;
                boat.SetPosition( x + 4 * margin + (_placeSizeWidth + margin) * (i % placesWidth),
                        y + margin + _placeSizeHeight * (i / placesWidth), pictureWidth, pictureHeight );
                _places[i] = boat;
                System.out.print( i + "\n" );
                return true;
            }
        }
        return false;
    }

    /// Перегрузка оператора вычитания
    /// Логика действия: с гавани забираем лодку
    public T remove(int index) {
        if (index >= _places.length || index < 0) {
            return null;
        }
        T boat = (T) _places[index];
        _places[index] = null;
        return boat;
    }

    public boolean equal(int numBoat) { // ==
        int numCorrectBoat = 0;
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] != null) {
                numCorrectBoat++;
            }
        }
        return numBoat == numCorrectBoat;
    }

    public boolean nonEqual(int numBus) { // !=
        return !equal( numBus );
    }

    /// Метод отрисовки парковки
    public void Draw(Graphics g) {
        DrawMarking( g );
        int margin = 10;
        int x = 0;
        int y = 0;
        int placesWidth = pictureWidth / _placeSizeWidth;
        for (int i = 0; i < _places.length; i++) {
            if (_places[i] != null) {
                T place = (T) _places[i];
                place.SetPosition( x + 4 * margin + (_placeSizeWidth + margin) * (i % placesWidth),
                        y + margin + _placeSizeHeight * (i / placesWidth), pictureWidth, pictureHeight );
                place.DrawTransport( g );
            }
        }
    }

    /// Метод отрисовки разметки парковочных мест
    public void DrawMarking(Graphics g) {
        int x = 0;
        int interval = 35;
        g.setColor( Color.BLACK );
        for (int i = 0; i < pictureWidth / _placeSizeWidth; i++) {
            for (int j = 0; j < pictureHeight / _placeSizeHeight + 1; ++j) {//линия рамзетки места
                g.drawLine( x + (_placeSizeWidth + interval) * i, j * _placeSizeHeight, x + _placeSizeWidth + (_placeSizeWidth + interval) * i, j * _placeSizeHeight );
            }
            g.drawLine( i * (_placeSizeWidth + interval), 0, i * (_placeSizeWidth + interval), (pictureHeight / _placeSizeHeight) * _placeSizeHeight );
        }
    }
}
