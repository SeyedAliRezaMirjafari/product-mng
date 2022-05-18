package com.sed.productmanagement.model.provider.dao;

import com.sed.productmanagement.model.provider.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderDao extends JpaRepository<Provider, Long> {
}
