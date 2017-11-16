package com.KurmOS.core.lineWinStrategy;

import com.KurmOS.core.coreUtills.WinContainer;
import com.KurmOS.core.Field;

/**
 * Created by Lite on 02.11.2017.
 */
public class FindWinLine {
    private ICalculateWinStrategy iCalculateWinStrategy;
    private int x;
    private int y;
    private WinContainer winContainer;
    private Field field;
    private int id;
    private int xInARow;

    public FindWinLine(int x, int y, WinContainer winContainer, Field field, int id, int xInARow) {
        this.x = x;
        this.y = y;
        this.winContainer = winContainer;
        this.field = field;
        this.id = id;
        this.xInARow = xInARow;
    }

    public void setCalculateWinStrategy(ICalculateWinStrategy strategy)
    {
        iCalculateWinStrategy = strategy;
    }

    public void returnWinPoints()
    {
        iCalculateWinStrategy.calculateLine(x, y, winContainer, field, id, xInARow);
    }

    public boolean getWinner() {

        return winContainer.getHorizontalWin() >= xInARow || winContainer.getVerticalWIn() >= xInARow || winContainer.getRightDownWin() >= xInARow || winContainer.getLeftDownWin() >= xInARow;
    }
}
