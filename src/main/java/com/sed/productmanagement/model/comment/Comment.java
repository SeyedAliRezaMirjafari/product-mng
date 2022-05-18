package com.sed.productmanagement.model.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import com.sed.productmanagement.model.product.Product;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import java.time.Instant;
import java.util.stream.Stream;

@Entity
@Table(name = "comment")
@Setter
@Getter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    @Column(nullable = false)
    private Instant creationTime;

    private Instant modificationStatusTime;

    @Setter(AccessLevel.NONE)
    private Status status = Status.INITIATED;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    @JsonIgnore
    @BatchSize(size = 3)
    private Product product;

    @Column(nullable = false)
    private String userId;

    public void changeStatus(Status status) {
        this.status = status;
        this.modificationStatusTime = Instant.now();
    }

    public enum Status {
        INITIATED(0), ACCEPTED(1), REJECTED(2);

        private Integer value;

        Status(Integer value) {
            this.value = value;
        }

        public static Status fromValue(int i) {
            return Stream.of(Status.values()).filter(status -> status.value == i).findFirst().orElseThrow(() -> new IllegalStateException("undefined value found for status " + i));
        }

        @JsonValue
        public int getValue() {
            return value;
        }
    }

}


