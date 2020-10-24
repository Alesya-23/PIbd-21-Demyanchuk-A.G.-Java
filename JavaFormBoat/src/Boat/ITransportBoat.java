package Boat;

import java.awt.Graphics;

import Enums.Direction;

public interface ITransportBoat {
    /// Установка позиции
    void SetPosition(int x, int y, int width, int height);

    /// Изменение направления пермещения
    void MoveTransport(Direction direction);

    /// Отрисовка
    void DrawTransport(Graphics g);
}
