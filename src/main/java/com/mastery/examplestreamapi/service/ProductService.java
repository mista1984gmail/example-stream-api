package com.mastery.examplestreamapi.service;

import com.mastery.examplestreamapi.domain.Product;

import java.time.LocalDate;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ProductService {
    List<Product> findAllProductsBelongsCategoryBookWithPriceMore50();
    List<Product> findAllProductOrderedBetweenDates(LocalDate start, LocalDate finish);
    Optional<Product>findCheapestProductInCategoryMedicine();
    List<Product> findAllProductOrderedInDate(LocalDate date);
    DoubleSummaryStatistics obtainCollectionOfStaticAllProductsCategoryFood();
    Map<String, List<String>> getMapWithListProductsNameByCategory();
    Map<String, Optional<Product>> getMostExpensiveProductByCategory();

}
