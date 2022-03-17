package com.company;

/**
 * @author limanovsv
 */
public class Apple {
    public int positionX;
    public int positionY;

    public Apple(int x, int y) {
        positionX = x;
        positionY = y;
    }

    public void setRandomPosition() {
        positionX = Math.abs((int) (Math.random() * SnakeWindow.WIDTH - 1));
        positionY = Math.abs((int) (Math.random() * SnakeWindow.HEIGHT - 1));
    }
}
