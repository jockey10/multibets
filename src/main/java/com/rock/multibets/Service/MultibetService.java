package com.rock.multibets.Service;

import com.rock.multibets.domain.Multibet;
import com.rock.multibets.repository.MultibetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("multibetService")
public class MultibetService {

    @Autowired
    private MultibetRepository multibetRepository;

    //@Transactional
    //public Multibet getMultibet(long id){
    //    return multibetRepository.findOne(id);
    //}

    @Transactional
    public void addMultibet(Multibet bet) {
        multibetRepository.save(bet);
    }
}
