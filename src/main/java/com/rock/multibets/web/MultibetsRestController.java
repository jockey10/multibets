package com.rock.multibets.web;

import com.rock.multibets.Service.ComboGeneratorService;
import com.rock.multibets.domain.MultiFormResult;
import com.rock.multibets.domain.MultiGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;


@RestController
@RequestMapping("/api/multiformresult")
public class MultibetsRestController {
    List<MultiFormResult> resultList = new ArrayList<MultiFormResult>();
    //ComboGeneratorService combos = new ComboGeneratorService();

    @Autowired
    ComboGeneratorService comboService;

    @PostMapping(value="/save")
    public String postMultiForm(@RequestBody List<MultiFormResult> multiresults,
                                @RequestHeader(value="Combo-Description") String desc) {
        resultList = new ArrayList<MultiFormResult>();
        resultList.addAll(multiresults);

        //get the description from the headers
        // this is where we create the logic to create combos from the objects returned, and
        // convert them into database objects
        MultiGroup newgroup = comboService.generateCombos(resultList,desc);
        return "Post successfully!";
    }

    //TODO Create a request mapping to update the max combos
}
