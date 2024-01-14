package com.example.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // Entityクラスであることを示す
@Table(name = "ITEMS") // 使用するテーブル名

public class Item {
	
	@Id // フィールドが主キーであることを示す
	@SequenceGenerator(name = "ITEM_ID_GENERATOR", sequenceName = "ITEM_ID_SEQ", allocationSize = 1)
	  // @SequenceGenerator ... シーケンスを生成
	  // name = "ITEM_ID_GENERATOR" ... シーケンスジェネレータの名前
	  // sequenceName = "ITEM_ID_SEQ" ... 使用するデータベースのシーケンス名
	  // allocationSize = 1 ... シーケンスから取得する値の数
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ITEM_ID_GENERATOR")
	  // @GeneratedValue ... 主キーの値を自動で生成する
	  // strategy = GenerationType.SEQUENCE ... 値の生成戦略を指定する
	  // generator = "ITEM_ID_GENERATOR" ... 使用するシーケンスジェネレータの名前
	@Column(name = "ID") //フィールドに対応するカラムを指定する
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PRICE")
	private Integer price;
	
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return this.price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    public LocalDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
