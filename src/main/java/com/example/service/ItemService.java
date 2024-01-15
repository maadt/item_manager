package com.example.service;

import java.time.LocalDateTime;
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
	
	// 一覧取得処理
	public List<Item> findAll() { 
		// List ... 順序を持つ要素の集合
		// Item ... Entityクラスの型
		return this.itemRepository.findAll(); //データベースからすべてのアイテムを取得し、その結果を呼び出し元に返す
	}
	
	// 挿入処理
	public Item save(ItemForm itemForm) {
	// save() ... DBに保存するためのメソッド
	// ItemForm itemForm ... コントローラーで指定したモデル属性名
		
		Item item = new Item(); // 新しく登録するエンティティクラスのオブジェクトを作成
		item.setName(itemForm.getName()); // 追加したモデル属性の情報を取得してエンティティクラスにセット
		item.setPrice(itemForm.getPrice());
		item.setCategoryId(itemForm.getCategoryId());
		item.setStock(0); // 新規商品の在庫の初期値は0とする
		return this.itemRepository.save(item); // itemRepositoryを介してDBにデータが保存される
	}
	
	// 一件取得処理
	public Item findById(Integer id) { 
	// findById(Integer id) ... コントローラークラスからidを受け取る
		Optional<Item> optionalItem = this.itemRepository.findById(id);
		// this.itemRepository.findById(id) ... itemRepositoryを介してidに対応したデータを取得
		// Optional ... データが見つからなかった場合でもnullではなくOptional.empty()を返す
		// <Item> ... 取得したデータをItemクラスのフィールドに代入
		// optionalItem ... Itemクラスのオブジェクトを保持する
		Item item = optionalItem.get();
		// Itemクラスからデータを取得し保持する
		return item;
		// 保持したデータを返す
	}
	
	// 更新処理
	public Item update(Integer id, ItemForm itemForm) {
	// Integer id ... コントローラークラスから商品IDを受け取る
	// ItemForm itemForm ... コントローラークラスから商品情報を受け取る
		Item item = this.findById(id);
		// this.findById(id) ... クラス内のfindByIdメソッドを実行
		item.setName(itemForm.getName()); // 追加したモデル属性の情報を取得してエンティティクラスにセット
		item.setPrice(itemForm.getPrice());
		item.setCategoryId(itemForm.getCategoryId());
		return this.itemRepository.save(item); // itemRepositoryを介してデータの更新処理を行う
	}
	
	// 論理削除1
	public Item delete(Integer id) {
	// Integer id ... コントローラークラスから商品IDを受け取る
        Item item = this.findById(id);
        // EntityクラスのdeletedAtフィールドを現在日時で上書きします
        item.setDeletedAt(LocalDateTime.now());
        return this.itemRepository.save(item);
		
        //物理削除
		//this.itemRepository.deleteById(id);
		// this.itemRepository.deleteById(id) ... itemRepositoryを介してデータの削除処理を行う
        //メソッドは値を返さないのでvoid型となる
	}
	
	// 論理削除2
    public List<Item> findByDeletedAtIsNull() {
        return this.itemRepository.findByDeletedAtIsNull();
        // this.itemRepository.findByDeletedAtIsNull() ... itemRepositoryを介して非表示処理を行う
    }
    
    // 入荷処理
    public Item nyuka(Integer id, Integer inputValue) {
        Item item = this.findById(id);
        item.setStock(item.getStock() + inputValue); // 在庫数の加算
        return this.itemRepository.save(item); // 在庫数の保存
    }

    // 出荷処理
    public Item shukka(Integer id, Integer inputValue) {
        Item item = this.findById(id);
        if (inputValue <= item.getStock()) { // 出荷数に対して在庫数が足りているかを判定する
            item.setStock(item.getStock() - inputValue); // 足りていれば在庫数の減算を行う
        }

        // 在庫数の変動を保存
        return this.itemRepository.save(item);
    }
}
