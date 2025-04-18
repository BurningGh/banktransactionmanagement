DROP TABLE IF EXISTS `transactions`;
CREATE TABLE `transactions` (
    `id` bigint(20) NOT NULL COMMENT '主键' ,
    `description` VARCHAR(255) NOT NULL COMMENT '描述',
    `buyer_id` bigint(20) NOT NULL COMMENT '购买者ID',
    `product_id` bigint(20) NOT NULL COMMENT '商品 ID',
    `quantity` INT NOT NULL COMMENT '购买数量',
    `amount` DECIMAL(13, 2) NOT NULL COMMENT '订单金额',
    `status` int NOT NULL COMMENT '订单状态 1 待支付 2 支付成功 3 支付失败',
    `order_time` DATETIME NOT NULL COMMENT '下单时间和创建时间可能不一致',
    `payment_time` DATETIME COMMENT '支付时间',
    `memo` VARCHAR(120) COMMENT '备注',
    `create_by` VARCHAR(64) NOT NULL COMMENT ' 创建人，不一定是购买者',
    `create_at` DATETIME NOT NULL COMMENT '创建时间',
    `update_by` VARCHAR(64) COMMENT '更新人',
    `update_at` DATETIME COMMENT '更新事件',
    `deleted` BOOLEAN DEFAULT FALSE COMMENT '假删除 1 删除 0 不删除',
    PRIMARY KEY (`id`)
);

DROP TABLE IF EXISTS `audit_logs`;
CREATE TABLE `audit_logs` (
    `id` bigint(20) NOT NULL COMMENT '主键',
    `operation_object_id` bigint(20) COMMENT '操作对象id' ,
    `operation_type` int COMMENT '操作类型,见枚举说明' ,
    `operator` VARCHAR(64) COMMENT '操作人',
    `operation_content` TEXT COMMENT '操作内容的json字符串',
    `operation_description` VARCHAR(64) COMMENT '操作描述',
    `operation_time` DATETIME COMMENT '操作时间',
    PRIMARY KEY (`id`)
);

CREATE INDEX `idx_transactions_buyer` ON `transactions` (`buyer_id`,`status`,`order_time`,`deleted`);
CREATE INDEX `idx_transactions_product` ON `transactions` (`product_id`,`status`,`order_time`,`deleted`);
CREATE INDEX `idx_transactions_create_by` ON `transactions` (`create_by`,`status`,`order_time`,`deleted`);

CREATE INDEX `idx_audit_log_operation_object` ON `audit_logs` (`operation_object_id`,`operation_type`,`operation_time`);
CREATE INDEX `idx_audit_log_operator` ON `audit_logs` (`operator`,`operation_time`);
