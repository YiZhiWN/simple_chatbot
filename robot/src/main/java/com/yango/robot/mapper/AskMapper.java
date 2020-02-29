package com.yango.robot.mapper;

import com.yango.robot.pojo.Ask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AskMapper {
    @Select("Select * from ask")
    List<Ask> listask();

    @Select("select MAX(id) from ask")
    int maxId();

    @Select("select MIN(id) from ask")
    int minId();

    @Select("select answer from ask where id=#{index}")
    String answer(@Param("index") int index);
}
