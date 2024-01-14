package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Item;
import com.example.form.ItemForm;
import com.example.service.ItemService;


@Controller //コントローラーとしての機能を付与
@RequestMapping("/item") //リクエストを受け取るパスを設定
public class ItemController {
	
	private final ItemService itemService; // サービスクラスのインスタンスを保持する
	
	@Autowired //引数に紐づくクラスのインスタンスを生成し、利用する
	public ItemController(ItemService itemService) { //コンストラクタインジェクション
		this.itemService = itemService;
	}
	
	//商品一覧を表示
	@GetMapping //「http://localhost:8080/item」で取得できる
	public String index(Model model) { // データを受け取りビューへ渡す
		List<Item> items = this.itemService.findAll();
		System.out.println(items.toString());
		  //http://localhost:8080/item にアクセスするとコンソールに取得したデータを出力
		model.addAttribute("items", items); //itemsをビューへ渡す
		return "item/index"; //「item/index.html」を返す
	}
	
	// 商品登録ページ表示用
	@GetMapping("toroku") //「http://localhost:8080/item/toroku」で取得できる
	public String torokuPage(@ModelAttribute("itemForm") ItemForm itemForm) {
		// @ModelAttribute("itemForm") ... itemFormをモデルに追加
		// ItemForm itemForm ... オブジェクト名
		
		return "item/torokuPage"; //「item/torokuPage.html」を返す
	}
	
	// 商品登録の実行
	@PostMapping("toroku") //「http://localhost:8080/item/toroku」で送信できる
	public String toroku(ItemForm itemForm) { // itemForm を受け取る
		this.itemService.save(itemForm); // サービスクラスのsaveメソッドを実行する
		return "redirect:/item"; // 登録実行後 "/item" へリダイレクト
	}
	
	// 商品編集ページ
	@GetMapping("henshu/{id}") //「http://localhost:8080/henshu/{id}」で取得できる
	public String henshuPage(@PathVariable("id") Integer id, Model model
			                 , @ModelAttribute("itemForm") ItemForm itemForm) {
		// @PathVariable("id") Integer id ... パスで渡された"id"がIntegerに変換される
		// Model model ... ビューにデータを渡す
		// @ModelAttribute("itemForm") ... itemFormをモデルに追加
	    // ItemForm itemForm ... オブジェクト名
		Item item = this.itemService.findById(id);
		// this.itemService.findById(id) ... idをサービスクラスのfindByIdメソッドに渡して実行
		// Item item ... サービスクラスが取得したデータを保持する
		itemForm.setName(item.getName());
		// item.getName() ... itemクラスからデータを取得する
		// itemForm.setName ... フォームクラスのフィールドにデータを渡す
		itemForm.setPrice(item.getPrice());
		model.addAttribute("id", id);
		// model ... ビューに渡すためのコンテナ
		// addAttribute ... 第一引数にモデル属性名、第二引数に変数名
		return "item/henshuPage"; //「item/henshuPage.html」を返す
	}
	
	//商品編集の実行
	@PostMapping("henshu/{id}") //「http://localhost:8080/henshu/{id}」で送信できる
	public String henshu(@PathVariable("id") Integer id, @ModelAttribute("itemForm") ItemForm itemForm) {
		this.itemService.update(id, itemForm);
		// サービスクラスのupdateメソッドに値を渡して実行
		return "redirect:/item";
		// redirect: ... 重複送信問題を防ぐためPOSTリクエスト内のreturn文に指定する
	}
	
	//商品削除の実行 
	@PostMapping("sakujo/{id}") //「http://localhost:8080/sakujo/{id}」で送信できる
	public String sakujo(@PathVariable("id") Integer id) {
		return "redirect:/item";
	}
}
