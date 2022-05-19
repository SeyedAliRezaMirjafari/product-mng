package com.sed.productmanagement.service.vote;

import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.service.vote.model.VoteModel;

public interface VoteService {
    void addVote(VoteModel voteModel) throws GeneralException;
}
