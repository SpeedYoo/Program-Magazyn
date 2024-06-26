import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository extends Repository<Customer> {

    public void add(Customer customer) {
        String sql = "INSERT customers (first_name,last_name,age) VALUES (?,?,?)";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, customer.getFirst_name());
            pstmt.setString(2, customer.getLast_name());
            pstmt.setInt(3, customer.getAge());
            pstmt.execute();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }


    public void remove(int id){
        String sql = "DELETE FROM customers WHERE id = ?";
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


    public List<Customer> get_all(){

        List <Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet resultSet = pstmt.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                int age = resultSet.getInt("age");

                customers.add(new Customer(id, first_name , last_name , age));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
        return customers;
    }

    @Override
    public void update(Customer updated_customer) {
        String sql = "UPDATE customers SET first_name = ?, last_name = ?, age = ? WHERE id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1,updated_customer.getFirst_name());
                pstmt.setString(2,updated_customer.getLast_name());
                pstmt.setInt(3,updated_customer.getAge());
                pstmt.setInt(4,updated_customer.getId());
                pstmt.executeUpdate();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void charge_penalty(Double penalty, int id){
        String sql = "UPDATE customers SET penalty = ? WHERE id = ?";

        try(Connection conn = getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setDouble(1,penalty);
            pstmt.setInt(2,id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }
}

