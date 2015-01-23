package org.springside.examples.quickstart.web.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springside.examples.quickstart.entity.Book;
import com.google.common.collect.Maps;

/**
 * 书店前台店面展示的Controller.
 *
 * @author wgw
 */
@Controller
@RequestMapping(value = "/shop")
public class ShopController {

	private static final int PAGE_SIZE = 3;

	private static Map<String, String> sortTypes = Maps.newLinkedHashMap();
	static {
		sortTypes.put("auto", "自动");
		sortTypes.put("title", "标题");
	}

	
	@RequestMapping(value = "")
	public String list( Model model, ServletRequest request) {
      List<Book> books = new ArrayList<Book>();
      Book book1 = new Book();
      book1.setId(1);
      book1.setImage("static/images/java6.gif");
      book1.setName("Java 6 Platform Revealed");
      book1.setDescn("Java 6 Platform Revealed");
      book1.setUnitprice(20.00);
      books.add(book1);
      
      Book book2 = new Book();
      book2.setId(2);
      book2.setImage("static/images/enterpriseejb3.gif");
      book2.setName("Enterprise JavaBeans 3.0 (5th Edition)");
      book2.setDescn("Enterprise JavaBeans 3.0 (5th Edition)");
      book2.setUnitprice(30.00);
      books.add(book2);
      
      Book book3 = new Book();
      book3.setId(3);
      book3.setImage("static/images/prospring.gif");
      book3.setName("Spring in Action");
      book3.setDescn("Spring in Action");
      book3.setUnitprice(40.00);
      books.add(book3);

     model.addAttribute("newBooks", books);
     model.addAttribute("totalCount", books.size());
		return "shop/index";
	}

	
}
