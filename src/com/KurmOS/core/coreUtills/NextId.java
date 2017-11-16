package com.KurmOS.core.coreUtills;

import com.KurmOS.players.AbstractPlayer;

import java.util.Map;

/**
 * Created by Lite on 03.11.2017.
 */
public class NextId {
    public static Integer nextIdPermutable(Integer id, Map<Integer, AbstractPlayer> fullPlayerMap) {
        if (fullPlayerMap.size() <= id + 1) {
            id = 0;
            return id;
        } else {
            return id + 1;
        }
    }

    public static Integer nextIdUnPermutable(Integer id, Map<Integer, AbstractPlayer> fullPlayerMap) {
        if (fullPlayerMap.size() <= id + 1) {
            id = 0;
            return id;
        } else {
            return id + 1;
        }
    }
}