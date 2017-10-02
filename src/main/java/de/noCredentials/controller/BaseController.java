package de.noCredentials.controller;

import de.noCredentials.model.Account;
import de.noCredentials.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Controller
public class BaseController {

    private static String ACCOUNT_FOLDER = System.getProperty("user.dir") + "//upload";
    private static String FIRST_ACCOUNT_FOLDER = System.getProperty("user.dir") + "//upload";
    private static String BUDDY_FOLDER = System.getProperty("user.dir") + "//upload";


    @Autowired
    private BaseService baseService;



    @RequestMapping(value = "/base", method = RequestMethod.GET)
    public String base(Model model, HttpSession session) {

        String pseudonym = "";
        pseudonym = session.getAttribute("pseudonym").toString();

        File directory = new File(FIRST_ACCOUNT_FOLDER + "//" + pseudonym);
        if (!directory.exists()) {
            directory.mkdir();
        }
        if (ACCOUNT_FOLDER.equals(FIRST_ACCOUNT_FOLDER)) {
            ACCOUNT_FOLDER = ACCOUNT_FOLDER + "//" + pseudonym;
        }
        String name = "";
        Account account = baseService.getAccountbyPseudonym(pseudonym);
        String accountName = account.getIdentifier();
        ArrayList<String> fileList = baseService.getUploadedFiles(ACCOUNT_FOLDER);
        model.addAttribute("accountName", accountName);
        model.addAttribute("fileList", fileList);
        model.addAttribute("name", name);
        return "base";
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile[] file, Model model,HttpServletRequest request) {
        if(file == null){
            return "redirect:/base";
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cookieP")) BUDDY_FOLDER = FIRST_ACCOUNT_FOLDER + "//" + c.getValue();
        }
        if (!(baseService.uploadFile(BUDDY_FOLDER, file))) {
            return "redirect:/";
        }
        ArrayList<String> fileList = baseService.getUploadedFiles(ACCOUNT_FOLDER);
        model.addAttribute("fileList", fileList);
        return "redirect:/base";
    }


    @RequestMapping(value = "/download{uploadedFiles}", method = RequestMethod.GET)
    public void download(@RequestParam String uploadedFiles,
                         HttpServletResponse response) throws IOException {
           baseService.downloadFile(ACCOUNT_FOLDER, uploadedFiles, response);
    }

    @RequestMapping(value = "/delete{defile}", method = RequestMethod.GET)
    public String download(@PathVariable("defile") String fileName,
                           @RequestParam String defile
    ) throws IOException {

        baseService.deleteFile(ACCOUNT_FOLDER, defile);
        return "redirect:/base";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam String name, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String pseudonym = "";
        if( baseService.getPseudonymByIdentifier(name) != null) pseudonym = baseService.getPseudonymByIdentifier(name);
        boolean temp = false;
        Cookie cookie;
        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("cookieP")){
                temp = true;
               c.setValue(pseudonym);
            }
        }
        if(!temp){
            cookie = new Cookie("cookieP", pseudonym);
            cookie.setMaxAge(24 * 60 * 60);   // A negative value means that the cookie is not stored persistently and will be deleted when the Web browser exits. A zero value causes the cookie to be deleted.
            cookie.setPath("/");
            response.addCookie(cookie);
        }




        return "redirect:/base";
    }
}
