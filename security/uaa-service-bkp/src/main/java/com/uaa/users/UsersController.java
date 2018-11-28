package com.uaa.users;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static org.apache.commons.lang.Validate.notNull;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/oauth/users")
public class UsersController {

    @NonNull
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @RequestMapping(value = "/signIn", method = POST)
    public void signIn(@RequestBody Users users) {
        usersService.signIn(users);
    }

    /*@RequestMapping(value = "/signUp", method = POST)
    public Users signUp(@RequestBody Users users) {
        return usersService.signUp(users);
    }

    @RequestMapping(value = "/signOut", method = GET)
    public void signOut() {
        SecurityContextHolder.clearContext();
    }*/

    @GetMapping
    public String test() {
        return "From Users";
    }

    /*@RequestMapping(method = GET)
    public Users getCurrentAccount(Principal principal) {
        notNull(principal);
        return usersService.findOneByEmail(principal.getName());
    }*/
}
