package com.sed.productmanagement.common.request;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sed.productmanagement.model.product.Product;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.util.stream.Stream;

@Setter
@Getter
public class ProductUserActionRequest {
    @NonNull
    private Boolean active;
    @NonNull
    private Boolean visible;
    @NonNull
    private ActionType votable;
    @NonNull
    private ActionType commentable;
    @NonNull
    private Boolean publicVisibleComment;

    public enum ActionType {
        DISABLE(0), PUBLIC(1), ONLY_BUYER(2);
        private Integer value;

        ActionType(Integer value) {
            this.value = value;
        }

        public static ActionType fromValue(int i) {
            return Stream.of(ActionType.values()).filter(status -> status.value == i).findFirst().orElseThrow(() -> new IllegalStateException("undefined value found for status " + i));
        }

        @JsonValue
        public int getValue() {
            return value;
        }
    }
}
