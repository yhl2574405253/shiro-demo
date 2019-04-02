package cn.et.demo02.mapper;

import cn.et.demo02.model.MenuModel;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    @Select("select * from t_menu_info where url=#{url}")
    List<MenuModel> getMenuByUrl(String url);
}
