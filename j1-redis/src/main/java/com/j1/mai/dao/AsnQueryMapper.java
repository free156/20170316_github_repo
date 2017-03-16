package com.j1.mai.dao;

import com.j1.mai.Entity.AsnEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by che2 on 2016/9/12.
 */

public interface AsnQueryMapper {
  public  AsnEntity asnQuery(Integer asnId);

  void saveAsn(AsnEntity asnEntity);
}
