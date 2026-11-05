package com.stockflow;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
@Entity @Table(name="products", uniqueConstraints=@UniqueConstraint(columnNames="sku"))
public class Product {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=120) private String name;
 @Column(nullable=false,unique=true,length=40) private String sku;
 @Column(nullable=false,precision=12,scale=2) private BigDecimal unitPrice;
 @Column(nullable=false) private int quantity;
 @Column(nullable=false,updatable=false) private Instant createdAt=Instant.now();
 protected Product(){}
 public Product(String name,String sku,BigDecimal unitPrice,int quantity){this.name=name;this.sku=sku;this.unitPrice=unitPrice;this.quantity=quantity;}
 public void update(String name,BigDecimal price,int quantity){this.name=name;this.unitPrice=price;this.quantity=quantity;}
 public Long getId(){return id;} public String getName(){return name;} public String getSku(){return sku;}
 public BigDecimal getUnitPrice(){return unitPrice;} public int getQuantity(){return quantity;} public Instant getCreatedAt(){return createdAt;}
}