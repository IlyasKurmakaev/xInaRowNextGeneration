package com.KurmOS.core;

import com.KurmOS.core.coreUtills.NextId;
import com.KurmOS.core.coreUtills.WinContainer;
import com.KurmOS.core.lineWinStrategy.*;
import com.KurmOS.gameModules.playerStepManagment.CoreListener;
import com.KurmOS.players.AbstractPlayer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Lite on 17.10.2017.
 */
public class Core {
    private Field field;
    private Map<Integer, AbstractPlayer> fullPlayerMap;
    private int xInARow;
    private Integer id = 0;
    private ArrayList<CoreListener> listManagersPlayers = new ArrayList();

    public Core(Field field, Map<Integer, AbstractPlayer> fullPlayerMap, int xInARow) {
        this.field = field;
        this.fullPlayerMap = fullPlayerMap;
        this.xInARow = xInARow;
    }

    public void acceptStep(int x, int y) {
        if (field.getCell(x,y) == null)
        {
            System.out.println("core accept the step x:" + x + "  y:" + y);
            field.setCell(x,y, id);
            WinContainer winContainer = new WinContainer();
            FindWinLine findWinLine = new FindWinLine(x, y, winContainer, field, id, xInARow);
            findWinLine.setCalculateWinStrategy(new HorizontalCalc());
            findWinLine.returnWinPoints();
            findWinLine.setCalculateWinStrategy(new VercticalCalc());
            findWinLine.returnWinPoints();
            findWinLine.setCalculateWinStrategy(new RightDownCalc());
            findWinLine.returnWinPoints();
            findWinLine.setCalculateWinStrategy(new LeftDownCalc());
            findWinLine.returnWinPoints();
            System.out.println(findWinLine.getWinner());
            System.out.println("id accepted " + id + "/ id for new step " + NextId.nextIdPermutable(id, fullPlayerMap));
            for (CoreListener coreListener : listManagersPlayers)
            {
                //coreListener.accept(x, y, id, NextId.nextIdPermutable(id, fullPlayerMap), findWinLine.getWinner());
                coreListener.paint(x, y, id, findWinLine.getWinner());
            }

            id = NextId.nextIdPermutable(id, fullPlayerMap);

            for (CoreListener coreListener : listManagersPlayers)
            {
                //coreListener.accept(x, y, id, NextId.nextIdPermutable(id, fullPlayerMap), findWinLine.getWinner());
                coreListener.accept(id, findWinLine.getWinner());
            }

            field.printArray();
        }
        else
        {
            for (CoreListener coreListener : listManagersPlayers)
            {
                coreListener.reAccept(id);
            }
        }
    }


    public void initializeFirstStep()
    {
        for (CoreListener coreListener : listManagersPlayers)
        {
            coreListener.initializeFirstStep();
        }
    }
    public void addCoreListener(CoreListener coreListener)
    {
        listManagersPlayers.add(coreListener);
    }
}