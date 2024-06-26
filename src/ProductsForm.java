import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListSelectionEvent;


public class ProductsForm extends JFrame {
    private JButton wsteczButton;
    private JPanel panel;
    private JList list;
    private JButton usuńWybranyElementButton;
    private JButton dodajNowyElementButton;
    private JButton zmodyfikujWybranyElementButton;
    private int currently_selected_element_id;
    private  String selected_name;
    private LocalDate selected_storage_date;
    private  LocalDate selected_expiration_date;
    private  double selected_weight;
    private  int selected_user_id;
    private double selected_fee;

    ProductRepository productRepository = new ProductRepository();

    public void setWsteczButton(JButton wsteczButton) {
        wsteczButton.setForeground(Color.WHITE);
        wsteczButton.setBackground(Color.WHITE);
        wsteczButton.setFocusPainted(false);
    }

    private int width= Window.getWidth(), height = Window.getHeight();

    ProductsForm(){
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

        wsteczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new MainForm();
            }
        });

        list.addComponentListener(new ComponentAdapter() {});

        list.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    String selectedElement = (String) list.getSelectedValue();
                    if (selectedElement != null) {
                        String parts[] = selectedElement.split(",");
                         int id = Integer.parseInt(parts[0].split(": ")[1]);
                         String name = parts[1].split(": ")[1];
                         LocalDate storage_date = LocalDate.parse(parts[2].split(": ")[1]);
                         LocalDate expiration_date = LocalDate.parse(parts[3].split(": ")[1]);
                         Double weight = Double.parseDouble((parts[4].split(": ")[1]).replaceAll("[^0-9]", ""));
                         double fee =  Double.parseDouble((parts[5].split(": ")[1]).replaceAll("[^0-9]", ""));
                         int user_id = Integer.parseInt(parts[6].split(": ")[1]);
                        currently_selected_element_id = id;
                        selected_name = name;
                        selected_storage_date = storage_date;
                        selected_expiration_date = expiration_date;
                        selected_weight = weight;
                        selected_fee =  fee;
                        selected_user_id = user_id;
                    }
                }
            }
        });
        usuńWybranyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                productRepository.remove(currently_selected_element_id);
                show_data();
            }
        });
        dodajNowyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new ProductAddForm();
            }
        });
        zmodyfikujWybranyElementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(currently_selected_element_id > 0){
                    dispose();
                    new ProductModifyForm(currently_selected_element_id,selected_name,selected_storage_date.toString(),selected_expiration_date.toString(),String.valueOf(selected_weight),String.valueOf(selected_fee),String.valueOf(selected_user_id));
                }
            }
        });
    }
    private void show_data(){
        List <Product> products = productRepository.get_all();

        List<String> list1 = new ArrayList<String>();
        for(Product p : products){
            list1.add(p.show_data());
        }
        list.setListData(list1.toArray());

        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setFont(new Font("Arial", Font.PLAIN, 14));
    }
}
