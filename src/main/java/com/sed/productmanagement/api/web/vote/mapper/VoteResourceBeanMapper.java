package com.sed.productmanagement.api.web.vote.mapper;

import com.sed.productmanagement.component.mapper.Utils;
import com.sed.productmanagement.service.vote.model.VoteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {Utils.class}, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface VoteResourceBeanMapper {

    @Mapping(target = "score", source = "score")
    @Mapping(target = "productCode", source = "productCode")
    @Mapping(target = "userId", source = "userId")
    VoteModel toVoteModel(String productCode, String userId, Integer score);
}
