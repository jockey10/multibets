package com.rock.multibets.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.rock.multibets.domain.MultiGroup;

import java.util.List;
import java.util.UUID;


@Repository
public interface MultiGroupRepository extends JpaRepository<MultiGroup, Long> {
    MultiGroup findByUuid(UUID uuid);
    List<MultiGroup> findAllByOrderByDateDesc();
}
