package io.github.jhipster.application.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import io.github.jhipster.application.domain.enumeration.Decision;

/**
 * A TradeInfo.
 */
@Document(collection = "trade_info")
public class TradeInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("time")
    private Instant time;

    @Field("action")
    private Decision action;

    @Field("last_action")
    private Decision lastAction;

    @Field("asset_price")
    private Long assetPrice;

    @Field("balance")
    private Long balance;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Instant getTime() {
        return time;
    }

    public TradeInfo time(Instant time) {
        this.time = time;
        return this;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public Decision getAction() {
        return action;
    }

    public TradeInfo action(Decision action) {
        this.action = action;
        return this;
    }

    public void setAction(Decision action) {
        this.action = action;
    }

    public Decision getLastAction() {
        return lastAction;
    }

    public TradeInfo lastAction(Decision lastAction) {
        this.lastAction = lastAction;
        return this;
    }

    public void setLastAction(Decision lastAction) {
        this.lastAction = lastAction;
    }

    public Long getAssetPrice() {
        return assetPrice;
    }

    public TradeInfo assetPrice(Long assetPrice) {
        this.assetPrice = assetPrice;
        return this;
    }

    public void setAssetPrice(Long assetPrice) {
        this.assetPrice = assetPrice;
    }

    public Long getBalance() {
        return balance;
    }

    public TradeInfo balance(Long balance) {
        this.balance = balance;
        return this;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TradeInfo tradeInfo = (TradeInfo) o;
        if (tradeInfo.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), tradeInfo.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TradeInfo{" +
            "id=" + getId() +
            ", time='" + getTime() + "'" +
            ", action='" + getAction() + "'" +
            ", lastAction='" + getLastAction() + "'" +
            ", assetPrice=" + getAssetPrice() +
            ", balance=" + getBalance() +
            "}";
    }
}
