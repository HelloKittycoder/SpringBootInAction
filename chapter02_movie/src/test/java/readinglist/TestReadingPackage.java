package readinglist;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by shucheng on 2020/8/20 9:05
 * 读取压缩包文件内容
 * https://blog.csdn.net/Peacock__/article/details/92840156
 */
public class TestReadingPackage {

    public static void main(String[] args) throws Exception {
        File file = ResourceUtils.getFile("classpath:static/movie/测试压缩文件.zip");
        // 获取文件输入流
        FileInputStream inputStream = new FileInputStream(file);
        // 获取ZIP输入流
        ZipInputStream zipInputStream = new ZipInputStream(inputStream, Charset.forName("GBK"));
        ZipEntry ze;
        while ((ze = zipInputStream.getNextEntry()) != null) {
            System.out.println("文件名：" + ze.getName() + "====文件大小：" + ze.getSize() + " bytes");
        }
        zipInputStream.closeEntry();
        inputStream.close();
    }
}
