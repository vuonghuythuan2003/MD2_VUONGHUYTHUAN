package ra.business;

import ra.entity.Categories;
import ra.entity.Product;
import ra.util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBusiness {
    public static List<Product> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Product> listProduct = null;
        try {
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{Call Proc_FindAllCategories}");
            ResultSet rs = callSt.executeQuery();
            listProduct = new ArrayList<>();
            while (rs.next()) {
                Product p = new Product();
                p.setProductid(rs.getInt("product_id"));
                p.setProductname(rs.getString("product_name"));
                p.setStock(rs.getInt("stock"));
                p.setCostPrice(rs.getDouble("cost_price"));
                p.setSellPrice(rs.getDouble("sell_price"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                p.setCategoryid(rs.getInt("category_id"));
                listProduct.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listProduct;
    }

    public static boolean save(Product product) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{CALL create_product(?, ?, ?, ?, ?, ?)}");
            callSt.setString(1, product.getProductname());
            callSt.setInt(2, product.getStock());
            callSt.setDouble(3, product.getCostPrice());
            callSt.setDouble(4, product.getSellPrice());
            callSt.setDate(5, new java.sql.Date(product.getCreatedAt().getTime()));
            callSt.setInt(6, product.getCategoryid());

            callSt.executeUpdate();
            result = true;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }
}
