package service;

import model.Category;
import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    void createProduct(Product product);

    Product selectProductById(int id);

    List<Product> selectAllProduct();

    boolean updateProduct(Product product);

    boolean deleteProduct(int id);

    Category selectCategoryById(int id);

    List<Product> searchByName(String name);

    List<Category> selectAllCategory();
}
