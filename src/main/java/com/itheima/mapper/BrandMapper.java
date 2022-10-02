package com.itheima.mapper;

import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {
    @Select("select * from brand")
    @ResultMap("brandRM")
    List<Brand> selectAll();

    @Insert("insert into brand(bname, cname, ordered, description, status) VALUES (#{brandName},#{companyName},#{ordered},#{description},#{status})")
    @ResultMap("brandRM")
    void add(Brand brand);

    @Select("select * from brand where id = #{id}")
    @ResultMap("brandRM")
    Brand selectById(int id);

    @Update("UPDATE brand set bname = #{brandName},cname = #{companyName},ordered = #{ordered},description = #{description},status = #{status} where id = #{id}")
    @ResultMap("brandRM")
    void update(Brand brand);

    @Delete("delete from brand where id = #{id}")
    void deleteById(int id);

    @ResultMap("brandRM")
    void delByIds(@Param("ids")int[] ids);

    @Select("select * from brand limit #{begin} , #{size} ")
    @ResultMap("brandRM")
    List<Brand> selectByPage(@Param("begin") int begin, @Param("size")int size);

    @Select("select count(*) from brand ")
    Integer selectTotalCount();
}
