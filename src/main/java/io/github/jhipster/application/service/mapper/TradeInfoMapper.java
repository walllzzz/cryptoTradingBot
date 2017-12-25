package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.TradeInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity TradeInfo and its DTO TradeInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TradeInfoMapper extends EntityMapper<TradeInfoDTO, TradeInfo> {

    

    
}
