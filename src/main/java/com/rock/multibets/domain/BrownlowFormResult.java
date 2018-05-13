package com.rock.multibets.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class BrownlowFormResult {
    private String player1;
    private String player2;
    private String player3;

    private List<String> comboStrings;

    private Random random;

    public BrownlowFormResult() {
        random = new Random();
    }

    public BrownlowFormResult(String player1, String player2, String player3) {
        this.player1 = player1;
        this.player2 = player2;
        this.player3 = player3;

        random = new Random();
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public String getPlayer3() {
        return player3;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public void setPlayer3(String player3) {
        this.player3 = player3;
    }

    public void generateComboStrings() {
        comboStrings = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.player1))
            comboStrings.add(this.player1 + "\n");
        if (StringUtils.isNotEmpty(this.player2))
            comboStrings.add(this.player2 + "\n");
        if (StringUtils.isNotEmpty(this.player3))
            comboStrings.add(this.player3 + "\n");
    }

    public int getComboSize() {
        return comboStrings.size();
    }

    public List<String> getComboStrings() {
        return comboStrings;
    }

}
