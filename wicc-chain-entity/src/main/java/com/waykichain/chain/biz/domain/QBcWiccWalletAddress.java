package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccWalletAddress is a Querydsl query type for BcWiccWalletAddress
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccWalletAddress extends com.querydsl.sql.RelationalPathBase<BcWiccWalletAddress> {

    private static final long serialVersionUID = -1427352301;

    public static final QBcWiccWalletAddress bcWiccWalletAddress = new QBcWiccWalletAddress("bc_wicc_wallet_address");

    public final StringPath address = createString("address");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Long> walletId = createNumber("walletId", Long.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccWalletAddress> primary = createPrimaryKey(id);

    public QBcWiccWalletAddress(String variable) {
        super(BcWiccWalletAddress.class, forVariable(variable), "null", "bc_wicc_wallet_address");
        addMetadata();
    }

    public QBcWiccWalletAddress(String variable, String schema, String table) {
        super(BcWiccWalletAddress.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccWalletAddress(String variable, String schema) {
        super(BcWiccWalletAddress.class, forVariable(variable), schema, "bc_wicc_wallet_address");
        addMetadata();
    }

    public QBcWiccWalletAddress(Path<? extends BcWiccWalletAddress> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_wallet_address");
        addMetadata();
    }

    public QBcWiccWalletAddress(PathMetadata metadata) {
        super(BcWiccWalletAddress.class, metadata, "null", "bc_wicc_wallet_address");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(5).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(password, ColumnMetadata.named("password").withIndex(4).ofType(Types.VARCHAR).withSize(64));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(6).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(walletId, ColumnMetadata.named("wallet_id").withIndex(2).ofType(Types.BIGINT).withSize(19));
    }

}

