package com.github.marceloedudev.infra.database.dao;

import com.github.marceloedudev.domain.dao.ItemDAO;
import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.infra.database.base.ItemPanache;
import com.github.marceloedudev.models.panache.ItemModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class ItemDAODatabase implements ItemDAO {

    private ItemPanache itemPanache;

    @Inject
    public ItemDAODatabase(ItemPanache itemPanache) {
        this.itemPanache = itemPanache;
    }

    @Override
    public Optional<Item> findById(Long id) {
        ItemModel itemModel = itemPanache.findById(id);
        if (itemModel == null) {
            return Optional.empty();
        }
        Item item = new Item(
                itemModel.getId(),
                itemModel.getDescription(),
                itemModel.getPrice(),
                itemModel.getWidth(),
                itemModel.getHeight(),
                itemModel.getLength(),
                itemModel.getWeight(),
                itemModel.getVolume(),
                itemModel.getDensity()
        );
        return Optional.of(item);
    }
}
