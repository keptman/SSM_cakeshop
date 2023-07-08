package SSM.controller;
import SSM.domain.Goods;
import SSM.domain.Order;
import SSM.domain.Type;
import SSM.domain.User;
import SSM.service.GoodsService;
import SSM.service.TypeService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Map;


@RequestMapping("/goods")
@SessionAttributes("order")
@Controller
public class GoodsController {
       @Resource(name = "goodsService")
       private GoodsService goodsService;
       @Resource(name = "typeService")
       private TypeService typeService;
       @RequestMapping("/index")
       public ModelAndView indexList(){
          ModelAndView modelAndView = new ModelAndView();
          List<Map<String, Object>> hotList = goodsService.GoodsList(21);
          List<Map<String, Object>> newList = goodsService.GoodsList(22);
          Map<String,Object>fGoods= goodsService.FGoods();
          modelAndView.addObject("hotList",hotList);
          modelAndView.addObject("newList",newList);
          modelAndView.addObject("fGoods",fGoods);
//           List<Map<String, Object>> get1List = goodsService.get1List();
//           List<Map<String, Object>> get2List = goodsService.get2List();
//           modelAndView.addObject("get1List",get1List);
//           modelAndView.addObject("get2List",get2List);
           modelAndView.setViewName("index");
          return modelAndView;
       }
       @RequestMapping("/goods-list")
       public ModelAndView goodsList( @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                      @RequestParam(name = "size", required = true, defaultValue = "8") Integer size){
           ModelAndView modelAndView = new ModelAndView();
           List<Goods> list = goodsService.selectGoods(page,size);
           PageInfo<Goods> pageInfo = new PageInfo<>(list);
           modelAndView.addObject("allList",pageInfo);
           modelAndView.setViewName("goods_list");
           return modelAndView;
    }
    @RequestMapping("/goods-tList")
    public ModelAndView typeList( @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                  @RequestParam(name = "size", required = true, defaultValue = "8") Integer size,
                                  @RequestParam(name = "id", required = true, defaultValue = "1") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        List<Goods> list = goodsService.selectTGoods(id,page,size);
        Type type = typeService.select(id);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("tList", pageInfo);
        modelAndView.addObject("t",type);
        modelAndView.setViewName("goods_tlist");
        return modelAndView;
    }
    @RequestMapping("/goodsrecommend_list")
    public ModelAndView recommendList( @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                       @RequestParam(name = "size", required = true, defaultValue = "8") Integer size,
                                       @RequestParam(name = "type", required = true) Integer type){
        ModelAndView modelAndView = new ModelAndView();
        List<Goods> list = goodsService.selectGoodsRecommend(type,page,size);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("relist", pageInfo);
        modelAndView.addObject("type",type);
        modelAndView.setViewName("goodsrecommend_list");
        return modelAndView;
    }
    @RequestMapping("/goods_detail")
    public ModelAndView goods_detail(@RequestParam(name = "id") Integer id){
        ModelAndView modelAndView = new ModelAndView();
        Goods goods = goodsService.getById(id);
        modelAndView.addObject("goods",goods);
        modelAndView.setViewName("goods_detail");
        return modelAndView;
    }
    @RequestMapping("/goods_cart")
    public String goods_cart(){
          return "goods_cart";
    }
    @ResponseBody
    @RequestMapping("/goods_buy")
    public String goods_buy(@RequestParam(name = "goodsid") Integer goodsid, Model model){
        Order o = null;
        if(model.getAttribute("order") != null) {
            o = (Order) model.getAttribute("order");
        }else {
            o = new Order();
            model.addAttribute("order",o);
        }
        Goods goods = goodsService.getById(goodsid);
        if(goods.getStock()>0) {
            o.addGoods(goods);
            return "ok";
        }else {
            return "fail";
        }
    }
    @ResponseBody
    @RequestMapping("/goods_lessen")
    public String goods_lessen(@RequestParam(name = "goodsid") Integer goodsid,Model model){
        Order o = (Order)model.getAttribute("order");
        o.lessen(goodsid);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/goods_delete")
    public String goods_delete(@RequestParam(name = "goodsid") Integer goodsid,Model model){
        Order o = (Order)model.getAttribute("order");
        o.delete(goodsid);
        return "ok";
    }
    @ResponseBody
    @RequestMapping("/goods_search")
    public ModelAndView goods_search(@RequestParam(name = "keyword") String keyword,
                               @RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                               @RequestParam(name = "size", required = true, defaultValue = "8") Integer size){
        ModelAndView modelAndView = new ModelAndView();
        List<Goods> list = goodsService.searchGoods(keyword,page,size);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        modelAndView.addObject("p",pageInfo);
        modelAndView.setViewName("goods_search");
        return modelAndView;
    }

    @RequestMapping("/order_confirm")
    public String order_confirm(Model model, @ModelAttribute("order") Order order, HttpServletRequest request, SessionStatus sessionStatus){
        Order o = (Order) model.getAttribute("order");
        o.setDatetime(new Date());
        o.setStatus(2);
        User loginuser = (User) request.getSession().getAttribute("loginuser");
        o.setUser(loginuser);
        goodsService.addOrder(o);
//        model.asMap().remove("order");
        sessionStatus.setComplete();
//        request.getSession().removeAttribute("order");

        model.addAttribute("msg", "订单支付成功！");
//        request.getRequestDispatcher("/order_success.jsp").forward(request, response);
        return "order_success";
    }
    @RequestMapping("/order_list")
    public String order_list(Model model,HttpServletRequest request){
        User longinuser = (User) request.getSession().getAttribute("loginuser");
        List<Order>list = goodsService.selectAll(longinuser.getId());
        model.addAttribute("orderList",list);
        return "order_list";
    }
}