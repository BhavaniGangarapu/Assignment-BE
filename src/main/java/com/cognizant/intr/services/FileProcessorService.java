package com.cognizant.intr.services;

import com.cognizant.intr.domain.TransactionRecord;
import com.cognizant.intr.domain.ValidatedRecord;
import com.cognizant.intr.endpoints.StatementProcessorController;
import com.cognizant.intr.exceptions.CustomerStatementProcessorException;
import com.cognizant.intr.mappers.CSVMapper;
import com.cognizant.intr.mappers.XMLMapper;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.cognizant.intr.mappers.CSVMapper.readRecordsFromCSV;
import static com.cognizant.intr.mappers.XMLMapper.readRecordsFromXML;

@Service
public class FileProcessorService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileProcessorService.class);

    @Autowired
    private ValidateService validateService;

    public List<ValidatedRecord> processInputFile(String originalName, InputStream fileInputStream) {
        LOGGER.info("Received file {}", originalName);
        String extension = FilenameUtils.getExtension(originalName);
        List<TransactionRecord> transactionRecords;
        try {
            switch (extension) {
                case "csv":
                    transactionRecords = readRecordsFromCSV(fileInputStream);
                    break;
                case "xml":
                    transactionRecords =  readRecordsFromXML(fileInputStream);
                    break;
                default:
                    throw new IllegalStateException("UnSupported file: " + extension);
            }
            return validateService.validateTransactionRecords(transactionRecords);
        } catch (JAXBException | IOException e) {
            LOGGER.info("Error occured during processing the inputfile {}", e.getLocalizedMessage());
            throw new CustomerStatementProcessorException("Error while processing file", e.getMessage());
        }
    }

}
