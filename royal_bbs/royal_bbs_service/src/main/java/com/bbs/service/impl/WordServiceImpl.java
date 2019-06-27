package com.bbs.service.impl;

import com.bbs.dao.WordDao;
import com.bbs.domain.Word;
import com.bbs.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WordServiceImpl implements WordService {

    @Autowired
    private WordDao wordDao;

    /**
     * 查询所有敏感词汇
     * @return
     * @throws Exception
     */
    @Override
    public List<String> findAll() throws Exception {
        return wordDao.findAll();
    }
}
