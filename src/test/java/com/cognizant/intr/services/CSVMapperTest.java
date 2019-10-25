package com.cognizant.intr.services;


import com.cognizant.intr.domain.TransactionRecord;
import com.cognizant.intr.mappers.CSVMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class CSVMapperTest {

    @InjectMocks
    CSVMapper csvMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testProcessDataFromCSV() throws IOException {
        FileInputStream fis = new FileInputStream("src/test/resources/records.csv");

        List<TransactionRecord> transactionRecords = CSVMapper.readRecordsFromCSV(fis);

        assertThat(transactionRecords).isNotNull();
        assertThat(transactionRecords.size()).isNotNull();
        assertThat(transactionRecords.size()).isEqualTo(10);
        for (TransactionRecord transactionRecord : transactionRecords) {
            assertThat(transactionRecord).isNotNull();
        }
    }
}