package com.sed.productmanagement.model.vote.dao;

import com.sed.productmanagement.model.vote.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDao extends JpaRepository<Vote, Long> {
}
