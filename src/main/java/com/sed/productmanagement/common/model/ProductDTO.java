package com.sed.productmanagement.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Stream;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ProductDTO {

    private String code;

    private String title;

    private String description;

    private String image;

    private String url;

    private ActionTypeDTO votable;

    private ActionTypeDTO commentable;

    private Boolean publicVisibleComment;

    private List<ProviderDTO> providers;

    private CommentSummaryDTO commentSummary;

    private VoteSummaryDTO voteSummary;

    public enum ActionTypeDTO {
        DISABLE(0), PUBLIC(1), ONLY_BUYER(2);
        private Integer value;

        ActionTypeDTO(Integer value) {
            this.value = value;
        }

        public static ActionTypeDTO fromValue(int i) {
            return Stream.of(ActionTypeDTO.values()).filter(status -> status.value == i).findFirst().orElseThrow(() -> new IllegalStateException("undefined value found for status " + i));
        }

        @JsonValue
        public int getValue() {
            return value;
        }
    }
}
