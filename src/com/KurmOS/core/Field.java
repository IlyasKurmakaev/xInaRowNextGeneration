package com.KurmOS.core;

/**
 * Created by Lite on 17.10.2017.
 */
public class Field {
    private Integer[][] field;
    public int x;
    public int y;
    public Field(int x, int y) {
        this.x = x;
        this.y = y;
        field = new Integer[x][y];
//        field[0][0] = 0;  // DEBUG
//        field[1][1] = 0;  // DEBUG
//        field[2][2] = 0;  // DEBUG
    }

    public Integer getCell(int x, int y)
    {
        if (x < 0 | y < 0 | x>= this.x | y>= this.y)
        {
            return null;
        }
        else
        {
            return field[x][y];
        }
    }

    public void setCell(int x, int y, int id)
    {
        field[x][y] = id;
    }


    public void printArray()
    {
        for (int localY = 0; localY < y; localY++)
        {
            for (int localX = 0; localX < x; localX ++)
            {
                System.out.println(field[localX][localY]);
            }
        }
    }
}
