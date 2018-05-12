package com.rock.multibets.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.UUID;

/**
 * Created by sboulden on 5/4/18.
 */
@Entity
public class Multibet implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private static final long serialVersionUID = 6529685098268982690L;
    @ManyToOne(fetch = FetchType.EAGER)
    private MultiGroup multiGroup;
    private LinkedList<String> bets;

    //JPA required default constructor
    protected Multibet() {}

    public Multibet(MultiGroup multiGroup) {
        this.multiGroup = multiGroup;
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
}
