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

    private int panelWidth = 880;
    private int panelHeight = 592;

    private MapGenerator mapGenerator;

    public GamePlay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        mapGenerator = new MapGenerator(3, 9);
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
        g.fillRect(0, 0, 880, 8);
        // right border
        g.fillRect(880, 0, 30, 592);

        // creating the scores
        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("" + score, 820, 50);

        // the rod/paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, playerY, 100, 8);

        // the ball
        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        mapGenerator.draw((Graphics2D) g);

        if(totalBricks <= 0) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("You Won!", 370, 300);

            g.setColor(Color.RED);
            g.drawString("Press Enter to restart.", 340, 350);
        }

        if(ballposY > 560) {
            play = false;
            ballXDir = 0;
            ballYDir = 0;

            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 25));
            g.drawString("Game Over, Scores: " + score, 340, 300);

            g.setColor(Color.RED);
            g.drawString("Press Enter to restart.", 340, 350);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();

        if(play) {
            if(new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, playerY, 100, 8))) {
                ballYDir = -ballYDir;
            }

            A: for(int i = 0; i < mapGenerator.bricks2DArray.length; ++i) {
                for(int j = 0; j < mapGenerator.bricks2DArray[ 0].length; ++j) {
                    if(mapGenerator.bricks2DArray[i][j] > 0) {
                        int brickX = mapGenerator.brickWidth * j + 80;
                        int brickY = mapGenerator.brickHeight * i + 70;

                        Rectangle brickRect = new Rectangle(brickX, brickY, mapGenerator.brickWidth, mapGenerator.brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

                        if(ballRect.intersects(brickRect)) {
                            mapGenerator.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;

                            if(ballposX +19 <=brickRect.x || ballposX +1 >=brickRect.x +brickRect.width){
                                ballXDir=-ballXDir;
                            }
                            else{
                                ballYDir=-ballYDir;
                            }

                            break A;
                        }

                    }
                }
            }
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
                ballXDir = -ballXDir;
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
        } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if(!play) {
                setDefaults();
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

    private void setDefaults() {
        play = true;
        ballposX = 150;
        ballposY = 400;
        ballXDir = -1;
        ballYDir = -2;
        playerX = 310;
        score = 0;
        totalBricks = 21;
        mapGenerator = new MapGenerator(3, 9);

        repaint();
    }
}
