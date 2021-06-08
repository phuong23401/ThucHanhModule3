package service;

import model.Category;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    private static final String URL = "jdbc:mysql://localhost:3306/product-management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String CREATE_PRODUCT = "INSERT INTO `product` (`pName`, `price`, `number`, `color`, `description`, `cId`) VALUES (?, ?, ?, ?, ?, ?);";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT `id`, `pName`, `price`, `number`, `color`, `description`, `cId` FROM `product` WHERE `id` = ?;";
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM `product`;";
    private static final String UPDATE_PRODUCT = "UPDATE `product` SET `pName` = ?, `price` = ?, `number` = ?, `color` = ?, `description` = ?, `cId` = ? WHERE `id` = ?;";
    private static final String DELETE_PRODUCT = "DELETE FROM `product` WHERE `id` = ?;";
    private static final String SEARCH_PRODUCT_BY_NAME = "SELECT * FROM `product` WHERE `pName` = ?;";
    private static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM `category` WHERE `id` = ?;";
    private static final String SELECT_ALL_CATEGORY = "SELECT * FROM `category`;";


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void createProduct(Product product) {
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(CREATE_PRODUCT)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getNumber());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public Product selectProductById(int id) {
        Product product = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("pName");
                int price = resultSet.getInt("price");
                int number = resultSet.getInt("number");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int cId = resultSet.getInt("cId");
                Category category = selectCategoryById(cId);

                product = new Product(name, price, number, color, description, category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
    }

    @Override
    public List<Product> selectAllProduct() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT)) {
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("pName");
                int price = resultSet.getInt("price");
                int number = resultSet.getInt("number");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int cId = resultSet.getInt("cId");
                Category category = selectCategoryById(cId);

                Product product = new Product(id, name, price, number, color, description, category);
                productList.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public boolean updateProduct(Product product) {
        boolean rowUpdated = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT)) {
            System.out.println(preparedStatement);

            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getPrice());
            preparedStatement.setInt(3, product.getNumber());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDescription());
            preparedStatement.setInt(6, product.getCategory().getId());
            preparedStatement.setInt(7, product.getId());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowUpdated;
    }

    @Override
    public boolean deleteProduct(int id) {
        boolean rowDeleted = false;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT)) {

            preparedStatement.setInt(1, id);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            printSQLException(e);
        }
        return rowDeleted;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_PRODUCT_BY_NAME)) {
            preparedStatement.setString(1, "%" + name + "%");
            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name1 = resultSet.getString("pName");
                int price = resultSet.getInt("price");
                int number = resultSet.getInt("number");
                String color = resultSet.getString("color");
                String description = resultSet.getString("description");
                int cId = resultSet.getInt("cId");
                Category category = selectCategoryById(cId);

                Product product = new Product(id, name1, price, number, color, description, category);
                productList.add(product);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return productList;
    }

    @Override
    public Category selectCategoryById(int id) {
        Category category = null;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
            preparedStatement.setInt(1, id);

            System.out.println(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("cName");
                category = new Category(id, name);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return category;
    }

    @Override
    public List<Category> selectAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORY)) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("cName");

                Category category = new Category(id, name);
                categoryList.add(category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return categoryList;
    }

    private void printSQLException(SQLException e) {
        for (Throwable throwable : e) {
            if (throwable instanceof SQLException) {
                throwable.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) throwable).getSQLState());
                System.err.println("Error Code: " + ((SQLException) throwable).getErrorCode());
                System.err.println("Message: " + throwable.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
