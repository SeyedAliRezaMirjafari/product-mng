package com.sed.productmanagement.model.product;

import com.sed.productmanagement.model.provider.Provider;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Set;

@Entity
@Immutable
@Table(name = "product")
@Getter
@Setter
public class ProductView implements Serializable {
    @Id
    @Column(updatable = false, insertable = false)
    private Long id;
    @Column(updatable = false, insertable = false)
    private String code;
    @Column(updatable = false, insertable = false)
    private String title;
    @Column(updatable = false, insertable = false)
    private String description;
    @Column(updatable = false, insertable = false)
    private String image;
    @Column(updatable = false, insertable = false)
    private boolean active = true;
    @Column(updatable = false, insertable = false)
    private boolean visible = true;
    @Column(updatable = false, insertable = false)
    private Product.ActionType votable = Product.ActionType.PUBLIC;
    @Column(updatable = false, insertable = false)
    private Product.ActionType commentable = Product.ActionType.PUBLIC;
    @Column(updatable = false, insertable = false)
    private boolean publicVisibleComment = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "product_providers",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "provider_id"))
    private Set<Provider> providers;

    @Formula("(SELECT COUNT(v.id) FROM Vote v WHERE v.status = 1 and id = v.product_id)")
    private Long voteCount;

    @Formula("(SELECT avg(v.score) FROM Vote v WHERE v.status = 1 and id = v.product_id)")
    private Double voteAverage;
    @Column(updatable = false, insertable = false)
    private Instant creationTime;
}
