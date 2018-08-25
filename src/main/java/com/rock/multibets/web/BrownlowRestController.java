package com.rock.multibets.web;

import com.rock.multibets.Service.BrownlowGroupService;
import com.rock.multibets.Service.ComboGeneratorService;
import com.rock.multibets.domain.*;
import com.rock.multibets.util.CSVUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;


@RestController
@RequestMapping("/api/brownlowresult")
public class BrownlowRestController {
    List<BrownlowFormResult> resultList = new ArrayList<>();
    //ComboGeneratorService combos = new ComboGeneratorService();

    @Autowired
    ComboGeneratorService comboService;

    @Autowired
    BrownlowGroupService brownlowGroupService;

    @PostMapping(value="/save")
    @Async
    public void postMultiForm(@RequestBody List<BrownlowFormResult> multiresults,
                              @RequestHeader(value="Combo-Description") String desc) {
        resultList = new ArrayList<>();
        resultList.addAll(multiresults);

        // this is where we create the logic to create combos from the objects returned, and
        // convert them into database objects
        comboService.generateBrownlowCombos(resultList,desc);
    }

    @GetMapping(value="/{uuid}/download", produces = "text/csv")
    public void downloadResults(HttpServletResponse response, @PathVariable UUID uuid) {
        try {
            BrownlowGroup group = brownlowGroupService.getBrownlowGroup(uuid);
            List<BrownlowBet> bets = group.getBrownlowbets();
            response.setHeader("Content-Disposition", "attachment; filename="+uuid+".csv");
            CSVUtilities.writeBrownlowBetsToCSV(response.getWriter(), bets);
        } catch (IOException ex) {
            System.out.println("Error occurred downloading data");
        }
    }
}
