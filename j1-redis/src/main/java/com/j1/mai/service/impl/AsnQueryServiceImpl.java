package com.j1.mai.service.impl;

import com.j1.mai.Entity.AsnEntity;
import com.j1.mai.dao.AsnQueryMapper;
import com.j1.mai.service.AsnQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by che2 on 2016/9/12.
 */
@Service
public class AsnQueryServiceImpl implements AsnQueryService {

    @Autowired
    private AsnQueryMapper asnQueryMapper;
    public AsnEntity sanQuery(Integer asnId) {
        return asnQueryMapper.asnQuery(asnId);
    }

    public void saveAsn(AsnEntity asnEntity) {
        asnQueryMapper.saveAsn(asnEntity);
    }
}
