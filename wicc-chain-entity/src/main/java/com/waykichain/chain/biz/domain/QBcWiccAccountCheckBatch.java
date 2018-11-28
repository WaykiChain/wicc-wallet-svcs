package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccAccountCheckBatch is a Querydsl query type for BcWiccAccountCheckBatch
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccAccountCheckBatch extends com.querydsl.sql.RelationalPathBase<BcWiccAccountCheckBatch> {

    private static final long serialVersionUID = -872678281;

    public static final QBcWiccAccountCheckBatch bcWiccAccountCheckBatch = new QBcWiccAccountCheckBatch("bc_wicc_account_check_batch");

    public final NumberPath<Long> amount = createNumber("amount", Long.class);

    public final NumberPath<Integer> checkType = createNumber("checkType", Integer.class);

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final DateTimePath<java.util.Date> eod = createDateTime("eod", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccAccountCheckBatch> primary = createPrimaryKey(id);

    public QBcWiccAccountCheckBatch(String variable) {
        super(BcWiccAccountCheckBatch.class, forVariable(variable), "null", "bc_wicc_account_check_batch");
        addMetadata();
    }

    public QBcWiccAccountCheckBatch(String variable, String schema, String table) {
        super(BcWiccAccountCheckBatch.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccAccountCheckBatch(String variable, String schema) {
        super(BcWiccAccountCheckBatch.class, forVariable(variable), schema, "bc_wicc_account_check_batch");
        addMetadata();
    }

    public QBcWiccAccountCheckBatch(Path<? extends BcWiccAccountCheckBatch> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_account_check_batch");
        addMetadata();
    }

    public QBcWiccAccountCheckBatch(PathMetadata metadata) {
        super(BcWiccAccountCheckBatch.class, metadata, "null", "bc_wicc_account_check_batch");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(amount, ColumnMetadata.named("amount").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(checkType, ColumnMetadata.named("check_type").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(count, ColumnMetadata.named("count").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(7).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(eod, ColumnMetadata.named("eod").withIndex(2).ofType(Types.DATE).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(status, ColumnMetadata.named("status").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(8).ofType(Types.TIMESTAMP).withSize(19));
    }

}

