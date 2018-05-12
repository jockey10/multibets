package com.rock.multibets.Service;

import com.rock.multibets.domain.BrownlowGroup;
import com.rock.multibets.domain.Multibet;
import com.rock.multibets.repository.BrownlowGroupRepository;
import com.rock.multibets.repository.BrownlowGroupRepository;
import com.rock.multibets.repository.MultibetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("brownlowGroupService")
public class BrownlowGroupService {

    @Autowired
    BrownlowGroupRepository brownlowGroupRepository;

    //@Transactional
    //public Multibet getMultibet(long id){
    //    return multibetRepository.findOne(id);
    //}

    @Transactional
    public void addBrownlowGroup(BrownlowGroup group) {
        brownlowGroupRepository.save(group);
    }

    @Transactional
    public List<BrownlowGroup> findAll() {
        return brownlowGroupRepository.findAllByOrderByDateDesc();
    }

    @Transactional
    public BrownlowGroup getBrownlowGroup(UUID uuid) {
        return brownlowGroupRepository.findByUuid(uuid);
    }
}