package com.rock.multibets.web;

import com.rock.multibets.Service.ComboGeneratorService;
import com.rock.multibets.domain.BrownlowFormResult;
import com.rock.multibets.domain.BrownlowGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/brownlowresult")
public class BrownlowRestController {
    List<BrownlowFormResult> resultList = new ArrayList<BrownlowFormResult>();
    //ComboGeneratorService combos = new ComboGeneratorService();

    @Autowired
    ComboGeneratorService comboService;

    @PostMapping(value="/save")
    public String postMultiForm(@RequestBody List<BrownlowFormResult> multiresults,
                                @RequestHeader(value="Combo-Description") String desc) {
        resultList = new ArrayList<BrownlowFormResult>();
        resultList.addAll(multiresults);

        //get the description from the headers
        // this is where we create the logic to create combos from the objects returned, and
        // convert them into database objects
        BrownlowGroup newgroup = comboService.generateBrownlowCombos(resultList,desc);
        return "Post successfully!";
    }

    //TODO Create a request mapping to update the max combos
}
