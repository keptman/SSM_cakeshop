package SSM.mapper;
import SSM.domain.Goods;
import SSM.domain.Order;
import SSM.domain.OrderItem;
import SSM.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;


public interface GoodsMapper {

    @Select("select g.id,g.name,g.cover,g.price,t.name typename from recommend r,goods g,type t where type=#{type} and r.goods_id=g.id and g.type_id=t.id")
    List<Map<String, Object>> GoodsList(int type);

    @Select("select g.id,g.name,g.cover,g.price from recommend r,goods g where type=23 and r.goods_id=g.id LIMIT 1")
    Map<String, Object> FGoods();

    @Select("select * from goods")
    List<Goods> selectGoods();

    @Select("select * from goods where type_id = #{type_id}")
    List<Goods> selectTGoods(int typeId);

    @Select({
            "<script>",
            "<choose>",
            "<when test='type == 0'>",
            "SELECT g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name AS typename",
            "FROM goods g, type t",
            "WHERE g.type_id = t.id",
            "ORDER BY g.id",
            "</when>",
            "<otherwise>",
            "SELECT g.id, g.name, g.cover, g.image1, g.image2, g.intro, g.price, g.stock, t.name AS typename",
            "FROM goods g, recommend r, type t",
            "WHERE g.id = r.goods_id",
            "AND g.type_id = t.id",
            "AND r.type = #{type}",
            "ORDER BY g.id",
            "</otherwise>",
            "</choose>",
            "</script>"
    })
    List<Goods> selectGoodsRecommend(int type);

    @Select("select g.id,g.name,g.cover,g.image1,g.image2,g.price,g.intro,g.stock,t.id typeid,t.name typename from goods g,type t where g.id =#{id} and g.type_id=t.id")
    Goods getById(int id);

    @Select("select * from goods where name like CONCAT('%', #{keyword}, '%') ")
    List<Goods> searchGoods(String keyword);

    @Insert("INSERT INTO `order` (total, amount, status, paytype, name, phone, address, datetime, user_id) " +
            "VALUES (#{total}, #{amount}, #{status}, #{paytype}, #{name}, #{phone}, #{address}, #{datetime}, #{user.id})")
    void addOrder(Order o);

    @Insert("insert into `orderitem`(price,amount,goods_id,order_id)" + "values(#{price},#{amount},#{goods.id},#{order.id})")
    void addOrderItem(OrderItem item);

    @Select("select last_insert_id()")
    int getLastInsertId();

    @Select("select * from `order` where user_id=#{user_id} order by datetime desc")
    List<Order> selectAll(int userid);

    @Select("select i.id,i.price,i.amount,g.name from orderitem i,goods g where order_id=#{order_id} and i.goods_id=g.id")
    List<OrderItem> selectAllItem(int orderid);

    @Select("select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id order by o.datetime desc")
    List<Order> selectOrderList();

    @Select("select o.id,o.total,o.amount,o.status,o.paytype,o.name,o.phone,o.address,o.datetime,u.username from `order` o,user u where o.user_id=u.id and o.status=#{status} order by o.datetime desc")
    List<Order> selectOrderListBy(int status);

    @Select("UPDATE `order` SET status = ${status} WHERE id = ${id}")
    void updateStatus(@Param("id") int id, @Param("status") int status);

    @Delete("delete from `order` where id = #{id}")
    void delete(@Param("id") int id);

    @Delete("delete from orderitem where order_id=#{order_id}")
    void deleteOrderItem(int order_id);

    @Select("SELECT * FROM user ORDER BY id DESC")
    List<User> selectUserList();

    @Select("SELECT * FROM recommend WHERE type = #{type} AND goods_id = #{g.id}")
    Boolean isRecommend(@Param("g") Goods g, @Param("type") int type);
    default boolean isHot(Goods g){
        Boolean result = isRecommend(g, 2);
        return result != null && result;
    }
    default boolean isNew(Goods g){
        Boolean result = isRecommend(g, 3);
        return result != null && result;
    }
    default boolean isScroll(Goods g){
        Boolean result = isRecommend(g, 1);
        return result != null && result;
    }

    @Insert("insert into recommend(type,goods_id) values(#{typeTarget},#{id})")
    void addRecommend(@Param("id")int id,@Param("typeTarget")int typeTarget);

    @Delete("delete from recommend where type=#{typeTarget} and goods_id=#{id}")
    void removeRecommend(@Param("id")int id,@Param("typeTarget")int typeTarget);


    @Select("insert into goods(name,cover,image1,image2,price,intro,stock,type_id) values(#{name},#{cover},#{image1},#{image2},#{price},#{intro},#{stock},#{type.id})")
    void insert(Goods g);


   @Update("update goods set name=#{name},cover=#{cover},image1=#{image1},image2=#{image2},price=#{price},intro=#{intro},stock=#{stock},type_id=#{type.id} where id=#{id}")
    void update(Goods g);

   @Delete("delete from goods where id = #{id}")
    void deleteG(int id);
}
