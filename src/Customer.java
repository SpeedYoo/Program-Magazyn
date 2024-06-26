
public class Customer extends Entity { ;
    private String first_name;
    private String last_name;
    private int age;
    private double penalty;

    Customer(int id, String first_name, String last_name, int age){
        setId(id);
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    Customer(String first_name, String last_name, int age){
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String toString() {
        return getId() + "," + first_name + "," + last_name + ","  + age;
    }
    public String show_data(){
        return "id: " + getId() + ", Imię: " + first_name + ", Nazwisko: " + last_name +
                ", wiek: " + age;
    }
}
