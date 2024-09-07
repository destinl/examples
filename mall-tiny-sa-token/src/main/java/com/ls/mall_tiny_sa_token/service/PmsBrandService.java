package com.ls.mall_tiny_sa_token.service;

import com.ls.mall_tiny_sa_token.mbg.model.PmsBrand;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/7 18:07
 */
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}