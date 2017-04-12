package com.rest.api.controller;

import com.rest.api.domain.Gene;
import com.rest.api.domain.Variant;
import com.rest.api.service.GeneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by suresh on 4/11/17.
 */
@RestController
public class GeneController {

    @Autowired
    GeneService geneService;

    @RequestMapping(value = "/genes/get/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Resource<Gene> getGenes(@PathVariable(value = "page") Integer page) {
        Gene album = geneService.get();
        return getAlbumResource(album);
    }

    @RequestMapping(value = "genes/variant/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Variant getVariant(@PathVariable(value = "id") Long id) {

        Gene album = geneService.get();
        Variant v = album.getVariants().stream().filter(variant -> variant.getId() == id).findAny().orElse(null);
        System.out.println(v);
        return v;
    }

    private Resource<Gene> getAlbumResource(Gene album) {

        Resource<Gene> resource = new Resource<Gene>(album);
        /**
         * Link to individual  variants
         */
        for (Variant v : album.getVariants()) {
            resource.add(linkTo(methodOn(GeneController.class).getVariant(v.getId())).withSelfRel());
        }

        return resource;

    }
}
