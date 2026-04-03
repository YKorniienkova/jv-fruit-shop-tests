package core.basesyntax.strategy;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class SupplyOperationTest {
    private Storage storage;
    private OperationHandler handler;
    private FruitTransaction fruitTransaction;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        handler = new SupplyOperation(storage);
        fruitTransaction = new FruitTransaction();
    }

    @Test
    void handle_plusQuantity_ok() {
        storage.getFruits().put("banana", 20);
        fruitTransaction.setFruit("banana");
        fruitTransaction.setQuantity(30);
        handler.handle(fruitTransaction);
        assertEquals(50, storage.getFruits().get("banana"));
    }
}
