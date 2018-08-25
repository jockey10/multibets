package com.rock.multibets.web;

import com.rock.multibets.Service.MultiGroupService;
import com.rock.multibets.domain.MultiGroup;
import com.rock.multibets.domain.Multibet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;


@Controller
public class MultibetsController {

    @Autowired
    MultiGroupService multigroupService;

    @RequestMapping(value="/")
    public String home() {
        return "home";
    }

    @RequestMapping(value="/multi")
    public String multi() { return "multi"; }

    @RequestMapping(value="/multicombos")
    public String multicombos(Model model) {
        ArrayList<MultiGroup> combos = (ArrayList<MultiGroup>)multigroupService.findAll();
        model.addAttribute("combos",combos);
        return "multicombos";
    }
}
