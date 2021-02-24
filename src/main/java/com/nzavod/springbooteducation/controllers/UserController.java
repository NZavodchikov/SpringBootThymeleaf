package com.nzavod.springbooteducation.controllers;

import com.nzavod.springbooteducation.model.Email;
import com.nzavod.springbooteducation.model.SearchUser;
import com.nzavod.springbooteducation.model.User;
import com.nzavod.springbooteducation.model.UserData;
import com.nzavod.springbooteducation.service.EmailService;
import com.nzavod.springbooteducation.service.UserService;
import eu.bitwalker.useragentutils.UserAgent;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    EmailService emailService;
    private String address;



    @GetMapping("/startPage")
    public String start() {
        return "startPage";
    }

    @GetMapping("/new")
    public String adUS(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @GetMapping("/usersList")
    public String userList(Model model) throws FileNotFoundException {
        model.addAttribute("listusers", userService.userList());
        return "userList";

    }

    @GetMapping("/search")
    public String searchUser(Model model){
        model.addAttribute("searchUser", new SearchUser());
        return "searchedUser";
    }
    @GetMapping("/email")
    public  String wantSentMail(Model model){
        model.addAttribute("email", new Email());
        return "email";
    }
    @PostMapping("/sendEmail")
    public String sendEmail(@ModelAttribute("email")Email email){
        System.out.println(email.getText());
        System.out.println(address);
        emailService.sendMessage(email.getText(), address);
        return "emailSended";
    }

    @PostMapping("/user")
    public String userAdd(@Valid @ModelAttribute("user") User user,
                          BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "addUser";
        }

        if(user.getSecondName().isEmpty()){
            user.setSecondName("Middle-Name-Absent");
        }

        userService.write(user);
        System.out.println(user);//for debug

        return "userAdded";
    }
    @PostMapping("/searchByName")
    public String getByName(Model model,
                            @ModelAttribute("searchUser") SearchUser searchUser,
                            BindingResult bindingResult,
                            HttpServletRequest httpServletRequest) throws FileNotFoundException {
        if(bindingResult.hasErrors()){
            return "searchedUser";
        }
        User user = userService.getByName(searchUser.getFirstName(), searchUser.getLastName());
        if (user == null){
            return "userNotFound";
        }
        address  = user.getEmail();
        UserData data = infoAboutUser(httpServletRequest);
        model.addAttribute("userdata", data);
        model.addAttribute("foundUser", user);
        model.addAttribute("email", new Email());

        return "userFound";
    }
    private UserData infoAboutUser(HttpServletRequest httpServletRequest){
        UserData data = new UserData();
        UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
        data.setBrowser(userAgent.getBrowser().getName());
        data.setDate(new Date().toString());
        return  data;
    }
}