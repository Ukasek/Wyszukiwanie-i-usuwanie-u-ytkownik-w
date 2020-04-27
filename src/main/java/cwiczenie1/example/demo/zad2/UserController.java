package cwiczenie1.example.demo.zad2;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @ResponseBody
    @GetMapping("/users")
    public String userList() {

        List<User> users = userRepository.getAll();
        String result = "";
        for (User user : users) {
            result += user.toString() + "</br>";
        }
        return result;
    }

    @RequestMapping("/add")
    public String add(@RequestParam(defaultValue = "Bezimienny", required = false) String imie, @RequestParam String nazwisko, @RequestParam Integer wiek) {

        if (StringUtils.isEmpty(imie)) {
            return "redirect:/err.html";
        } else {
            User user = new User(imie, nazwisko, wiek);
            userRepository.add(user);
            return "redirect:/success.html";
        }
    }

    @ResponseBody
    @RequestMapping("/find")
    public String find(@RequestParam(required = false, defaultValue = "") String imie, @RequestParam(required = false, defaultValue = "") String nazwisko, @RequestParam(required = false, defaultValue = "") Integer wiek) {

        List<User> users = userRepository.getAll();
        String result = "";
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(imie) || user.getLastName().equalsIgnoreCase(nazwisko) || user.getAge() == wiek) {
                result += user.toString() + "</br>";
            }
        }
        if ("".equals(result)) {
            return "Nie znaleziono użytkownika!";
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String delete(@RequestParam(required = false, defaultValue = "") String imie, @RequestParam(required = false, defaultValue = "") String nazwisko, @RequestParam(required = false, defaultValue = "") Integer wiek) {

        List<User> users = userRepository.getAll();
        for (User user : users) {
            if (user.getFirstName().equalsIgnoreCase(imie) || user.getLastName().equalsIgnoreCase(nazwisko) || user.getAge() == wiek) {

                userRepository.remove(user);

                return "Poprawnie usunięto użytkownika";
            }
        }
        return "Nie znaleziono użytkownika!";
    }
}
