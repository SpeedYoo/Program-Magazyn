import java.time.LocalDate;

public class Product extends Entity{
    private String name;
    private LocalDate storage_date;
    private LocalDate expiration_date;
    private double price;
    private double weight;
    private int user_id;

    public Product(int product_id, String name, LocalDate storage_data, LocalDate expiration_date, double weight, double price, int user_id) {
        setId(product_id);
        this.name = name;
        this.storage_date = storage_data;
        this.expiration_date = expiration_date;
        this.weight = weight;
        this.price = price;
        this.user_id = user_id;
    }

    public Product(String name, LocalDate storage_data, LocalDate expiration_date, double weight, double price, int user_id) {
        this.name = name;
        this.storage_date = storage_data;
        this.expiration_date = expiration_date;
        this.weight = weight;
        this.price = price;
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStorage_date() {
        return storage_date;
    }

    public double getWeight() {
        return weight;
    }

    public int getUser_id() {
        return user_id;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getExpiration_date() {
        return expiration_date;
    }

    public String toString() {
        return getId() + "," + name + "," + storage_date + ","  + expiration_date + "," + weight + ","  + price + ","  + user_id;
    }

    public String show_data(){
        return "id: " + getId() + ", nazwa: " + name +
                ", Przechowywany od: " + storage_date + ", Przechowywany do: " + expiration_date + ", waga produktu: "
                + weight + "kg"+ ", opłata za przechowywanie: " + price + "zł" + ", id właściciela produktu: " + user_id;
    }
}
