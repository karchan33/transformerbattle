package org.java.transformerbattle.dao;

import org.java.transformerbattle.entity.Transformer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransformerRepository extends JpaRepository<Transformer,Integer> {
}
