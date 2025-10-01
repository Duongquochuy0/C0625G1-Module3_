package com.example.quan_ly_san_pham.repository;

import com.example.quan_ly_san_pham.enity.Product;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProductRepository implements IProductRepository{
    private static final List<Product> products = new ArrayList<>();
    static {
        products.add(new Product(1, "Laptop", 1500, "Gaming laptop", "Asus"));
        products.add(new Product(2, "Phone",  800, "Flagship phone", "Samsung"));
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(products);
    }

    @Override
    public boolean save(Product product) {
        for (Product p : products) {
            if (p.getId() == product.getId()) {
                return false; // id đã tồn tại
            }
        }
        products.add(product);
        return true;
    }

    @Override
    public Product findById(int id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    @Override
    public boolean update(int id, Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean remove(int id) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        String lower = name.toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(lower)) {
                result.add(p);
            }
        }
        return result;
    }

}
