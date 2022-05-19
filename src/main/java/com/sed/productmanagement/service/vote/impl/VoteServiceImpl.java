package com.sed.productmanagement.service.vote.impl;

import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.model.product.Product;
import com.sed.productmanagement.model.vote.Vote;
import com.sed.productmanagement.model.vote.dao.VoteDao;
import com.sed.productmanagement.service.product.ProductJournalService;
import com.sed.productmanagement.service.vote.VoteService;
import com.sed.productmanagement.service.vote.model.VoteModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class VoteServiceImpl implements VoteService {

    private final VoteDao voteDao;
    private final ProductJournalService productJournalService;

    @Override
    public void addVote(VoteModel voteModel) throws GeneralException {
        logger.info("gonna add vote. model: {}", voteModel);
        Product product = productJournalService.findByActiveIsTrueAndVisibleIsTrueAndCodeIs(voteModel.getProductCode());
        canVote(product, voteModel);
        initVote(product, voteModel);

    }

    private void canVote(Product product, VoteModel voteModel) throws GeneralException {
        //FIXME: external service
        if (!Product.ActionType.PUBLIC.equals(product.getVotable()) /*|| !(Product.ActionType.ONLY_BUYER.equals(product.getVotable()) && buyer(voteModel))*/){
            throw new GeneralException(Result.ACCESS_ERROR,"can't vote");
        }
    }

    private void initVote(Product product, VoteModel voteModel) {
        Vote vote = Vote.builder()
                .product(product)
                .score(voteModel.getScore())
                .userId(voteModel.getUserId())
                .build();
        voteDao.save(vote);
    }
}
