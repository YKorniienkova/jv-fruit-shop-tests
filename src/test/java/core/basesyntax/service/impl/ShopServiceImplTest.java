package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.OperationStrategy;
import core.basesyntax.service.ShopService;
import core.basesyntax.strategy.BalanceOperation;
import core.basesyntax.strategy.OperationHandler;
import core.basesyntax.strategy.PurchaseOperation;
import core.basesyntax.strategy.ReturnOperation;
import core.basesyntax.strategy.SupplyOperation;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static core.basesyntax.model.FruitTransaction.Operation.BALANCE;
import static core.basesyntax.model.FruitTransaction.Operation.PURCHASE;
import static core.basesyntax.model.FruitTransaction.Operation.RETURN;
import static core.basesyntax.model.FruitTransaction.Operation.SUPPLY;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ShopServiceImplTest {
    private ShopService shopService;
    private OperationStrategy operationStrategy;
    private Map<FruitTransaction.Operation, OperationHandler> operationHandlers;
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        operationHandlers = new HashMap<>();
        operationStrategy = new OperationStrategyImpl(operationHandlers);
        shopService = new ShopServiceImpl(operationStrategy);
        operationHandlers.put(BALANCE, new BalanceOperation(storage));
        operationHandlers.put(SUPPLY, new SupplyOperation(storage));
        operationHandlers.put(PURCHASE, new PurchaseOperation(storage));
        operationHandlers.put(RETURN, new ReturnOperation(storage));
    }

    @Test
    void process_validTransactions_ok() {
        FruitTransaction balanceTransaction = new FruitTransaction();
        balanceTransaction.setOperation(BALANCE);
        balanceTransaction.setFruit("banana");
        balanceTransaction.setQuantity(20);
        FruitTransaction returnTransaction = new FruitTransaction();
        returnTransaction.setOperation(RETURN);
        returnTransaction.setFruit("banana");
        returnTransaction.setQuantity(10);
        FruitTransaction purchaseTransaction = new FruitTransaction();
        purchaseTransaction.setOperation(PURCHASE);
        purchaseTransaction.setFruit("banana");
        purchaseTransaction.setQuantity(5);
        FruitTransaction supplyTransaction = new FruitTransaction();
        supplyTransaction.setOperation(SUPPLY);
        supplyTransaction.setFruit("banana");
        supplyTransaction.setQuantity(25);

        List<FruitTransaction> fruitTransactionList = List.of(balanceTransaction, returnTransaction, purchaseTransaction, supplyTransaction);

        shopService.process(fruitTransactionList);
        assertEquals(50, storage.getFruits().get("banana"));

    }

}
