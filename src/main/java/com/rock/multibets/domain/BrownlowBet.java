package com.rock.multibets.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;


@Entity
public class BrownlowBet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private static final long serialVersionUID = 6529665197688982690L;
    @ManyToOne(fetch = FetchType.EAGER)
    private BrownlowGroup brownlowGroup;
    private LinkedList<String> bets;

    //JPA required default constructor
    protected BrownlowBet() {}

    public BrownlowBet(BrownlowGroup brownlowGroup) {
        this.brownlowGroup = brownlowGroup;
        bets = new LinkedList<>();
    }

    public void addBet(String bet) {
        bets.add(bet);
    }

    public String toString() {
        String ret = "";
        for(String s : bets) {
            ret += s;
        }
        return ret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LinkedList<String> getBets() {
        return bets;
    }

    public void setBets(LinkedList<String> bets) {
        this.bets = bets;
    }
}
