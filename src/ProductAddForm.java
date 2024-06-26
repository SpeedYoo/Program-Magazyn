import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ProductAddForm extends JFrame {
    private JTextField name;
    private JTextField storage_date;
    private JTextField experation_date;
    private JButton dodajButton;
    private JButton wsteczButton;
    private JTextField weight;
    private JTextField price;
    private JTextField owner_id;
    private JPanel panel;

    private int width= Window.getWidth(), height = Window.getHeight();

    ProductRepository productRepository = new ProductRepository();

    ProductAddForm(){
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        ImageIcon image = new ImageIcon("warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductsForm();
            }
        });
        dodajButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    productRepository.add(new Product(name.getText(), LocalDate.parse(storage_date.getText())
                            , LocalDate.parse(experation_date.getText()), Double.parseDouble(weight.getText())
                            , Double.parseDouble(price.getText()), Integer.parseInt(owner_id.getText())));
                    dispose();
                    new ProductsForm();
                }catch(Exception er){
                    JOptionPane.showMessageDialog(null,er.getMessage());
                    JOptionPane.showMessageDialog(null,"Nieprawidłowe dane !");
                    throw new RuntimeException(er);
                }
            }
        });
    }
}
