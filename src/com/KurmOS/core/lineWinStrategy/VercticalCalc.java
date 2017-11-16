package com.KurmOS.core.lineWinStrategy;

import com.KurmOS.core.coreUtills.WinContainer;
import com.KurmOS.core.Field;

/**
 * Created by Lite on 03.11.2017.
 */
public class VercticalCalc implements ICalculateWinStrategy {
    @Override
    public void calculateLine(int x, int y, WinContainer winContainer, Field field, int id, int xInARow) {

        for (int lineIterator = -xInARow + 1; lineIterator < xInARow; lineIterator++) {

            if (field.getCell(x, y + lineIterator) != null && field.getCell(x, y + lineIterator).equals(id)) {
                winContainer.addWinPointVertical();
                System.out.println(winContainer.getVerticalWIn() + " vertical");
            }
        }
    }
}
