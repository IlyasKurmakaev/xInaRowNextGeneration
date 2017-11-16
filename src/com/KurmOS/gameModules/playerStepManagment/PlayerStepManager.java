package com.KurmOS.gameModules.playerStepManagment;

import com.KurmOS.players.AbstractPlayer;

import java.util.Map;

/**
 * Created by Lite on 29.10.2017.
 */
public class PlayerStepManager implements CoreListener {
    private Map<Integer, AbstractPlayer> playersList;
    private IPaintMakeStep paintMakeStep;
    private int stepId;

    public PlayerStepManager(Map<Integer, AbstractPlayer> playersList, IPaintMakeStep paintMakeStep) {
        this.playersList = playersList;
        this.paintMakeStep = paintMakeStep;
    }

    @Override
    public void accept(Integer stepId, boolean winner) {
      //  paintMakeStep.paintStep(showID, x, y, winner);              // отрисовывает пришедший ход оппонента или себя и записывает его во внутренний массив
        for (Map.Entry<Integer, AbstractPlayer> entryIdPlayers : playersList.entrySet())  // сделать ход если игрок есть в спискке
        {
            if (entryIdPlayers.getKey().equals(stepId))
            {
                if (!winner)
                {
                    paintMakeStep.initializeStep(stepId);
                }
            }
        }

    }

    @Override
    public void paint(int x, int y, Integer showID, boolean winner) {
        paintMakeStep.paintStep(showID, x, y, winner);
    }

    @Override
    public void reAccept(int id) {
        for (Map.Entry<Integer, AbstractPlayer> entryIdPlayers : playersList.entrySet())  // сделать ход если игрок есть в спискке
        {
            System.out.println(playersList.entrySet());
            if (entryIdPlayers.getKey().equals(id))
            {
                paintMakeStep.initializeStep(id);
            }
        }
    }

    private static int ZERO_ID = 0;
    @Override
    public void initializeFirstStep() {

        for (Map.Entry<Integer, AbstractPlayer> entryIdPlayers : playersList.entrySet())
        {
            if (entryIdPlayers.getKey().equals(ZERO_ID))
            {
                paintMakeStep.initializeStep(ZERO_ID);      // сделать ход если есть в спискке ходов
            }
        }
    }

}
