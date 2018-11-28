package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QCoin is a Querydsl query type for Coin
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QCoin extends com.querydsl.sql.RelationalPathBase<Coin> {

    private static final long serialVersionUID = 37945030;

    public static final QCoin coin = new QCoin("coin");

    public final StringPath blockUrl = createString("blockUrl");

    public final NumberPath<Long> circulationAmount = createNumber("circulationAmount", Long.class);

    public final StringPath contractAddress = createString("contractAddress");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath description = createString("description");

    public final StringPath iconUrl = createString("iconUrl");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Integer> miniConfirmCount = createNumber("miniConfirmCount", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> onShelf = createNumber("onShelf", Integer.class);

    public final NumberPath<Integer> precision = createNumber("precision", Integer.class);

    public final StringPath regId = createString("regId");

    public final NumberPath<Long> releaseAmount = createNumber("releaseAmount", Long.class);

    public final DateTimePath<java.util.Date> releaseDate = createDateTime("releaseDate", java.util.Date.class);

    public final NumberPath<Integer> startNumber = createNumber("startNumber", Integer.class);

    public final StringPath symbol = createString("symbol");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final StringPath whitepaperUrl = createString("whitepaperUrl");

    public final com.querydsl.sql.PrimaryKey<Coin> primary = createPrimaryKey(id);

    public QCoin(String variable) {
        super(Coin.class, forVariable(variable), "null", "coin");
        addMetadata();
    }

    public QCoin(String variable, String schema, String table) {
        super(Coin.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QCoin(String variable, String schema) {
        super(Coin.class, forVariable(variable), schema, "coin");
        addMetadata();
    }

    public QCoin(Path<? extends Coin> path) {
        super(path.getType(), path.getMetadata(), "null", "coin");
        addMetadata();
    }

    public QCoin(PathMetadata metadata) {
        super(Coin.class, metadata, "null", "coin");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(blockUrl, ColumnMetadata.named("block_url").withIndex(14).ofType(Types.VARCHAR).withSize(512));
        addMetadata(circulationAmount, ColumnMetadata.named("circulation_amount").withIndex(11).ofType(Types.BIGINT).withSize(19));
        addMetadata(contractAddress, ColumnMetadata.named("contract_address").withIndex(5).ofType(Types.VARCHAR).withSize(64));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(17).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(description, ColumnMetadata.named("description").withIndex(6).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(iconUrl, ColumnMetadata.named("icon_url").withIndex(13).ofType(Types.VARCHAR).withSize(512));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(miniConfirmCount, ColumnMetadata.named("mini_confirm_count").withIndex(10).ofType(Types.INTEGER).withSize(10));
        addMetadata(name, ColumnMetadata.named("name").withIndex(3).ofType(Types.VARCHAR).withSize(32));
        addMetadata(onShelf, ColumnMetadata.named("on_shelf").withIndex(16).ofType(Types.INTEGER).withSize(10));
        addMetadata(precision, ColumnMetadata.named("precision").withIndex(15).ofType(Types.INTEGER).withSize(10));
        addMetadata(regId, ColumnMetadata.named("reg_id").withIndex(4).ofType(Types.VARCHAR).withSize(16));
        addMetadata(releaseAmount, ColumnMetadata.named("release_amount").withIndex(8).ofType(Types.BIGINT).withSize(19));
        addMetadata(releaseDate, ColumnMetadata.named("release_date").withIndex(7).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(startNumber, ColumnMetadata.named("start_number").withIndex(9).ofType(Types.INTEGER).withSize(10));
        addMetadata(symbol, ColumnMetadata.named("symbol").withIndex(2).ofType(Types.VARCHAR).withSize(32));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(18).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(whitepaperUrl, ColumnMetadata.named("whitepaper_url").withIndex(12).ofType(Types.VARCHAR).withSize(512));
    }

}

