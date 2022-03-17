package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @author limanovsv
 */
public class SnakeWindow extends JPanel implements ActionListener {

    public static int speed = 10;
    public static JFrame jFrame;
    public static final int SCALE = 32; //масштаб
    public static final int WIDTH = 16;
    public static final int HEIGHT = 16;
    Timer timer = new Timer(1000/speed, this);

    Snake snake = new Snake(5, 6, 5, 5);
    Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeWindow.WIDTH - 1)), Math.abs((int) (Math.random() * SnakeWindow.HEIGHT - 1)));

    public SnakeWindow(){
        timer.start();
        addKeyListener(new KeyBoard());
        setFocusable(true);
    }

    public void paint(Graphics graphics) {
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);

        for (int i = 0; i < WIDTH * SCALE; i += SCALE) {
            graphics.setColor(Color.WHITE);
            graphics.drawLine(i, 0, i, HEIGHT * SCALE);
        }

        for (int i = 0; i < HEIGHT * SCALE; i += SCALE) {
            graphics.setColor(Color.WHITE);
            graphics.drawLine(0, i, WIDTH * SCALE, i);
        }
        graphics.setColor(Color.RED);
        graphics.fillOval(apple.positionX * SCALE + 1, apple.positionY * SCALE - 1, SCALE - 1, SCALE - 1);

        for (int i = 0; i < snake.lenght; i++) {
            graphics.setColor(Color.GREEN);
            graphics.fillRect(snake.sX[i] * SCALE, snake.sY[i] * SCALE, SCALE, SCALE);
        }
    }


    public static void main(String[] args) {
        jFrame = new JFrame("SnakeApp");

        jFrame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\limanovsv\\IdeaProjects\\snake\\src\\snakej.png"));
        jFrame.add(new SnakeWindow());
        jFrame.setSize(WIDTH * SCALE + 16, HEIGHT * SCALE + 6);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        jFrame.setResizable(false);
        jFrame.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        snake.move();
        if ((snake.sX[0] == apple.positionX) && (snake.sY[0] == apple.positionY)) {
            apple.setRandomPosition();
            snake.lenght++;
        }
        repaint();
    }
    public class KeyBoard extends KeyAdapter {
        public void keyPressed(KeyEvent keyEvent) {
            int key = keyEvent.getKeyCode();

            if (key == KeyEvent.VK_UP) snake.direction=0;
            if (key == KeyEvent.VK_LEFT) snake.direction=3;
            if (key == KeyEvent.VK_DOWN) snake.direction=2;
            if (key == KeyEvent.VK_RIGHT) snake.direction=1;
        }
    }

}
