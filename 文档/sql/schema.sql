CREATE DATABASE IF NOT EXISTS `demo` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

USE `demo`;

/**
*活动表:
*用于描述平台举办的活动或者是商家举办的活动，例如双十一狂欢节活动
*   活动 N:M 商家 
*   活动 N:M 用户 
*   活动 1:M 优惠
*/
DROP TABLE IF EXISTS `t_activity`;
CREATE TABLE `t_activity` (
  `activity_id` int(11) NOT NULL COMMENT '活动ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺ID',
  `activity_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '活动名称',
  `introduction` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '活动介绍',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  `preview_time` timestamp NULL DEFAULT NULL COMMENT '预告时间',
  PRIMARY KEY (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='活动表';

/**
*地址表:
*用于描述用户的地址，用于收货的地址，用户可以有多个地址
*   地址 1:M 订单项  
*   地址 N:1 用户 
*/
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `address_id` int(11) NOT NULL COMMENT '地址ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `province` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '省',
  `city` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `county` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '区',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `is_default` tinyint(4) DEFAULT NULL COMMENT '是否是默认地址',
  `status` varchar(10) COLLATE utf8_bin DEFAULT NULL COMMENT '地址状态',
  PRIMARY KEY (`address_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='地址表';

/**
*属性表:
*用于描述商品的属性，例如华为Mate 20，它有颜色，这个颜色就是它的属性
*   属性 1:M 属性项  
*   属性 N:1 分类 
*/
DROP TABLE IF EXISTS `t_attribute`;
CREATE TABLE `t_attribute` (
  `attribute_id` int(11) NOT NULL COMMENT '属性ID',
  `sort_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `attribute_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '属性名称',
  PRIMARY KEY (`attribute_id`),
  KEY `index_sort_id` (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='属性表';

/**
*属性项表:
*用于描述商品的属性的不同值，例如华为Mate 20，它有颜色，这个颜色就是它的属性，不同的颜色对应的就是不同的属性项
*   属性项 N:1 属性  
*   属性项 N:1 SPU
*/
DROP TABLE IF EXISTS `t_attribute_item`;
CREATE TABLE `t_attribute_item` (
  `attribute_item_id` int(11) NOT NULL COMMENT '属性选项ID',
  `attribute_id` int(11) DEFAULT NULL COMMENT '属性ID',
  `attribute_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '属性的值',
  PRIMARY KEY (`attribute_item_id`),
  KEY `index_attribute_id` (`attribute_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='属性选项表';

/**
*属性项与SPU中间表:
*用于描述属性项与SPU多对多关系
*/
DROP TABLE IF EXISTS `t_attribute_item_spu`;
CREATE TABLE `t_attribute_item_spu` (
  `attr_sku_id` int(11) NOT NULL,
  `spu_id` int(11) DEFAULT NULL,
  `attribute_item_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`attr_sku_id`),
  KEY `index_attribute_item_id` (`attribute_item_id`),
  KEY `index_spu_id` (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='属性选项表于SKU表';

/**
*权限表:
*用于描述不同角色或者用户对应拥有的权限执行某些动作或者操作
*   权限 N:M 用户 
*   权限 N:M 角色
*/
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `auth_id` int(11) NOT NULL COMMENT '权限ID',
  `auth_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '权限名称',
  `auth_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '权限码',
  `auth_level` int(11) DEFAULT NULL COMMENT '权限等级',
  PRIMARY KEY (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='权限表';

/**
*品牌表:
*用于描述品牌
*   品牌 1:M SPU  
*/
DROP TABLE IF EXISTS `t_brand`;
CREATE TABLE `t_brand` (
  `brand_id` int(11) NOT NULL COMMENT '品牌ID',
  `brand` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌名称',
  `brand_code` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `logo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT 'logo图标',
  `letter` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '品牌首字母',
  PRIMARY KEY (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='品牌表';

/**
*购物车表:
*用于描述用户添加了某一个店铺的某一个商品在自己的购物车中
*   购物车 1:M SKU 
*   购物车 1:M 用户  
*/
DROP TABLE IF EXISTS `t_cart`;
CREATE TABLE `t_cart` (
  `cart_id` int(11) NOT NULL COMMENT '购物车ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU_ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `count` int(11) DEFAULT NULL COMMENT '数量',
  PRIMARY KEY (`cart_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='购物车表';

/**
*收藏表:
*用于描述用户收藏了某一个店铺的某一个商品
*   收藏 1:M SKU  
*   收藏 1:M 用户 
*/
DROP TABLE IF EXISTS `t_collection`;
CREATE TABLE `t_collection` (
  `collection_id` int(11) NOT NULL COMMENT '收藏ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU_ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`collection_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='收藏表';

/**
*收藏表:
*用于描述用户收藏了某一个店铺的某一个商品
*   收藏 1:M SKU  
*   收藏 1:M 用户 
*/
DROP TABLE IF EXISTS `t_coupon`;
CREATE TABLE `t_coupon` (
  `coupon_id` int(11) NOT NULL COMMENT '优惠',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺ID',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动',
  `coupon_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '优惠名称',
  `total_count` decimal(10,0) DEFAULT NULL COMMENT '总金额',
  `coupon_count` decimal(10,0) DEFAULT NULL COMMENT '优惠金额',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `preview_time` timestamp NULL DEFAULT NULL COMMENT '预告时间',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `end_time` timestamp NULL DEFAULT NULL COMMENT '结束时间',
  PRIMARY KEY (`coupon_id`),
  KEY `index_activity_id` (`activity_id`),
  KEY `index_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='优惠表';

/**
*评价表:
*用于描述用户在此次购物中对商家服务，物流，商品的评价，用户购买后才能评价，即通过订单完成
*   评价 1:1 订单 
*/
DROP TABLE IF EXISTS `t_evaluation`;
CREATE TABLE `t_evaluation` (
  `eval_id` int(11) NOT NULL COMMENT '评价',
  `order_item_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `content` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT '评论内容',
  `pic1` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图1',
  `pic2` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图2',
  `pic3` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图3',
  `pic4` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图4',
  `pic5` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图5',
  `pic6` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图6',
  `pic7` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图7',
  `pic8` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图8',
  `pic9` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '晒图9',
  `service_start` tinyint(4) DEFAULT NULL COMMENT '服务星评',
  `match_start` tinyint(4) DEFAULT NULL COMMENT '相符星评',
  `logistics_start` tinyint(4) DEFAULT NULL COMMENT '物流星评',
  `is_incognito` tinyint(4) DEFAULT NULL COMMENT '是否匿名',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `browse_count` int(11) DEFAULT NULL COMMENT '浏览数',
  `like_count` int(11) DEFAULT NULL COMMENT '点赞数',
  PRIMARY KEY (`eval_id`),
  KEY `index_order_item_id` (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='评价表';

/**
*足迹表:
*用于描述用户浏览过的商品
*   足迹 N:1 用户 
*   足迹 N:1 SKU  
*/
DROP TABLE IF EXISTS `t_footprint`;
CREATE TABLE `t_footprint` (
  `footprint_id` int(11) NOT NULL COMMENT '足迹ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU_ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`footprint_id`),
  KEY `index_sku_id` (`sku_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='足迹表';

/**
*物流表:
*用于描述用户购买的订单项的物流信息
*   物流 N:1 订单项  
*   物流 N:1 物流状态 
*/
DROP TABLE IF EXISTS `t_logistics`;
CREATE TABLE `t_logistics` (
  `logistics_id` int(11) NOT NULL COMMENT '物流',
  `order_item_id` int(11) DEFAULT NULL COMMENT '订单项ID',
  `logistics_status_id` int(11) DEFAULT NULL COMMENT '物流状态ID',
  `province` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '当前省',
  `city` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '当前市',
  `county` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '当前区',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '当前详细地址',
  `next_province` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '下一个省',
  `next_city` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '下一个市',
  `next_county` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '下一个城',
  `next_address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '下一个详细城市',
  PRIMARY KEY (`logistics_id`),
  KEY `index_logistics_status_id` (`logistics_status_id`),
  KEY `index_order_item_id` (`order_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物流表';

/**
*物流状态表:
*用于描述物流的状态
*   物流状态 1:N 物流
*/
DROP TABLE IF EXISTS `t_logistics_status`;
CREATE TABLE `t_logistics_status` (
  `logistics_status_id` int(11) NOT NULL COMMENT '物流状态ID',
  `logistics_status` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物流状态名称',
  `logistics_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '物流码',
  PRIMARY KEY (`logistics_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='物流状态ID';

/**
*商家表:
*用于描述商家
*   商家 1:N 店铺
*/
DROP TABLE IF EXISTS `t_merchant`;
CREATE TABLE `t_merchant` (
  `merchant_id` int(11) NOT NULL COMMENT '商家ID',
  `merchant` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '商家名字',
  `id_number` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证号',
  `gender` varchar(3) COLLATE utf8_bin DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电话',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `id_front_Photo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '身份证正面照片',
  `id_back_photo` varbinary(255) DEFAULT NULL COMMENT '身份证反面照片',
  `business_licence` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '营业执照',
  `merchant_status` int(11) DEFAULT NULL COMMENT '商家状态',
  PRIMARY KEY (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='商家表';

/**
*订单表:
*用于描述用户购物时生成的订单
*   订单 N:1 用户
*   订单 N:1 订单状态
*   订单 1:N 订单项
*   订单 1:1 支付信息
*   订单 1:1 评价
*/
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` int(11) NOT NULL COMMENT '订单',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `order_status_id` int(11) DEFAULT NULL COMMENT '订单状态',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `completed_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `total_count` decimal(10,0) DEFAULT NULL COMMENT '总金额',
  `actual_count` decimal(10,0) DEFAULT NULL COMMENT '实际支付金额',
  `post_count` decimal(10,0) DEFAULT NULL COMMENT '邮费',
  `payment_type` int(11) DEFAULT NULL COMMENT '支付方式',
  PRIMARY KEY (`order_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_order_status_id` (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单表';

/**
*订单项表:
*用于描述用户购物时生成的订单的订单详情，即订单项
*   订单项 1:1 地址
*   订单项 1:N 物流
*   订单项 N:1 SKU
*   订单项 N:1 订单
*/
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `order_item_id` int(11) NOT NULL COMMENT '订单项ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU_ID',
  `address_id` int(11) DEFAULT NULL COMMENT '地址',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  `quantity` int(11) DEFAULT NULL COMMENT '数量',
  `message` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '留言',
  PRIMARY KEY (`order_item_id`),
  KEY `index_sku_id` (`sku_id`),
  KEY `index_order_id` (`order_id`),
  KEY `index_address_id` (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单项表';

/**
*订单状态表:
*用于描述用户购物时生成的订单的状态
*   订单状态 1:N 订单
*/
DROP TABLE IF EXISTS `t_order_status`;
CREATE TABLE `t_order_status` (
  `order_status_id` int(11) NOT NULL COMMENT '订单状态ID',
  `order_status` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态',
  `order_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '订单状态码',
  PRIMARY KEY (`order_status_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='订单状态表';

/**
*支付表:
*用于描述用户购物时，记录支付的信息
*   支付 1:1 订单
*/
DROP TABLE IF EXISTS `t_payment`;
CREATE TABLE `t_payment` (
  `pay_id` int(11) NOT NULL COMMENT '支付ID',
  `order_id` int(11) DEFAULT NULL COMMENT '订单ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `payment_status_id` int(11) DEFAULT NULL COMMENT '支付状态ID',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `completed_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `refund_time` timestamp NULL DEFAULT NULL COMMENT '退款时间',
  `pay_time` timestamp NULL DEFAULT NULL COMMENT '支付时间',
  PRIMARY KEY (`pay_id`),
  KEY `index_order_id` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='支付表';

/**
*角色表:
*用于描述系统某一种类型的人物
*   角色 N:M 用户
*   角色 N:M 权限
*/
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `role_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色名称',
  `role_level` int(11) DEFAULT NULL COMMENT '角色等级',
  `role_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '角色码',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色表';

/**
*角色与权限中间表:
*用于描述角色与权限多对多关系
*/
DROP TABLE IF EXISTS `t_role_auth`;
CREATE TABLE `t_role_auth` (
  `role_auth_id` int(11) NOT NULL COMMENT '角色权限ID',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`role_auth_id`),
  KEY `index_role_id` (`role_id`),
  KEY `index_auth_id` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='角色-权限-中间表';

/**
*店铺表:
*用于描述系统的商店
*   店铺 1:M 优惠
*   店铺 N:1 商家
*   店铺 N:M SKU
*   店铺 N:M 活动
*/
DROP TABLE IF EXISTS `t_shop`;
CREATE TABLE `t_shop` (
  `shop_id` int(11) NOT NULL COMMENT '店铺ID',
  `merchant_id` int(11) DEFAULT NULL COMMENT '商家ID',
  `shop_name` varbinary(255) DEFAULT NULL COMMENT '店铺名称',
  `province` varbinary(255) DEFAULT NULL COMMENT '省',
  `city` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '市',
  `county` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '区',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '详细地址',
  `phone` varbinary(30) DEFAULT NULL COMMENT '电话',
  `logo` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺logo',
  `follow_count` int(11) DEFAULT NULL COMMENT '关注数',
  `composite_score` int(11) DEFAULT NULL COMMENT '综合得分',
  `shop_status` int(11) DEFAULT NULL COMMENT '店铺状态',
  PRIMARY KEY (`shop_id`),
  KEY `index_merchant_id` (`merchant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺表';

/**
*店铺与活动中间表:
*用于描述店铺与活动多对多关系
*/DROP TABLE IF EXISTS `t_shop_activity`;
CREATE TABLE `t_shop_activity` (
  `shop_activity_id` int(11) NOT NULL COMMENT '商家活动ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '商家ID',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  PRIMARY KEY (`shop_activity_id`),
  KEY `index_shop_id` (`shop_id`),
  KEY `index_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺-活动-中间表';

/**
*店铺与活动中间表:
*用于描述店铺与活动多对多关系
*/
DROP TABLE IF EXISTS `t_shop_sku`;
CREATE TABLE `t_shop_sku` (
  `shop_sku_id` int(11) NOT NULL COMMENT '店铺SKU_ID',
  `sku_id` int(11) DEFAULT NULL COMMENT 'SKU_ID',
  `shop_id` int(11) DEFAULT NULL COMMENT '店铺ID',
  PRIMARY KEY (`shop_sku_id`),
  KEY `index_shop_id` (`shop_id`),
  KEY `index_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='店铺-SKU-中间表';

/**
*库存量单位表(Stock Keeping Unit):
*用于描述系统中的一款商品，有一个单位，编码，同时需要一个名称规范，如：金色 -16G- 移动版 iphone6就是一个SKU
*   SKU N:M 规格选项
*   SKU 1:N 订单项
*   SKU N:1 SPU
*   SKU N:M 店铺
*   SKU N:M 用户(收藏)
*   SKU N:M 用户(足迹)
*   SKU N:M 用户(购物车)
*/
DROP TABLE IF EXISTS `t_sku`;
CREATE TABLE `t_sku` (
  `sku_id` int(11) NOT NULL COMMENT 'SKU_ID',
  `spu_id` int(11) DEFAULT NULL COMMENT 'SPU_ID',
  `sku_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'SKU名称',
  `sku_code` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT 'SKU编码',
  `available_stock` int(11) DEFAULT NULL COMMENT '可用库存',
  `total_stock` int(11) DEFAULT NULL COMMENT '总库存',
  `price` decimal(10,0) DEFAULT NULL COMMENT '价格',
  PRIMARY KEY (`sku_id`),
  KEY `index_spu_id` (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='库存量单位表(Stock Keeping Unit)';

/**
*分类表:
*用于描述系统商品中不同的类别，方便用户查找对应的商品
*   分类 1:N 属性
*   分类 1:N 规格
*   分类 1:N SPU
*/
DROP TABLE IF EXISTS `t_sort`;
CREATE TABLE `t_sort` (
  `sort_id` int(11) NOT NULL COMMENT '分类ID',
  `parent_id` int(11) DEFAULT NULL COMMENT '父ID',
  `is_parent` tinyint(4) DEFAULT NULL COMMENT '是否是父节点',
  `sort_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '分类名称',
  `sort_level` tinyint(4) DEFAULT NULL COMMENT '分类等级',
  PRIMARY KEY (`sort_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类表';

/**
*规格选项与SKU中间表:
*用于描述规格选项与SKU多对多关系
*/
DROP TABLE IF EXISTS `t_spec_group`;
CREATE TABLE `t_spec_group` (
  `spec_group_id` int(11) NOT NULL COMMENT '规格组ID',
  `group_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '规格组名称',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更改时间',
  `status` int(11) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`spec_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规格组表';

/**
*规格选项组表:
*用于描述用户查询商品时的规格类型：如查询手机，网络就是一个规格组，其下面有5G，4G，3G，2G
*   规格选项组 1:M 规格
*/
DROP TABLE IF EXISTS `t_spec_item`;
CREATE TABLE `t_spec_item` (
  `spec_item_id` int(11) NOT NULL COMMENT '规格选项ID',
  `spec_id` int(11) DEFAULT NULL COMMENT '规格ID',
  `spec_value` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '规格值',
  PRIMARY KEY (`spec_item_id`),
  KEY `index_spec_id` (`spec_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规格选项表';

/**
*规格选项表:
*用于描述用户查询商品时选择的条件
*   规格选项组 1:M 规格
*/
DROP TABLE IF EXISTS `t_spec_item_sku`;
CREATE TABLE `t_spec_item_sku` (
  `spec_item_sku_id` int(11) NOT NULL,
  `spec_item_id` int(11) DEFAULT NULL,
  `sku_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`spec_item_sku_id`),
  KEY `index_spec_item_id` (`spec_item_id`),
  KEY `index_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='分类项-SKU-中间表';

/**
*规格表:
*用于描述系统的商品的规格，如手机，内存64G，128G
*   规格 1:N 规格选项
*   规格 N:1 分类
*   规格 N:1 规格组
*/
DROP TABLE IF EXISTS `t_specification`;
CREATE TABLE `t_specification` (
  `spec_id` int(11) NOT NULL COMMENT '规格ID',
  `sort_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `spec_group_id` int(11) DEFAULT NULL COMMENT '规格组',
  `spec_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '参数名称',
  PRIMARY KEY (`spec_id`),
  KEY `index_sort_id` (`sort_id`),
  KEY `index_spec_group_id` (`spec_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='规格表';

/**
*标准化产品单元表(Standard Product Unit)表:
*用于描述系统的标准化产品单元，就是最小化的一个单元，如华为mate 20
*   SPU 1:1 品牌
*   SPU 1:N SKU
*   SPU N:1 分类
*   SPU N:M 属性选项
*/
DROP TABLE IF EXISTS `t_spu`;
CREATE TABLE `t_spu` (
  `spu_id` int(11) NOT NULL COMMENT 'SPU_ID',
  `sort_id` int(11) DEFAULT NULL COMMENT '分类ID',
  `product_name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '产品名称',
  `spu_code` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '产品编码',
  `introduction` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `brand_id` int(11) DEFAULT NULL COMMENT '品牌ID',
  PRIMARY KEY (`spu_id`),
  KEY `index_sort_id` (`sort_id`),
  KEY `index_brand_id` (`brand_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='标准化产品单元表(Standard Product Unit)';

/**
*用户表:
*用于描述系统的用户
*   用户 1:1 用户资料
*   用户 1:N 订单
*   用户 1:N 地址
*   用户 N:M SKU (收藏)
*   用户 N:M SKU (足迹)
*   用户 N:M SKU (购物车)
*   用户 N:M 角色
*   用户 N:M 权限
*/
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `phone` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '手机',
  `email` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `nickname` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '昵称',
  `profile` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '头像',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间(注册时间)',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户表';

/**
*用户与活动中间表:
*用于描述用户与活动多对多关系
*/
DROP TABLE IF EXISTS `t_user_activity`;
CREATE TABLE `t_user_activity` (
  `user_activity_id` int(11) NOT NULL COMMENT '用户活动ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户',
  `activity_id` int(11) DEFAULT NULL COMMENT '活动ID',
  PRIMARY KEY (`user_activity_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_activity_id` (`activity_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户－活动－中间表';

/**
*用户与权限中间表:
*用于描述用户与权限多对多关系
*/
DROP TABLE IF EXISTS `t_user_auth`;
CREATE TABLE `t_user_auth` (
  `user_auth_id` int(11) NOT NULL COMMENT '用户权限ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `auth_id` int(11) DEFAULT NULL COMMENT '权限',
  PRIMARY KEY (`user_auth_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_auth_id` (`auth_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-权限-中间表';

/**
*用户与优惠中间表:
*用于描述用户与优惠多对多关系
*/
DROP TABLE IF EXISTS `t_user_coupon`;
CREATE TABLE `t_user_coupon` (
  `user_coupon_id` int(11) NOT NULL COMMENT '用户优惠ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `coupon_id` int(11) DEFAULT NULL COMMENT '优惠ID',
  PRIMARY KEY (`user_coupon_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_coupon_id` (`coupon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户－优惠－中间表';

/**
*用户信息表:
*用于描述用户其的信息
*    用户信息 1：1 用户
*/
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `user_info_id` int(11) NOT NULL COMMENT '用户信息ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `wcchat` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '微信',
  `qq` varchar(12) COLLATE utf8_bin DEFAULT NULL COMMENT 'qq',
  `birthday` timestamp NULL DEFAULT NULL COMMENT '生日',
  PRIMARY KEY (`user_info_id`),
  KEY `index_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户信息表';

/**
*用户与角色中间表:
*用于描述用户与角色多对多关系
*/
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `user_role_id` int(11) NOT NULL COMMENT '用户角色ID',
  `user_id` int(11) DEFAULT NULL COMMENT '用户ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色表',
  PRIMARY KEY (`user_role_id`),
  KEY `index_user_id` (`user_id`),
  KEY `index_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='用户-角色-中间表';
