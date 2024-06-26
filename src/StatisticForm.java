import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticForm extends JFrame{
    private JButton wsteczButton;
    private JPanel panel;
    private JList list;
    private JButton ilośćTowarówPrzechowywanychPrzezButton;
    private JButton klienciKtórzyPrzekroczyliTerminyButton;
    private JButton zyskZaPrzechowywanieTowaruButton;

    private int width= Window.getWidth(), height = Window.getHeight();

    StatisticForm(){
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        ImageIcon image = new ImageIcon("warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);

        list.setFont(new Font("Arial", Font.PLAIN, 14));
        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm();
            }
        });
        klienciKtórzyPrzekroczyliTerminyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List <String> customers_exceed = new ArrayList<>();
                String sql = "SELECT u.id,`first_name`,`last_name`,name,expiration_date, p.id,(SELECT 0.5 * (SELECT DATEDIFF(CURDATE(),p.expiration_date))) as \"penalty\" FROM customers u JOIN products p ON u.id = p.user_id WHERE p.expiration_date < CURDATE();";

                try(Connection conn = Repository.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)){
                    ResultSet resultSet = pstmt.executeQuery(sql);

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String first_name = resultSet.getString("first_name");
                        String last_name = resultSet.getString("last_name");
                        String product_name =  resultSet.getString("name");
                        String exceed_date =  resultSet.getString("expiration_date");
                        Double charged_penalty = resultSet.getDouble("penalty");

                        customers_exceed.add("id: " + id + ", Imię: " + first_name + ", Nazwisko: "+ last_name+ ", Nazwa produktu: " + product_name + ", Data: " + exceed_date + ", Naliczona kara: "+charged_penalty+"zł");
                    }

                } catch (SQLException er) {
                    throw new RuntimeException(er);
                }
                list.setListData(customers_exceed.toArray());
            }
        });
        ilośćTowarówPrzechowywanychPrzezButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //SELECT u.id, u.first_name, u.last_name, COUNT(u.id) AS "Ilosc towarow" FROM customers u JOIN products p ON u.id = p.user_id GROUP BY u.id, u.first_name, u.last_name;
                List <String> quantity_of_products_list = new ArrayList<>();
                String sql = "SELECT u.id, u.first_name, u.last_name, COUNT(u.id) AS 'Ilosc_towarow' FROM customers u JOIN products p ON u.id = p.user_id GROUP BY u.id, u.first_name, u.last_name";
                try(Connection conn = Repository.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)){
                    ResultSet resultSet = pstmt.executeQuery(sql);

                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String first_name = resultSet.getString("first_name");
                        String last_name = resultSet.getString("last_name");
                        String product_quantity =  resultSet.getString("Ilosc_towarow");

                        quantity_of_products_list.add("id: " + id + ", Imię: " + first_name + ", Nazwisko: "+ last_name+ ", Ilość towarów:" + product_quantity);
                    }

                } catch (SQLException er) {
                    throw new RuntimeException(er);
                }

                list.setListData(quantity_of_products_list.toArray());

            }
        });
        zyskZaPrzechowywanieTowaruButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                List <String> profit_list = new ArrayList<>();
                String sql = "SELECT YEAR(p.storage_date) AS year, MONTH(p.storage_date) AS month, SUM(p.fee) AS total_profit FROM products p GROUP BY YEAR(p.storage_date), MONTH(p.storage_date);";
                try(Connection conn = Repository.getConnection();
                    PreparedStatement pstmt = conn.prepareStatement(sql)){
                    ResultSet resultSet = pstmt.executeQuery(sql);

                    while (resultSet.next()) {
                        String year = resultSet.getString("year");
                        String month = resultSet.getString("month");
                        String total_profit =  resultSet.getString("total_profit");

                        profit_list.add("Zysk za dany okres: "+year+"-"+month+": "+ total_profit+"zł");
                    }

                } catch (SQLException er) {
                    throw new RuntimeException(er);
                }



                list.setListData(profit_list.toArray());


            }
        });
    }
}
