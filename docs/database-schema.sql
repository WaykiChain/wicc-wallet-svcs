-- Create syntax for TABLE 'bc_wicc_account_ajust_log'
CREATE TABLE `bc_wicc_account_ajust_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `eod` date DEFAULT NULL,
  `address` varchar(64) DEFAULT NULL,
  `type` int(11) DEFAULT '100',
  `coin_symbol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资产货币符号 (E.g. btc)',
  `before_balance` bigint(20) DEFAULT '0' COMMENT '可用余额',
  `ajust_balance` bigint(20) DEFAULT NULL COMMENT '链上余额',
  `after_balance` bigint(20) DEFAULT NULL COMMENT '差额',
  `ajust_at` datetime DEFAULT NULL COMMENT '调整时间',
  `deal_date` int(11) DEFAULT NULL COMMENT '处理时间',
  `deal_balance` bigint(20) DEFAULT NULL COMMENT '处理的时候余额',
  `diff_reason` text COMMENT '差额原因',
  `deal_memo` text COMMENT '处理记录',
  `memo` varchar(36) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_ca_address` (`address`),
  KEY `idx_ca_created_at` (`created_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户余额调整信息';

-- Create syntax for TABLE 'bc_wicc_account_check_batch'
CREATE TABLE `bc_wicc_account_check_batch` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `eod` date DEFAULT NULL COMMENT '批次日期',
  `check_type` int(11) DEFAULT NULL COMMENT '10自动 20.手动批次',
  `status` int(11) DEFAULT NULL COMMENT '状态：10:处理中/放款中；80：已完成',
  `count` int(11) DEFAULT NULL COMMENT '完成个数',
  `amount` bigint(20) DEFAULT NULL COMMENT '校验总额',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间|申请时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_cacb_eod` (`eod`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'bc_wicc_account_check_log'
CREATE TABLE `bc_wicc_account_check_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `eod` date DEFAULT NULL COMMENT '生效日',
  `batch_id` bigint(20) DEFAULT NULL COMMENT '批次号',
  `address` varchar(64) DEFAULT NULL COMMENT '地址',
  `type` int(11) DEFAULT '100' COMMENT '类型',
  `coin_symbol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资产货币符号 (E.g. btc)',
  `balance` bigint(20) DEFAULT '0' COMMENT '可用余额',
  `chain_balance` bigint(20) DEFAULT NULL COMMENT '链上余额',
  `diff_amount` bigint(20) DEFAULT NULL COMMENT '差额',
  `status` int(11) DEFAULT NULL COMMENT '这个账号的状态(100:已生成| 400:待处理 | 800:正常状态| 880:处理完成状态| 900:冻结状态)',
  `deal_date` datetime DEFAULT NULL COMMENT '处理时间',
  `deal_balance` bigint(20) DEFAULT NULL COMMENT '处理的时候余额',
  `deal_chain_balance` bigint(20) DEFAULT NULL,
  `diff_reason` text COMMENT '差额原因',
  `deal_memo` text COMMENT '处理记录',
  `memo` varchar(36) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bwacl_eod` (`eod`),
  KEY `idx_bwacl_batch_id` (`batch_id`),
  KEY `idx_bwacl_address` (`address`),
  KEY `idx_bwacl_diff_amount` (`diff_amount`),
  KEY `idx_bwacl_created_at` (`created_at`)
) ENGINE=InnoDB AUTO_INCREMENT=44278 DEFAULT CHARSET=utf8mb4 COMMENT='交易所客户账号余额（不直接和区块链发生关系）';

-- Create syntax for TABLE 'bc_wicc_alert_log'
CREATE TABLE `bc_wicc_alert_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数字货币钱包地址',
  `notify_link_url` text COLLATE utf8mb4_unicode_ci,
  `alert_type` int(11) DEFAULT NULL COMMENT '类型',
  `alert_param` text COLLATE utf8mb4_unicode_ci COMMENT '参数',
  `alert_info` longtext COLLATE utf8mb4_unicode_ci,
  `active` int(11) DEFAULT NULL COMMENT '是否有效',
  `memo` text COLLATE utf8mb4_unicode_ci COMMENT '备注',
  `alert_at` int(11) DEFAULT NULL COMMENT '通知时间',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bwal_alert_at` (`alert_at`),
  KEY `idx_bwal_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='异常日志信息';

-- Create syntax for TABLE 'bc_wicc_block'
CREATE TABLE `bc_wicc_block` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `number` int(11) DEFAULT NULL COMMENT '区块号	',
  `hash` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `confirmations` int(11) DEFAULT NULL COMMENT '确认次数',
  `size` int(11) DEFAULT NULL COMMENT '快大小 （KB）',
  `height` int(11) DEFAULT NULL,
  `version` int(11) DEFAULT NULL COMMENT '哈希值',
  `merkle_root` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `time` datetime DEFAULT NULL COMMENT '交易费',
  `nonce` bigint(20) DEFAULT NULL,
  `chainwork` varchar(256) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `fuel` int(11) DEFAULT NULL,
  `fuel_rate` int(11) DEFAULT NULL COMMENT '矿工',
  `previous_block_hash` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `next_block_hash` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bwb_number` (`number`),
  KEY `idx_bwb_hash` (`hash`),
  KEY `idx_bwb_time` (`time`)
) ENGINE=InnoDB AUTO_INCREMENT=366311 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create syntax for TABLE 'bc_wicc_contract_info'
CREATE TABLE `bc_wicc_contract_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `coin_symbol` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '货币类型',
  `admin_address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '管理员账号',
  `contract_address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合约的地址',
  `contract_address_reg_id` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `active` int(11) DEFAULT '1',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bwci_contract_address` (`contract_address`),
  KEY `unique_bwtcwa_address` (`admin_address`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钱包地址';

-- Create syntax for TABLE 'bc_wicc_monitor_address'
CREATE TABLE `bc_wicc_monitor_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数字货币钱包地址',
  `monitor_percent` int(11) DEFAULT NULL COMMENT '通知百分比',
  `monitor_amount` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '类型',
  `active` int(11) DEFAULT NULL COMMENT '是否有效',
  `memo` text COLLATE utf8mb4_unicode_ci COMMENT '备注',
  `current_balance` bigint(20) DEFAULT '1' COMMENT '当前余额',
  `heigh` int(11) DEFAULT NULL COMMENT '已确认高度',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uinque_bwma_address` (`address`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='监控地址配置';

-- Create syntax for TABLE 'bc_wicc_offline_transacation_log'
CREATE TABLE `bc_wicc_offline_transacation_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `request_uuid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `txid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易ID',
  `info` mediumtext COLLATE utf8mb4_unicode_ci,
  `status` int(11) DEFAULT NULL,
  `remark` text COLLATE utf8mb4_unicode_ci,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bwstl_request_uuid` (`request_uuid`),
  KEY `unique_bwstl_txid` (`txid`),
  KEY `idx_bwstl_created_at` (`created_at`)
) ENGINE=InnoDB AUTO_INCREMENT=732 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钱包地址';

-- Create syntax for TABLE 'bc_wicc_send_transaction_log'
CREATE TABLE `bc_wicc_send_transaction_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `request_uuid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `recv_address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数字货币钱包地址',
  `amount` bigint(20) DEFAULT NULL COMMENT '交易金额',
  `trans_amount` bigint(20) DEFAULT NULL COMMENT '用户可得金额',
  `trans_fee` bigint(20) DEFAULT NULL COMMENT '交易费',
  `txid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易ID',
  `prameter` mediumtext COLLATE utf8mb4_unicode_ci,
  `status` int(11) DEFAULT NULL,
  `remark` text COLLATE utf8mb4_unicode_ci,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_bwstl_request_uuid` (`request_uuid`),
  KEY `idx_bwstl_recv_address` (`recv_address`),
  KEY `unique_bwstl_txid` (`txid`),
  KEY `idx_bwstl_created_at` (`created_at`)
) ENGINE=InnoDB AUTO_INCREMENT=7578 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钱包地址';

-- Create syntax for TABLE 'bc_wicc_transaction'
CREATE TABLE `bc_wicc_transaction` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `block_number` int(11) DEFAULT NULL COMMENT '区块号	',
  `block_hash` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `txid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易号',
  `tx_type` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `ver` int(11) DEFAULT NULL,
  `regid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `addr` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `descregid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `desaddr` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `pubkey` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `miner_pubkey` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  `fees` bigint(20) DEFAULT NULL,
  `script` text COLLATE utf8mb4_unicode_ci,
  `height` int(11) DEFAULT NULL,
  `contract` text COLLATE utf8mb4_unicode_ci,
  `oper_vote_fund_list` text COLLATE utf8mb4_unicode_ci,
  `confirm_height` int(11) DEFAULT NULL,
  `confirmed_time` bigint(20) DEFAULT NULL,
  `rawtx` mediumtext COLLATE utf8mb4_unicode_ci,
  `list_output` text COLLATE utf8mb4_unicode_ci,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_bwt_block_number` (`block_number`),
  KEY `idx_bwt_block_hash` (`block_hash`),
  KEY `idx_bwt_txid` (`txid`),
  KEY `idx_bwt_addr` (`addr`),
  KEY `idx_bwt_desaddr` (`desaddr`)
) ENGINE=InnoDB AUTO_INCREMENT=371026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create syntax for TABLE 'bc_wicc_wallet_account'
CREATE TABLE `bc_wicc_wallet_account` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(64) DEFAULT NULL COMMENT '账户地址',
  `type` int(11) DEFAULT '100' COMMENT '100:普通账号,500:挖矿节点地址',
  `coin_symbol` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '资产货币符号 (E.g. btc)',
  `balance` bigint(20) DEFAULT '0' COMMENT '可用余额',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_wa_address` (`address`),
  KEY `idx_bwwa_created_at` (`created_at`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8mb4 COMMENT='账户余额';

-- Create syntax for TABLE 'bc_wicc_wallet_account_log'
CREATE TABLE `bc_wicc_wallet_account_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `type` int(11) DEFAULT NULL COMMENT '110: 充值，710: 下庄， 720：投注',
  `tx_type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '交易方式:COMMON_TX | 合约:CONTRACT_TX | 激活:REG_ACCT_TX | 发布合约:REG_APP_TX |挖矿奖励: REWARD_TX',
  `coin_symbol` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数字货币符号',
  `before_balance` bigint(20) DEFAULT NULL COMMENT '操作前钱包余额',
  `available_amount` bigint(20) DEFAULT NULL COMMENT '本次操作货币数量(正数加，负数减)',
  `fees` bigint(20) DEFAULT NULL COMMENT '转账小费',
  `after_balance` bigint(20) DEFAULT NULL COMMENT '操作后钱包金额',
  `txid` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '交易ID',
  `number` int(11) DEFAULT NULL COMMENT '高度',
  `confirmed_at` datetime DEFAULT NULL COMMENT '确认时间',
  `remark` text COLLATE utf8mb4_unicode_ci COMMENT '备注',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_wal_address` (`address`),
  KEY `idx_bwwal_txid` (`txid`),
  KEY `idx_bwwal_number` (`number`),
  KEY `idx_bwwal_confirmed_at` (`confirmed_at`)
) ENGINE=InnoDB AUTO_INCREMENT=375539 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='区块链对应的客户托管充币地址（具有地址公私钥对）';

-- Create syntax for TABLE 'bc_wicc_wallet_address'
CREATE TABLE `bc_wicc_wallet_address` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `wallet_id` bigint(20) DEFAULT NULL COMMENT '钱包ID',
  `address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '数字货币钱包地址',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '钱包地址解锁密码',
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `unique_bwtcwa_address` (`address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='钱包地址';

-- Create syntax for TABLE 'coin'
CREATE TABLE `coin` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `symbol` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '货币符合',
  `name` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '名字',
  `reg_id` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合约的RegId',
  `contract_address` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '合约地址',
  `description` text COLLATE utf8mb4_unicode_ci COMMENT '描述',
  `release_date` datetime DEFAULT NULL COMMENT '发布日期',
  `release_amount` bigint(20) DEFAULT NULL COMMENT '发布个数',
  `start_number` int(11) DEFAULT NULL COMMENT '开始扫描的起始高度',
  `mini_confirm_count` int(11) DEFAULT NULL COMMENT '最少确认次数',
  `circulation_amount` bigint(20) DEFAULT NULL COMMENT '发行量',
  `whitepaper_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '白皮书地址',
  `icon_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图标URL',
  `block_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '区块地址URL',
  `precision` int(11) DEFAULT NULL COMMENT '精度( 比特币:8)',
  `on_shelf` int(11) DEFAULT NULL COMMENT '是否上架(0否1是）',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_c_symbol` (`symbol`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create syntax for TABLE 'sys_chain_msg_notify_setting'
CREATE TABLE `sys_chain_msg_notify_setting` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `module` varchar(64) DEFAULT NULL COMMENT '模块',
  `msg_id` varchar(128) NOT NULL DEFAULT '' COMMENT '配置变量名',
  `group_name` varchar(128) DEFAULT NULL COMMENT '组名',
  `robot_name` varchar(128) DEFAULT NULL COMMENT '机器人名字',
  `msg_url` varchar(128) DEFAULT NULL COMMENT '请求地址',
  `msg_format` text COMMENT '格式',
  `description` varchar(256) DEFAULT NULL COMMENT '说明',
  `creator_uid` bigint(20) DEFAULT NULL COMMENT '创建着UID',
  `op_uid` bigint(20) DEFAULT NULL COMMENT '操作员UID',
  `on_self` int(11) DEFAULT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `index_smns_msg_id` (`msg_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Create syntax for TABLE 'sys_chain_request_log'
CREATE TABLE `sys_chain_request_log` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `customer_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `device_uuid` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `error_code` int(11) DEFAULT NULL COMMENT '访问密钥',
  `request_uuid` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求唯一编号',
  `method` varchar(11) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求方式',
  `module` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '请求时间毫秒值',
  `request_ip` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '请求参数',
  `request_parmas` text COLLATE utf8mb4_unicode_ci COMMENT '签名',
  `request_uri` varchar(64) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `response` longtext COLLATE utf8mb4_unicode_ci,
  `request_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `response_time` bigint(20) DEFAULT NULL COMMENT '响应',
  `stack_trace` text COLLATE utf8mb4_unicode_ci COMMENT '请求地址',
  `timestamp` bigint(20) DEFAULT NULL COMMENT 'ip',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_srl_request_uuid` (`request_uuid`),
  KEY `idx_hrl_customer_id` (`customer_id`),
  KEY `idx_hrl_device_log` (`device_uuid`),
  KEY `idx_hrl_method` (`method`),
  KEY `idx_hrl_created_at` (`created_at`)
) ENGINE=InnoDB AUTO_INCREMENT=144626 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create syntax for TABLE 'sys_config'
CREATE TABLE `sys_config` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `module` varchar(64) DEFAULT NULL COMMENT '模块（openAPi,risk,pay,repay,debt）',
  `name` varchar(128) NOT NULL COMMENT '配置变量名',
  `value` text COMMENT '配置变量值',
  `description` varchar(256) DEFAULT NULL COMMENT '说明',
  `creator_uid` bigint(20) DEFAULT NULL COMMENT '创建着UID',
  `op_uid` bigint(20) DEFAULT NULL COMMENT '操作员UID',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_sys_conf_name` (`name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;