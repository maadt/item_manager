package com.example.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "CATEGORIES")
public class Category {

    @Id
    @SequenceGenerator(name = "CATEGORY_ID_GENERATOR", sequenceName = "CATEGORY_ID_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORY_ID_GENERATOR")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String  name;


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
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    // @OneToMany ... Categoryが複数のItem エンティティを持つことを示す（1対多）
    // mappedBy = "category" ... categoryがcategory_idに所有されていることを示す
    // cascade ... カスケード操作（Categoryエンティティに対する操作）を設定する
    // CascadeType.ALL ... 関連するすべてのItemエンティティにも適用される
    // fetch ... データの取得方法を設定する
    // FetchType.LAZY ... 遅延ロードされることを示す
    private List<Item> items;

    public List<Item> getItems() {
        return this.items;
    }
}