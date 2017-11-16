package com.KurmOS.core.coreUtills;

/**
 * Created by Lite on 03.11.2017.
 */
public class WinContainer {
    private int horizontalWin = 0;
    private int verticalWIn = 0;
    private int rightDownWin = 0;
    private int leftDownWin = 0;

    public void addWinPointHorizontal()
    {
        horizontalWin++;
    }
    public void addWinPointVertical()
    {
        verticalWIn++;
    }

    public void addWinPointRightDown()
    {
        rightDownWin++;
    }

    public void addWinPointLeftDown()
    {
        leftDownWin++;
    }




    public int getHorizontalWin() {
        return horizontalWin;
    }

    public int getVerticalWIn() {
        return verticalWIn;
    }

    public int getRightDownWin() {
        return rightDownWin;
    }

    public int getLeftDownWin() {
        return leftDownWin;
    }

    @Deprecated
    public void clearValues()
    {
        horizontalWin = 0;
        verticalWIn = 0;
        rightDownWin = 0;
        leftDownWin = 0;
    }

}
