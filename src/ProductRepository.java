import javax.swing.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends Repository<Product> {

    public void add(Product product) {

        String sql = "INSERT products (name,storage_date,expiration_date,weight,fee,user_id)" +
                " VALUES (?,?,?,?,?,?)";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, product.getName());
           pstmt.setDate(2, Date.valueOf(product.getStorage_date()));
           pstmt.setDate(3, Date.valueOf(product.getStorage_date()));
            pstmt.setDouble(4, product.getWeight());
            pstmt.setDouble(5, product.getPrice());
            pstmt.setDouble(6, product.getUser_id());
            pstmt.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void remove(int id){
        String sql = "DELETE FROM products WHERE id = ?";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1,id);
            pstmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public List<Product> get_all(){
        List <Product> products = new ArrayList<>();

        String sql = "SELECT * FROM products";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet resultSet = pstmt.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                LocalDate storage_date = resultSet.getDate("storage_date").toLocalDate();
                LocalDate experation_date = resultSet.getDate("expiration_date").toLocalDate();
                double weight = resultSet.getDouble("weight");
                double fee = resultSet.getDouble("fee");
                int user_id = resultSet.getInt("user_id");

                //customers.add(new Customer(id, first_name , last_name , age));
                products.add(new Product(id , name, storage_date , experation_date , weight
                , fee , user_id));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }

        return products;
    }

    public void update(Product updated_product){
        String sql = "UPDATE products SET name = ?, storage_date = ?, expiration_date = ?,weight = ?, fee = ?, user_id = ? WHERE id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, updated_product.getName());
            pstmt.setDate(2, Date.valueOf(updated_product.getStorage_date()));
            pstmt.setDate(3, Date.valueOf(updated_product.getExpiration_date()));
            pstmt.setDouble(4,updated_product.getWeight());
            pstmt.setDouble(5,updated_product.getPrice());
            pstmt.setInt(6,updated_product.getUser_id());
            pstmt.setInt(7,updated_product.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
