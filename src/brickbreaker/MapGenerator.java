package brickbreaker;

import java.awt.*;

/**
 * @author AshwinAnand868
 * @version 1.0
 * @email anandashwin868@gmail.com
 * @since 2024-05-16
 */
public class MapGenerator {
    public int[][] bricks2DArray;
    public int brickHeight;
    public int brickWidth;

    public MapGenerator(int row, int col) {
        bricks2DArray = new int[row][col];
        for(int i = 0; i < bricks2DArray.length; ++i) {
            for(int j = 0; j < bricks2DArray[0].length; ++j) {
                bricks2DArray[i][j] = 1;
            }
        }

        brickHeight = 150/row;
        brickWidth = 700/col;
    }

    public void draw(Graphics2D g2D) {
        for(int i = 0; i < bricks2DArray.length; ++i) {
            for(int j = 0; j < bricks2DArray[0].length; ++j) {
                if(bricks2DArray[i][j] > 0) {
                    // drawing brick
                    g2D.setColor(Color.WHITE);
                    g2D.fillRect(j * brickWidth + 80, i * brickHeight + 70, brickWidth, brickHeight);

                    // setting border of that brick
                    g2D.setStroke(new BasicStroke(5));
                    g2D.setColor(Color.BLACK);
                    g2D.drawRect(j * brickWidth + 80, i * brickHeight + 70, brickWidth, brickHeight);
                }
            }
        } 
    }

    public void setBrickValue(int value, int row, int col) {
        bricks2DArray[row][col] = value;
    }
}
