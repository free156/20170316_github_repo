package com.j1.mai.service;

import com.j1.mai.Entity.AsnEntity;
import org.springframework.stereotype.Service;

/**
 * Created by che2 on 2016/9/12.
 */

public interface AsnQueryService {
    AsnEntity sanQuery(Integer asnId);
    void saveAsn(AsnEntity asnEntity);

}
