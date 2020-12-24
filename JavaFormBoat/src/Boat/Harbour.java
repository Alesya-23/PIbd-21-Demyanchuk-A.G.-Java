package Boat;

import Logics.IAdditional;

import java.awt.Color;
import java.awt.Graphics;

public class Harbour<T extends ITransportBoat, D extends IAdditional> {
    /// Массив объектов, которые храним
    public ArrayList<T> _places;
    /// Ширина окна отрисовки
    private int pictureWidth;
    /// Высота окна отрисовки
    private int pictureHeight;
    /// Размер парковочного места (ширина)
    private int _placeSizeWidth = 210;
    /// Размер парковочного места (высота)
    private int _placeSizeHeight = 80;

    private int _maxCount;

    /// Конструктор
    public Harbour(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _maxCount = width * height;
        _places = new ArrayList<T>();
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    /// Перегрузка оператора сложения
    /// Логика действия: на гавань добавляется лодка
    public boolean add(T boat) {
        if (_places.size() >= _maxCount) {
            throw new HarbourOverflowException();
        }
        _places.add( boat );
        return true;
    }

    /// Перегрузка оператора вычитания
    /// Логика действия: с гавани забираем лодку
    public T remove(int index) {
        if (index < 0 || index >= _places.size()) {
            throw new HarbourNotFoundExeption( index );
        }
        T boat = (T) _places.get( index );
        _places.remove( index );
        return boat;
    }

    public T get(int index) {
        if (index >= _places.size() || index < 0) {
            return null;
        }
        return (T) _places.get( index );
    }

    public boolean equal(int numBoat) { // ==
        int numCorrectBoat = 0;
        for (int i = 0; i < _places.size(); i++) {
            if (_places.get( i ) != null) {
                numCorrectBoat++;
            }
        }
        return numBoat == numCorrectBoat;
    }

    public boolean nonEqual(int numBus) { // !=
        return !equal( numBus );
    }

    /// Метод отрисовки гавани
    public void Draw(Graphics g) {
        DrawMarking( g );
        for (int i = 0; i < _places.size(); i++) {
            if (_places.get( i ) != null) {
                _places.get( i ).SetPosition( 35 + i / 4 * _placeSizeWidth + 5, i % 4 *
                        _placeSizeHeight + 10, pictureWidth, pictureHeight );
                _places.get( i ).DrawTransport( g );
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

    public T GetNext(int index) {
        if (index < 0 || index >= _places.size()) {
            return null;
        }
        return _places.get( index );
    }

    public void clear() {
        _places.clear();
    }
}
