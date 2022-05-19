package com.sed.productmanagement.api.web.vote;

import com.sed.productmanagement.api.web.vote.mapper.VoteResourceBeanMapper;
import com.sed.productmanagement.common.response.base.ResponseServiceBase;
import com.sed.productmanagement.common.response.base.Result;
import com.sed.productmanagement.exception.GeneralException;
import com.sed.productmanagement.service.vote.VoteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Slf4j
@RestController
@RequestMapping(path = "/product/{productCode}/vote")
@RequiredArgsConstructor
@Validated
public class VoteResource {

    private final VoteResourceBeanMapper mapper;
    private final VoteService voteService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseServiceBase> addVote(
            @PathVariable(value = "productCode", required = true) String productCode,
            @RequestHeader(value = "USER-ID", required = true) String userId,
            @Valid @RequestParam(value = "score", required = true) @Max(5) @Min(0) Integer score) throws GeneralException {
        logger.info("going to add vote, productCode {}, userId {}, vote {}", productCode, userId, score);
        voteService.addVote(mapper.toVoteModel(productCode, userId, score));
        return ResponseEntity.ok(new ResponseServiceBase(Result.SUCCESS));
    }
}
