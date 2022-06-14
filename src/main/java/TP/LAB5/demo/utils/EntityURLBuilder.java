package TP.LAB5.demo.utils;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

public class EntityURLBuilder {

    public static URI buildURL(final String entity, final String id){

        //Siguiendo el formato rest, luego de un post te devuelve la url para poder acceder al elemento posteado
        return ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/{entity}/{id}")
                .buildAndExpand(entity,id)
                .toUri();

    }

}
