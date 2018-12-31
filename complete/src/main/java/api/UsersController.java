package api;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import api.utilities.Converter;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@Controller    // This means that this class is a Controller
// @RequestMapping(path="/users") // This means URL's start with /user (after Application path)
public class UsersController {
    @Autowired // This means to get the bean called usersRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private UsersRepository usersRepository;


//    @GetMapping(path="/add") // Map ONLY GET Requests
//    public @ResponseBody
//    Users addNewUser (
//            @RequestParam String first_name,
//            @RequestParam String last_name,
//            @RequestParam String email
//    ) {
//        // @ResponseBody means the returned String is the response, not a view name
//        // @RequestParam means it is a parameter from the GET or POST request
//
//        Users n = new Users();
//        n.setFullName(first_name, last_name);
//        n.setEmail(email);
//        usersRepository.save(n);
//        return n;
//    }

    @GetMapping(path="/auth")
    public @ResponseBody JSONArray generateAuthToken(String email, String password) {
        DatabaseQuery dbq = new DatabaseQuery("SELECT * FROM users WHERE user_email = " + email + " AND user_password = " + password);
        return dbq.getResults();
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Users> getAllUsers() {
        // This returns a JSON or XML with the users
        return usersRepository.findAll();
    }
}