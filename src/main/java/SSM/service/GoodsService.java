package SSM.service;

import SSM.domain.Goods;
import SSM.domain.Order;
import SSM.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GoodsService {
   List<Map<String, Object>> GoodsList(int type);

   Map<String,Object>FGoods();

   List<Goods> selectGoods(int page, int size);
   List<Goods> selectTGoods(int typeId,int page, int size);

   List<Goods> selectGoodsRecommend(int type,int page,int size);

   Goods getById(int id);

   List<Goods> searchGoods(String keyword, Integer page, Integer size);

   void addOrder(Order o);

   List<Order> selectAll(int userid);

   List <Order> selectOrderList(Integer page, Integer size);

   List <Order> selectOrderListBy(int status,Integer page, Integer size);

   void updateStatus(@Param("id") int id, @Param("status") int status);

    void delete(@Param("id") int id);

   List<User> selectUserList(Integer page, Integer size);

   void addRecommend(int id, int typeTarget);

   void removeRecommend(int  id, int  typeTarget);

    void insert(Goods g);

   void update(Goods g);

   void deleteG(int id);
}
