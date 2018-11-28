package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccAlertLog is a Querydsl query type for BcWiccAlertLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccAlertLog extends com.querydsl.sql.RelationalPathBase<BcWiccAlertLog> {

    private static final long serialVersionUID = 1510567408;

    public static final QBcWiccAlertLog bcWiccAlertLog = new QBcWiccAlertLog("bc_wicc_alert_log");

    public final NumberPath<Integer> active = createNumber("active", Integer.class);

    public final StringPath address = createString("address");

    public final NumberPath<Integer> alertAt = createNumber("alertAt", Integer.class);

    public final StringPath alertInfo = createString("alertInfo");

    public final StringPath alertParam = createString("alertParam");

    public final NumberPath<Integer> alertType = createNumber("alertType", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final StringPath notifyLinkUrl = createString("notifyLinkUrl");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccAlertLog> primary = createPrimaryKey(id);

    public QBcWiccAlertLog(String variable) {
        super(BcWiccAlertLog.class, forVariable(variable), "null", "bc_wicc_alert_log");
        addMetadata();
    }

    public QBcWiccAlertLog(String variable, String schema, String table) {
        super(BcWiccAlertLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccAlertLog(String variable, String schema) {
        super(BcWiccAlertLog.class, forVariable(variable), schema, "bc_wicc_alert_log");
        addMetadata();
    }

    public QBcWiccAlertLog(Path<? extends BcWiccAlertLog> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_alert_log");
        addMetadata();
    }

    public QBcWiccAlertLog(PathMetadata metadata) {
        super(BcWiccAlertLog.class, metadata, "null", "bc_wicc_alert_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(7).ofType(Types.INTEGER).withSize(10));
        addMetadata(address, ColumnMetadata.named("address").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(alertAt, ColumnMetadata.named("alert_at").withIndex(9).ofType(Types.INTEGER).withSize(10));
        addMetadata(alertInfo, ColumnMetadata.named("alert_info").withIndex(6).ofType(Types.LONGVARCHAR).withSize(2147483647));
        addMetadata(alertParam, ColumnMetadata.named("alert_param").withIndex(5).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(alertType, ColumnMetadata.named("alert_type").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(10).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(memo, ColumnMetadata.named("memo").withIndex(8).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(notifyLinkUrl, ColumnMetadata.named("notify_link_url").withIndex(3).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(11).ofType(Types.TIMESTAMP).withSize(19));
    }

}

