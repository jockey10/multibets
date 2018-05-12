package com.rock.multibets.Service;

import com.rock.multibets.domain.BrownlowBet;
import com.rock.multibets.domain.Multibet;
import com.rock.multibets.repository.BrownlowBetRepository;
import com.rock.multibets.repository.MultibetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service("brownlowBetService")
public class BrownlowBetService {

    @Autowired
    private BrownlowBetRepository brownlowBetRepository;

    @Transactional
    public void addBrownlowBet(BrownlowBet bet) {
        brownlowBetRepository.save(bet);
    }
}