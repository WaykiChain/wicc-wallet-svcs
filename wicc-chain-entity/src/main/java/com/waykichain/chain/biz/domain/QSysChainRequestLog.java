package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSysChainRequestLog is a Querydsl query type for SysChainRequestLog
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSysChainRequestLog extends com.querydsl.sql.RelationalPathBase<SysChainRequestLog> {

    private static final long serialVersionUID = -26991426;

    public static final QSysChainRequestLog sysChainRequestLog = new QSysChainRequestLog("sys_chain_request_log");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> customerId = createNumber("customerId", Long.class);

    public final StringPath deviceUuid = createString("deviceUuid");

    public final NumberPath<Integer> errorCode = createNumber("errorCode", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath method = createString("method");

    public final StringPath module = createString("module");

    public final StringPath requestIp = createString("requestIp");

    public final StringPath requestParmas = createString("requestParmas");

    public final StringPath requestUri = createString("requestUri");

    public final StringPath requestUrl = createString("requestUrl");

    public final StringPath requestUuid = createString("requestUuid");

    public final StringPath response = createString("response");

    public final NumberPath<Long> responseTime = createNumber("responseTime", Long.class);

    public final StringPath stackTrace = createString("stackTrace");

    public final NumberPath<Long> timestamp = createNumber("timestamp", Long.class);

    public final com.querydsl.sql.PrimaryKey<SysChainRequestLog> primary = createPrimaryKey(id);

    public QSysChainRequestLog(String variable) {
        super(SysChainRequestLog.class, forVariable(variable), "null", "sys_chain_request_log");
        addMetadata();
    }

    public QSysChainRequestLog(String variable, String schema, String table) {
        super(SysChainRequestLog.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSysChainRequestLog(String variable, String schema) {
        super(SysChainRequestLog.class, forVariable(variable), schema, "sys_chain_request_log");
        addMetadata();
    }

    public QSysChainRequestLog(Path<? extends SysChainRequestLog> path) {
        super(path.getType(), path.getMetadata(), "null", "sys_chain_request_log");
        addMetadata();
    }

    public QSysChainRequestLog(PathMetadata metadata) {
        super(SysChainRequestLog.class, metadata, "null", "sys_chain_request_log");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(16).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(customerId, ColumnMetadata.named("customer_id").withIndex(2).ofType(Types.BIGINT).withSize(19));
        addMetadata(deviceUuid, ColumnMetadata.named("device_uuid").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(errorCode, ColumnMetadata.named("error_code").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(method, ColumnMetadata.named("method").withIndex(6).ofType(Types.VARCHAR).withSize(11));
        addMetadata(module, ColumnMetadata.named("module").withIndex(7).ofType(Types.VARCHAR).withSize(64));
        addMetadata(requestIp, ColumnMetadata.named("request_ip").withIndex(8).ofType(Types.VARCHAR).withSize(64));
        addMetadata(requestParmas, ColumnMetadata.named("request_parmas").withIndex(9).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(requestUri, ColumnMetadata.named("request_uri").withIndex(10).ofType(Types.VARCHAR).withSize(64));
        addMetadata(requestUrl, ColumnMetadata.named("request_url").withIndex(12).ofType(Types.VARCHAR).withSize(512));
        addMetadata(requestUuid, ColumnMetadata.named("request_uuid").withIndex(5).ofType(Types.VARCHAR).withSize(32));
        addMetadata(response, ColumnMetadata.named("response").withIndex(11).ofType(Types.LONGVARCHAR).withSize(2147483647));
        addMetadata(responseTime, ColumnMetadata.named("response_time").withIndex(13).ofType(Types.BIGINT).withSize(19));
        addMetadata(stackTrace, ColumnMetadata.named("stack_trace").withIndex(14).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(timestamp, ColumnMetadata.named("timestamp").withIndex(15).ofType(Types.BIGINT).withSize(19));
    }

}

