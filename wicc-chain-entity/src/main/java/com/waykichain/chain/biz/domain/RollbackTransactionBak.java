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
 * RollbackTransactionBak is a Querydsl bean type
 */
@Entity
@DynamicInsert
@DynamicUpdate
public class RollbackTransactionBak implements Serializable {

    @Column("addr")
    private String addr;

    @Column("block_hash")
    private String blockHash;

    @Column("block_number")
    private Integer blockNumber;

    @Column("confirmed_time")
    private Long confirmedTime;

    @Column("confirm_height")
    private Integer confirmHeight;

    @Column("contract")
    private String contract;

    @Column("created_at")
    private java.util.Date createdAt;

    @Column("desaddr")
    private String desaddr;

    @Column("descregid")
    private String descregid;

    @Column("fees")
    private Long fees;

    @Column("height")
    private Integer height;

    @Column("id")
    @Id
    @GeneratedValue(strategy=javax.persistence.GenerationType.IDENTITY)
    private Integer id;

    @Column("list_output")
    private String listOutput;

    @Column("miner_pubkey")
    private String minerPubkey;

    @Column("money")
    private Long money;

    @Column("oper_vote_fund_list")
    private String operVoteFundList;

    @Column("pubkey")
    private String pubkey;

    @Column("rawtx")
    private String rawtx;

    @Column("regid")
    private String regid;

    @Column("script")
    private String script;

    @Column("txid")
    private String txid;

    @Column("tx_type")
    private String txType;

    @Column("updated_at")
    private java.util.Date updatedAt;

    @Column("ver")
    private Integer ver;

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getBlockHash() {
        return blockHash;
    }

    public void setBlockHash(String blockHash) {
        this.blockHash = blockHash;
    }

    public Integer getBlockNumber() {
        return blockNumber;
    }

    public void setBlockNumber(Integer blockNumber) {
        this.blockNumber = blockNumber;
    }

    public Long getConfirmedTime() {
        return confirmedTime;
    }

    public void setConfirmedTime(Long confirmedTime) {
        this.confirmedTime = confirmedTime;
    }

    public Integer getConfirmHeight() {
        return confirmHeight;
    }

    public void setConfirmHeight(Integer confirmHeight) {
        this.confirmHeight = confirmHeight;
    }

    public String getContract() {
        return contract;
    }

    public void setContract(String contract) {
        this.contract = contract;
    }

    public java.util.Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(java.util.Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesaddr() {
        return desaddr;
    }

    public void setDesaddr(String desaddr) {
        this.desaddr = desaddr;
    }

    public String getDescregid() {
        return descregid;
    }

    public void setDescregid(String descregid) {
        this.descregid = descregid;
    }

    public Long getFees() {
        return fees;
    }

    public void setFees(Long fees) {
        this.fees = fees;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getListOutput() {
        return listOutput;
    }

    public void setListOutput(String listOutput) {
        this.listOutput = listOutput;
    }

    public String getMinerPubkey() {
        return minerPubkey;
    }

    public void setMinerPubkey(String minerPubkey) {
        this.minerPubkey = minerPubkey;
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getOperVoteFundList() {
        return operVoteFundList;
    }

    public void setOperVoteFundList(String operVoteFundList) {
        this.operVoteFundList = operVoteFundList;
    }

    public String getPubkey() {
        return pubkey;
    }

    public void setPubkey(String pubkey) {
        this.pubkey = pubkey;
    }

    public String getRawtx() {
        return rawtx;
    }

    public void setRawtx(String rawtx) {
        this.rawtx = rawtx;
    }

    public String getRegid() {
        return regid;
    }

    public void setRegid(String regid) {
        this.regid = regid;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxType() {
        return txType;
    }

    public void setTxType(String txType) {
        this.txType = txType;
    }

    public java.util.Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(java.util.Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getVer() {
        return ver;
    }

    public void setVer(Integer ver) {
        this.ver = ver;
    }

}

