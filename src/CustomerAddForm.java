import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerAddForm extends JFrame {
    private JButton wsteczButton;
    private JPanel panel;
    private JTextField first_name;
    private JTextField last_name;
    private JTextField age;
    private JButton dodajKlientaButton;


    private int width= Window.getWidth(), height = Window.getHeight();

    CustomerRepository customerRepository = new CustomerRepository();

    CustomerAddForm(){
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
                new CustomersForm();
            }
        });
        dodajKlientaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    customerRepository.add(new Customer(first_name.getText(), last_name.getText(), Integer.parseInt(age.getText())));
                    dispose();
                    new CustomersForm();
                } catch (Exception er){
                    JOptionPane.showMessageDialog(null,"Nieprawidłowe dane !");
                    JOptionPane.showMessageDialog(null,er.getMessage());
                    throw new RuntimeException(er);
                }
            }
        });
    }
}
