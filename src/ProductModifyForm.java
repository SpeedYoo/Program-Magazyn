import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ProductModifyForm extends JFrame {
    private JButton okButton;
    private JButton button2;
    private JTextField name;
    private JTextField storage_date;
    private JTextField expiration_date;
    private JTextField weight;
    private JTextField price;
    private JTextField owner_id;
    private JPanel panel;

    private int width= Window.getWidth(), height = Window.getHeight();

    ProductRepository productRepository = new ProductRepository();

    ProductModifyForm(int id, String initial_name, String initial_storage_date, String initial_expiration_date, String initial_weight, String initial_price, String initial_owner_id) {
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        name.setText(initial_name);
        storage_date.setText(initial_storage_date);
        expiration_date.setText(initial_expiration_date);
        weight.setText(initial_weight);
        price.setText(initial_price);
        owner_id.setText(initial_owner_id);

        ImageIcon image = new ImageIcon("warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductsForm();
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    productRepository.update(new Product(id,name.getText(), LocalDate.parse(storage_date.getText())
                            ,LocalDate.parse(expiration_date.getText()),Integer.parseInt(weight.getText()), Integer.parseInt(price.getText()), Integer.parseInt(owner_id.getText())));
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Nieprawidłowe dane !");
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                dispose();
                new ProductsForm();
            }
        });
    }
}
