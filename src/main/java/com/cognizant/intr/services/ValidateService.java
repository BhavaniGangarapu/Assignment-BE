package com.cognizant.intr.services;

import com.cognizant.intr.domain.TransactionRecord;
import com.cognizant.intr.domain.ValidatedRecord;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class ValidateService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidateService.class);

    public List<ValidatedRecord> validateTransactionRecords(List<TransactionRecord> transactionRecords) {
        LOGGER.info("total number of transaction records {} ", transactionRecords.size());
        List<TransactionRecord> validatedTransactions = validateReference(transactionRecords);
        validatedTransactions.addAll(validateEndBalances(transactionRecords));
        List<ValidatedRecord> validatedRecordList = Lists.newArrayList();
        validatedTransactions.forEach(transactionRecord -> validatedRecordList.add(
                        ValidatedRecord
                                .builder()
                                .transactionReference(transactionRecord.getReference())
                                .description(transactionRecord.getDescription())
                                .build()));
        LOGGER.info("Size of the transaction records with Error {} ", validatedRecordList.size());
        return validatedRecordList;
    }

    private List<TransactionRecord> validateEndBalances(List<TransactionRecord> transactionRecords) {
        return transactionRecords.stream()
                .filter(record -> (record.getStartBalance().add(record.getMutation())).compareTo(record.getEndBalance()) != 0)
                .collect(Collectors.toList());
    }

    private List<TransactionRecord> validateReference(List<TransactionRecord> transactionRecords) {
        return transactionRecords.stream()
                .collect(groupingBy(TransactionRecord::getReference))
                .values()
                .stream()
                .filter(duplicates -> duplicates.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
