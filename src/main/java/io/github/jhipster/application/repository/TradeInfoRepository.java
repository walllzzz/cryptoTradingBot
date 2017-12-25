package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.TradeInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TradeInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TradeInfoRepository extends MongoRepository<TradeInfo, String> {

}
