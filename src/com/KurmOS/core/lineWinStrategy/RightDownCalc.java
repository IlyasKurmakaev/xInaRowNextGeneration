package com.KurmOS.core.lineWinStrategy;

import com.KurmOS.core.coreUtills.WinContainer;
import com.KurmOS.core.Field;

/**
 * Created by Lite on 03.11.2017.
 */
public class RightDownCalc implements ICalculateWinStrategy {
    @Override
    public void calculateLine(int x, int y, WinContainer winContainer, Field field, int id, int xInARow) {

        for (int lineIterator = -xInARow + 1; lineIterator < xInARow; lineIterator++) {

            if (field.getCell(x + lineIterator, y + lineIterator) != null && field.getCell(x + lineIterator, y + lineIterator).equals(id)) {
                winContainer.addWinPointRightDown();
                System.out.println(winContainer.getRightDownWin() + " rightDown");
            }
        }
    }
}
