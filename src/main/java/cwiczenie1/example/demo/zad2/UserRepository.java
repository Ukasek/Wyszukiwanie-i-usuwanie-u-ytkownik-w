package cwiczenie1.example.demo.zad2;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {

    List<User> users;

    public UserRepository() {
        users = new ArrayList<>();
        users.add(new User("Ania", "Koteluj", 22));
        users.add(new User("Marek", "Cichoper", 29));
        users.add(new User("Tomek", "Jelonek", 44));
    }

    public List<User> getAll() {
        return users;
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(User user) {
        users.remove(user);
    }
}
