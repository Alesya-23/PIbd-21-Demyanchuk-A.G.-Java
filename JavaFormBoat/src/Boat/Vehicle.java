package Boat;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Direction;

public abstract class Vehicle implements ITransportBoat {
    //левая координата орисовки
    public static int _startPosX;

    /// Правая кооридната отрисовки лодки
    public static int _startPosY;

    /// Ширина окна отрисовки
    protected int _pictureWidth;

    /// Высота окна отрисовки
    protected int _pictureHeight;

    /// Максимальная скорость
    public int MaxSpeed;

    /// Вес лодки
    public float Weight;

    /// Основной цвет корпуса
    public Color MainColor;

    public void SetPosition(int x, int y, int width, int height) {
        _startPosX = x;
        _startPosY = y;
        _pictureWidth = width;
        _pictureHeight = height;
    }

    public abstract void DrawTransport(Graphics g);

    public abstract void MoveTransport(Direction direction);
}
