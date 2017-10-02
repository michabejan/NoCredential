package de.noCredentials.service;

import de.noCredentials.dao.AccountRepository;
import de.noCredentials.model.Account;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by Micha-PC on 25.06.2017.
 */
@Service("baseService")
public class BaseServiceStubImp  implements BaseService{

    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account getAccountbyPseudonym(String pseudonym) {
        return accountRepository.findAccountByPseudonym(pseudonym);
    }

    @Override
    public ArrayList<String> getUploadedFiles(String folder) {
        File directory = new File(folder);
        File[] fList = directory.listFiles();
        ArrayList<String> fileList = new ArrayList<>();
        if(fList!= null){
            for (File file : fList){
                if (file.isFile()){
                    fileList.add(file.getName());
                }
            }

        }return fileList;

    }
    @Override
    public boolean uploadFile(String folder, MultipartFile[] file) {
        try {

            // Get the file and save it somewhere
            byte[] bytes = file[0].getBytes();
            String[] fileNameSplit = file[0].getOriginalFilename().split("\\.");
            int fileNameSplitLength = fileNameSplit.length;

            Path path = Paths.get(folder + "//" + file[0].getOriginalFilename());
            Files.write(path, bytes);
            return true;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void downloadFile(String folder, String dfile, HttpServletResponse response)throws IOException {
        String src= folder.concat("//" + dfile);
        InputStream is = new FileInputStream(src);
        response.addHeader("Content-disposition", "attachment;filename=" + dfile);
        response.setContentType("txt/plain");
        IOUtils.copy(is, response.getOutputStream());
        response.flushBuffer();
    }

    @Override
    public boolean deleteFile(String folder, String dfile)throws IOException {
        try{
            File source = new File(folder + "//" + dfile);
            FileUtils.forceDelete(source);
        } catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;

    }

    @Override
    public String getPseudonymByIdentifier(String identifier) {
        return accountRepository.findByIdentifier(identifier).getPseudonym();
    }
}
