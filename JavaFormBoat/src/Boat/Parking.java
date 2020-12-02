package Boat;

import java.awt.Color;
import java.awt.Graphics;

public class Parking<T extends ITransportBoat> {

    private T ITransportBoat;
    /// <summary>
    /// Массив объектов, которые храним
    /// </summary>
    private T[] _places;
    /// <summary>
    /// Ширина окна отрисовки
    /// </summary>
    private int pictureWidth;
    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    private int pictureHeight;
    /// <summary>
    /// Размер парковочного места (ширина)
    /// </summary>
    private int _placeSizeWidth = 210;
    /// <summary>
    /// Размер парковочного места (высота)
    /// </summary>
    private int _placeSizeHeight = 80;

    /// <summary>
    /// Конструктор
    /// </summary>
    /// <param name="picWidth">Рамзер парковки - ширина</param>
    /// <param name="picHeight">Рамзер парковки - высота</param>
    @SuppressWarnings("unchecked")
    public Parking(int picWidth, int picHeight) {
        int width = picWidth / _placeSizeWidth;
        int height = picHeight / _placeSizeHeight;
        _places = (T[]) new ITransportBoat[width * height];
        pictureWidth = picWidth;
        pictureHeight = picHeight;
    }

    /// <summary>
    /// Перегрузка оператора сложения
    /// Логика действия: на парковку добавляется автомобиль
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="boat">Добавляемый автомобиль</param>
    /// <returns></returns>
    public boolean Plus(Parking<T> p, T boat) {
        for (int i = 0; i < p._places.length; i++) {
            if (p._places[i] == null) {
                int margin = 10;
                int x = 0;
                int y = 0;
                int placesWidth = p.pictureWidth / p._placeSizeWidth;
                p._places[i] = boat;
                boat.SetPosition( x + 4 * margin + (p._placeSizeWidth + margin) * (i % placesWidth),
                        y + margin + p._placeSizeHeight * (i / placesWidth), p.pictureWidth, p.pictureHeight );
                return true;
            }
        }
        return false;
    }

    /// <summary>
    /// Перегрузка оператора вычитания
    /// Логика действия: с парковки забираем автомобиль
    /// </summary>
    /// <param name="p">Парковка</param>
    /// <param name="index">Индекс места, с которого пытаемся извлечь
    ///объект</param>
    /// <returns></returns>
    public T Minus(Parking<T> p, int index) {
        if (index >= p._places.length || index < 0) {
            return null;
        }
        T boat = p._places[index];
        p._places[index] = null;
        return boat;
    }

    /// <summary>
    /// Метод отрисовки парковки
    /// </summary>
    /// <param name="g"></param>
    public void Draw(Graphics g) {
        DrawMarking( g );
        for (int i = 0; i < _places.length; i++) {
            _places[i].DrawTransport( g );
        }
    }

    /// <summary>
    /// Метод отрисовки разметки парковочных мест
    /// </summary>
    /// <param name="g"></param>
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
