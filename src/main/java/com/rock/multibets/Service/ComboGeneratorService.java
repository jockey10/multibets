package com.rock.multibets.Service;

import com.rock.multibets.domain.*;
import groovy.util.GroovyCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Service("comboService")
public class ComboGeneratorService {

    @Autowired
    private MultibetService multibetService;

    @Autowired
    private MultiGroupService multigroupService;

    @Autowired
    private BrownlowBetService brownlowBetService;

    @Autowired
    private BrownlowGroupService brownlowGroupService;

    private Random random;


    private int maxCombos;

    public void setMaxCombos(int maxCombos) {
        this.maxCombos = maxCombos;
    }

    public int getMaxCombos() {
        return maxCombos;
    }

    public MultiGroup generateCombos(List<MultiFormResult> formResults, String desc) {
        random = new Random();

        // this is where the magic happens!
        // we take the MultiFormResults from the form, and
        // create MultiGroup combos

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        MultiGroup multiGroup = new MultiGroup(timestamp,desc);
        multigroupService.addMultiGroup(multiGroup);

        //Create a list of lists
        ArrayList<List<String>> lists = new ArrayList<>();


        // firstly, we generate combo strings for each
        // game
        for(MultiFormResult m : formResults) {
            m.generateComboStrings();
            lists.add(m.getComboStrings());
        }

        // now, use Groovy to generate all combinations
        List combos = GroovyCollections.combinations((Iterable)lists);

        for(int i=0; i<combos.size(); i++) {
            Multibet multi = new Multibet(multiGroup);
            ArrayList<String> stringcombos = (ArrayList<String>)combos.get(i);
            for(String s : stringcombos) {
                multi.addBet(s);
            }

            //add to the database and multigroup
            multibetService.addMultibet(multi);
            multiGroup.addMultiBet(multi);
        }
        return multiGroup;
    }


    public BrownlowGroup generateBrownlowCombos(List<BrownlowFormResult> formResults, String desc) {
        random = new Random();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        BrownlowGroup brownlowGroup = new BrownlowGroup(timestamp,desc);
        brownlowGroupService.addBrownlowGroup(brownlowGroup);

        //Create a list of lists
        ArrayList<List<String>> lists = new ArrayList<>();

        for(BrownlowFormResult b : formResults) {
            b.generateComboStrings();
            lists.add(b.getComboStrings());
        }

        // now, use Groovy to generate all combinations
        List combos = GroovyCollections.combinations((Iterable)lists);

        for(int i=0; i<combos.size(); i++) {
            BrownlowBet bet = new BrownlowBet(brownlowGroup);
            ArrayList<String> stringcombos = (ArrayList<String>)combos.get(i);
            for(String s : stringcombos) {
                bet.addBet(s);
            }

            //add to the database and multigroup
            brownlowBetService.addBrownlowBet(bet);
            brownlowGroup.addBrownlowBet(bet);
        }
        return brownlowGroup;
    }
}
