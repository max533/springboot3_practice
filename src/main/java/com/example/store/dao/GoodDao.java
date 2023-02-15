package com.example.store.dao;

import com.example.store.entity.Good;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface GoodDao extends CrudRepository<Good, UUID> {
}
