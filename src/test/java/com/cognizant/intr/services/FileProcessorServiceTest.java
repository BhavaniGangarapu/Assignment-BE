package com.cognizant.intr.services;

import com.cognizant.intr.domain.ValidatedRecord;
import com.cognizant.intr.exceptions.CustomerStatementProcessorException;
import com.cognizant.intr.mappers.CSVMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class FileProcessorServiceTest {

    @InjectMocks
    private FileProcessorService fileProcessorService;

    @Spy
    private CSVMapper csvMapper;

    @Spy
    private ValidateService validateService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void processInputFileCSV() throws IOException {
        testProcessFile("src/test/resources/records.csv", "records.csv", 4);

    }
    @Test
    public void processInputFileXML() throws IOException {
        testProcessFile("src/test/resources/records.xml", "records.xml", 2);
    }
    @Test(expected = IllegalStateException.class)
    public void processInputFileNone() throws IOException {
        testProcessFile("src/test/resources/records.xml", "records.", 2);
    }
    private void testProcessFile(String filePath, String fileName, int sizeOfValidRecords) throws IOException {
        final InputStream inputStream = Files.newInputStream(Paths.get(filePath));
        List<ValidatedRecord> transactionRecords = fileProcessorService.processInputFile(fileName, inputStream);

        assertThat(transactionRecords).isNotNull();
        assertThat(transactionRecords.size()).isEqualTo(sizeOfValidRecords);
        for (ValidatedRecord transactionRecord : transactionRecords) {
            assertThat(transactionRecord).isNotNull();
            assertThat(transactionRecord.getTransactionReference()).isNotEmpty();
        }
    }
}