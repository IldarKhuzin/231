package example.controller;

import example.entity.User;
import example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String showAllUser(Model model) {
        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("users", allUsers);
        return "users";
    }

    @RequestMapping("/add")
    public String add(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/edit")
    public String createOrUpdateUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/";
    }

    @RequestMapping(value="/edit", method = RequestMethod.GET)
    public String editUser(@RequestParam Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user-edit";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteUser(@RequestParam Long id){
        userService.delete(id);
        return "redirect:/";
    }
}
