package Boat;

import java.awt.Graphics;

import Enums.Direction;

public interface ITransportBoat {
    /// Установка позиции
    public void SetPosition(int x, int y, int width, int height);

    /// Изменение направления пермещения
    void MoveTransport(Direction direction);

    /// Отрисовка
    public void DrawTransport(Graphics g);
}
