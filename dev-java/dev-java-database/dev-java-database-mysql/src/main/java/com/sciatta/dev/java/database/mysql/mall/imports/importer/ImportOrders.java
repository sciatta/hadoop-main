package com.sciatta.dev.java.database.mysql.mall.imports.importer;

import com.sciatta.dev.java.database.mysql.mall.imports.AbstractImport;
import com.sciatta.dev.java.database.mysql.mall.model.Product;
import com.sciatta.dev.java.database.mysql.mall.model.User;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangxiaoyu on 2021/1/4<br>
 * All Rights Reserved(C) 2017 - 2021 SCIATTA<br><p/>
 * ImportOrders
 */
@Slf4j
public class ImportOrders extends AbstractImport {
    private final Random random = new Random();
    private static final int CACHE_USER_NUMBER = 100;
    private static final int CACHE_PRODUCT_NUMBER = 30;
    private static final int CACHE_INVALID_ORDER_NUMBER = 5000;
    private static final int MAX_PRODUCTS_FOR_ORDER = 8;
    protected static final int ONCE_COMMIT_NUMBER = 100000;
    
    private void execute(int number) throws SQLException {
        Connection connection = getConnection();
        
        // 缓存用户和产品数据
        List<User> randomUsers = getRandomUsers(connection);
        List<Product> randomProducts = getRandomProducts(connection);
        
        int fetchData = random.nextInt(CACHE_INVALID_ORDER_NUMBER);
        int fetchDataIndex = 0;
        int unCommited = 0;
        
        PreparedStatement orderPs = null;
        PreparedStatement orderDetailPs = null;
        try {
            connection.setAutoCommit(false);
            orderPs = connection.prepareStatement("insert into orders(user_id, total, amount) values(?,?,?)");
            orderDetailPs = connection.prepareStatement("insert into order_details(order_id, product_id, price) values(?,?,?)");
            
            for (int i = 0; i < number; i++) {
                unCommited++;
                fetchDataIndex++;
                
                if (fetchDataIndex == fetchData) {
                    // log.debug("缓存数据失效: " + fetchDataIndex);
                    randomUsers.clear();
                    randomProducts.clear();
                    // 缓存用户和产品数据
                    randomUsers = getRandomUsers(connection);
                    randomProducts = getRandomProducts(connection);
                    fetchDataIndex = 0;
                    fetchData = random.nextInt(CACHE_INVALID_ORDER_NUMBER);
                }
                
                // 随机获得缓存中的用户和其购买产品构造订单和订单明细数据
                User userForOrder = getUserForOrder(randomUsers);
                List<Product> productsForOrder = getProductsForOrder(randomProducts);
                
                // 第一次迭代，获取产品的数量和总金额
                Iterator<Product> one = productsForOrder.iterator();
                int productNumber = 0;
                BigDecimal productPrice = new BigDecimal(0);
                while (one.hasNext()) {
                    productNumber++;
                    Product next = one.next();
                    productPrice = productPrice.add(next.getPrice());
                }
                
                // 构建订单数据
                orderPs.setInt(1, userForOrder.getId());
                orderPs.setInt(2, productNumber);
                orderPs.setBigDecimal(3, productPrice);
                orderPs.addBatch();
                
                // 第二次迭代，构建订单明细数据
                for (Product next : productsForOrder) {
                    orderDetailPs.setInt(1, i + 1);
                    orderDetailPs.setInt(2, next.getId());
                    orderDetailPs.setBigDecimal(3, next.getPrice());
                    orderDetailPs.addBatch();
                }
                
                if ((unCommited) % ONCE_COMMIT_NUMBER == 0) {
                    commitBatch(connection, orderPs, orderDetailPs, unCommited);
                    unCommited = 0;
                }
            }
            
            if (unCommited != 0) {
                commitBatch(connection, orderPs, orderDetailPs, unCommited);
            }
            
            connection.commit(); // 最后提交保证数据一致性
        } finally {
            if (orderPs != null) orderPs.close();
            if (orderDetailPs != null) orderDetailPs.close();
            connection.close();
        }
    }
    
    private void commitBatch(Connection connection, PreparedStatement orderPs, PreparedStatement orderDetailPs, int unCommited) throws SQLException {
        long start = System.currentTimeMillis();
        
        orderPs.executeBatch();   // 请求服务端
        orderDetailPs.executeBatch();
        
        orderPs.clearBatch();     // 重新初始化
        orderDetailPs.clearBatch();
        
        long duration = System.currentTimeMillis() - start;
        log.debug("本次提交记录数: " + unCommited + " 持续: " + duration + " 毫秒");
    }
    
    private User getUserForOrder(List<User> users) {
        return users.get(random.nextInt(users.size()));
    }
    
    private List<Product> getProductsForOrder(List<Product> products) {
        int number = random.nextInt(MAX_PRODUCTS_FOR_ORDER) + 1;    // 避免出现0
        List<Product> productForOrder = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            Product product = products.get(random.nextInt(products.size()));
            productForOrder.add(product);
        }
        return productForOrder;
    }
    
    private List<Product> getRandomProducts(Connection connection) throws SQLException {
        long start = System.currentTimeMillis();
        
        PreparedStatement rangePs = null, cachePs = null;
        ResultSet rangeRs = null, cacheRs = null;
        List<Product> products = new ArrayList<>();
        try {
            rangePs = connection.prepareStatement("select count(id) as producttotal from products");
            rangeRs = rangePs.executeQuery();
            int producttotal = 0;
            if (rangeRs.next()) {
                producttotal = rangeRs.getInt("producttotal");
            }
            int randId = random.nextInt(producttotal);
            
            cachePs = connection.prepareStatement("select id, name, price from products limit ?,?");
            cachePs.setInt(1, randId);
            cachePs.setInt(2, CACHE_PRODUCT_NUMBER);
            cacheRs = cachePs.executeQuery();
            while (cacheRs.next()) {
                Product product = new Product();
                product.setId(cacheRs.getInt("id"));
                product.setName(cacheRs.getString("name"));
                product.setPrice(cacheRs.getBigDecimal("price"));
                products.add(product);
            }
        } finally {
            if (rangeRs != null) rangeRs.close();
            if (cacheRs != null) cacheRs.close();
            if (rangePs != null) rangePs.close();
            if (cachePs != null) cachePs.close();
        }
        
        return products;
    }
    
    private List<User> getRandomUsers(Connection connection) throws SQLException {
        long start = System.currentTimeMillis();
        
        PreparedStatement rangePs = null, cachePs = null;
        ResultSet rangeRs = null, cacheRs = null;
        List<User> users = new ArrayList<>();
        try {
            rangePs = connection.prepareStatement("select count(id) as usertotal from users");
            rangeRs = rangePs.executeQuery();
            int userTotal = 0;
            while (rangeRs.next()) {
                userTotal = rangeRs.getInt("usertotal");
            }
            int randId = random.nextInt(userTotal);
            
            cachePs = connection.prepareStatement("select id, name, nickname, password, id_number from users limit ?,?");
            cachePs.setInt(1, randId);
            cachePs.setInt(2, CACHE_USER_NUMBER);
            cacheRs = cachePs.executeQuery();
            while (cacheRs.next()) {
                User user = new User();
                user.setId(cacheRs.getInt("id"));
                user.setName(cacheRs.getString("name"));
                user.setNickname(cacheRs.getString("nickname"));
                user.setPassword(cacheRs.getString("password"));
                user.setIdNumber(cacheRs.getString("id_number"));
                users.add(user);
            }
        } finally {
            if (rangeRs != null) rangeRs.close();
            if (cacheRs != null) cacheRs.close();
            if (rangePs != null) rangePs.close();
            if (cachePs != null) cachePs.close();
        }
        
        return users;
    }
    
    public static void main(String[] args) throws SQLException {
        ImportOrders importOrders = new ImportOrders();
        
        long start = System.currentTimeMillis();
        importOrders.execute(1000000);
        long duration = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
        log.debug("本次提交共持续: " + duration + " 秒");
    }
}
