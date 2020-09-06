package readinglist;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

/**
 * Created by shucheng on 2020/8/19 20:47
 * 读取视频流
 * 参考链接：https://www.cnblogs.com/xiaowangxiao/p/12951165.html
 */
@Controller
public class VideoController {

    @RequestMapping("/getVideo/{videoId}")
    public void getVideo(HttpServletRequest request, HttpServletResponse response, @PathVariable String videoId) {
        response.reset();
        // 获取从哪个字节开始读取文件
        String rangeString = request.getHeader("Range");

        try {
            // 获取响应的输出流
            OutputStream outputStream = response.getOutputStream();
            // 从类路径下获取文件资源（参考链接：https://www.jianshu.com/p/7d7e5e4e8ae3）
            File file = ResourceUtils.getFile("classpath:static/movie/aaa.mp4");
            if (file.exists()) {
                RandomAccessFile targetFile = new RandomAccessFile(file, "r");
                long fileLength = targetFile.length();
                // 播放
                if (rangeString != null) { // "bytes=0-"
                    long range = Long.valueOf(rangeString.substring(rangeString.indexOf("=") + 1, rangeString.indexOf("-")));
                    // 设置内容类型
                    response.setHeader("Content-Type", "video/mp4");
                    // 设置此次相应返回的数据长度
                    response.setHeader("Content-Length", String.valueOf(fileLength - range));
                    // 设置此次相应返回的数据范围 bytes 0-65474109/65474110
                    response.setHeader("Content-Range", "bytes " + range + "-" + (fileLength - 1) + "/" + fileLength);
                    // 返回码需要为206，而不是200
                    response.setStatus(HttpServletResponse.SC_PARTIAL_CONTENT);
                    // 设定文件读取开始位置
                    targetFile.seek(range);
                } else { // 下载
                    System.out.println("需要下载");
                }

                byte[] cache = new byte[1024 * 300];
                int len;
                while ((len = targetFile.read(cache)) != -1) {
                    outputStream.write(cache, 0, len);
                }
            } else {
                String message = "file:aaa not exists";
                // 解决编码问题
                response.setHeader("Content-Type", "application/json");
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
            }

            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
