import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomersForm extends JFrame{
    private JButton wsteczButton;
    private JPanel panel;
    private JButton usuńWybranyElementButton;
    private JButton dodajNowyElementButton;
    private JButton zmodyfikujWybranyElementButton;
    private JList list;
    private int currently_selected_element_id;
    private String selected_first_name;
    private String selected_last_name;
    private int selected_age;

    CustomerRepository customerRepository = new CustomerRepository();

    private int width= Window.getWidth(), height = Window.getHeight();

    CustomersForm(){
        super("Magazyn");
        this.setContentPane(this.panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        show_data();

        ImageIcon image = new ImageIcon("warehouse.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedElement = (String) list.getSelectedValue();
                    if (selectedElement != null) {
                        String parts[] = selectedElement.split(",");
                        int id = Integer.parseInt(parts[0].split(" ")[1]);
                        String firstName = parts[1].split(": ")[1];
                        String lastName = parts[2].split(": ")[1];
                        int age = Integer.parseInt(parts[3].split(": ")[1]);
                        currently_selected_element_id = id;
                        selected_first_name = firstName;
                        selected_last_name = lastName;
                        selected_age = age;
                    }
                }
            }
        });
        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm();
            }
        });
        usuńWybranyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerRepository.remove(currently_selected_element_id);
                show_data();
            }
        });
        dodajNowyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerAddForm();
            }
        });
        zmodyfikujWybranyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerModifyForm(currently_selected_element_id, selected_first_name,selected_last_name,String.valueOf(selected_age));
            }
        });
    }
    private void show_data(){
        List<Customer> customers = customerRepository.get_all();


        List<String> list1 = new ArrayList<String>();
        for(Customer p : customers){
            list1.add(p.show_data());
        }
        list.setListData(list1.toArray());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
    }
}
