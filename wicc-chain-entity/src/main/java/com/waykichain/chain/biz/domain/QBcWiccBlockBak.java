package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccBlockBak is a Querydsl query type for BcWiccBlockBak
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccBlockBak extends com.querydsl.sql.RelationalPathBase<BcWiccBlockBak> {

    private static final long serialVersionUID = 1135053209;

    public static final QBcWiccBlockBak bcWiccBlockBak = new QBcWiccBlockBak("bc_wicc_block_bak");

    public final StringPath chainwork = createString("chainwork");

    public final NumberPath<Integer> confirmations = createNumber("confirmations", Integer.class);

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Integer> fuel = createNumber("fuel", Integer.class);

    public final NumberPath<Integer> fuelRate = createNumber("fuelRate", Integer.class);

    public final StringPath hash = createString("hash");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath merkleRoot = createString("merkleRoot");

    public final StringPath nextBlockHash = createString("nextBlockHash");

    public final NumberPath<Long> nonce = createNumber("nonce", Long.class);

    public final NumberPath<Integer> number = createNumber("number", Integer.class);

    public final StringPath previousBlockHash = createString("previousBlockHash");

    public final NumberPath<Integer> size = createNumber("size", Integer.class);

    public final DateTimePath<java.util.Date> time = createDateTime("time", java.util.Date.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccBlockBak> primary = createPrimaryKey(id);

    public QBcWiccBlockBak(String variable) {
        super(BcWiccBlockBak.class, forVariable(variable), "null", "bc_wicc_block_bak");
        addMetadata();
    }

    public QBcWiccBlockBak(String variable, String schema, String table) {
        super(BcWiccBlockBak.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccBlockBak(String variable, String schema) {
        super(BcWiccBlockBak.class, forVariable(variable), schema, "bc_wicc_block_bak");
        addMetadata();
    }

    public QBcWiccBlockBak(Path<? extends BcWiccBlockBak> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_block_bak");
        addMetadata();
    }

    public QBcWiccBlockBak(PathMetadata metadata) {
        super(BcWiccBlockBak.class, metadata, "null", "bc_wicc_block_bak");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(chainwork, ColumnMetadata.named("chainwork").withIndex(11).ofType(Types.VARCHAR).withSize(256));
        addMetadata(confirmations, ColumnMetadata.named("confirmations").withIndex(4).ofType(Types.INTEGER).withSize(10));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(16).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(fuel, ColumnMetadata.named("fuel").withIndex(12).ofType(Types.INTEGER).withSize(10));
        addMetadata(fuelRate, ColumnMetadata.named("fuel_rate").withIndex(13).ofType(Types.INTEGER).withSize(10));
        addMetadata(hash, ColumnMetadata.named("hash").withIndex(3).ofType(Types.VARCHAR).withSize(128));
        addMetadata(height, ColumnMetadata.named("height").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(merkleRoot, ColumnMetadata.named("merkle_root").withIndex(8).ofType(Types.VARCHAR).withSize(64));
        addMetadata(nextBlockHash, ColumnMetadata.named("next_block_hash").withIndex(15).ofType(Types.VARCHAR).withSize(64));
        addMetadata(nonce, ColumnMetadata.named("nonce").withIndex(10).ofType(Types.BIGINT).withSize(19));
        addMetadata(number, ColumnMetadata.named("number").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(previousBlockHash, ColumnMetadata.named("previous_block_hash").withIndex(14).ofType(Types.VARCHAR).withSize(64));
        addMetadata(size, ColumnMetadata.named("size").withIndex(5).ofType(Types.INTEGER).withSize(10));
        addMetadata(time, ColumnMetadata.named("time").withIndex(9).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(17).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(version, ColumnMetadata.named("version").withIndex(7).ofType(Types.INTEGER).withSize(10));
    }

}

