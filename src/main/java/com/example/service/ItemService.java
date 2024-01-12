package com.example.service;

import java.util.List;
//import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
//import com.example.form.ItemForm;
import com.example.repository.ItemRepository;


@Service // ビジネスロジックを実装し、アプリケーションの機能を提供するためのクラス
public class ItemService {
	
	private final ItemRepository itemRepository;
	  // ItemRepositoryの実装クラスのインスタンスを保持する
	
	@Autowired //引数に紐づくクラスのインスタンスを生成し、利用する
	public ItemService(ItemRepository itemRepository) { //コンストラクタ・インジェクション
		this.itemRepository = itemRepository;
	}
	
	public List<Item> findAll() { 
		// List ... 順序を持つ要素の集合
		// Item ... Entityクラスの型
		return this.itemRepository.findAll(); //データベースからすべてのアイテムを取得し、その結果を呼び出し元に返す
	}
}
