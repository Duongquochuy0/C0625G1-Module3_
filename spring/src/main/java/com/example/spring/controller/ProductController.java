package com.example.spring.controller;

import com.example.spring.model.Product;
import com.example.spring.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping( "/products")
public class ProductController {

    private final IProductService productService;

    @Autowired
    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String listProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product,
                              RedirectAttributes redirectAttributes) {
        productService.save(product);
        redirectAttributes.addFlashAttribute("message",
                product.getId() > 0 ? "Cập nhật sản phẩm thành công!" : "Thêm sản phẩm mới thành công!");
        return "redirect:/products";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "form";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id,
                                RedirectAttributes redirectAttributes) {
        productService.deleteById(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sản phẩm thành công!");
        return "redirect:/products";
    }
    @GetMapping("/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model) {
        List<Product> products = productService.searchByName(keyword);
        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);
        return "list";
    }
}
