package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.PurchaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OperationStrategyImplTest {
    private Storage storage;
    private Map<FruitTransaction.Operation, OperationHandler> operationHandlers = new HashMap<>();
    private OperationStrategy operationStrategy = new OperationStrategyImpl(operationHandlers);

    @Test
    void getHandler_validOperation_ok() {

    }

    @Test
    void handle_negativeQuantity_notOK() {
        OperationHandler handler = new PurchaseOperation(storage);

        FruitTransaction transaction = new FruitTransaction();
        transaction.setFruit("banana");
        transaction.setQuantity(-5);

        assertThrows(RuntimeException.class, () -> {
            handler.handle(transaction);
        });
    }
}