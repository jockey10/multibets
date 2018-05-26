package com.rock.multibets.web;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.rock.multibets.Service.ComboGeneratorService;
import com.rock.multibets.Service.MultiGroupService;
import com.rock.multibets.domain.MultiFormResult;
import com.rock.multibets.domain.MultiGroup;
import com.rock.multibets.domain.Multibet;
import com.rock.multibets.util.CSVUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/multiformresult")
public class MultibetsRestController {
    List<MultiFormResult> resultList = new ArrayList<>();
    //ComboGeneratorService combos = new ComboGeneratorService();

    @Autowired
    ComboGeneratorService comboService;

    @Autowired
    MultiGroupService multigroupService;

    @PostMapping(value="/save")
    @Async
    public void postMultiForm(@RequestBody List<MultiFormResult> multiresults,
                                          @RequestHeader(value="Combo-Description") String desc) {
        resultList = new ArrayList<>();
        resultList.addAll(multiresults);

        //get the description from the headers
        // this is where we create the logic to create combos from the objects returned, and
        // convert them into database objects

        MultiGroup newgroup = comboService.generateCombos(resultList,desc);
    }

    @GetMapping(value="/{uuid}/download", produces = "text/csv")
    public void downloadResults(HttpServletResponse response, @PathVariable UUID uuid) {
        try {
            MultiGroup group = multigroupService.getMultiGroup(uuid);
            List<Multibet> bets = group.getMultibets();
            response.setHeader("Content-Disposition", "attachment; filename="+uuid+".csv");
            CSVUtilities.writeMultiBetsToCSV(response.getWriter(), bets);
        } catch (IOException ex) {
            System.out.println("Error occurred downloading data");
        }
    }
}
