package com.mastery.examplestreamapi.service;

import com.mastery.examplestreamapi.domain.Category;
import com.mastery.examplestreamapi.domain.Product;
import com.mastery.examplestreamapi.repository.BookingRepository;
import com.mastery.examplestreamapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final BookingRepository bookingRepository;

    @Override
    public List<Product> findAllProductsBelongsCategoryBookWithPriceMore50() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals(Category.BOOK))
                .filter(product -> product.getPrice() > 50)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllProductOrderedBetweenDates(LocalDate start, LocalDate finish) {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getOrderDate().compareTo(start) >= 0)
                .filter(booking -> booking.getOrderDate().compareTo(finish) <= 0)
                .flatMap(booking -> booking.getProducts().stream())
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findCheapestProductInCategoryMedicine() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals(Category.MEDICINE))
                .min(Comparator.comparing(Product::getPrice));
    }

    @Override
    public List<Product> findAllProductOrderedInDate(LocalDate date) {
        return bookingRepository.findAll()
                .stream()
                .filter(booking -> booking.getOrderDate().isEqual(date))
                .flatMap(booking -> booking.getProducts().stream())
                .collect(Collectors.toList());
    }

    @Override
    public DoubleSummaryStatistics obtainCollectionOfStaticAllProductsCategoryFood() {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory().equals(Category.FOOD))
                .mapToDouble(Product::getPrice)
                .summaryStatistics();

    }

    @Override
    public Map<String, List<String>> getMapWithListProductsNameByCategory() {
        return productRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        product -> product.getCategory().name(),
                        Collectors.mapping(Product::getName, Collectors.toList())
                ));
    }

    @Override
    public Map<String, Optional<Product>> getMostExpensiveProductByCategory() {
        return productRepository.findAll()
                .stream()
                .collect(
                        Collectors.groupingBy(
                        product -> product.getCategory().name(),
                                Collectors.maxBy(Comparator.comparing(Product::getPrice)))
                );
    }
}
