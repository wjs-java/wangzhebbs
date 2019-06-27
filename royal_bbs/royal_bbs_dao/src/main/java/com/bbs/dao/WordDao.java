package com.bbs.dao;

import com.bbs.domain.Word;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface WordDao {

    /**
     * 查询所有敏感词
     * @return
     */
    @Select("select word from bbs_word_table")
    List<String> findAll();

}
