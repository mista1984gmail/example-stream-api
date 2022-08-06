package com.mastery.examplestreamapi.bootstrup;

import com.github.javafaker.Faker;
import com.mastery.examplestreamapi.domain.*;
import com.mastery.examplestreamapi.repository.BookingRepository;
import com.mastery.examplestreamapi.repository.ClientRepository;
import com.mastery.examplestreamapi.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader {

    @Bean
    public CommandLineRunner loadDataClient(ClientRepository clientRepository, ProductRepository productRepository, BookingRepository bookingRepository) {
        return (args) -> {
            Faker faker = new Faker();
            long indexForProduct = 1;
            for (long i = 0; i <= 30; i++) {
                Set<Product>products = new HashSet<>();
                long indexToIncreaseProduct =0;
                for (long j = indexForProduct; j <= faker.number().numberBetween(indexForProduct, indexForProduct+10); j++) {
                    indexToIncreaseProduct = j;
                        Product product = productRepository.save(new Product(j, faker.lorem().word(),
                                Category.valueOf(Category.values()[faker.number().numberBetween(0, 5)].name()),
                                faker.number().randomDouble(2, 1, 100)));
                        products.add(product);
                }
                indexForProduct += indexToIncreaseProduct;

                Client client = clientRepository.save(new Client(i, faker.name().firstName() + " " + faker.name().lastName()));

                bookingRepository.save(new Booking(i, LocalDate.of(2022, Month.JANUARY, 1).plusDays(faker.number().numberBetween(1, 365)), Status.valueOf(Status.values()[faker.number().numberBetween(0, 3)].name()),
                        client, products));
            }


        };
    }
}


