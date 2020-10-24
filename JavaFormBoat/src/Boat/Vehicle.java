package Boat;

import java.awt.Color;
import java.awt.Graphics;

import Enums.Direction;

public abstract class Vehicle implements ITransportBoat {
    //левая координата орисовки
    public static int _startPosX;

    /// <summary>
    /// Правая кооридната отрисовки лодки
    /// </summary>
    public static int _startPosY;

    /// <summary>
    /// Ширина окна отрисовки         /// </summary>
    protected int _pictureWidth;

    /// <summary>
    /// Высота окна отрисовки
    /// </summary>
    protected int _pictureHeight;

    /// <summary>
    /// Максимальная скорость
    /// </summary>
    public int MaxSpeed;

    /// <summary>
    /// Вес лодки
    /// </summary>

    public float Weight;

    /// <summary>
    /// Основной цвет корпуса
    /// </summary>
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
