package org.springside.examples.quickstart.web.shop;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.drools.RuleBase;
import org.drools.RuleBaseFactory;
import org.drools.StatefulSession;
import org.drools.compiler.DroolsParserException;
import org.drools.compiler.PackageBuilder;
import org.drools.runtime.StatefulKnowledgeSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springside.examples.quickstart.entity.Customer;
import org.springside.examples.quickstart.entity.Order;
import org.springside.examples.quickstart.entity.OrderItem;
import org.springside.examples.quickstart.entity.Product;
import org.springside.examples.quickstart.web.Cart;
import org.springside.examples.quickstart.web.OrderPricer;


/**
 * 书店前台购物流程的Controller.
 * 
 * @author wgw
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController {

	private static final int PAGE_SIZE = 3;
	private static final String ORDER_NAME = "order";
	@Autowired(required=true)
	private StatefulKnowledgeSession  ksession1;
	static {
		/*String filePath = "E:\\workspace\\quickstart\\src\\main\\java\\org\\springside\\examples\\quickstart\\web\\shop\\Pricing.drl";
		PackageBuilder builder = new PackageBuilder();
		try {
			builder.addPackageFromDrl(new FileReader(filePath));
		} catch (DroolsParserException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (builder.hasErrors()) {
		    System.out.println(builder.getErrors().toString());
		    throw new RuntimeException("Unable to compile drl");
		   }
		   org.drools.rule.Package pkg = builder.getPackage();		   
		    RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		   ruleBase.addPackage(pkg);
		   kstateless = ruleBase.newStatefulSession();*/
		
	}

	private Cart cart = new Cart();
	
	

	/**
	 * 放入购物车.
	 */
	@ResponseBody
	@RequestMapping(value = "addToCart/{id}", method = RequestMethod.POST)
	public String addToCart(Model model, @PathVariable Integer id) {
		Product product = new Product();
		if (id == 1) {
			product.setName("Java 6 Platform Revealed");
			product.setShortDescn("Java 6 Platform Revealed");
			product.setUnitprice(20.00);
		} else if (id == 2) {
			product.setName("Enterprise JavaBeans 3.0 (5th Edition)");
			product.setShortDescn("Enterprise JavaBeans 3.0 (5th Edition)");
			product.setUnitprice(30.00);
		} else {
			product.setName("Spring in Action");
			product.setShortDescn("Spring in Action");
			product.setUnitprice(30.00);
		}
		product.setId(id);
		cart.addItem(product);
		return "《" + product.getName() + "》Added to your cart";
	}

	/**
	 * 查看购物车.
	 */

	@RequestMapping(value = "viewCart")
	public String viewCart(Model model) {
		model.addAttribute("cart", cart);
		return "shop/cart";
	}

	/**
	 * 用户结账,显示订单送货付款信息输入界面.
	 */

	@RequestMapping(value = "newOrderForm", method = RequestMethod.GET)
	public String newOrderForm(Model model, HttpServletRequest request) {
		// 从购物车初始化订单项
		List<OrderItem> orderItems = cart.getOrderItems();
		// 暂时没有用户登录界面,直接取得Customer 0
		Customer customer = new Customer();
		customer.setName("wgw");
		customer.setEmail("670688199@qq.com");
		// 初始化Order,放入session
		request.getSession().setAttribute(ORDER_NAME,
				initOrder(customer, orderItems));

		return "shop/newOrder";
	}

	/**
	 * 保存订单.
	 */

	@RequestMapping(value = "newOrder")
	public String newOrder(HttpServletRequest request) {
		// 从session获得order变量,填入送货地址
		Order order = (Order) request.getSession().getAttribute(ORDER_NAME);
	      if (order != null) {
			order.setShipAddress(request.getParameter("shipAddress"));
	        // 生成订单
			//orderManager.placeOrder(order);
		  // 清除session
			cart.clear();
			request.getSession().removeAttribute(ORDER_NAME);
			// 显示订单
			request.setAttribute(ORDER_NAME, order);
	      }
		      return "shop/viewOrder";
	}
	
	/**
	 * 根据顾客与订单项初始化订单.
	 */
	public Order initOrder(Customer customer, List<OrderItem> orderItems) {
		Order order = new Order();
		order.setCustomer(customer);
		order.setOrderItems(orderItems);
		subOrderPrice(order);
		return order;
	}
	
	/**
	 * 计算订单价格.
	 */
	public void subOrderPrice(Order order) {
		   //计算订单项价格
		    order.setTotalPrice(order.subTotalPrice());
			//使用规则引擎计价
		    ksession1.setGlobal("pricer", new OrderPricer());
		    ksession1.insert(order);  
		    ksession1.fireAllRules();  
		    ksession1.dispose();  
		}
		

}
