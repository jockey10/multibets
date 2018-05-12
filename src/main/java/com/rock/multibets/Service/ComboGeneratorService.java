package com.rock.multibets.Service;

import com.rock.multibets.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


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


    private int maxCombos;

    public void setMaxCombos(int maxCombos) {
        this.maxCombos = maxCombos;
    }

    public int getMaxCombos() {
        return maxCombos;
    }

    public MultiGroup generateCombos(List<MultiFormResult> formResults, String desc) {
        //TODO make this method asynchronous

        // this is where the magic happens!
        // we take the MultiFormResults from the form, and
        // create MultiGroup combos

        // firstly, we generate combo strings for each
        // game
        for(MultiFormResult m : formResults) {
            m.generateComboStrings();
        }

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        MultiGroup multiGroup = new MultiGroup(timestamp,desc);
        multigroupService.addMultiGroup(multiGroup);

        // now that we have combo strings, we can pseudo-randomly
        // pick a string from each and create a Multibet object
        //TODO start with 100, and properly wrap this with the maximum
        // TODO number of bets, or the max set
        for(int i=0; i< 100; i++) {
            Multibet multi = new Multibet(multiGroup);
            for (MultiFormResult m : formResults) {
                multi.addBet(m.randomComboString());
            }
            multibetService.addMultibet(multi);
            multiGroup.addMultiBet(multi);
        }
        return multiGroup;
    }

    public BrownlowGroup generateBrownlowCombos(List<BrownlowFormResult> formResults, String desc) {
        //TODO make this method async
        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        BrownlowGroup brownlowGroup = new BrownlowGroup(timestamp,desc);
        brownlowGroupService.addBrownlowGroup(brownlowGroup);

        // firstly, we generate combo strings for each
        // game
        for(BrownlowFormResult b : formResults) {
            b.generateComboStrings();
        }

        for(int i=0; i<100; i++) {
            BrownlowBet bet = new BrownlowBet(brownlowGroup);
            for (BrownlowFormResult b : formResults) {
                bet.addBet(b.randomPlayerString());
            }
            brownlowBetService.addBrownlowBet(bet);
            brownlowGroup.addBrownlowBet(bet);
        }
        return brownlowGroup;
    }
}
