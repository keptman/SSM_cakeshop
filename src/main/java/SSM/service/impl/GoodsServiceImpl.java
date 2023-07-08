package SSM.service.impl;

import SSM.domain.Goods;
import SSM.domain.Order;
import SSM.domain.OrderItem;
import SSM.domain.User;
import SSM.mapper.GoodsMapper;
import SSM.service.GoodsService;
import com.github.pagehelper.PageHelper;
import jakarta.annotation.Resource;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Resource(name = "goodsMapper")
    private GoodsMapper goodsMapper;
    @Override
    public List<Map<String, Object>>GoodsList(int type) {
        List<Map<String,Object>>goodsList= goodsMapper.GoodsList(type);
        return goodsList;
    }

    @Override
    public Map<String, Object> FGoods() {
        Map<String,Object>fGoods = goodsMapper.FGoods();
        return fGoods;
    }


    @Override
    public List<Goods> selectGoods(int page,int size) {
        PageHelper.startPage(page,size);
        List<Goods> list = goodsMapper.selectGoods();
        return list;
    }

    @Override
    public List<Goods> selectTGoods(int typeId, int page, int size) {
        PageHelper.startPage(page,size);
        List<Goods> tlist = goodsMapper.selectTGoods(typeId);
        return tlist;
    }

    @Override
    public List<Goods> selectGoodsRecommend(int type,int page, int size) {
        PageHelper.startPage(page,size);
        List<Goods> relist = goodsMapper.selectGoodsRecommend(type);
        for(Goods g : relist) {
            g.setScroll(goodsMapper.isScroll(g));
            g.setHot(goodsMapper.isHot(g));
            g.setNew(goodsMapper.isNew(g));
        }
        return relist;
    }

    @Override
    public Goods getById(int id) {
        Goods goods = goodsMapper.getById(id);
        return goods;
    }

    @Override
    public List<Goods> searchGoods(String keyword, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Goods> searchlist = goodsMapper.searchGoods(keyword);
        return searchlist;
    }

    @Override
    public void addOrder(Order o) {
      goodsMapper.addOrder(o);
      int id = goodsMapper.getLastInsertId();
      o.setId(id);
      for (OrderItem item : o.getItemMap().values()){
          goodsMapper.addOrderItem(item);
      }

    }

    @Override
    public List<Order> selectAll(int userid) {
        List<Order>list =  goodsMapper.selectAll(userid);
        for (Order order : list){
         List<OrderItem> l =   goodsMapper.selectAllItem(order.getId());
         order.setItemList(l);
        }
        return list;
    }

    @Override
    public List<Order> selectOrderList(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Order> list = goodsMapper.selectOrderList();
        for(Order o : list) {
            List<OrderItem> l = goodsMapper.selectAllItem(o.getId());
            o.setItemList(l);
        }
        return list;
    }

    @Override
    public List<Order> selectOrderListBy(int status,Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<Order> list = goodsMapper.selectOrderListBy(status);
        for(Order o : list) {
            List<OrderItem> l = goodsMapper.selectAllItem(o.getId());
            o.setItemList(l);
        }
        return list;
    }

    @Override
    public void updateStatus(@Param("id") int id, @Param("status") int status) {
        goodsMapper.updateStatus(id, status);
    }

    @Override
    public void delete(@Param("id") int id) {
        goodsMapper.deleteOrderItem(id);
        goodsMapper.delete(id);
    }

    @Override
    public List<User> selectUserList(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<User> list = goodsMapper.selectUserList();
        return list;
    }

    @Override
    public void addRecommend(int id, int typeTarget) {
        goodsMapper.addRecommend(id, typeTarget);
    }

    @Override
    public void removeRecommend(int id, int typeTarget) {
        goodsMapper.removeRecommend(id, typeTarget);
    }

    @Override
    public void insert(Goods g) {
        goodsMapper.insert(g);
    }

    @Override
    public void update(Goods g) {
        goodsMapper.update(g);
    }

    @Override
    public void deleteG(int id) {
        goodsMapper.deleteG(id);
    }

}
