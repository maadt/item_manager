package com.example.service;

import java.util.List;

//データの操作を伴う処理を作成する場合はServiceやRepositoryに処理を追加してからControllerの実装に入るのが基本です。

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Item;
import com.example.form.ItemForm;
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
	
	public Item save(ItemForm itemForm) {
	// save() ... DBに保存するためのメソッド
	// ItemForm itemForm ... コントローラーで指定したモデル属性名
		
		Item item = new Item(); // 新しく登録するエンティティクラスのオブジェクトを作成
		item.setName(itemForm.getName()); // 追加したモデル属性の情報を取得してエンティティクラスにセット
		item.setPrice(itemForm.getPrice());
		return this.itemRepository.save(item); // itemRepositoryを介してDBにデータが保存される
	}
}
