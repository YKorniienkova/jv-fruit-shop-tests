package core.basesyntax.service.impl;

import core.basesyntax.db.Storage;
import core.basesyntax.service.ReportGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ReportGeneratorImplTest {
    private ReportGenerator reportGenerator;
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
        reportGenerator = new ReportGeneratorImpl(storage);
    }

    @Test
    void getReport_validStorage_ok() {
        storage.getFruits().put("banana", 100);
        storage.getFruits().put("apple", 50);
        String result = reportGenerator.getReport();
        assertTrue(result.contains("fruit,quantity"));
        assertTrue(result.contains("banana,100"));
        assertTrue(result.contains("apple,50"));
    }
}