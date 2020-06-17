package co.daniel.springboot.app.item.service;

import co.daniel.springboot.app.item.models.Item;
import co.daniel.springboot.app.item.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service("serviceRestTemplate")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private RestTemplate clienteRest;

    @Override
    public List<Item> findAll() {
        List<Producto> productos = Arrays.asList(clienteRest.getForObject("http://servicio-productos/listar", Producto[].class));
        return productos.stream().map(p -> new Item(p, 1)).collect(Collectors.toList());
    }

    @Override
    public Item findById(Long id, Integer cantidad) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        Producto producto = clienteRest.getForObject("http://servicio-productos/ver/{id}", Producto.class, pathVariables);
        return new Item(producto, cantidad);
    }

    @Override
    public Producto save(Producto producto) {
        HttpEntity<Producto> body = new HttpEntity<>(producto);
        ResponseEntity<Producto> responseEntity = clienteRest.exchange("http://servicio-productos/crear", HttpMethod.POST, body, Producto.class);
        return responseEntity.getBody();
    }

    @Override
    public Producto update(Producto producto, Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());

        HttpEntity<Producto> body = new HttpEntity<>(producto);
        ResponseEntity<Producto> responseEntity = clienteRest.exchange("http://servicio-productos/editar/{id}", HttpMethod.PUT, body, Producto.class, pathVariables);
        return responseEntity.getBody();
    }

    @Override
    public void delete(Long id) {
        Map<String, String> pathVariables = new HashMap<>();
        pathVariables.put("id", id.toString());
        clienteRest.delete("http://servicio-productos/eliminar/{id}", pathVariables);
    }
}
