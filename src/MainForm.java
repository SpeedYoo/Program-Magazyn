import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class MainForm extends JFrame {

    private int width= Window.getWidth(), height = Window.getHeight();
    private JPanel panel;
    private JButton użytkonicyMagazynuButton;
    private JButton statystykiMagazynuButton;
    private JButton prouktyWMagazynieButton;
    private JButton eksportujDoCSVButton;
    private JButton importujZCSVButton;

    MainForm(){
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);


        ImageIcon image = new ImageIcon("warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        użytkonicyMagazynuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomersForm();
            }
        });
        prouktyWMagazynieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductsForm();
            }
        });
        statystykiMagazynuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new StatisticForm();
            }
        });
        eksportujDoCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    CsvManager.export_products();
                    CsvManager.export_customers();
                    JOptionPane.showMessageDialog(null,"Udało się wyeksportować !");
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null,er.getMessage());
                    throw new RuntimeException(er);
                }

            }
        });
        importujZCSVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    CsvManager.import_products();
                    CsvManager.import_customers();
                    JOptionPane.showMessageDialog(null,"Udało zaimportować !");
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null,er.getMessage());
                    throw new RuntimeException(er);
                }
            }
        });
    }
}
