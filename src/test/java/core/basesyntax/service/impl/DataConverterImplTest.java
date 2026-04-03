package core.basesyntax.service.impl;

import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.DataConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataConverterImplTest {
    private DataConverter converter;
    @BeforeEach
    void setUp() {
        converter = new DataConverterImpl();
    }

    @Test
    void convertToTransaction_validInput_ok() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "b,banana,20",
                "s,apple,10"
        );
        List<FruitTransaction> result = converter.convertToTransaction(input);
        assertEquals(2, result.size());
        FruitTransaction firstTransaction = result.get(0);
        assertEquals(FruitTransaction.Operation.BALANCE, firstTransaction.getOperation());
        assertEquals("banana", firstTransaction.getFruit());
        assertEquals(20, firstTransaction.getQuantity());
        FruitTransaction secondTransaction = result.get(1);
        assertEquals(FruitTransaction.Operation.SUPPLY, secondTransaction.getOperation());
        assertEquals("apple", secondTransaction.getFruit());
        assertEquals(10, secondTransaction.getQuantity());
    }

    @Test
    void convertToTransaction_unValidInput_notOk() {
        List<String> input = List.of(
                "type,fruit,quantity",
                "x,banana,20"
        );
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertToTransaction(input);
        });
    }
}
