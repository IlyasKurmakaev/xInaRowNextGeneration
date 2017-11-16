package com.KurmOS.gui;

import com.KurmOS.core.Core;
import com.KurmOS.gameModules.playerStepManagment.IPaintMakeStep;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Lite on 27.10.2017.
 */
public class GameFrame extends JFrame implements IPaintMakeStep {
    private int xCells;
    private int yCells;
    private Integer[][] localField;
    private Core core;
    boolean lisenseAtStep = false;  // всё так и должно быть, модификатор неявно объявлен
    int id = 0;
    boolean winner = false;

    public GameFrame(int xCells, int yCells, Core core) {
        localField = new Integer[xCells][yCells];
        this.xCells = xCells;
        this.yCells = yCells;
        this.core = core;
        Painter painter = new Painter(this);
        add(painter);
        setVisible(true);
        setSize(xCells*100,yCells*100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//        localField[0][0] = 0;
//        localField[1][1] = 1;
//        localField[2][2] = 3;
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked");
                System.out.println("lisenseAtStep " + lisenseAtStep);
                if (lisenseAtStep) {
                    lisenseAtStep = false;
                    painter.cellSize(); // дабы гарантированно заполнить переменную с дабл
                    int x = (int)(e.getX() / painter.doubleCellSize);
                    int y = (int)( e.getY() / painter.doubleCellSize);
                    core.acceptStep(x, y);
                    repaint();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public int getXCells()
    {
        return xCells;
    }

    public int getYCells()
    {
        return yCells;
    }

    public Integer getLocalFieldCell(int x, int y) {
        return localField[x][y];
    }

    @Override
    public void initializeStep(int id) {
        //System.out.println("!!!!!!!!!!!!!!!");
        lisenseAtStep = true;
        this.id = id;
        repaint();

    }

    @Override
    public void paintStep(int id, int x, int y, boolean winner) {
        this.winner = winner;
        localField[x][y] = id;
        repaint();
    }
}
