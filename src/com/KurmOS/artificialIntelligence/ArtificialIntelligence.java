package com.KurmOS.artificialIntelligence;

import com.KurmOS.core.Core;
import com.KurmOS.gameModules.playerStepManagment.IPaintMakeStep;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Lite on 09.11.2017.
 */
public class ArtificialIntelligence implements IPaintMakeStep {
    public Integer[][] localField;
    public int x;
    public int y;
    private boolean winner = false;
    private Core core;

    public ArtificialIntelligence(int x, int y, Core core) {
        this.x = x;
        this.y = y;
        this.core = core;
        localField = new Integer[x][y];
    }

    @Override
    public void initializeStep(int id) {
        if (!winner) {
            makeRandomStep();
        }

    }

    @Override
    public void paintStep(int id, int x, int y, boolean winner) {
        localField[x][y] = id;
        this.winner = winner;
    }


    private void makeRandomStep()   // гамнокод, лишь бы работало
    {
        Point2d point2d;
        ArrayList listOfEmptyCells = new ArrayList();
        for (int localY = 0; localY < y; localY++)
        {
            for (int localX = 0; localX < x; localX++)
            {
                if (localField[localX][localY] == null)
                {
                    listOfEmptyCells.add(new Point2d(localX,localY));
                }
            }
        }
        Random random = new Random();
        point2d = (Point2d) listOfEmptyCells.get(random.nextInt(listOfEmptyCells.size()));
        core.acceptStep(point2d.getX(), point2d.getY());
    }
}
