import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

abstract class Repository<T> extends DataBase {
    abstract void add(T element);
    abstract void remove(int id);
    abstract List<T> get_all();
    abstract void update(T element);
}
