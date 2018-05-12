package com.rock.multibets.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sboulden on 5/5/18.
 */
@Entity
public class BrownlowGroup implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private static final long serialVersionUID = 6529685098266666690L;
    UUID uuid;
    @OneToMany(mappedBy="brownlowGroup",fetch=FetchType.EAGER,
            cascade = CascadeType.ALL)
    private List<BrownlowBet> brownlowbets;
    private Timestamp date;
    private String description;

    //JPA-required constructor
    protected BrownlowGroup() {}

    public BrownlowGroup(Timestamp date, String description) {
        this.brownlowbets = new LinkedList<>();
        this.uuid = UUID.randomUUID();
        this.date = date;
        this.description = description;
    }

    public String getDescription() { return this.description; }

    public String getUUID() {
        return this.uuid.toString();
    }

    public Timestamp getDate() {
        return this.date;
    }

    public List<BrownlowBet> getBrownlowbets() {
        return this.brownlowbets;
    }

    public void addBrownlowBet(BrownlowBet bet) {
        this.brownlowbets.add(bet);
    }

    public String toString() {
        String ret = "UUID: " + uuid + "\n";
        ret += "Displaying Brownlow bets: \n";
        for(BrownlowBet b : brownlowbets) {
            ret += b.toString() + "\n";
        }
        return ret;
    }

}
