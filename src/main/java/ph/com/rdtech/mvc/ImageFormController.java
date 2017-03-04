package ph.com.rdtech.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ph.com.rdtech.image.Image;
import ph.com.rdtech.image.ImageDao;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Daniel on 04/03/2017.
 */
@SuppressWarnings("ALL")
@Controller
@RequestMapping("/")
public class ImageFormController {

    @Autowired
    private ImageDao imageDao;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String createForm(Model model) {

        model.addAttribute("imageurl", imageDao.getDefaultImage().getUrl());

        return "forms/form";
    }

    @RequestMapping(value = "/image", method = RequestMethod.POST)
    public String imageUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){

        try {

            String filePath = "./" + file.getOriginalFilename();

            // Get the file and save it locally
            byte[] bytes = file.getBytes();
            Path path = Paths.get(filePath);
            Files.write(path, bytes);

            imageDao.update(new Image("DEFAULT", filePath));
            redirectAttributes.addFlashAttribute("imageUploadMessage", "Successfully changed image!");


        } catch (IOException e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("imageUploadMessage", "Changing image failed!");
        }

        return "redirect:/";
    }
}
