package com.example.service;

import java.util.List;
import java.util.Optional;

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
	
	// データ一覧表示処理
	public List<Item> findAll() { 
		// List ... 順序を持つ要素の集合
		// Item ... Entityクラスの型
		return this.itemRepository.findAll(); //データベースからすべてのアイテムを取得し、その結果を呼び出し元に返す
	}
	
	// データ挿入処理
	public Item save(ItemForm itemForm) {
	// save() ... DBに保存するためのメソッド
	// ItemForm itemForm ... コントローラーで指定したモデル属性名
		
		Item item = new Item(); // 新しく登録するエンティティクラスのオブジェクトを作成
		item.setName(itemForm.getName()); // 追加したモデル属性の情報を取得してエンティティクラスにセット
		item.setPrice(itemForm.getPrice());
		return this.itemRepository.save(item); // itemRepositoryを介してDBにデータが保存される
	}
	
	// データ取得処理
	public Item findById(Integer id) { 
	// findById(Integer id) ... コントローラーからidを受け取る
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		// this.itemRepository.findById(id) ... ItemRepositoryインターフェイスを介してidに対応したデータを取得
		// Optional ... データが見つからなかった場合でもnullではなくOptional.empty()を返す
		// <Item> ... 取得したデータをItemクラスのフィールドに代入
		// optionalItem ... Itemクラスのオブジェクトを保持する
		Item item = optionalItem.get();
		// Itemクラスからデータを取得し保持する
		return item;
		// 保持したデータを返す
	}
	
	//データ更新処理
	public Item update(Integer id, ItemForm itemForm) {
		// update ... ItemRepositoryインターフェイスを介してデータの更新処理を行う
		// Integer id ... サービスクラスから商品IDを受け取る
		// ItemForm itemForm ... サービスクラスから商品情報を受け取る
		Item item = this.findById(id);
		// this.findById(id) ... クラス内のfindByIdメソッドを実行
		item.setName(itemForm.getName()); // 追加したモデル属性の情報を取得してエンティティクラスにセット
		item.setPrice(itemForm.getPrice());
		return this.itemRepository.save(item); // itemRepositoryを介してDBにデータが保存される
	}
}
