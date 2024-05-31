/*
 Navicat Premium Data Transfer

 Source Server         : Local PotsgreSQL
 Source Server Type    : PostgreSQL
 Source Server Version : 160002 (160002)
 Source Host           : localhost:5432
 Source Catalog        : online_shop
 Source Schema         : public

 Target Server Type    : PostgreSQL
 Target Server Version : 160002 (160002)
 File Encoding         : 65001

 Date: 31/05/2024 07:41:55
*/


-- ----------------------------
-- Sequence structure for customers_customer_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."customers_customer_id_seq";
CREATE SEQUENCE "public"."customers_customer_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for items_items_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."items_items_id_seq";
CREATE SEQUENCE "public"."items_items_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Sequence structure for orders_order_id_seq
-- ----------------------------
DROP SEQUENCE IF EXISTS "public"."orders_order_id_seq";
CREATE SEQUENCE "public"."orders_order_id_seq" 
INCREMENT 1
MINVALUE  1
MAXVALUE 2147483647
START 1
CACHE 1;

-- ----------------------------
-- Table structure for customers
-- ----------------------------
DROP TABLE IF EXISTS "public"."customers";
CREATE TABLE "public"."customers" (
  "customer_id" int4 NOT NULL DEFAULT nextval('customers_customer_id_seq'::regclass),
  "customer_name" varchar(64) COLLATE "pg_catalog"."default",
  "customer_address" varchar(255) COLLATE "pg_catalog"."default",
  "customer_code" varchar(32) COLLATE "pg_catalog"."default",
  "customer_phone" varchar(32) COLLATE "pg_catalog"."default",
  "is_active" bool,
  "last_order_date" date,
  "pic" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of customers
-- ----------------------------
INSERT INTO "public"."customers" VALUES (1, 'Customer 1', 'Blitar', 'customer1', '081234567890', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (2, 'Customer 2', 'Blitar', 'customer2', '081234567891', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (3, 'Customer 3', 'Blitar', 'customer3', '081234567892', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (4, 'Customer 4', 'Blitar', 'customer4', '081234567893', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (5, 'Customer 5', 'Blitar', 'customer5', '081234567894', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (6, 'Customer 6', 'Blitar', 'customer6', '081234567895', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (7, 'Customer 7', 'Blitar', 'customer7', '081234567896', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (8, 'Customer 8', 'Blitar', 'customer8', '081234567897', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (9, 'Customer 9', 'Blitar', 'customer9', '081234567898', 't', NULL, NULL);
INSERT INTO "public"."customers" VALUES (10, 'Customer 10', 'Blitar', 'customer10', '081234567899', 't', NULL, NULL);

-- ----------------------------
-- Table structure for items
-- ----------------------------
DROP TABLE IF EXISTS "public"."items";
CREATE TABLE "public"."items" (
  "items_id" int4 NOT NULL DEFAULT nextval('items_items_id_seq'::regclass),
  "items_name" varchar(64) COLLATE "pg_catalog"."default",
  "items_code" varchar(32) COLLATE "pg_catalog"."default",
  "stock" int4,
  "price" int4,
  "is_available" bool,
  "last_re_stock" date
)
;

-- ----------------------------
-- Records of items
-- ----------------------------
INSERT INTO "public"."items" VALUES (1, 'Item 1', 'item1', 100, 100000, 't', NULL);
INSERT INTO "public"."items" VALUES (2, 'Item 2', 'item2', 200, 200000, 't', NULL);
INSERT INTO "public"."items" VALUES (3, 'Item 3', 'item3', 300, 300000, 't', NULL);
INSERT INTO "public"."items" VALUES (4, 'Item 4', 'item4', 400, 400000, 't', NULL);
INSERT INTO "public"."items" VALUES (5, 'Item 5', 'item5', 500, 500000, 't', NULL);
INSERT INTO "public"."items" VALUES (6, 'Item 6', 'item6', 600, 600000, 't', NULL);
INSERT INTO "public"."items" VALUES (7, 'Item 7', 'item7', 700, 700000, 't', NULL);
INSERT INTO "public"."items" VALUES (8, 'Item 8', 'item8', 800, 800000, 't', NULL);
INSERT INTO "public"."items" VALUES (9, 'Item 9', 'item9', 900, 900000, 't', NULL);
INSERT INTO "public"."items" VALUES (10, 'Item 10', 'item10', 1000, 1000000, 't', NULL);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS "public"."orders";
CREATE TABLE "public"."orders" (
  "order_id" int4 NOT NULL DEFAULT nextval('orders_order_id_seq'::regclass),
  "order_code" varchar(32) COLLATE "pg_catalog"."default",
  "order_date" date,
  "total_price" int4,
  "customer_id" int4,
  "items_id" int4,
  "quantity" int4
)
;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO "public"."orders" VALUES (1, 'Order-202405310001', '2024-05-31', 100000, 1, 1, 1);
INSERT INTO "public"."orders" VALUES (2, 'Order-202405310002', '2024-05-31', 200000, 1, 2, 1);
INSERT INTO "public"."orders" VALUES (3, 'Order-202405310003', '2024-05-31', 300000, 1, 3, 1);
INSERT INTO "public"."orders" VALUES (4, 'Order-202405310004', '2024-05-31', 400000, 1, 4, 1);
INSERT INTO "public"."orders" VALUES (5, 'Order-202405310005', '2024-05-31', 500000, 1, 5, 1);
INSERT INTO "public"."orders" VALUES (6, 'Order-202405310006', '2024-05-31', 600000, 1, 6, 1);
INSERT INTO "public"."orders" VALUES (7, 'Order-202405310007', '2024-05-31', 700000, 1, 7, 1);
INSERT INTO "public"."orders" VALUES (8, 'Order-202405310008', '2024-05-31', 800000, 1, 8, 1);
INSERT INTO "public"."orders" VALUES (9, 'Order-202405310009', '2024-05-31', 900000, 1, 9, 1);
INSERT INTO "public"."orders" VALUES (10, 'Order-202405310010', '2024-05-31', 1000000, 1, 10, 1);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."customers_customer_id_seq"
OWNED BY "public"."customers"."customer_id";
SELECT setval('"public"."customers_customer_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."items_items_id_seq"
OWNED BY "public"."items"."items_id";
SELECT setval('"public"."items_items_id_seq"', 10, true);

-- ----------------------------
-- Alter sequences owned by
-- ----------------------------
ALTER SEQUENCE "public"."orders_order_id_seq"
OWNED BY "public"."orders"."order_id";
SELECT setval('"public"."orders_order_id_seq"', 10, true);

-- ----------------------------
-- Primary Key structure for table customers
-- ----------------------------
ALTER TABLE "public"."customers" ADD CONSTRAINT "pk_customers" PRIMARY KEY ("customer_id");

-- ----------------------------
-- Primary Key structure for table items
-- ----------------------------
ALTER TABLE "public"."items" ADD CONSTRAINT "pk_items" PRIMARY KEY ("items_id");

-- ----------------------------
-- Primary Key structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders" ADD CONSTRAINT "pk_orders" PRIMARY KEY ("order_id");

-- ----------------------------
-- Foreign Keys structure for table orders
-- ----------------------------
ALTER TABLE "public"."orders" ADD CONSTRAINT "fk_orders_reference_customer" FOREIGN KEY ("customer_id") REFERENCES "public"."customers" ("customer_id") ON DELETE RESTRICT ON UPDATE CASCADE;
ALTER TABLE "public"."orders" ADD CONSTRAINT "fk_orders_reference_items" FOREIGN KEY ("items_id") REFERENCES "public"."items" ("items_id") ON DELETE RESTRICT ON UPDATE CASCADE;
