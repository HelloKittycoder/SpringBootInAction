package readinglist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import readinglist.po.Book;
import readinglist.po.Reader;
import readinglist.properties.AmazonProperties;
import readinglist.repository.ReadingListRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by shucheng on 2020/8/28 20:42
 */
@Controller
@RequestMapping("/")
// @ConfigurationProperties("amazon")
public class ReadingListController {

    // private String associateId;
    private ReadingListRepository readingListRepository;

    private AmazonProperties amazonConfig;

    // 这里的Autowired可以省略，spring会找构造器注入的
    // @Autowired
    /*public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }*/

    /*public void setAssociateId(String associateId) {
        this.associateId = associateId;
    }*/

    public ReadingListController(ReadingListRepository readingListRepository, AmazonProperties amazonConfig) {
        this.readingListRepository = readingListRepository;
        this.amazonConfig = amazonConfig;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/fail")
    public void fail() {
        throw new RuntimeException("出错了");
    }

    /* // 这个要求templates目录下有一个error.html页面，但无法获取到BasicErrorController中提供的一系列属性
    // 如：timestamp、status、error、exception、message、trace、path
    @ExceptionHandler(value = RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BANDWIDTH_LIMIT_EXCEEDED)
    public String error() {
        return "error";
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public String readerBooks(HttpServletRequest request, Reader reader, Model model) {
        /* 如果传来的是一个new Reader()，里面属性均为null，后面的findByReader查询会报错（这个错有点奇怪）；
        org.hibernate.TransientObjectException: object references an unsaved transient instance
        这里为了测试下，先手动设置了值
        reader = new Reader();
        reader.setUsername("craig");*/
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null) {
            model.addAttribute("books", readingList);
            model.addAttribute("reader", reader);
            // model.addAttribute("amazonID", associateId);
            model.addAttribute("amazonID", amazonConfig.getAssociateId());
        }
        return "readingList";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String addToReadingList(Reader reader, Book book) {
        book.setReader(reader);
        readingListRepository.save(book);
        return "redirect:/";
    }
}
