package com.rock.multibets.Service;

import com.rock.multibets.domain.MultiGroup;
import com.rock.multibets.domain.Multibet;
import com.rock.multibets.repository.MultiGroupRepository;
import com.rock.multibets.repository.MultibetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service("multigroupService")
public class MultiGroupService {

    @Autowired
    MultiGroupRepository multigroupRepository;

    //@Transactional
    //public Multibet getMultibet(long id){
    //    return multibetRepository.findOne(id);
    //}

    @Transactional
    public void addMultiGroup(MultiGroup group) {
        multigroupRepository.save(group);
    }

    @Transactional
    public List<MultiGroup> findAll() {
        return multigroupRepository.findAllByOrderByDateDesc();
    }

    @Transactional
    public MultiGroup getMultiGroup(UUID uuid) {
        return multigroupRepository.findByUuid(uuid);
    }
}
