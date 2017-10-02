package de.noCredentials.controller;

import de.noCredentials.mobileService.QRCodeScanner;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by Micha-PC on 01.07.2017.
 */
@Controller
public class QrCodeController {

    @RequestMapping(value = "/qr", method = RequestMethod.GET)
    public String getQRCode(String inputUUID, Model model )  throws IOException {

        UUID uuid = UUID.fromString("0e2161d7-25e7-4846-84c8-faaa87a81313");

        BufferedImage image = QRCodeScanner.generateQRCode(uuid);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", baos);
        byte[] bytes = baos.toByteArray();

        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);

        BufferedImage img = ImageIO.read(stream);
        System.out.println(System.getProperty("user.dir"));
        ImageIO.write(img, "jpg", new File(System.getProperty("user.dir")  + "\\src" + "\\main" + "\\resources"  + "\\static"+ "\\images" + "\\" + "lala.jpeg"));
        //return new ResponseEntity<byte[]> (bytes, headers, HttpStatus.CREATED);
return "qr";
    }

}
