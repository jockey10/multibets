package com.rock.multibets.repository;

import com.rock.multibets.domain.BrownlowGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface BrownlowGroupRepository extends JpaRepository<BrownlowGroup,Long>{
    BrownlowGroup findByUuid(UUID uuid);
    List<BrownlowGroup> findAllByOrderByDateDesc();
}
