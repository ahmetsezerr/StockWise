package org.ahmetsezer.stockwise.config;

import org.ahmetsezer.stockwise.dto.response.ProductResponse;
import org.ahmetsezer.stockwise.entity.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT)
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true);


        modelMapper.typeMap(Product.class, ProductResponse.class)
                .addMappings(mapper ->
                        mapper.map(
                                src -> src.getCategory().getName(),
                                ProductResponse::setCategoryName
                        )
                );

        return modelMapper;
    }
}
