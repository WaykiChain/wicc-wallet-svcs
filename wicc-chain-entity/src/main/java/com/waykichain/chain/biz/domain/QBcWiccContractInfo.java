package com.waykichain.chain.biz.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;

import com.querydsl.sql.ColumnMetadata;
import java.sql.Types;




/**
 * QBcWiccContractInfo is a Querydsl query type for BcWiccContractInfo
 */
@Generated("com.querydsl.sql.codegen.MetaDataSerializer")
public class QBcWiccContractInfo extends com.querydsl.sql.RelationalPathBase<BcWiccContractInfo> {

    private static final long serialVersionUID = 997234312;

    public static final QBcWiccContractInfo bcWiccContractInfo = new QBcWiccContractInfo("bc_wicc_contract_info");

    public final NumberPath<Integer> active = createNumber("active", Integer.class);

    public final StringPath adminAddress = createString("adminAddress");

    public final StringPath coinSymbol = createString("coinSymbol");

    public final StringPath contractAddress = createString("contractAddress");

    public final StringPath contractAddressRegId = createString("contractAddressRegId");

    public final DateTimePath<java.util.Date> createdAt = createDateTime("createdAt", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> updatedAt = createDateTime("updatedAt", java.util.Date.class);

    public final com.querydsl.sql.PrimaryKey<BcWiccContractInfo> primary = createPrimaryKey(id);

    public QBcWiccContractInfo(String variable) {
        super(BcWiccContractInfo.class, forVariable(variable), "null", "bc_wicc_contract_info");
        addMetadata();
    }

    public QBcWiccContractInfo(String variable, String schema, String table) {
        super(BcWiccContractInfo.class, forVariable(variable), schema, table);
        addMetadata();
    }

    public QBcWiccContractInfo(String variable, String schema) {
        super(BcWiccContractInfo.class, forVariable(variable), schema, "bc_wicc_contract_info");
        addMetadata();
    }

    public QBcWiccContractInfo(Path<? extends BcWiccContractInfo> path) {
        super(path.getType(), path.getMetadata(), "null", "bc_wicc_contract_info");
        addMetadata();
    }

    public QBcWiccContractInfo(PathMetadata metadata) {
        super(BcWiccContractInfo.class, metadata, "null", "bc_wicc_contract_info");
        addMetadata();
    }

    public void addMetadata() {
        addMetadata(active, ColumnMetadata.named("active").withIndex(6).ofType(Types.INTEGER).withSize(10));
        addMetadata(adminAddress, ColumnMetadata.named("admin_address").withIndex(3).ofType(Types.VARCHAR).withSize(64));
        addMetadata(coinSymbol, ColumnMetadata.named("coin_symbol").withIndex(2).ofType(Types.VARCHAR).withSize(32));
        addMetadata(contractAddress, ColumnMetadata.named("contract_address").withIndex(4).ofType(Types.VARCHAR).withSize(64));
        addMetadata(contractAddressRegId, ColumnMetadata.named("contract_address_reg_id").withIndex(5).ofType(Types.VARCHAR).withSize(32));
        addMetadata(createdAt, ColumnMetadata.named("created_at").withIndex(7).ofType(Types.TIMESTAMP).withSize(19).notNull());
        addMetadata(id, ColumnMetadata.named("id").withIndex(1).ofType(Types.BIGINT).withSize(20).notNull());
        addMetadata(updatedAt, ColumnMetadata.named("updated_at").withIndex(8).ofType(Types.TIMESTAMP).withSize(19));
    }

}

