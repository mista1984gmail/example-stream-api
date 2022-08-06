package com.mastery.examplestreamapi.web.controller;

import com.mastery.examplestreamapi.domain.Product;
import com.mastery.examplestreamapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/findAllProductsBelongsCategoryBookWithPriceMore50")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProductsBelongsCategoryBookWithPriceMore50() {
        return productService.findAllProductsBelongsCategoryBookWithPriceMore50();
    }

    @GetMapping("/findAllProductOrderedBetweenDates")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProductOrderedBetweenDates(@RequestParam("localDateStart")
                                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDateStart,
                                                                   @RequestParam("localDateFinish")
                                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDateFinish) {
        return productService.findAllProductOrderedBetweenDates(localDateStart, localDateFinish);
    }

    @GetMapping("/findCheapestProductInCategoryMedicine")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Product> findCheapestProductInCategoryMedicine() {
        return productService.findCheapestProductInCategoryMedicine();
    }

    @GetMapping("/findAllProductOrderedInDate")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> findAllProductOrderedInDate(@RequestParam("localDate")
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate){
        return productService.findAllProductOrderedInDate(localDate);
    }

    @GetMapping("/obtainCollectionOfStaticAllProductsCategoryFood")
    @ResponseStatus(HttpStatus.OK)
    public DoubleSummaryStatistics obtainCollectionOfStaticAllProductsCategoryFood(){
        return productService.obtainCollectionOfStaticAllProductsCategoryFood();
    }

    @GetMapping("/getMapWithListProductsNameByCategory")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, List<String>> getMapWithListProductsNameByCategory(){
        return productService.getMapWithListProductsNameByCategory();
    }

    @GetMapping("/getMostExpensiveProductByCategory")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Optional<Product>> getMostExpensiveProductByCategory(){
        return productService.getMostExpensiveProductByCategory();
    }





}
