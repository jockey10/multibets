package com.rock.multibets.web;

import com.rock.multibets.Service.ComboGeneratorService;
import com.rock.multibets.domain.MultiFormResult;
import com.rock.multibets.domain.MultiGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;


@RestController
@RequestMapping("/api/multiformresult")
public class MultibetsRestController {
    List<MultiFormResult> resultList = new ArrayList<>();
    //ComboGeneratorService combos = new ComboGeneratorService();

    @Autowired
    ComboGeneratorService comboService;

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

    //TODO Create a request mapping to update the max combos
}
