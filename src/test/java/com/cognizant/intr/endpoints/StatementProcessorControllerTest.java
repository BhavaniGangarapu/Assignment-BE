package com.cognizant.intr.endpoints;

import com.cognizant.intr.domain.ValidatedRecord;
import com.cognizant.intr.services.FileProcessorService;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class StatementProcessorControllerTest {

    @InjectMocks
    private StatementProcessorController statementProcessorController;

    @Mock
    private FileProcessorService fileProcessorService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void processTransactions() throws IOException {
        File file = new File("src/test/resources/records.csv");
        FileInputStream input = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile("records", file.getName(), "text/plain", IOUtils.toByteArray(input));

        ResponseEntity<List<ValidatedRecord>> response = statementProcessorController.processTransactions(multipartFile);

        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();

    }

}