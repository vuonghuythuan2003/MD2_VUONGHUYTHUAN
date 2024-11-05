package ra.business;

import ra.entity.Categories;
import ra.util.ConnectionDB;

import java.security.PublicKey;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class CategoriesBusiness {
    public static List<Categories> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Categories> listCategories = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{Call find_all_product}");
            ResultSet rs = callSt.executeQuery();
            listCategories = new ArrayList<>();
            while(rs.next()) {
                Categories c = new Categories();
                c.setCategoryId(rs.getInt("category_id"));
                c.setCategoryName(rs.getString("category_name"));
                c.setCategoryStatus(rs.getBoolean("category_status"));
                listCategories.add(c);
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return listCategories;
    }

    public static boolean save(Categories categories) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{CALL create_categories(?, ?)}");
            callSt.setString(1, categories.getCategoryName());
            callSt.setBoolean(2, categories.isCategoryStatus());
            callSt.executeUpdate();
            result = true;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static boolean updateCategory(Categories categories) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{Call update_categories(?, ?, ?)}");
            callSt.setInt(1, categories.getCategoryId());
            callSt.setString(2, categories.getCategoryName());
            callSt.setBoolean(3, categories.isCategoryStatus());
            callSt.executeUpdate();
            result = true;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static Categories findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Categories categories = null;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{Call find_id(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();

            if(rs.next()) {
                categories = new Categories();
                categories.setCategoryId(rs.getInt("category_id"));
                categories.setCategoryName(rs.getString("category_name"));
                categories.setCategoryStatus(rs.getBoolean("category_status"));
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories;
    }

    public static boolean deleteCategories(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        boolean result = false;
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{CALL delete_category(?)}");
            callSt.setInt(1, id);
            callSt.executeUpdate();
            result = true;
        }catch(SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return result;
    }

    public static List<Map<String, Object>> countProductByCategories() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Map<String, Object>> categories = new ArrayList<>();
        try{
            conn = ConnectionDB.getConnection();
            callSt = conn.prepareCall("{CALL Proc_CountByCategoryes()}");
            ResultSet rs = callSt.executeQuery();
            while(rs.next()) {
                Map<String, Object> map = new HashMap<>();
                map.put("category_id", rs.getInt("category_id"));
                categories.add(map);
            }
        } catch(SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return categories;
    }

}
