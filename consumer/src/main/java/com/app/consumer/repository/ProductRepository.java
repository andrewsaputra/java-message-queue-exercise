package com.app.consumer.repository;

import com.app.consumer.model.entity.Product;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query("UPDATE products SET compressed_images = :compressedImages WHERE id = :productId RETURNING *")
    Product setCompressedImages(int productId, String[] compressedImages);

}
