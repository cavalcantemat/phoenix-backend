package com.ecom.phoenix.models;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import java.util.List;

@Entity
@Table(name = "products", schema = "public")
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String team;
    private String category;
    private double price;
    private int storage;
    private String directory;
    private String league;
    private String description;
    //todo
    @Type(value = JsonBinaryType.class)
    @Column(name = "color", columnDefinition = "jsonb")
    private List<String> color;

    public Product() {}

    public Product(long id, String team, String category, List<String> color, double price, int storage,
                   String directory, String league, String description) {
        this.id = id;
        this.team = team;
        this.category = category;
        this.color = color;
        this.price = price;
        this.storage = storage;
        this.directory = directory;
        this.league = league;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", team='" + team + '\'' +
                ", directory='" + directory + '\'' +
                ", category='" + category + '\'' +
                ", league='" + league + '\'' +
                ", color='" + color + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", storage=" + storage +
                '}';
    }
}