package co.daniel.springboot.app.item.service;

import co.daniel.springboot.app.item.models.Item;

import java.util.List;

public interface ItemService {
    public List<Item> findAll();

    public Item findById(Long id, Integer cantidad);
}
