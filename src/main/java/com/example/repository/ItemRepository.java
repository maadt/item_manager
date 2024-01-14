package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer>{
  // @Repository ... データベース操作を抽象化し、エンティティの永続化と取得を行う
  // JpaRepository<Item, Integer> ... エンティティとその主キーの型を指定することで、CRUD操作をサポートする
}
