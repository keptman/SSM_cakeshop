package SSM.controller;
import SSM.domain.Goods;
import SSM.domain.Order;
import SSM.domain.Type;
import SSM.domain.User;
import SSM.service.GoodsService;
import SSM.service.TypeService;
import SSM.service.UserService;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.jdbc.Null;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;


@RequestMapping("/admin")
@Controller
public class adminController {
    @Resource(name = "goodsService")
    private GoodsService goodsService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "typeService")
    private TypeService typeService;
    @Resource(name = "servletContext")
    private ServletContext servletContext;
    @RequestMapping("/order_list")
    public String selectOrderList(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
                                  @RequestParam(name = "size", defaultValue = "10") Integer size, HttpServletRequest request){
        int status = 0;
        if(request.getParameter("status") != null) {
            status=Integer.parseInt(request.getParameter("status") ) ;
        }
        request.setAttribute("status", status);
        List<Order> orderList = goodsService.selectOrderList(page, size);
//        for (Order o : orderList){
//            System.out.println(o.getId());
////            System.out.println(o.getUser().getUsername());
//        }
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("p",pageInfo);
        return "/admin/order_list";
    }
    @RequestMapping("/order_listBy")
    public String selectOrderListBy(Model model, @RequestParam(name = "page", defaultValue = "1") Integer page,
                                    @RequestParam(name = "size", defaultValue = "10") Integer size, HttpServletRequest request){
        int status = 0;
        if(request.getParameter("status") != null) {
            status=Integer.parseInt(request.getParameter("status") ) ;
        }
        request.setAttribute("status", status);
        List<Order> orderList = goodsService.selectOrderListBy(status,page, size);
//        for (Order o : orderList){
//            System.out.println(o.getId());
////            System.out.println(o.getUser().getUsername());
//        }
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("p",pageInfo);
        return "/admin/order_listby";
    }
    @RequestMapping("/order_status")
    public String order_status(@RequestParam(name = "id") Integer id,@RequestParam(name = "status") Integer status){
        goodsService.updateStatus(id, status);
        return "forward:/admin/order_listBy?status="+status;
    }
    @RequestMapping("/order_delete")
        public String order_delete(@RequestParam(name = "id") Integer id){
            goodsService.delete(id);
            return "forward:/admin/order_list";
        }
    @RequestMapping("/order_deleteBy")
    public String order_deleteBy(@RequestParam(name = "id") Integer id){
        goodsService.delete(id);
        return "forward:/admin/order_listBy";
    }
    @RequestMapping("/user_list")
    public String user_list(@RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer size,Model model){
        List<User> list = goodsService.selectUserList(page, size);
        PageInfo<User> pageInfo = new PageInfo<>(list);
        model.addAttribute("p",pageInfo);
        return "forward:/admin/user_list.jsp";
    }
    @RequestMapping("/user_add")
    public  ModelAndView user_add(User user){
        ModelAndView modelAndView = new ModelAndView();
        if (userService.register(user)){
            user.setPassword(null);
            modelAndView.addObject("msg","客户添加成功");
            modelAndView.setViewName("forward:/admin/user_list");
        }else {
            modelAndView.addObject("failmsg","用户名或邮箱已存在");
            modelAndView.addObject("u",user);
            modelAndView.setViewName("/admin/user_add");
        }
        return modelAndView;
    }
    @RequestMapping("/user_delete")
    public  String user_delete(@RequestParam(name = "id") Integer id,Model model){
        boolean isSuccess = userService.delete(id);
        if(isSuccess) {
            model.addAttribute("msg", "客户删除成功");
        }else {
            model.addAttribute("failMsg", "客户有下的订单，请先删除该客户下的订单，再来删除客户！");
        }
        return "forward:/admin/user_list";
    }
    @RequestMapping("/user_reset")
    public  String user_reset(User user,Model model){
        String newPassword = userService.getPasswordById(user);
        if (!newPassword.equals(user.getPassword())){
            model.addAttribute("msg","密码重置成功");
            userService.updatePwd(user);
            return "forward:/admin/user_list";
        }else {
            model.addAttribute("failMsg","新密码和原密码相同");
            return "forward:/admin/user_list";
        }
    }
    @RequestMapping("/user_edit")
    public  String user_edit(User user,Model model){
        userService.updateUserAddress(user);
        model.addAttribute("msg","修改成功");
        return "forward:/admin/user_list";
    }
    @RequestMapping("/type_list")
    public  String type_list(Model model){
        List<Type>list =  typeService.selectAll();
        model.addAttribute("list",list);
        servletContext.removeAttribute("typeList");
        servletContext.setAttribute("typeList", list);
        return "forward:/admin/type_list.jsp";
    }
    @RequestMapping("/type_add")
    public  String type_add(@RequestParam(name = "name") String name){
        typeService.insert(new Type(name));
        return "forward:/admin/type_list";
    }
    @RequestMapping("/type_edit")
    public  String user_edit(Type type){
        typeService.update(type);
        return "forward:/admin/type_list";
    }
    @RequestMapping("/type_delete")
    public  String user_edit(@RequestParam(name = "id") Integer id,Model model){
        boolean isSuccess = typeService.delete(id);
        if(isSuccess) {
            model.addAttribute("msg", "删除成功");
        }else {
            model.addAttribute("failMsg", "类目下包含商品，无法直接删除类目！");
        }
        return "forward:/admin/type_list";
    }
    @RequestMapping("/goods_list")
    public  String goods_list(@RequestParam(name = "type",  defaultValue = "0") Integer type,
                              @RequestParam(name = "page",  defaultValue = "1") Integer page,
                              @RequestParam(name = "size",  defaultValue = "8") Integer size,Model model){
        List<Goods> list = goodsService.selectGoodsRecommend(type, page, size);
        PageInfo<Goods> pageInfo = new PageInfo<>(list);
        model.addAttribute("p",pageInfo);
        model.addAttribute("type",type);
        return "forward:/admin/goods_list.jsp";
    }
    @RequestMapping("/goods_recommend")
    public  String admin_goods_recommend(@RequestParam(name = "id") Integer id,
                              @RequestParam(name = "method") String method,
                              @RequestParam(name = "typeTarget") Integer typeTarget){
        if(method.equals("add")) {
            goodsService.addRecommend(id, typeTarget);
        }else {
            goodsService.removeRecommend(id, typeTarget);
        }
        return "forward:/admin/goods_list";
    }
    @RequestMapping("/goods_add")
    public String goods_add(
            @RequestParam(name = "cover") MultipartFile cover,
            @RequestParam(name = "image1") MultipartFile image1,
            @RequestParam(name = "image2") MultipartFile image2,
            @RequestParam(name = "name") String name,
            @RequestParam(name = "price", required = false) float price,
            @RequestParam(name = "intro", required = false) String intro,
            @RequestParam(name = "stock", required = false) Integer stock,
            @RequestParam(name = "typeid") Integer typeid
    ) throws IOException {
        // 处理商品信息
        Goods g = new Goods();
        g.setName(name);
        g.setPrice(price);
        g.setIntro(intro);
        g.setStock(stock);
        g.setTypeid(typeid);
        // 设置其他属性...

        // 保存文件
        String originalFilename = cover.getOriginalFilename();
//        originalFilename = originalFilename.substring(originalFilename.lastIndexOf("."));
        originalFilename = "/"+new Date().getTime()+originalFilename.substring(originalFilename.lastIndexOf("."));
        String path =this.servletContext.getRealPath("/picture")+originalFilename;
        System.out.println(path);
        cover.transferTo(new File(path));
        g.setCover("/picture"+originalFilename);


        String originalFilename1 = image1.getOriginalFilename();
        originalFilename1 = "/"+new Date().getTime()+originalFilename1.substring(originalFilename1.lastIndexOf("."));
        String path1 =this.servletContext.getRealPath("/picture")+originalFilename1;
        System.out.println(path1);
        image1.transferTo(new File(path1));
        g.setImage1("/picture"+originalFilename1);

        String originalFilename2 = image2.getOriginalFilename();
        originalFilename2 = "/"+new Date().getTime()+originalFilename2.substring(originalFilename2.lastIndexOf("."));
        String path2 =this.servletContext.getRealPath("/picture")+originalFilename2;
        System.out.println(path2);
        image2.transferTo(new File(path2));
        g.setImage2("/picture"+originalFilename2);

        // 处理其他业务逻辑...
        goodsService.insert(g);
        return "forward:/admin/goods_list";
    }
    @RequestMapping("/goods_editshow")
   public String goods_editshow(@RequestParam(name = "id") Integer id,Model model){
        Goods g = goodsService.getById(id);
        model.addAttribute("g", g);
        return "/admin/goods_edit";
   }

   @RequestMapping("/goods_edit")
   public String goods_edit(@RequestParam(name = "cover") MultipartFile cover,
                            @RequestParam(name = "image1") MultipartFile image1,
                            @RequestParam(name = "image2") MultipartFile image2,
                            @RequestParam(name = "name") String name,
                            @RequestParam(name = "price", required = false) float price,
                            @RequestParam(name = "intro", required = false) String intro,
                            @RequestParam(name = "stock", required = false) Integer stock,
                            @RequestParam(name = "typeid") Integer typeid,
                            @RequestParam(name = "pageNum") Integer pageNum,
                            @RequestParam(name = "type") Integer type,
                            @RequestParam(name = "cover0") String cover0,
                            @RequestParam(name = "image10") String image10,
                            @RequestParam(name = "image20") String image20,
                            @RequestParam(name = "id") Integer id) throws IOException {
       Goods g = new Goods();
       g.setId(id);
       g.setName(name);
       g.setPrice(price);
       g.setIntro(intro);
       g.setStock(stock);
       g.setTypeid(typeid);
       // 设置其他属性...
       // 保存文件
       if (!Objects.requireNonNull(cover.getOriginalFilename()).isEmpty()){
           String originalFilename = cover.getOriginalFilename();
           originalFilename = "/"+new Date().getTime()+originalFilename.substring(originalFilename.lastIndexOf("."));
           String path =this.servletContext.getRealPath("/picture")+originalFilename;
           System.out.println(path);
           cover.transferTo(new File(path));
           g.setCover("/picture"+originalFilename);
       }else {
           g.setCover(cover0);
       }

       if (!Objects.requireNonNull(image1.getOriginalFilename()).isEmpty()) {
           String originalFilename1 = image1.getOriginalFilename();
           originalFilename1 = "/" + new Date().getTime() + originalFilename1.substring(originalFilename1.lastIndexOf("."));
           String path1 = this.servletContext.getRealPath("/picture") + originalFilename1;
           System.out.println(path1);
           image1.transferTo(new File(path1));
           g.setImage1("/picture" + originalFilename1);
       }else {
           g.setImage1(image10);
       }

       if (!Objects.requireNonNull(image2.getOriginalFilename()).isEmpty()) {
           String originalFilename2 = image2.getOriginalFilename();
           originalFilename2 = "/" + new Date().getTime() + originalFilename2.substring(originalFilename2.lastIndexOf("."));
           String path2 = this.servletContext.getRealPath("/picture") + originalFilename2;
           System.out.println(path2);
           image2.transferTo(new File(path2));
           g.setImage2("/picture" + originalFilename2);
       }else {
           g.setImage2(image20);
       }
       // 处理其他业务逻辑...
       goodsService.update(g);
       String s = "forward:/admin/goods_list?page=" + pageNum + "&type=" + type;
       return s;
   }

   @RequestMapping("/goods_delete")
   public String goods_delete(@RequestParam(name = "id") Integer id){
        goodsService.deleteG(id);
        return "forward:/admin/goods_list";
   }
}
