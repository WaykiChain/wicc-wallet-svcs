package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccMonitorAddress is a Querydsl query type for BcWiccMonitorAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccMonitorAddress extends com.querydsl.sql.RelationalPathBase<BcWiccMonitorAddress> {

    private static final long serialVersionUID = 1286297826;

    public static final QBcWiccMonitorAddress bcWiccMonitorAddress = new QBcWiccMonitorAddress("bc_wicc_monitor_address");

    public final NumberPath<Integer> active = createNumber("active", Integer.class);

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> currentBalance = createNumber("currentBalance", Long.class);

    public final NumberPath<Integer> heigh = createNumber("heigh", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Long> monitorAmount = createNumber("monitorAmount", Long.class);

    public final NumberPath<Integer> monitorPercent = createNumber("monitorPercent", Integer.class);

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccMonitorAddress> primary = createPrimaryKey(id);

    public QBcWiccMonitorAddress(String variable) {
        super(BcWiccMonitorAddress.class, forVariable(variable), "null", "bc_wicc_monitor_address");
        addMetadata();
    }

    public QBcWiccMonitorAddress(String variable, String schema, String table) {
        super(BcWiccMonitorAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccMonitorAddress(String variable, String schema) {
        super(BcWiccMonitorAddress.class, forVariable(variable), schema, "bc_wicc_monitor_address");
        addMetadata();
    }

    public QBcWiccMonitorAddress(Path<? extends BcWiccMonitorAddress> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_monitor_address");
        addMetadata();
    }

    public QBcWiccMonitorAddress(PathMetadata metadata) {
        super(BcWiccMonitorAddress.class, metadata, "null", "bc_wicc_monitor_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(address, ColumnMetadata.named("address").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(10).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(currentBalance, ColumnMetadata.named("current_balance").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(heigh, ColumnMetadata.named("heigh").withIndex(9).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(memo, ColumnMetadata.named("memo").withIndex(7).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(monitorAmount, ColumnMetadata.named("monitor_amount").withIndex(4).ofType(Types.BIGINT).withSize(19));
        addMetadata(monitorPercent, ColumnMetadata.named("monitor_percent").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(type, ColumnMetadata.named("type").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(11).ofType(Types.TIMESTAMP).withSize(19));
    }

}

