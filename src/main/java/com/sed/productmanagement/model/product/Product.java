package com.sed.productmanagement.model.product;

import com.fasterxml.jackson.annotation.JsonValue;
import com.sed.productmanagement.model.provider.Provider;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Stream;

@Entity
@Table(name = "product")
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String title;

    private String description;

    private String image;

    private boolean active = true;

    private boolean visible = true;

    private ActionType voteable = ActionType.PUBLIC;

    private ActionType commentable = ActionType.PUBLIC;

    private boolean publicVisibleComment = true;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "product_providers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id"))
    private Set<Provider> providers;

    @Formula("(SELECT COUNT(v.id) FROM Vote v WHERE v.status = 1 and id = v.product_id)")
    private Long voteCount;

    @Formula("(SELECT avg(v.score) FROM Vote v WHERE v.status = 1 and id = v.product_id)")
    private Double voteAverage;

    @Setter(AccessLevel.NONE)
    private Instant creationTime;


    public Product() {
    }

    @Builder
    public Product(String code, String title, String description, String image, Set<Provider> providers) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.image = image;
        this.providers = providers;
        this.creationTime = Instant.now();
    }

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


    public void addProvider(Provider provider) {
        this.providers.add(provider);
    }

    public void removeProvider(Provider provider) {
        this.providers.remove(provider);
    }
}

