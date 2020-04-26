package com.inti.formation.product.util;



import com.inti.formation.product.data.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFileInfoProduct {

    private final static String PATH = "C:/Users/guill/Documents/FormationJavaData/BIG_DATA_PROJET/producerKafka/src/main/resources/";
    private List<String> COUNTRY_NAMES = Collections.EMPTY_LIST;
    private List<String> SIMPLE_WORD_LIST = Collections.EMPTY_LIST;

    public ReadFileInfoProduct() throws IOException {
        COUNTRY_NAMES = getPaysNames();
        SIMPLE_WORD_LIST = getSimpleWords();
    }

    public Product getRandomProduct() throws IOException {
        Product product = new Product();
        product.setIdproduit(getIdProduit());
        product.setOrigine(getOrigine());
        product.setLibelle(getLibelle());
        product.setDescription(getDescription());
        return product;
    }

    private String getOrigine() throws IOException {
        List<String> lst = getPaysNames();
        int rand = (int)(Math.random()*lst.size());
        return lst.get(rand);
    }

    private String getLibelle() throws IOException {
        int rand;
        String res = "";
        List<String> lst = getSimpleWords();

        for(int i = 0; i<10; i++){
            rand = (int)(Math.random()*lst.size());
            res += lst.get(rand) + " ";
        }
        return res;
    }

    private String getDescription() throws IOException {
        int rand;
        String res = "";
        List<String> lst = getSimpleWords();

        for(int i = 0; i<25; i++){
            rand = (int)(Math.random()*lst.size());
            res += lst.get(rand) + " ";
        }
        return res;
    }

    private String getIdProduit() {
        return UUID.randomUUID().toString();
    }

    private List<String> getSimpleWords() throws IOException {
        return Files.readAllLines(Paths.get(PATH + "liste_francais.txt")).stream()
                .map(word -> word.trim())
                .collect(Collectors.toList());
    }

    private List<String> getPaysNames() throws IOException {
        return Files.readAllLines(Paths.get(PATH + "pays.csv")).stream()
                .map(line -> line.split(",")[4]
                        .replaceAll("\"", " ").trim())
                .collect(Collectors.toList());
    }
}
