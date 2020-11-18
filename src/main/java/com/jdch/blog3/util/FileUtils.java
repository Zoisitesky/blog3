package com.jdch.blog3.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * description:文件上传与删除
 *
 * @author dianchenhui.jiang
 * @date 2020/3/27 16:18
 */
public class FileUtils {
    /*
     * @Description: 上传文件 paraName:前台页面的name参数; path:路径
     * @Param: [request, paramName, path]
     * @return: java.lang.String
     */
    public static String upload(HttpServletRequest request, MultipartFile paraName, String path) {
        String pic = null;
        if (!paraName.isEmpty()) {
            String filename = paraName.getOriginalFilename();
            if (filename != null) {
                pic = UUID.randomUUID().toString() + "-" + filename.substring(filename.lastIndexOf("."));
                try {
                    paraName.transferTo(new File(request.getServletContext().getRealPath("") + path + pic));
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        return pic;
    }

    /*
     * @Description: 删除文件
     * @Param: [path]
     * @return: boolean
     */
    public static boolean remove(String path) {
        boolean flag = false;
        File file = new File(path);
        if (file.exists()) {
            flag = file.delete();
        }
        return flag;
    }
}
