package com.yango.robot.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WordMapper {

    @Select("Select word from word")
    List<String> listword();
}
