package controller;

import model.Category;
import model.Product;
import service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class Controller extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    public void init() {
        productService = new ProductService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            case "search":
                searchByName(req, resp);
                break;
            default:
                listProduct(req, resp);
                break;
        }
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Category> categoryList = productService.selectAllCategory();
            req.setAttribute("categoryList", categoryList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            Product product = productService.selectProductById(id);
            req.setAttribute("product", product);

            List<Category> categoryList = productService.selectAllCategory();
            req.setAttribute("categoryList", categoryList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            productService.deleteProduct(id);

            List<Product> productList = productService.selectAllProduct();
            req.setAttribute("productList", productList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            List<Product> productList = productService.selectAllProduct();
            req.setAttribute("productList", productList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.setCharacterEncoding("utf-8");

        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int number = Integer.parseInt(req.getParameter("number"));
            String color = req.getParameter("color");
            String description = req.getParameter("description");
            int cId = Integer.parseInt(req.getParameter("cId"));
            Category category = productService.selectCategoryById(cId);

            Product product = new Product(name, price, number, color, description, category);
            productService.createProduct(product);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            int price = Integer.parseInt(req.getParameter("price"));
            int number = Integer.parseInt(req.getParameter("number"));
            String color = req.getParameter("color");
            String description = req.getParameter("description");
            int cId = Integer.parseInt(req.getParameter("cId"));
            Category category = productService.selectCategoryById(cId);

            Product product = new Product(id, name, price, number, color, description, category);
            productService.updateProduct(product);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/edit.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) {
        try {
            String name = req.getParameter("search");
            List<Product> productList = productService.searchByName(name);
            req.setAttribute("productList", productList);

            RequestDispatcher dispatcher = req.getRequestDispatcher("product/searchResult.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
