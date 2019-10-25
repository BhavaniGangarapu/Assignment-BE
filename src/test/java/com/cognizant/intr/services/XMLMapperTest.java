package com.cognizant.intr.services;

import com.cognizant.intr.domain.TransactionRecord;
import com.cognizant.intr.mappers.XMLMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class XMLMapperTest {

    @InjectMocks
    XMLMapper xmlMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void process() throws IOException, JAXBException {
        final InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/records.xml"));
        List<TransactionRecord> transactionRecords = XMLMapper.readRecordsFromXML(inputStream);

        assertThat(transactionRecords).isNotNull();
        assertThat(transactionRecords.size()).isNotNull();
        assertThat(transactionRecords.size()).isEqualTo(10);
        for (TransactionRecord transactionRecord : transactionRecords) {
            assertThat(transactionRecord).isNotNull();
        }
    }
}