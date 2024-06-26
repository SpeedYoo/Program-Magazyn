import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerModifyForm extends JFrame {
    private JTextField first_name;
    private JButton potwierdźButton;
    private JButton wsteczButton;
    private JTextField last_name;
    private JTextField age;
    private JPanel panel;

    CustomerRepository customerRepository = new CustomerRepository();
    private int width= Window.getWidth(), height = Window.getHeight();


    CustomerModifyForm(int id, String initial_first_name, String initial_last_name, String initial_age){
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        first_name.setText(initial_first_name);
        last_name.setText(initial_last_name);
        age.setText(initial_age);

        this.setVisible(true);
        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomersForm();
            }
        });
        potwierdźButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    customerRepository.update(new Customer(id, first_name.getText(), last_name.getText(),Integer.parseInt(age.getText())));
                    dispose();
                    new CustomersForm();
                } catch(Exception er){
                    JOptionPane.showMessageDialog(null,er.getMessage());
                    JOptionPane.showMessageDialog(null,"Nieprawidłowe dane !");
                    throw new RuntimeException(er);
                }

            }
        });
    }
}
