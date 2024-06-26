import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class CsvManager {
    private static final String customers_csv = "customers.csv";
    private static final String products_csv = "poducts.csv";

    static public void export_customers(){
        CustomerRepository customerRepository  = new CustomerRepository();
        List<Customer> customers = customerRepository.get_all();
        clear_file(customers_csv);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(customers_csv, true))) {
            writer.write("id,first_name,last_name,age\n");
            for(Customer customer : customers){
                writer.write(customer.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }
    static public void export_products(){
        ProductRepository productRepository = new ProductRepository();
        List<Product> products = productRepository.get_all();
        clear_file(products_csv);
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(products_csv, true))){
            writer.write("id,name,storage_date,expiration_date,weight,fee,user_id\n");
            for(Product product : products){
                writer.write(product.toString());
                writer.newLine();
            }
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
            throw new RuntimeException(e);
        }
    }
    static public void import_customers(){
        CustomerRepository customerRepository = new CustomerRepository();

        try (BufferedReader br = new BufferedReader(new FileReader(customers_csv))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String first_name = parts[1];
                String last_name = parts[2];
                int age = Integer.parseInt(parts[3]);
                customerRepository.add(new Customer(id, first_name, last_name, age));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static public void import_products(){
        ProductRepository productRepository = new ProductRepository();

        try (BufferedReader br = new BufferedReader(new FileReader(products_csv))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                LocalDate storage_date = LocalDate.parse(parts[2]);
                LocalDate expiry_date = LocalDate.parse(parts[3]);
                double fee = Double.parseDouble(parts[4]);
                double weight = Double.parseDouble(parts[5]);
                int user_id = Integer.parseInt(parts[6]);

                productRepository.add(new Product(id, name, storage_date, expiry_date, weight, fee, user_id));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static private void clear_file(String path){
        try (FileWriter writer = new FileWriter(path, false)) {
            writer.write("");
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error clearing file: " + e.getMessage());
        }
    }

}
