package com.KurmOS.core.lineWinStrategy;

import com.KurmOS.core.coreUtills.WinContainer;
import com.KurmOS.core.Field;

/**
 * Created by Lite on 02.11.2017.
 */
public class HorizontalCalc implements ICalculateWinStrategy {
    @Override
    public void calculateLine(int x, int y, WinContainer winContainer, Field field, int id, int xInARow) {

        for (int lineIterator = -xInARow + 1; lineIterator < xInARow; lineIterator++) {
                //System.out.println("field.getCell(x  + lineIterator, y)  " + field.getCell(x  + lineIterator, y) + "/  x= " + x  + "/ " + "/ lineiterator = " + lineIterator );
                if (field.getCell(x  + lineIterator, y) != null && field.getCell(x + lineIterator, y).equals(id)) {
                    winContainer.addWinPointHorizontal();
                    //System.out.println(winContainer.getHorizontalWin() + " horizontal");
                }
        }
    }
}
