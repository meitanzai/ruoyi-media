package com.ruoyi.ufo.util;

import com.ruoyi.common.constant.FileConstant;
import com.ruoyi.ufo.autoconfiguration.UFOAutoConfiguration;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PathUtil {

    /**
     * 获取项目所在的根目录路径 resources路径
     *
     * @return
     */
    public static String getProjectRootPath() {
        String absolutePath = null;
        try {
            String url = ResourceUtils.getURL("classpath:").getPath();
            absolutePath = urlDecode(new File(url).getAbsolutePath()) + File.separator;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return absolutePath;
    }

    /**
     * 路径解码
     *
     * @param url
     * @return
     */
    public static String urlDecode(String url) {
        String decodeUrl = null;
        try {
            decodeUrl = URLDecoder.decode(url, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return decodeUrl;
    }

    /**
     * 得到static路径
     *
     * @return
     */
    public static String getStaticPath() {
        //PropertiesUtil.getProperty("qiwen-file.local-storage-path")
        String localStoragePath = UFOAutoConfiguration.localStoragePath;
        if (StringUtils.isNotEmpty(localStoragePath)) {
            return new File(localStoragePath).getPath() + File.separator;
        } else {
            String projectRootAbsolutePath = getProjectRootPath();

            int index = projectRootAbsolutePath.indexOf("file:");
            if (index != -1) {
                projectRootAbsolutePath = projectRootAbsolutePath.substring(0, index);
            }
            return new File(projectRootAbsolutePath + "static").getPath() + File.separator;
        }


    }


    public static String getParentPath(String path) {
        return path.substring(0, path.lastIndexOf(FileConstant.pathSeparator));
    }

}
