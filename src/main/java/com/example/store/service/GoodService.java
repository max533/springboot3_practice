package com.example.store.service;

import com.example.store.dao.GoodDao;
import com.example.store.entity.Good;
import com.example.store.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author Jeff
 */

@Service
public class GoodService {
    @Autowired
    GoodDao goodDao;

    public Iterable<Good> getGoodCollection() {
        return goodDao.findAll();
    }

    public Good getGood(UUID id) {
        return goodDao.findById(id).orElseThrow(
                () -> new BadRequestException("This is wrong good id")
        );
    }

    public Good createGood(Good good) {
        if ("".equals(good.getGoods_name())) {
            throw new BadRequestException("The goods_name can not be blank");
        }
        return goodDao.save(good);
    }

    public Good updateGood(UUID id, Good good) {
        if ("".equals(good.getGoods_name())) {
            throw new BadRequestException("The goods_name can not be blank");
        }

        Good goodInstance = goodDao.findById(id).orElseThrow(
                () -> new BadRequestException("This is wrong good id")
        );
        goodInstance.setGoods_name(good.getGoods_name());
        goodDao.save(goodInstance);
        return goodInstance;
    }

    public void deleteGood(UUID id) {
        goodDao.findById(id).orElseThrow(
                () -> new BadRequestException("This is wrong good id")
        );
        goodDao.deleteById(id);
    }

}
