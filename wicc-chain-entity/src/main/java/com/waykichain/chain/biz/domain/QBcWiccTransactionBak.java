package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccTransactionBak is a Querydsl query type for BcWiccTransactionBak
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccTransactionBak extends com.querydsl.sql.RelationalPathBase<BcWiccTransactionBak> {

    private static final long serialVersionUID = -328687064;

    public static final QBcWiccTransactionBak bcWiccTransactionBak = new QBcWiccTransactionBak("bc_wicc_transaction_bak");

    public final StringPath addr = createString("addr");

    public final StringPath blockHash = createString("blockHash");

    public final NumberPath<Integer> blockNumber = createNumber("blockNumber", Integer.class);

    public final NumberPath<Long> confirmedTime = createNumber("confirmedTime", Long.class);

    public final NumberPath<Integer> confirmHeight = createNumber("confirmHeight", Integer.class);

    public final StringPath contract = createString("contract");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath desaddr = createString("desaddr");

    public final StringPath descregid = createString("descregid");

    public final NumberPath<java.math.BigDecimal> fees = createNumber("fees", java.math.BigDecimal.class);

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath listOutput = createString("listOutput");

    public final StringPath minerPubkey = createString("minerPubkey");

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final StringPath pubkey = createString("pubkey");

    public final StringPath rawtx = createString("rawtx");

    public final StringPath regid = createString("regid");

    public final StringPath script = createString("script");

    public final StringPath txid = createString("txid");

    public final StringPath txType = createString("txType");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> ver = createNumber("ver", Integer.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccTransactionBak> primary = createPrimaryKey(id);

    public QBcWiccTransactionBak(String variable) {
        super(BcWiccTransactionBak.class, forVariable(variable), "null", "bc_wicc_transaction_bak");
        addMetadata();
    }

    public QBcWiccTransactionBak(String variable, String schema, String table) {
        super(BcWiccTransactionBak.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccTransactionBak(String variable, String schema) {
        super(BcWiccTransactionBak.class, forVariable(variable), schema, "bc_wicc_transaction_bak");
        addMetadata();
    }

    public QBcWiccTransactionBak(Path<? extends BcWiccTransactionBak> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_transaction_bak");
        addMetadata();
    }

    public QBcWiccTransactionBak(PathMetadata metadata) {
        super(BcWiccTransactionBak.class, metadata, "null", "bc_wicc_transaction_bak");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addr, ColumnMetadata.named("addr").withIndex(8).ofType(Types.VARCHAR).withSize(64));
        addMetadata(blockHash, ColumnMetadata.named("block_hash").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(blockNumber, ColumnMetadata.named("block_number").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(confirmedTime, ColumnMetadata.named("confirmed_time").withIndex(19).ofType(Types.BIGINT).withSize(19));
        addMetadata(confirmHeight, ColumnMetadata.named("confirm_height").withIndex(18).ofType(Types.INTEGER).withSize(10));
        addMetadata(contract, ColumnMetadata.named("contract").withIndex(17).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(22).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(desaddr, ColumnMetadata.named("desaddr").withIndex(10).ofType(Types.VARCHAR).withSize(64));
        addMetadata(descregid, ColumnMetadata.named("descregid").withIndex(9).ofType(Types.VARCHAR).withSize(64));
        addMetadata(fees, ColumnMetadata.named("fees").withIndex(15).ofType(Types.DECIMAL).withSize(36).withDigits(18));
        addMetadata(height, ColumnMetadata.named("height").withIndex(16).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(listOutput, ColumnMetadata.named("list_output").withIndex(21).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(minerPubkey, ColumnMetadata.named("miner_pubkey").withIndex(12).ofType(Types.VARCHAR).withSize(128));
        addMetadata(money, ColumnMetadata.named("money").withIndex(13).ofType(Types.BIGINT).withSize(19));
        addMetadata(pubkey, ColumnMetadata.named("pubkey").withIndex(11).ofType(Types.VARCHAR).withSize(128));
        addMetadata(rawtx, ColumnMetadata.named("rawtx").withIndex(20).ofType(Types.LONGVARCHAR).withSize(16777215));
        addMetadata(regid, ColumnMetadata.named("regid").withIndex(7).ofType(Types.VARCHAR).withSize(64));
        addMetadata(script, ColumnMetadata.named("script").withIndex(14).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(txid, ColumnMetadata.named("txid").withIndex(4).ofType(Types.VARCHAR).withSize(64));
        addMetadata(txType, ColumnMetadata.named("tx_type").withIndex(5).ofType(Types.VARCHAR).withSize(64));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(23).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(ver, ColumnMetadata.named("ver").withIndex(6).ofType(Types.INTEGER).withSize(10));
    }

}

