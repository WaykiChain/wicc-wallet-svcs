package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccAccountCheckLog is a Querydsl query type for BcWiccAccountCheckLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccAccountCheckLog extends com.querydsl.sql.RelationalPathBase<BcWiccAccountCheckLog> {

    private static final long serialVersionUID = -671288383;

    public static final QBcWiccAccountCheckLog bcWiccAccountCheckLog = new QBcWiccAccountCheckLog("bc_wicc_account_check_log");

    public final StringPath address = createString("address");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final NumberPath<Long> batchId = createNumber("batchId", Long.class);

    public final NumberPath<Long> chainBalance = createNumber("chainBalance", Long.class);

    public final StringPath coinSymbol = createString("coinSymbol");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> dealBalance = createNumber("dealBalance", Long.class);

    public final NumberPath<Long> dealChainBalance = createNumber("dealChainBalance", Long.class);

    public final DateTimePath<java.util.Date> dealDate = createDateTime("dealDate", java.util.Date.class);

    public final StringPath dealMemo = createString("dealMemo");

    public final NumberPath<Long> diffAmount = createNumber("diffAmount", Long.class);

    public final StringPath diffReason = createString("diffReason");

    public final DateTimePath<java.util.Date> eod = createDateTime("eod", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccAccountCheckLog> primary = createPrimaryKey(id);

    public QBcWiccAccountCheckLog(String variable) {
        super(BcWiccAccountCheckLog.class, forVariable(variable), "null", "bc_wicc_account_check_log");
        addMetadata();
    }

    public QBcWiccAccountCheckLog(String variable, String schema, String table) {
        super(BcWiccAccountCheckLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccAccountCheckLog(String variable, String schema) {
        super(BcWiccAccountCheckLog.class, forVariable(variable), schema, "bc_wicc_account_check_log");
        addMetadata();
    }

    public QBcWiccAccountCheckLog(Path<? extends BcWiccAccountCheckLog> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_account_check_log");
        addMetadata();
    }

    public QBcWiccAccountCheckLog(PathMetadata metadata) {
        super(BcWiccAccountCheckLog.class, metadata, "null", "bc_wicc_account_check_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(4).ofType(Types.VARCHAR).withSize(64));
        addMetadata(balance, ColumnMetadata.named("balance").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(batchId, ColumnMetadata.named("batch_id").withIndex(3).ofType(Types.BIGINT).withSize(19));
        addMetadata(chainBalance, ColumnMetadata.named("chain_balance").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(6).ofType(Types.VARCHAR).withSize(32));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(17).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(dealBalance, ColumnMetadata.named("deal_balance").withIndex(12).ofType(Types.BIGINT).withSize(19));
        addMetadata(dealChainBalance, ColumnMetadata.named("deal_chain_balance").withIndex(13).ofType(Types.BIGINT).withSize(19));
        addMetadata(dealDate, ColumnMetadata.named("deal_date").withIndex(11).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(dealMemo, ColumnMetadata.named("deal_memo").withIndex(15).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(diffAmount, ColumnMetadata.named("diff_amount").withIndex(9).ofType(Types.BIGINT).withSize(19));
        addMetadata(diffReason, ColumnMetadata.named("diff_reason").withIndex(14).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(eod, ColumnMetadata.named("eod").withIndex(2).ofType(Types.DATE).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(memo, ColumnMetadata.named("memo").withIndex(16).ofType(Types.VARCHAR).withSize(36));
        addMetadata(status, ColumnMetadata.named("status").withIndex(10).ofType(Types.INTEGER).withSize(10));
        addMetadata(type, ColumnMetadata.named("type").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(18).ofType(Types.TIMESTAMP).withSize(19));
    }

}

