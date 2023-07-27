package com.bloomberg.clustereddatawarehouse.repositories;

import com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse;
import com.bloomberg.clustereddatawarehouse.models.FXDeal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FXDealsRepository extends JpaRepository<FXDeal, Long> {

    boolean existsByDealUniqueId(String uniqueId);

    @Query("SELECT new com.bloomberg.clustereddatawarehouse.dtos.responses.FXDealsResponse(" +
            " fxd.dealUniqueId, fxd.fromISOCode, fxd.toISOCode, fxd.dealAmount, fxd.dealCreatedOn) FROM FXDeal fxd")
    Page<FXDealsResponse> findAllFXDealsResponse(Pageable pageable);
}
