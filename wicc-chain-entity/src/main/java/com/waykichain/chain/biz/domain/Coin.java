package com.waykichain.chain.biz.domain;

import javax.persistence.Entity;
import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import org.hibernate.annotations.DynamicUpdate;
import com.querydsl.sql.Column;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Coin is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class Coin implements Serializable {

    @Column("block_url")
    private String blockUrl;

    @Column("circulation_amount")
    private Long circulationAmount;

    @Column("contract_address")
    private String contractAddress;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("description")
    private String description;

    @Column("icon_url")
    private String iconUrl;

    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    @Id
    @Column("id")
    private Long id;

    @Column("mini_confirm_count")
    private Integer miniConfirmCount;

    @Column("name")
    private String name;

    @Column("on_shelf")
    private Integer onShelf;

    @Column("precision")
    private Integer precision;

    @Column("reg_id")
    private String regId;

    @Column("release_amount")
    private Long releaseAmount;

    @Column("release_date")
    private java.util.Date releaseDate;

    @Column("start_number")
    private Integer startNumber;

    @Column("symbol")
    private String symbol;

    @Column("updated_at")
    private java.util.Date updatedAt;

    @Column("whitepaper_url")
    private String whitepaperUrl;

    public String getBlockUrl() {
        return blockUrl;
    }

    public void setBlockUrl(String blockUrl) {
        this.blockUrl = blockUrl;
    }

    public Long getCirculationAmount() {
        return circulationAmount;
    }

    public void setCirculationAmount(Long circulationAmount) {
        this.circulationAmount = circulationAmount;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMiniConfirmCount() {
        return miniConfirmCount;
    }

    public void setMiniConfirmCount(Integer miniConfirmCount) {
        this.miniConfirmCount = miniConfirmCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOnShelf() {
        return onShelf;
    }

    public void setOnShelf(Integer onShelf) {
        this.onShelf = onShelf;
    }

    public Integer getPrecision() {
        return precision;
    }

    public void setPrecision(Integer precision) {
        this.precision = precision;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public Long getReleaseAmount() {
        return releaseAmount;
    }

    public void setReleaseAmount(Long releaseAmount) {
        this.releaseAmount = releaseAmount;
    }

    public java.util.Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(java.util.Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getWhitepaperUrl() {
        return whitepaperUrl;
    }

    public void setWhitepaperUrl(String whitepaperUrl) {
        this.whitepaperUrl = whitepaperUrl;
    }

}

