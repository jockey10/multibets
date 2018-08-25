package com.rock.multibets.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Entity
public class MultiGroup implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private static final long serialVersionUID = 6529685098267757690L;
    UUID uuid;
    @OneToMany(mappedBy="multiGroup",fetch=FetchType.EAGER,
    cascade = CascadeType.ALL)
    private List<Multibet> multibets;
    private Timestamp date;
    private String description;

    //JPA-required constructor
    protected MultiGroup() {}

    public MultiGroup(Timestamp date, String description) {
        this.multibets = new LinkedList<>();
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

    public List<Multibet> getMultibets() {
        return this.multibets;
    }

    public Integer getNumBets() { return this.multibets.size(); }

    public void addMultiBet(Multibet multi) {
        this.multibets.add(multi);
    }

    public String toString() {
        String ret = "UUID: " + uuid + "\n";
        ret += "Displaying multibets: \n";
        for(Multibet m : multibets) {
            ret += m.toString() + "\n";
        }
        return ret;
    }

}
