package com.rock.multibets.domain;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MultiFormResult {
    private String team1;
    private String team2;
    private String team1option1;
    private String team2option1;
    private String team1option2;
    private String team2option2;

    private List<String> comboStrings;

    private Random random;

    public MultiFormResult() {
        random = new Random();
    }

    public MultiFormResult(String team1, String team2,
                           String team1option1, String team2option1,
                           String team1option2, String team2option2) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1option1 = team1option1;
        this.team2option1 = team2option1;
        this.team1option2 = team1option2;
        this.team2option2 = team2option2;

        random = new Random();
    }

    public String getTeam1() {
        return this.team1;
    }

    public String getTeam2() {
        return this.team2;
    }

    public String getTeam1option1() {
        return this.team1option1;
    }

    public String getTeam1option2() {
        return this.team1option2;
    }

    public String getTeam2option1() {
        return this.team2option1;
    }

    public String getTeam2option2() {
        return this.team2option2;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public void setTeam1option1(String team1option1) {
        this.team1option1 = team1option1;
    }

    public void setTeam2option1(String team2option1) {
        this.team2option1 = team2option1;
    }

    public void setTeam1option2(String team1option2) {
        this.team1option2 = team1option2;
    }

    public void setTeam2option2(String team2option2) {
        this.team2option2 = team2option2;
    }

    public void generateComboStrings() {
        comboStrings = new ArrayList<>();
        if (StringUtils.isNotEmpty(this.team1option1))
             comboStrings.add(this.team1 + " " + team1option1 + "\n");
        if(StringUtils.isNotEmpty(this.team1option2))
            comboStrings.add(this.team1 + " " + team1option2 + "\n");
        if(StringUtils.isNotEmpty(this.team2option1))
            comboStrings.add(this.team2 + " " + team2option1 + "\n");
        if(StringUtils.isNotEmpty(this.team2option2))
            comboStrings.add(this.team2 + " " + team2option2 + "\n");
    }

    public int getComboSize() {
        return comboStrings.size();
    }

    public List<String> getComboStrings() {
        return comboStrings;
    }

    public String toString() {
        String ret = "" + this.team1 + " vs " + this.team2 + "\n";
        if (StringUtils.isNotEmpty(this.team1option1))
            ret += this.team1 + " " + team1option1 + "\n";
        if(StringUtils.isNotEmpty(this.team1option2))
            ret += this.team1 + " " + team1option2 + "\n";
        if(StringUtils.isNotEmpty(this.team2option1))
            ret += this.team2 + " " + team2option1 + "\n";
        if(StringUtils.isNotEmpty(this.team2option2))
            ret += this.team2 + " " + team2option2 + "\n";
        return ret;

    }
}
