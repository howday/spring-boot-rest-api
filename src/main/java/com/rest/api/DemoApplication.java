package com.rest.api;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rest.api.domain.Gene;
import com.rest.api.domain.Variant;
import com.rest.api.repository.GeneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(GeneRepository repository) {

        return (args) -> {


            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject("http://oncokb.org/api/v1/genes/673/variants", String.class);

            try {
                JsonParser jsonParser = new JsonFactory().createParser(result);


                ObjectMapper mapper = new ObjectMapper();

                JsonNode node = mapper.readTree(result);
                Gene gene = new Gene();
                gene.setGeneAliases("geneAlias");
                boolean isGeneAdded = false;
                List<Variant> variants = new ArrayList<>();
                for (JsonNode geneVariant : node) {

                    if (!isGeneAdded) {
                        String hugoSymbol = geneVariant.path("gene").path("hugoSymbol").asText();
                        String geneAliases = geneVariant.path("gene").path("geneAliases").asText(); //need to change to list
                        Long entrezGeneId = geneVariant.path("gene").path("entrezGeneId").asLong();
                        Boolean oncogene = geneVariant.path("gene").path("oncogene").asBoolean();
                        Boolean tsg = geneVariant.path("gene").path("tsg").asBoolean();

                        gene.setHugoSymbol(hugoSymbol);
                        gene.setGeneAliases(geneAliases);
                        gene.setEntrezGeneId(entrezGeneId);
                        gene.setOncogene(oncogene);
                        gene.setTsg(tsg);
                        isGeneAdded = true;
                    }


                    String alteration = geneVariant.path("alteration").asText();
                    String consequenceTerm = geneVariant.path("consequence").path("term").asText();
                    Boolean isGenerallyTruncating = geneVariant.path("consequence").path("isGenerallyTruncating").asBoolean();

                    Variant variant = new Variant();
                    variant.setAlteration(alteration);
                    variant.setConsequenceTerm(consequenceTerm);
                    variant.setGenerallyTruncating(isGenerallyTruncating);

                    variants.add(variant);

                }

                gene.setVariants(variants);
                repository.save(gene);

            } catch (IOException e) {
                e.printStackTrace();
            }

//            Gene gene = new Gene();
//            gene.setGeneAliases("Main Test");
//            repository.save(gene);
        };

    }
}
