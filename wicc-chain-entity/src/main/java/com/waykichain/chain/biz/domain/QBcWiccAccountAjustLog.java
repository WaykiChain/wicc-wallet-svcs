package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccAccountAjustLog is a Querydsl query type for BcWiccAccountAjustLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccAccountAjustLog extends com.querydsl.sql.RelationalPathBase<BcWiccAccountAjustLog> {

    private static final long serialVersionUID = -1908790820;

    public static final QBcWiccAccountAjustLog bcWiccAccountAjustLog = new QBcWiccAccountAjustLog("bc_wicc_account_ajust_log");

    public final StringPath address = createString("address");

    public final NumberPath<Long> afterBalance = createNumber("afterBalance", Long.class);

    public final DateTimePath<java.util.Date> ajustAt = createDateTime("ajustAt", java.util.Date.class);

    public final NumberPath<Long> ajustBalance = createNumber("ajustBalance", Long.class);

    public final NumberPath<Long> beforeBalance = createNumber("beforeBalance", Long.class);

    public final StringPath coinSymbol = createString("coinSymbol");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> dealBalance = createNumber("dealBalance", Long.class);

    public final NumberPath<Integer> dealDate = createNumber("dealDate", Integer.class);

    public final StringPath dealMemo = createString("dealMemo");

    public final StringPath diffReason = createString("diffReason");

    public final DateTimePath<java.util.Date> eod = createDateTime("eod", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccAccountAjustLog> primary = createPrimaryKey(id);

    public QBcWiccAccountAjustLog(String variable) {
        super(BcWiccAccountAjustLog.class, forVariable(variable), "null", "bc_wicc_account_ajust_log");
        addMetadata();
    }

    public QBcWiccAccountAjustLog(String variable, String schema, String table) {
        super(BcWiccAccountAjustLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccAccountAjustLog(String variable, String schema) {
        super(BcWiccAccountAjustLog.class, forVariable(variable), schema, "bc_wicc_account_ajust_log");
        addMetadata();
    }

    public QBcWiccAccountAjustLog(Path<? extends BcWiccAccountAjustLog> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_account_ajust_log");
        addMetadata();
    }

    public QBcWiccAccountAjustLog(PathMetadata metadata) {
        super(BcWiccAccountAjustLog.class, metadata, "null", "bc_wicc_account_ajust_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(afterBalance, ColumnMetadata.named("after_balance").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(ajustAt, ColumnMetadata.named("ajust_at").withIndex(9).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(ajustBalance, ColumnMetadata.named("ajust_balance").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(beforeBalance, ColumnMetadata.named("before_balance").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(5).ofType(Types.VARCHAR).withSize(32));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(15).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(dealBalance, ColumnMetadata.named("deal_balance").withIndex(11).ofType(Types.BIGINT).withSize(19));
        addMetadata(dealDate, ColumnMetadata.named("deal_date").withIndex(10).ofType(Types.INTEGER).withSize(10));
        addMetadata(dealMemo, ColumnMetadata.named("deal_memo").withIndex(13).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(diffReason, ColumnMetadata.named("diff_reason").withIndex(12).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(eod, ColumnMetadata.named("eod").withIndex(2).ofType(Types.DATE).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(memo, ColumnMetadata.named("memo").withIndex(14).ofType(Types.VARCHAR).withSize(36));
        addMetadata(type, ColumnMetadata.named("type").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(16).ofType(Types.TIMESTAMP).withSize(19));
    }

}

