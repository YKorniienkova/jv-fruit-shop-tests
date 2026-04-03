package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OperationStrategyImplTest {
    private Storage storage;
    private Map<FruitTransaction.Operation, OperationHandler> operationHandlers;
    private OperationStrategy operationStrategy;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        operationHandlers = new HashMap<>();
        operationStrategy = new OperationStrategyImpl(operationHandlers);
    }

    @Test
    void getHandler_validOperation_ok() {
        BalanceOperation handler = new BalanceOperation(storage);
        operationHandlers.put(FruitTransaction.Operation.BALANCE, handler);
        assertSame(handler, operationStrategy.getHandler(FruitTransaction.Operation.BALANCE));
    }

    @Test
    void getHandler_nullHandler() {
        assertNull(operationStrategy.getHandler(FruitTransaction.Operation.BALANCE));
    }

//    @Test
//    void handle_negativeQuantity_notOK() {
//        OperationHandler handler = new PurchaseOperation(storage);
//
//        FruitTransaction transaction = new FruitTransaction();
//        transaction.setFruit("banana");
//        transaction.setQuantity(-5);
//
//        assertThrows(RuntimeException.class, () -> {
//            handler.handle(transaction);
//        });
//    }
//
//    @Test
//    void handle_minusQuantity_notOk() {
//        OperationHandler handler = new SupplyOperation(storage);
//
//        FruitTransaction transaction = new FruitTransaction();
//
//    }
}
