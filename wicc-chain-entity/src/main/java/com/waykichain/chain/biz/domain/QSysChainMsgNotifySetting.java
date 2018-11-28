package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QSysChainMsgNotifySetting is a Querydsl query type for SysChainMsgNotifySetting
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QSysChainMsgNotifySetting extends com.querydsl.sql.RelationalPathBase<SysChainMsgNotifySetting> {

    private static final long serialVersionUID = -1316889457;

    public static final QSysChainMsgNotifySetting sysChainMsgNotifySetting = new QSysChainMsgNotifySetting("sys_chain_msg_notify_setting");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> creatorUid = createNumber("creatorUid", Long.class);

    public final StringPath description = createString("description");

    public final StringPath groupName = createString("groupName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath module = createString("module");

    public final StringPath msgFormat = createString("msgFormat");

    public final StringPath msgId = createString("msgId");

    public final StringPath msgUrl = createString("msgUrl");

    public final NumberPath<Integer> onSelf = createNumber("onSelf", Integer.class);

    public final NumberPath<Long> opUid = createNumber("opUid", Long.class);

    public final StringPath robotName = createString("robotName");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<SysChainMsgNotifySetting> primary = createPrimaryKey(id);

    public QSysChainMsgNotifySetting(String variable) {
        super(SysChainMsgNotifySetting.class, forVariable(variable), "null", "sys_chain_msg_notify_setting");
        addMetadata();
    }

    public QSysChainMsgNotifySetting(String variable, String schema, String table) {
        super(SysChainMsgNotifySetting.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QSysChainMsgNotifySetting(String variable, String schema) {
        super(SysChainMsgNotifySetting.class, forVariable(variable), schema, "sys_chain_msg_notify_setting");
        addMetadata();
    }

    public QSysChainMsgNotifySetting(Path<? extends SysChainMsgNotifySetting> path) {
        super(path.getType(), path.getMetadata(), "null", "sys_chain_msg_notify_setting");
        addMetadata();
    }

    public QSysChainMsgNotifySetting(PathMetadata metadata) {
        super(SysChainMsgNotifySetting.class, metadata, "null", "sys_chain_msg_notify_setting");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(12).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(creatorUid, ColumnMetadata.named("creator_uid").withIndex(9).ofType(Types.BIGINT).withSize(19));
        addMetadata(description, ColumnMetadata.named("description").withIndex(8).ofType(Types.VARCHAR).withSize(256));
        addMetadata(groupName, ColumnMetadata.named("group_name").withIndex(4).ofType(Types.VARCHAR).withSize(128));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(module, ColumnMetadata.named("module").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(msgFormat, ColumnMetadata.named("msg_format").withIndex(7).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(msgId, ColumnMetadata.named("msg_id").withIndex(3).ofType(Types.VARCHAR).withSize(128).notNull());
        addMetadata(msgUrl, ColumnMetadata.named("msg_url").withIndex(6).ofType(Types.VARCHAR).withSize(128));
        addMetadata(onSelf, ColumnMetadata.named("on_self").withIndex(11).ofType(Types.INTEGER).withSize(10));
        addMetadata(opUid, ColumnMetadata.named("op_uid").withIndex(10).ofType(Types.BIGINT).withSize(19));
        addMetadata(robotName, ColumnMetadata.named("robot_name").withIndex(5).ofType(Types.VARCHAR).withSize(128));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(13).ofType(Types.TIMESTAMP).withSize(19));
    }

}

