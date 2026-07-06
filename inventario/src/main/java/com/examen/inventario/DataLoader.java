package com.examen.inventario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.examen.inventario.model.InventarioModel;
import com.examen.inventario.repository.InventarioRepo;

@Configuration
public class DataLoader {


    @Bean
    CommandLineRunner init(InventarioRepo repo){
        return args -> {
            if(repo.count()==0){

                repo.save(new InventarioModel(null, 1L, "Pepperoni", 500, 50));
                repo.save(new InventarioModel(null, 2L, "Queso Mozzarella", 300, 30));
                repo.save(new InventarioModel(null, 3L, "Harina", 40, 10));
                repo.save(new InventarioModel(null, 4L, "Salsa de Tomate Especial", 100, 20));
                repo.save(new InventarioModel(null, 5L, "Pizza Pepperoni", 15, 5));
                repo.save(new InventarioModel(null,6L,"Pizza Tres Quesos", 35,5));
            }
        };
    }
}
