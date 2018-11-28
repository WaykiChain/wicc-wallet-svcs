package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSysConfig is a Querydsl query type for SysConfig
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSysConfig extends com.querydsl.sql.RelationalPathBase<SysConfig> {

    private static final long serialVersionUID = -120870566;

    public static final QSysConfig sysConfig = new QSysConfig("sys_config");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> creatorUid = createNumber("creatorUid", Long.class);

    public final StringPath description = createString("description");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath module = createString("module");

    public final StringPath name = createString("name");

    public final NumberPath<Long> opUid = createNumber("opUid", Long.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath value = createString("value");

    public final com.querydsl.sql.PrimaryKey<SysConfig> primary = createPrimaryKey(id);

    public QSysConfig(String variable) {
        super(SysConfig.class, forVariable(variable), "null", "sys_config");
        addMetadata();
    }

    public QSysConfig(String variable, String schema, String table) {
        super(SysConfig.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSysConfig(String variable, String schema) {
        super(SysConfig.class, forVariable(variable), schema, "sys_config");
        addMetadata();
    }

    public QSysConfig(Path<? extends SysConfig> path) {
        super(path.getType(), path.getMetadata(), "null", "sys_config");
        addMetadata();
    }

    public QSysConfig(PathMetadata metadata) {
        super(SysConfig.class, metadata, "null", "sys_config");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(8).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(creatorUid, ColumnMetadata.named("creator_uid").withIndex(6).ofType(Types.BIGINT).withSize(19));
        addMetadata(description, ColumnMetadata.named("description").withIndex(5).ofType(Types.VARCHAR).withSize(256));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(module, ColumnMetadata.named("module").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(128).notNull());
        addMetadata(opUid, ColumnMetadata.named("op_uid").withIndex(7).ofType(Types.BIGINT).withSize(19));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(9).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(value, ColumnMetadata.named("value").withIndex(4).ofType(Types.LONGVARCHAR).withSize(65535));
    }

}

