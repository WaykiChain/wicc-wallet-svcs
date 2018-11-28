package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccWalletAccountLog is a Querydsl query type for BcWiccWalletAccountLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccWalletAccountLog extends com.querydsl.sql.RelationalPathBase<BcWiccWalletAccountLog> {

    private static final long serialVersionUID = 74486872;

    public static final QBcWiccWalletAccountLog bcWiccWalletAccountLog = new QBcWiccWalletAccountLog("bc_wicc_wallet_account_log");

    public final StringPath address = createString("address");

    public final NumberPath<Long> afterBalance = createNumber("afterBalance", Long.class);

    public final NumberPath<Long> availableAmount = createNumber("availableAmount", Long.class);

    public final NumberPath<Long> beforeBalance = createNumber("beforeBalance", Long.class);

    public final StringPath coinSymbol = createString("coinSymbol");

    public final DateTimePath<java.util.Date> confirmedAt = createDateTime("confirmedAt", java.util.Date.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> fees = createNumber("fees", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final StringPath remark = createString("remark");

    public final StringPath txid = createString("txid");

    public final StringPath txType = createString("txType");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccWalletAccountLog> primary = createPrimaryKey(id);

    public QBcWiccWalletAccountLog(String variable) {
        super(BcWiccWalletAccountLog.class, forVariable(variable), "null", "bc_wicc_wallet_account_log");
        addMetadata();
    }

    public QBcWiccWalletAccountLog(String variable, String schema, String table) {
        super(BcWiccWalletAccountLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccWalletAccountLog(String variable, String schema) {
        super(BcWiccWalletAccountLog.class, forVariable(variable), schema, "bc_wicc_wallet_account_log");
        addMetadata();
    }

    public QBcWiccWalletAccountLog(Path<? extends BcWiccWalletAccountLog> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_wallet_account_log");
        addMetadata();
    }

    public QBcWiccWalletAccountLog(PathMetadata metadata) {
        super(BcWiccWalletAccountLog.class, metadata, "null", "bc_wicc_wallet_account_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(afterBalance, ColumnMetadata.named("after_balance").withIndex(9).ofType(Types.BIGINT).withSize(19));
        addMetadata(availableAmount, ColumnMetadata.named("available_amount").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(beforeBalance, ColumnMetadata.named("before_balance").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(5).ofType(Types.VARCHAR).withSize(32));
        addMetadata(confirmedAt, ColumnMetadata.named("confirmed_at").withIndex(12).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(14).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(fees, ColumnMetadata.named("fees").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(number, ColumnMetadata.named("number").withIndex(11).ofType(Types.INTEGER).withSize(10));
        addMetadata(remark, ColumnMetadata.named("remark").withIndex(13).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(txid, ColumnMetadata.named("txid").withIndex(10).ofType(Types.VARCHAR).withSize(128));
        addMetadata(txType, ColumnMetadata.named("tx_type").withIndex(4).ofType(Types.VARCHAR).withSize(16));
        addMetadata(type, ColumnMetadata.named("type").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(15).ofType(Types.TIMESTAMP).withSize(19));
    }

}

