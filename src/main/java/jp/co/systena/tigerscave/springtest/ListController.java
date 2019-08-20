package jp.co.systena.tigerscave.springtest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ListController {

  @Autowired
  HttpSession session;


  @RequestMapping(value = "/list",method = RequestMethod.GET)
  public ModelAndView index(ModelAndView mav) {

    Map<Integer,Item> itemListMap = getItemListMap();
    mav.addObject("itemList",itemListMap);



    Cart cart = getCart();
    mav.addObject("orderList", cart.getOrderList());

    int totalPrice = 0;
    for (Order order : cart.getOrderList()) {
      if (itemListMap.containsKey(order.getItemid())) {
        totalPrice += order.getNum() * temListMap.get(order.getItemid()).getPrice();
      }
    }
    mav.addObject("totalPrice", totalPrice);



    mav.setViewName("ListView");

    return mav;

  }



  private Cart getCart() {
    // TODO 自動生成されたメソッド・スタブ
    Cart cart = (Cart) session.getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      session.setAttribute("cart", cart);
    }
    return cart;
  }


  private Map<Integer, Item> getItemListMap() {
    // TODO 自動生成されたメソッド・スタブ
    ListService service = new ListService();
    List<Item> ItemList = service.getItemList();

    // 商品一覧をMapに入れ直し
    Map<Integer, Item> ItemListMap = new HashMap<Integer, item>();
    for (item item : itemList) {
      ItemListMap.put(item.getItemid(), item);
    }

    return ItemListMap;
  }

  @RequestMapping(value = "/list", method = RequestMethod.POST) // URLとのマッピング
  private ModelAndView order(ModelAndView mav, @Valid ListForm listForm,
      BindingResult bindingResult, HttpServletRequest request) {

    Cart cart = getCart();

    if (bindingResult.getAllErrors().size() > 0) {
      // エラーがある場合はそのまま戻す
      mav.addObject("listForm", listForm); // 新規クラスを設定

      Map<Integer, Item> itemListMap = getItemListMap();
      mav.addObject("itemList", itemListMap);

      // Viewのテンプレート名を設定
      mav.setViewName("ListView");

      return mav;

    }
    Order order = new Order();
    order.setItemid(listForm.getItemid());
    order.setNum(listForm.getNum());
    cart.add(order);
    // データをセッションへ保存
    session.setAttribute("cart", cart);
    return new ModelAndView("redirect:/list"); // リダイレクト
  }

}
