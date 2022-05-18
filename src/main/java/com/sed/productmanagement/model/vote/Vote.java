package com.sed.productmanagement.model.vote;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sed.productmanagement.model.product.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.persistence.*;
import java.time.Instant;
import java.util.stream.Stream;

@Entity
@Table(name = "vote")
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int score;

    @Column(nullable = false)
    private String userId;

    private Status status = Status.INITIATED;

    @Column(nullable = false)
    private Instant creationTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @Indexed
    private Product product;


    public enum Status {
        INITIATED(0), ACCEPTED(1), REJECTED(2);

        private Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public static Vote.Status fromValue(int i) {
            return Stream.of(Vote.Status.values()).filter(status -> status.value == i).findFirst().orElseThrow(() -> new IllegalStateException("undefined value found for status " + i));
        }

        @JsonValue
        public int getValue() {
            return value;
        }
    }
}
