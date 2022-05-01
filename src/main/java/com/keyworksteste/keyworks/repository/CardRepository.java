package com.keyworksteste.keyworks.repository;

import com.keyworksteste.keyworks.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {

    Card findByTitleContainingIgnoreCase(String title);
}
