package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QRollbackBlockBak is a Querydsl query type for RollbackBlockBak
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QRollbackBlockBak extends com.querydsl.sql.RelationalPathBase<RollbackBlockBak> {

    private static final long serialVersionUID = -753680392;

    public static final QRollbackBlockBak rollbackBlockBak = new QRollbackBlockBak("rollback_block_bak");

    public final StringPath chainwork = createString("chainwork");

    public final NumberPath<Integer> confirmations = createNumber("confirmations", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> fuel = createNumber("fuel", Integer.class);

    public final NumberPath<Integer> fuelRate = createNumber("fuelRate", Integer.class);

    public final StringPath hash = createString("hash");

    public final StringPath hashOnChain = createString("hashOnChain");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath merkleRoot = createString("merkleRoot");

    public final StringPath nextBlockHash = createString("nextBlockHash");

    public final NumberPath<Long> nonce = createNumber("nonce", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final StringPath previousBlockHash = createString("previousBlockHash");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final NumberPath<Integer> status = createNumber("status", Integer.class);

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.querydsl.sql.PrimaryKey<RollbackBlockBak> primary = createPrimaryKey(id);

    public QRollbackBlockBak(String variable) {
        super(RollbackBlockBak.class, forVariable(variable), "null", "rollback_block_bak");
        addMetadata();
    }

    public QRollbackBlockBak(String variable, String schema, String table) {
        super(RollbackBlockBak.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QRollbackBlockBak(String variable, String schema) {
        super(RollbackBlockBak.class, forVariable(variable), schema, "rollback_block_bak");
        addMetadata();
    }

    public QRollbackBlockBak(Path<? extends RollbackBlockBak> path) {
        super(path.getType(), path.getMetadata(), "null", "rollback_block_bak");
        addMetadata();
    }

    public QRollbackBlockBak(PathMetadata metadata) {
        super(RollbackBlockBak.class, metadata, "null", "rollback_block_bak");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(chainwork, ColumnMetadata.named("chainwork").withIndex(12).ofType(Types.VARCHAR).withSize(256));
        addMetadata(confirmations, ColumnMetadata.named("confirmations").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(18).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(fuel, ColumnMetadata.named("fuel").withIndex(13).ofType(Types.INTEGER).withSize(10));
        addMetadata(fuelRate, ColumnMetadata.named("fuel_rate").withIndex(14).ofType(Types.INTEGER).withSize(10));
        addMetadata(hash, ColumnMetadata.named("hash").withIndex(3).ofType(Types.VARCHAR).withSize(128));
        addMetadata(hashOnChain, ColumnMetadata.named("hash_on_chain").withIndex(4).ofType(Types.VARCHAR).withSize(128));
        addMetadata(height, ColumnMetadata.named("height").withIndex(7).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(merkleRoot, ColumnMetadata.named("merkle_root").withIndex(9).ofType(Types.VARCHAR).withSize(64));
        addMetadata(nextBlockHash, ColumnMetadata.named("next_block_hash").withIndex(16).ofType(Types.VARCHAR).withSize(64));
        addMetadata(nonce, ColumnMetadata.named("nonce").withIndex(11).ofType(Types.BIGINT).withSize(19));
        addMetadata(number, ColumnMetadata.named("number").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(previousBlockHash, ColumnMetadata.named("previous_block_hash").withIndex(15).ofType(Types.VARCHAR).withSize(64));
        addMetadata(size, ColumnMetadata.named("size").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(status, ColumnMetadata.named("status").withIndex(17).ofType(Types.INTEGER).withSize(10));
        addMetadata(time, ColumnMetadata.named("time").withIndex(10).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(19).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(version, ColumnMetadata.named("version").withIndex(8).ofType(Types.INTEGER).withSize(10));
    }

}

