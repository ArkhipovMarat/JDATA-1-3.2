package com.example.demo.repository;

import com.example.demo.dto.Product;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class DataBaseRepository {
    private static final String GET_PRODUCT_SCRIPT_NAME = "getProductScript.sql";
    private static final String QUERY_FIELD_NAME = "name";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public DataBaseRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public static String readSqlQuery(String scriptName) {
        try (InputStream is = new ClassPathResource(scriptName).getInputStream()) {
            return String.join("\n", readLines(is));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<String> readLines(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().collect(Collectors.toList());
    }

    public String getProductName(String name) {
        String sqlQuery = readSqlQuery(GET_PRODUCT_SCRIPT_NAME);

        List<Product> productList = namedParameterJdbcTemplate.queryForList(sqlQuery,
                Map.of(QUERY_FIELD_NAME, name), Product.class);

        StringBuilder resultString= new StringBuilder();

        for (Product product : productList) {
            resultString.append(product.getProductName()).append(",");
        }

        return resultString.toString();
    }
}
