package com.KurmOS.gameModules.playerStepManagment;

/**
 * Created by Lite on 29.10.2017.
 */
public interface CoreListener {
     void accept(Integer stepId, boolean winner);
     void paint (int x, int y, Integer showID, boolean winner);
     void initializeFirstStep();
     void reAccept(int id);
}
