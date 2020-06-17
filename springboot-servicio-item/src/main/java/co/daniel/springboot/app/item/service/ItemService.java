package co.daniel.springboot.app.item.service;

import co.daniel.springboot.app.item.models.Item;
import co.daniel.springboot.app.item.models.Producto;

import java.util.List;

public interface ItemService {
    List<Item> findAll();

    Item findById(Long id, Integer cantidad);

    Producto save(Producto producto);

    Producto update(Producto producto, Long id);

    void delete(Long id);

}
