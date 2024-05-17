package brickbreaker;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author AshwinAnand868
 * @version 1.0
 * @email anandashwin868@gmail.com
 * @since 2024-05-16
 */
public class GamePlay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;
    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int playerY = 550;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;

    private int panelWidth = 692;
    private int panelHeight = 592;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g) {
        // background of the panel
        g.setColor(Color.BLACK);
        g.fillRect(1, 1, panelWidth, panelHeight);

        // borders of the panel
        g.setColor(Color.YELLOW);
        // left border
        g.fillRect(0, 0, 5, 592);
        // top border
        g.fillRect(0, 0, 692, 8);
        // right border
        g.fillRect(680, 0, 30, 592);

        // the rod/paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, 100, 8);

        // the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play) {
            ballposX += ballXDir;
            ballposY += ballYDir;

            // going towards negative values in x axis
            if(ballposX < 0) {
                ballXDir = -ballXDir;
            }
            // going towards negative values in y axis
            if(ballposY < 0) {
                ballYDir = -ballYDir;
            }
            // going towards positive values in both y and x axis
            if(ballposX > 670) {
                ballXDir -= ballXDir;
            }

        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if(playerX >= 580) {
                playerX = 580;
            } else {
                moveRight();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (playerX <= 8) {
                playerX = 8;
            } else {
                moveLeft();
            }
        }
    }

    public void moveRight() {
        play = true;
        playerX += 20;
    }

    public void moveLeft() {
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
