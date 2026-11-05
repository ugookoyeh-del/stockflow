package com.stockflow;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.http.HttpStatus;import org.springframework.web.bind.annotation.*;import java.math.BigDecimal;import java.util.List;
@RestController @RequestMapping("/api/products")
public class ProductController{
 private final ProductRepository products;public ProductController(ProductRepository products){this.products=products;}
 public record ProductRequest(@NotBlank @Size(max=120) String name,@NotBlank @Size(max=40) String sku,@NotNull @DecimalMin("0.00") BigDecimal unitPrice,@Min(0) int quantity){}
 @GetMapping public List<Product> all(){return products.findAll();}
 @GetMapping("/{id}") public Product one(@PathVariable Long id){return products.findById(id).orElseThrow(()->new IllegalArgumentException("Product not found"));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product create(@Valid @RequestBody ProductRequest r){if(products.existsBySku(r.sku().trim()))throw new IllegalArgumentException("SKU already exists");return products.save(new Product(r.name().trim(),r.sku().trim(),r.unitPrice(),r.quantity()));}
 @PutMapping("/{id}") public Product update(@PathVariable Long id,@Valid @RequestBody ProductRequest r){Product p=one(id);if(!p.getSku().equals(r.sku().trim())&&products.existsBySku(r.sku().trim()))throw new IllegalArgumentException("SKU already exists");p.update(r.name().trim(),r.unitPrice(),r.quantity());return products.save(p);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){products.delete(one(id));}
 @GetMapping("/low-stock") public List<Product> lowStock(@RequestParam(defaultValue="5") @Min(0) int threshold){return products.findAll().stream().filter(p->p.getQuantity()<=threshold).toList();}
}