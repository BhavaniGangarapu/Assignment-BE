package com.cognizant.intr.endpoints;


import com.cognizant.intr.domain.ValidatedRecord;
import com.cognizant.intr.exceptions.CustomerStatementProcessorException;
import com.cognizant.intr.services.FileProcessorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/statement")
public class StatementProcessorController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatementProcessorController.class);
    @Autowired
    private FileProcessorService fileProcessorService;

    @PostMapping(value = "/process")
    public ResponseEntity<List<ValidatedRecord>> processTransactions(@RequestParam("file") MultipartFile file) {
        try{
            List<ValidatedRecord> response =  fileProcessorService.processInputFile(file.getOriginalFilename(), file.getInputStream());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (CustomerStatementProcessorException | IOException e) {
            LOGGER.debug("Exception Occured while processing Input File", e.fillInStackTrace());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(Collections.emptyList());
        }
    }
}