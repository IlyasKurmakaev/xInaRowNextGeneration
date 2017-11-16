package com.KurmOS.gui;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lite on 04.10.2017.
 */
public class Painter extends JComponent{
    Map<Integer, Image> imageMap;
    private GameFrame gameFrame;
    private final Integer CELL_ID = 9999; // вроде индусство а вроде и нет
    private final Integer X_ID = 0;
    private final Integer O_ID = 1;
    private final Integer Z_ID = 2;
    private final Integer W_ID = 3;
    public Painter(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
        imageMap = new HashMap<>();
        putInImageMap(CELL_ID, new File("resources/cell.png"));
        putInImageMap(X_ID, new File("resources/xSide.png"));
        putInImageMap(O_ID, new File("resources/oSide.png"));
        putInImageMap(Z_ID, new File("resources/zSide.png"));
        putInImageMap(W_ID, new File("resources/wSide.png"));

    }


    @Override
    protected void paintComponent(Graphics g) {
        for (int x = 0; x < gameFrame.getXCells(); x++)
        {
            for (int y = 0; y < gameFrame.getYCells(); y++)
            {
                g.drawImage(imageMap.get(CELL_ID), x*cellSize(), y*cellSize(), cellSize(), cellSize(), this);   // рисование клетки

                if (isNotNull(gameFrame.getLocalFieldCell(x,y)))
                {
                    g.drawImage(imageMap.get(gameFrame.getLocalFieldCell(x,y)), x*cellSize(), y*cellSize(), cellSize(), cellSize(), this);
                }
            }
        }



        if (gameFrame.lisenseAtStep)
        {
            g.setFont(new Font("cat" , Font.ITALIC,cellSize()/5));
            g.drawString(Integer.toString(gameFrame.id), cellSize() / 20, cellSize() / 5);
        }

        if (gameFrame.winner == true)
        {
            g.drawString("we have a winner", cellSize() / 20, cellSize() / 5);
        }
    }

    private boolean isNull(Integer integer)
    {
        return integer == null;
    }

    private boolean isNotNull(Integer integer)
    {
        return integer != null;
    }

    private void putInImageMap(Integer id , File file ) {
        try {
            imageMap.put(id, ImageIO.read(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public double doubleCellSize;


    public int cellSize()
    {
        double horizontalCellSize;
        double verticalCellSize;
        horizontalCellSize = getWidth()/gameFrame.getXCells();
        verticalCellSize = getHeight()/gameFrame.getYCells();
        if (horizontalCellSize < verticalCellSize)
        {
            doubleCellSize = horizontalCellSize;
            return (int)horizontalCellSize;
        }
        else
        {
            doubleCellSize = verticalCellSize;
            return (int)verticalCellSize;
        }
    }


    @Deprecated
    private Image getFromImageMap(ImageNames imageNames) {
        return imageMap.get(imageNames);
    }

    @Deprecated
    private enum ImageNames {XImage, OImage, Cell}

}
