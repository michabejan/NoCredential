package de.noCredentials.service;

import de.noCredentials.model.Account;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Micha-PC on 25.06.2017.
 */
public interface BaseService {

    Account getAccountbyPseudonym(String pseudonym);
    ArrayList<String> getUploadedFiles(String folder);
    boolean uploadFile(String folder, MultipartFile[] file);
    void downloadFile(String folder, String dfile, HttpServletResponse response) throws IOException;
    boolean deleteFile(String folder, String dfile) throws IOException;
    String getPseudonymByIdentifier(String identifier);
}
