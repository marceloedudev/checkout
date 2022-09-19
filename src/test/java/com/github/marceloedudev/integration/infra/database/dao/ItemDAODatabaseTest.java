package com.github.marceloedudev.integration.infra.database.dao;

import com.github.marceloedudev.domain.entity.Item;
import com.github.marceloedudev.infra.database.dao.ItemDAODatabase;
import com.github.marceloedudev.infra.database.repository.ItemRepositoryDatabase;
import com.github.marceloedudev.unit.domain.entity.Item.ItemObjectMother;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.transaction.Transactional;

@QuarkusTest
public class ItemDAODatabaseTest {

    @Inject
    ItemDAODatabase itemDAODatabase;

    @Inject
    ItemRepositoryDatabase itemRepositoryDatabase;

    Item item;

    Long idItemCreated;

    @BeforeEach
    @Transactional
    public void setup() {
        item = ItemObjectMother.valid();
        Item itemCreated = itemRepositoryDatabase.create(item);
        idItemCreated = itemCreated.getIdItem();
    }

    @Test
    @DisplayName("should be get item by id")
    public void findItemById() {
        Item foundItem = itemDAODatabase.findById(idItemCreated).get();
        Assertions.assertEquals(idItemCreated, foundItem.getIdItem());
        Assertions.assertEquals(item.getDescription(), foundItem.getDescription());
        Assertions.assertEquals(item.getPrice(), foundItem.getPrice());
        Assertions.assertEquals(item.getWidth(), foundItem.getWidth());
        Assertions.assertEquals(item.getHeight(), foundItem.getHeight());
        Assertions.assertEquals(item.getLength(), foundItem.getLength());
        Assertions.assertEquals(item.getWeight(), foundItem.getWeight());
        Assertions.assertEquals(item.getVolume(), foundItem.getVolume());
        Assertions.assertEquals(item.getDensity(), foundItem.getDensity());
    }
}
