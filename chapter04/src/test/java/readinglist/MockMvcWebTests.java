package readinglist;

import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.web.context.WebApplicationContext;
import readinglist.po.Book;

/**
 * Created by shucheng on 2020/8/31 18:53
 * 使用MockMvc测试ReadingListController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// 下面这个注解不知道有什么用，不加代码一样能正常运行
// @WebAppConfiguration
public class MockMvcWebTests {

    @Autowired
    private WebApplicationContext webContext;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webContext).build();
    }

    // 验证直接访问路径“/”，是否会被重定向至“/readingList”
    @Test
    public void redirectFromRoot() throws Exception {
        mockMvc.perform(get("/"))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/readingList"));
    }

    /**
     * 测试直接访问“/readingList”时，返回的页面中，model里是否包含属性books，
     * 且属性books是否为一个空集合
     */
    @Test
    public void homePage() throws Exception {
        mockMvc.perform(get("/readingList"))
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attributeExists("books"))
            .andExpect(model().attribute("books", is(empty())));
    }

    /**
     * 测试提交一本书的信息后，再次访问“/readingList”时，
     * 返回的页面中，model里是否包含属性books，且属性books中是否包含前面提交的书的信息
     */
    @Test
    public void postBook() throws Exception {
        mockMvc.perform(post("/readingList")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .param("title", "BOOK TITLE")
            .param("author", "BOOK AUTHOR")
            .param("isbn", "1234567890")
            .param("description", "DESCRIPTION"))
            .andExpect(status().is3xxRedirection())
            .andExpect(header().string("Location", "/readingList"));

        Book expectedBook = new Book();
        expectedBook.setId(1L);
        expectedBook.setReader("craig");
        expectedBook.setTitle("BOOK TITLE");
        expectedBook.setAuthor("BOOK AUTHOR");
        expectedBook.setIsbn("1234567890");
        expectedBook.setDescription("DESCRIPTION");

        mockMvc.perform(get("/readingList"))
            .andExpect(status().isOk())
            .andExpect(view().name("readingList"))
            .andExpect(model().attributeExists("books"))
            .andExpect(model().attribute("books", hasSize(1)))
            .andExpect(model().attribute("books",
                    contains(samePropertyValuesAs(expectedBook))));
    }
}
