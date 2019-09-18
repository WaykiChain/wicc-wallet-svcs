package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccTransaction is a Querydsl query type for BcWiccTransaction
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccTransaction extends com.querydsl.sql.RelationalPathBase<BcWiccTransaction> {

    private static final long serialVersionUID = 1941567542;

    public static final QBcWiccTransaction bcWiccTransaction = new QBcWiccTransaction("bc_wicc_transaction");

    public final StringPath addr = createString("addr");

    public final StringPath blockHash = createString("blockHash");

    public final NumberPath<Integer> blockNumber = createNumber("blockNumber", Integer.class);

    public final NumberPath<Long> money = createNumber("money", Long.class);

    public final StringPath coinSymbol = createString("coinSymbol");

    public final NumberPath<Long> confirmedTime = createNumber("confirmedTime", Long.class);

    public final NumberPath<Integer> confirmHeight = createNumber("confirmHeight", Integer.class);

    public final StringPath contract = createString("contract");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final StringPath desaddr = createString("desaddr");

    public final StringPath descregid = createString("descregid");

    public final NumberPath<Long> fees = createNumber("fees", Long.class);

    public final StringPath feeSymbol = createString("feeSymbol");

    public final NumberPath<Integer> height = createNumber("height", Integer.class);

    public final NumberPath<Integer> id = createNumber("id", Integer.class);

    public final StringPath listOutput = createString("listOutput");

    public final StringPath minerPubkey = createString("minerPubkey");

    public final StringPath operVoteFundList = createString("operVoteFundList");

    public final StringPath pubkey = createString("pubkey");

    public final StringPath rawtx = createString("rawtx");

    public final StringPath regid = createString("regid");

    public final StringPath script = createString("script");

    public final StringPath txContent = createString("txContent");

    public final StringPath txid = createString("txid");

    public final StringPath txType = createString("txType");

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final NumberPath<Integer> ver = createNumber("ver", Integer.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccTransaction> primary = createPrimaryKey(id);

    public QBcWiccTransaction(String variable) {
        super(BcWiccTransaction.class, forVariable(variable), "null", "bc_wicc_transaction");
        addMetadata();
    }

    public QBcWiccTransaction(String variable, String schema, String table) {
        super(BcWiccTransaction.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccTransaction(String variable, String schema) {
        super(BcWiccTransaction.class, forVariable(variable), schema, "bc_wicc_transaction");
        addMetadata();
    }

    public QBcWiccTransaction(Path<? extends BcWiccTransaction> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_transaction");
        addMetadata();
    }

    public QBcWiccTransaction(PathMetadata metadata) {
        super(BcWiccTransaction.class, metadata, "null", "bc_wicc_transaction");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(addr, ColumnMetadata.named("addr").withIndex(8).ofType(Types.VARCHAR).withSize(64));
        addMetadata(blockHash, ColumnMetadata.named("block_hash").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(blockNumber, ColumnMetadata.named("block_number").withIndex(2).ofType(Types.INTEGER).withSize(10));
        addMetadata(money, ColumnMetadata.named("money").withIndex(13).ofType(Types.BIGINT).withSize(19));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(15).ofType(Types.VARCHAR).withSize(32));
        addMetadata(confirmedTime, ColumnMetadata.named("confirmed_time").withIndex(22).ofType(Types.BIGINT).withSize(19));
        addMetadata(confirmHeight, ColumnMetadata.named("confirm_height").withIndex(21).ofType(Types.INTEGER).withSize(10));
        addMetadata(contract, ColumnMetadata.named("contract").withIndex(19).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(26).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(desaddr, ColumnMetadata.named("desaddr").withIndex(10).ofType(Types.VARCHAR).withSize(64));
        addMetadata(descregid, ColumnMetadata.named("descregid").withIndex(9).ofType(Types.VARCHAR).withSize(64));
        addMetadata(fees, ColumnMetadata.named("fees").withIndex(14).ofType(Types.BIGINT).withSize(19));
        addMetadata(feeSymbol, ColumnMetadata.named("fee_symbol").withIndex(16).ofType(Types.VARCHAR).withSize(32));
        addMetadata(height, ColumnMetadata.named("height").withIndex(18).ofType(Types.INTEGER).withSize(10));
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.INTEGER).withSize(10).notNull());
        addMetadata(listOutput, ColumnMetadata.named("list_output").withIndex(25).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(minerPubkey, ColumnMetadata.named("miner_pubkey").withIndex(12).ofType(Types.VARCHAR).withSize(128));
        addMetadata(operVoteFundList, ColumnMetadata.named("oper_vote_fund_list").withIndex(20).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(pubkey, ColumnMetadata.named("pubkey").withIndex(11).ofType(Types.VARCHAR).withSize(128));
        addMetadata(rawtx, ColumnMetadata.named("rawtx").withIndex(23).ofType(Types.LONGVARCHAR).withSize(16777215));
        addMetadata(regid, ColumnMetadata.named("regid").withIndex(7).ofType(Types.VARCHAR).withSize(64));
        addMetadata(script, ColumnMetadata.named("script").withIndex(17).ofType(Types.LONGVARCHAR).withSize(65535));
        addMetadata(txContent, ColumnMetadata.named("tx_content").withIndex(24).ofType(Types.LONGVARCHAR).withSize(16777215));
        addMetadata(txid, ColumnMetadata.named("txid").withIndex(4).ofType(Types.VARCHAR).withSize(64));
        addMetadata(txType, ColumnMetadata.named("tx_type").withIndex(5).ofType(Types.VARCHAR).withSize(64));
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(27).ofType(Types.TIMESTAMP).withSize(19));
        addMetadata(ver, ColumnMetadata.named("ver").withIndex(6).ofType(Types.INTEGER).withSize(10));
    }

}

