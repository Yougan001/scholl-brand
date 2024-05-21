package icu.yougan.trade.controller;

import icu.yougan.trade.enums.ErrorMsg;
import icu.yougan.trade.service.FileService;
import icu.yougan.trade.utils.IdFactoryUtil;
import icu.yougan.trade.vo.R;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @Author:油柑
 * @Date:2024/5/21 11:31
 * @Version:v1.0.0
 * @Description:文件上传控制类
 **/
@CrossOrigin
@RestController
public class FileController {

    @Value("${userFilePath}")
    private String userFilePath;

    @Value("${baseUrl}")
    private String baseUrl;

    @Resource
    private FileService fileService;

    /**
     * 上传文件
     *
     * @param multipartFile 二进制文件
     * @return 结果
     */
    @PostMapping("/file")
    public R uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String uuid = "file" + IdFactoryUtil.getFileId();
        String fileName = uuid + multipartFile.getOriginalFilename();
        try {
            if (fileService.uploadFile(multipartFile, fileName)) {
                return R.success(baseUrl + "/image?imageName=" + fileName);
            }
        } catch (IOException e) {
            return R.fail(ErrorMsg.SYSTEM_ERROR);
        }
        return R.fail(ErrorMsg.FILE_UPLOAD_ERROR);
    }

    /**
     * 获取图片
     *
     * @param imageName 图片名
     * @param response  返回结果
     * @throws IOException io异常
     */
    @GetMapping("/image")
    public void getImage(
            @RequestParam("imageName") String imageName,
            HttpServletResponse response
    ) throws IOException {
        File fileDir = new File(userFilePath);
        File image = new File(fileDir.getAbsolutePath() + "/" + imageName);
        if (image.exists()) {
            FileInputStream fileInputStream = new FileInputStream(image);
            byte[] bytes = new byte[fileInputStream.available()];
            if (fileInputStream.read(bytes) > 0) {
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.close();
            }
            fileInputStream.close();
        }
    }
}
