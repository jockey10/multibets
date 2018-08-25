package com.rock.multibets.web;

import com.rock.multibets.Service.BrownlowGroupService;
import com.rock.multibets.Service.MultiGroupService;
import com.rock.multibets.domain.BrownlowBet;
import com.rock.multibets.domain.BrownlowGroup;
import com.rock.multibets.domain.MultiGroup;
import com.rock.multibets.domain.Multibet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Controller
public class BrownlowController {

    @Autowired
    BrownlowGroupService brownlowGroupService;

    @RequestMapping(value="/brownlow")
    public String brownlow() {
        return "brownlow";
    }

    @RequestMapping(value="/brownlowcombos")
    public String combos(Model model) {
        ArrayList<BrownlowGroup> combos = (ArrayList<BrownlowGroup>)brownlowGroupService.findAll();
        model.addAttribute("combos",combos);
        return "brownlowcombos";
    }
}
