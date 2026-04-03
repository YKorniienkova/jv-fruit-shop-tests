package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BalanceOperationTest {
    private Storage storage;
    private OperationHandler handler;
    private FruitTransaction fruitTransaction;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        handler = new BalanceOperation(storage);
        fruitTransaction = new FruitTransaction();
    }

    @Test
    void handle_setQuantity_ok() {
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(100);
        handler.handle(fruitTransaction);
        assertEquals(100, storage.getFruits().get("banana"));
    }
}
