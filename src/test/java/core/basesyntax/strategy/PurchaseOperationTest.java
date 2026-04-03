package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PurchaseOperationTest {
    private Storage storage;
    private OperationHandler handler;
    private FruitTransaction fruitTransaction;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        handler = new PurchaseOperation(storage);
        fruitTransaction = new FruitTransaction();
    }
    @Test
    void handle_minusQuantity_ok() {
        storage.getFruits().put("banana", 20);
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(5);
        handler.handle(fruitTransaction);
        assertEquals(15, storage.getFruits().get("banana"));
    }

    @Test
    void handle_minusQuantity_notOk() {
        storage.getFruits().put("banana", 3);
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(5);
        assertThrows(RuntimeException.class, () -> {
            handler.handle(fruitTransaction);
        });
        assertEquals(3, storage.getFruits().get("banana"));
    }
}
