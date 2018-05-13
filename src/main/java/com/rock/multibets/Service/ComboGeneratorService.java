package com.rock.multibets.Service;

import com.rock.multibets.domain.*;
import groovy.util.GroovyCollections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;


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
        int maxsize=1;
        for(MultiFormResult m : formResults) {
            m.generateComboStrings();
            lists.add(m.getComboStrings());
            maxsize *= m.getComboSize();
        }

        //test if the size is less than 200, otherwise use that
        maxsize = maxsize < 200 ? maxsize : 200;

        // now, use Groovy to generate all combinations
        List combos = GroovyCollections.combinations((Iterable)lists);

        for(int i=0; i<maxsize; i++) {
            Multibet multi = new Multibet(multiGroup);
            int idx = random.nextInt(combos.size());
            ArrayList<String> randomcombos = (ArrayList<String>)combos.get(idx);
            for(String s : randomcombos) {
                multi.addBet(s);
            }
            //ensure we can't select this combo again
            combos.remove(idx);

            //add to the database and multigroup
            multibetService.addMultibet(multi);
            multiGroup.addMultiBet(multi);
        }
        return multiGroup;
    }

    @Async
    public BrownlowGroup generateBrownlowCombos(List<BrownlowFormResult> formResults, String desc) {
        random = new Random();

        java.sql.Timestamp timestamp = new java.sql.Timestamp(System.currentTimeMillis());
        BrownlowGroup brownlowGroup = new BrownlowGroup(timestamp,desc);
        brownlowGroupService.addBrownlowGroup(brownlowGroup);

        //Create a list of lists
        ArrayList<List<String>> lists = new ArrayList<>();

        // firstly, we generate combo strings for each
        // game
        int maxsize = 1;
        for(BrownlowFormResult b : formResults) {
            b.generateComboStrings();
            lists.add(b.getComboStrings());
            maxsize *= b.getComboSize();
        }

        //test if the size is less than 200, otherwise use that
        maxsize = maxsize < 200 ? maxsize : 200;

        // now, use Groovy to generate all combinations
        List combos = GroovyCollections.combinations((Iterable)lists);
        for(int i=0; i<maxsize; i++) {
            BrownlowBet bet = new BrownlowBet(brownlowGroup);
            int idx = random.nextInt(combos.size());
            ArrayList<String> randomcombos = (ArrayList<String>)combos.get(idx);
            for(String s : randomcombos) {
                bet.addBet(s);
            }
            //ensure we can't select this combo again
            combos.remove(idx);

            //add to the database and multigroup
            brownlowBetService.addBrownlowBet(bet);
            brownlowGroup.addBrownlowBet(bet);
        }
        return brownlowGroup;
    }
}
