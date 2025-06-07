package com.nisum.jagannath.controller;

import model.UserForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class RequestController {

    // 1) Show a simple form (GET)
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "form"; // /WEB-INF/views/form.jsp
    }

    // 2) Process form submission (POST → forward to JSP)
    @PostMapping("/submitForm")
    public String handleFormSubmit(@ModelAttribute("userForm") UserForm form, Model model) {
        // form.getName(), form.getAge()
        model.addAttribute("message", "Hello, " + form.getName() + "! You are " + form.getAge() + ".");
        return "result"; // /WEB-INF/views/result.jsp
    }

    // 3) Return JSON directly (e.g., GET /userJson?name=Alice&age=30)
    @GetMapping("/userJson")
    @ResponseBody
    public UserForm getUserJson(@RequestParam String name, @RequestParam int age) {
        UserForm u = new UserForm();
        u.setName(name);
        u.setAge(age);
        return u; // JSON thanks to Jackson converter
    }

    // 4) File upload (multipart/form-data)
    @GetMapping("/upload")
    public String showUploadForm() {
        return "fileForm"; // /WEB-INF/views/fileForm.jsp
    }

    @PostMapping("/doUpload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, Model model) {
        if (!file.isEmpty()) {
            String uploadDir = System.getProperty("user.home") + "/uploads";
            File dest = new File(uploadDir, file.getOriginalFilename());
            try {
                file.transferTo(dest);
                model.addAttribute("message", "File uploaded to: " + dest.getAbsolutePath());
            } catch (IOException e) {
                model.addAttribute("message", "Upload failed: " + e.getMessage());
            }
        } else {
            model.addAttribute("message", "No file selected.");
        }
        return "fileUploadResult"; // /WEB-INF/views/fileUploadResult.jsp
    }

    // 5) Redirect example
    @GetMapping("/redirectExample")
    public String redirectExample() {
        return "redirect:/"; // redirects to "/"
    }

    // 6) Forward example
    @GetMapping("/forwardExample")
    public String forwardExample() {
        return "forward:/submitForm"; // forwards to /submitForm (requires form data)
    }
}
