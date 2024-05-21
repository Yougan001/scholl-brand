package icu.yougan.trade.service.Impl;

import icu.yougan.trade.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @Author:油柑
 * @Date:2024/5/21 11:33
 * @Version:v1.0.0
 * @Description:上传文件 服务实现类
 **/
@Service
public class FileServiceImpl implements FileService {
    @Value("${userFilePath}")
    private String userFilePath;

    @Override
    public boolean uploadFile(MultipartFile multipartFile, String fileName) throws IOException {
        File fileDir = new File(userFilePath);
        if (!fileDir.exists()) {
            if (!fileDir.mkdirs()) {
                return false;
            }
        }
        File file = new File(fileDir.getAbsolutePath() + "/" + fileName);
        if (file.exists()) {
            if (!file.delete()) {
                return false;
            }
        }
        if (file.createNewFile()) {
            multipartFile.transferTo(file);
            return true;
        }
        return false;
    }
}
