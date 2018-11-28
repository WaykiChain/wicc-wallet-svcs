package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccSendTransactionLog is a Querydsl query type for BcWiccSendTransactionLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccSendTransactionLog extends com.querydsl.sql.RelationalPathBase<BcWiccSendTransactionLog> {

    private static final long serialVersionUID = 1444772054;

    public static final QBcWiccSendTransactionLog bcWiccSendTransactionLog = new QBcWiccSendTransactionLog("bc_wicc_send_transaction_log");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath prameter = createString("prameter");

    public final StringPath recvAddress = createString("recvAddress");

    public final StringPath remark = createString("remark");

    public final StringPath requestUuid = createString("requestUuid");

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final NumberPath<Long> transAmount = createNumber("transAmount", Long.class);

    public final NumberPath<Long> transFee = createNumber("transFee", Long.class);

    public final StringPath txid = createString("txid");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccSendTransactionLog> primary = createPrimaryKey(id);

    public QBcWiccSendTransactionLog(String variable) {
        super(BcWiccSendTransactionLog.class, forVariable(variable), "null", "bc_wicc_send_transaction_log");
        addMetadata();
    }

    public QBcWiccSendTransactionLog(String variable, String schema, String table) {
        super(BcWiccSendTransactionLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccSendTransactionLog(String variable, String schema) {
        super(BcWiccSendTransactionLog.class, forVariable(variable), schema, "bc_wicc_send_transaction_log");
        addMetadata();
    }

    public QBcWiccSendTransactionLog(Path<? extends BcWiccSendTransactionLog> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_send_transaction_log");
        addMetadata();
    }

    public QBcWiccSendTransactionLog(PathMetadata metadata) {
        super(BcWiccSendTransactionLog.class, metadata, "null", "bc_wicc_send_transaction_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(amount, ColumnMetadata.named("amount").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(11).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(prameter, ColumnMetadata.named("prameter").withIndex(8).ofType(Types.LONGVARCHAR).withSize(16777215));
        addMetadata(recvAddress, ColumnMetadata.named("recv_address").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(remark, ColumnMetadata.named("remark").withIndex(10).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(requestUuid, ColumnMetadata.named("request_uuid").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(status, ColumnMetadata.named("status").withIndex(9).ofType(Types.INTEGER).withSize(10));
        addMetadata(transAmount, ColumnMetadata.named("trans_amount").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(transFee, ColumnMetadata.named("trans_fee").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(txid, ColumnMetadata.named("txid").withIndex(7).ofType(Types.VARCHAR).withSize(64));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(12).ofType(Types.TIMESTAMP).withSize(19));
    }

}

