package io.github.jhipster.application.service.dto;


import java.time.Instant;
import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.application.domain.enumeration.Decision;
import io.github.jhipster.application.domain.enumeration.Decision;

/**
 * A DTO for the TradeInfo entity.
 */
public class TradeInfoDTO implements Serializable {

    private String id;

    private Instant time;

    private Decision action;

    private Decision lastAction;

    private Long assetPrice;

    private Long balance;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Decision getAction() {
        return action;
    }

    public void setAction(Decision action) {
        this.action = action;
    }

    public Decision getLastAction() {
        return lastAction;
    }

    public void setLastAction(Decision lastAction) {
        this.lastAction = lastAction;
    }

    public Long getAssetPrice() {
        return assetPrice;
    }

    public void setAssetPrice(Long assetPrice) {
        this.assetPrice = assetPrice;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TradeInfoDTO tradeInfoDTO = (TradeInfoDTO) o;
        if(tradeInfoDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tradeInfoDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TradeInfoDTO{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", action='" + getAction() + "'" +
            ", lastAction='" + getLastAction() + "'" +
            ", assetPrice=" + getAssetPrice() +
            ", balance=" + getBalance() +
            "}";
    }
}
