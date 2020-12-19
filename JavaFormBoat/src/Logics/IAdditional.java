package Logics;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public interface IAdditional extends Serializable {
    void setCountMotors(int countMotors);

    void drawMotors(Graphics g, Color dopColor, int x, int y);
}
