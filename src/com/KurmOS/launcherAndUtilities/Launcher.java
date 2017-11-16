package com.KurmOS.launcherAndUtilities;

import com.KurmOS.artificialIntelligence.ArtificialIntelligence;
import com.KurmOS.core.Core;
import com.KurmOS.core.Field;
import com.KurmOS.exceptions.EnumException;
import com.KurmOS.gameModules.playerStepManagment.PlayerStepManager;
import com.KurmOS.players.AbstractPlayer;
import com.KurmOS.players.ArtificialIntelligencePlayer;
import com.KurmOS.players.RemotePlayer;
import com.KurmOS.players.UserInterfacePlayer;
import com.KurmOS.gui.GameFrame;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class Launcher extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JSlider sliderWidth;
    private JSlider sliderHeight;
    private JSlider sliderMines;
    private JLabel labelWidth;
    private JLabel labelHeight;
    private JLabel labelMines;
    private JButton addUIPlayersButton;
    private JButton addAIPlayersButton;
    private JButton addRPlayersButton;
    private JList list1;
    private JList list2;
    private JList list3;
    private Map<Integer, AbstractPlayer> UIPlayerMap;
    private Map<Integer, AbstractPlayer> AIPlayerMap;
    private Map<Integer, AbstractPlayer> RPlayerMap;
    private Integer localID = 0;


    private enum InputValue {width, height, mines}

    public Launcher() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        UIPlayerMap = new HashMap<>();
        AIPlayerMap = new HashMap<>();
        RPlayerMap = new HashMap<>();
        try {
            labelWidth.setText(Integer.toString(returnInputValue(InputValue.width)));
            labelHeight.setText(Integer.toString(returnInputValue(InputValue.height)));
            labelMines.setText(Integer.toString(returnInputValue(InputValue.mines)));
        } catch (EnumException e) {
            e.printStackTrace();
        }
        list1.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list2.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list3.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });


        sliderWidth.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelWidth.setText(Integer.toString(sliderWidth.getValue()));
            }
        });

        sliderHeight.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelHeight.setText(Integer.toString(sliderHeight.getValue()));
            }
        });


        sliderMines.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int biggestSide = 0;
                try {
                    if (returnInputValue(InputValue.width) > returnInputValue(InputValue.height)) {
                        biggestSide = returnInputValue(InputValue.width);
                    } else {
                        biggestSide = returnInputValue(InputValue.height);
                    }
                    if (returnInputValue(InputValue.mines) > biggestSide) {
                        sliderMines.setValue(biggestSide);
                        labelMines.setText(Integer.toString(returnInputValue(InputValue.mines)));
                    } else {
                        labelMines.setText(Integer.toString(returnInputValue(InputValue.mines)));
                    }
                } catch (EnumException e1) {
                    e1.printStackTrace();
                }
            }
        });

        addUIPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                UIPlayerMap.put(idManager(), new UserInterfacePlayer());
                list1.setModel(playerListToListModel(UIPlayerMap));
                pack();
            }
        });

        addAIPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                AIPlayerMap.put(idManager(), new ArtificialIntelligencePlayer());
                list2.setModel(playerListToListModel(AIPlayerMap));
                pack();
            }
        });

        addRPlayersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                RPlayerMap.put(idManager(), new RemotePlayer());
                list3.setModel(playerListToListModel(RPlayerMap));
                pack();
            }
        });


        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private Map<Integer, AbstractPlayer> mergerMaps(Map<Integer, AbstractPlayer> first, Map<Integer, AbstractPlayer> second, Map<Integer, AbstractPlayer> third) {
        Map<Integer, AbstractPlayer> fullMap = new HashMap<>();
        fullMap.putAll(first);
        fullMap.putAll(second);
        fullMap.putAll(third);
        return fullMap;
    }

    private void createrOfPlayerManagerModules()
    {
        Map<Integer, AbstractPlayer> UIPlayerMap;
        Map<Integer, AbstractPlayer> AIPlayerMap;
        Map<Integer, AbstractPlayer> RPlayerMap;
    }

    private void onOK() {
        Map<Integer, AbstractPlayer> fullMap = mergerMaps(UIPlayerMap, AIPlayerMap, RPlayerMap);
        Core core;
        GameFrame gameFrame;
        ArtificialIntelligence artificialIntelligence;
        try {
            core = new Core(new Field(returnInputValue(InputValue.width), returnInputValue(InputValue.height)), fullMap, returnInputValue(InputValue.mines));
            gameFrame = new GameFrame(returnInputValue(InputValue.width), returnInputValue(InputValue.height), core);
            artificialIntelligence = new ArtificialIntelligence(returnInputValue(InputValue.width), returnInputValue(InputValue.height), core);
            dispose();
            gameFrame.toFront();
            if (!UIPlayerMap.isEmpty())
            {
                core.addCoreListener(new PlayerStepManager(UIPlayerMap, gameFrame));
            }
            if (!AIPlayerMap.isEmpty())
            {
                core.addCoreListener(new PlayerStepManager(AIPlayerMap, artificialIntelligence));
            }
            core.initializeFirstStep();
        } catch (EnumException e) {
            e.printStackTrace();
        }




    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    private int returnInputValue(InputValue inputValue) throws EnumException {
        if (inputValue == InputValue.width) {
            return sliderWidth.getValue();
        }
        if (inputValue == InputValue.height) {
            return sliderHeight.getValue();
        }
        if (inputValue == InputValue.mines) {
            return sliderMines.getValue();
        } else {
            //TODO кинуть нормальное исключение
            throw new EnumException("Non exist enum");
        }
    }

    private Integer idManager() {
        Integer returnValue = localID;
        localID++;
        return returnValue;
    }

    private DefaultListModel playerListToListModel(Map<Integer, AbstractPlayer> map) {
        DefaultListModel listModel = new DefaultListModel();
        if (!map.isEmpty()) {
            for (Map.Entry<Integer, AbstractPlayer> entry : map.entrySet()) {
                listModel.addElement(entry.getKey() + " Player ");
            }
            return listModel;
        }
        return listModel;
    }


    public static void main(String[] args) {
        Launcher dialog = new Launcher();
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }
}
