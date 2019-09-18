package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccWalletAccount is a Querydsl query type for BcWiccWalletAccount
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccWalletAccount extends com.querydsl.sql.RelationalPathBase<BcWiccWalletAccount> {

    private static final long serialVersionUID = -1456979124;

    public static final QBcWiccWalletAccount bcWiccWalletAccount = new QBcWiccWalletAccount("bc_wicc_wallet_account");

    public final StringPath address = createString("address");

    public final StringPath assetsTokens = createString("assetsTokens");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final StringPath coinSymbol = createString("coinSymbol");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath desc = createString("desc");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath memo = createString("memo");

    public final NumberPath<Integer> type = createNumber("type", Integer.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccWalletAccount> primary = createPrimaryKey(id);

    public QBcWiccWalletAccount(String variable) {
        super(BcWiccWalletAccount.class, forVariable(variable), "null", "bc_wicc_wallet_account");
        addMetadata();
    }

    public QBcWiccWalletAccount(String variable, String schema, String table) {
        super(BcWiccWalletAccount.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccWalletAccount(String variable, String schema) {
        super(BcWiccWalletAccount.class, forVariable(variable), schema, "bc_wicc_wallet_account");
        addMetadata();
    }

    public QBcWiccWalletAccount(Path<? extends BcWiccWalletAccount> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_wallet_account");
        addMetadata();
    }

    public QBcWiccWalletAccount(PathMetadata metadata) {
        super(BcWiccWalletAccount.class, metadata, "null", "bc_wicc_wallet_account");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(address, ColumnMetadata.named("address").withIndex(2).ofType(Types.VARCHAR).withSize(64));
        addMetadata(assetsTokens, ColumnMetadata.named("assets_tokens").withIndex(6).ofType(Types.VARCHAR).withSize(256));
        addMetadata(balance, ColumnMetadata.named("balance").withIndex(5).ofType(Types.BIGINT).withSize(19));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(4).ofType(Types.VARCHAR).withSize(32));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(9).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(desc, ColumnMetadata.named("desc").withIndex(8).ofType(Types.VARCHAR).withSize(128));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(memo, ColumnMetadata.named("memo").withIndex(7).ofType(Types.VARCHAR).withSize(512));
        addMetadata(type, ColumnMetadata.named("type").withIndex(3).ofType(Types.INTEGER).withSize(10));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(10).ofType(Types.TIMESTAMP).withSize(19));
    }

}

