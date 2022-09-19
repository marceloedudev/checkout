package com.github.marceloedudev.infra.database.repository;

import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.infra.database.base.ItemPanache;
import com.github.marceloedudev.models.panache.ItemModel;
import com.github.marceloedudev.domain.repository.ItemRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class ItemRepositoryDatabase implements ItemRepository {

    private ItemPanache itemPanache;

    @Inject
    public ItemRepositoryDatabase(ItemPanache itemPanache) {
        this.itemPanache = itemPanache;
    }

    @Override
    public Item create(Item item) {
        ItemModel itemModel = new ItemModel();
        itemModel.setDescription(item.getDescription());
        itemModel.setPrice(item.getPrice());
        itemModel.setWidth(item.getWidth());
        itemModel.setHeight(item.getHeight());
        itemModel.setLength(item.getLength());
        itemModel.setWeight(item.getWeight());
        itemModel.setVolume(item.getVolume());
        itemModel.setDensity(item.getDensity());
        itemPanache.persist(itemModel);
        item.setIdItem(itemModel.getId());
        return item;
    }
}
