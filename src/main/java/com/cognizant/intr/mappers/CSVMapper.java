package com.cognizant.intr.mappers;

import com.cognizant.intr.domain.TransactionRecord;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class CSVMapper {

    public static List<TransactionRecord> readRecordsFromCSV(InputStream inputStream) throws IOException {
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper
                .typedSchemaFor(TransactionRecord.class)
                .withHeader()
                .withColumnReordering(true);
        MappingIterator<TransactionRecord> dataIterator = csvMapper
                .readerFor(TransactionRecord.class)
                .with(schema)
                .readValues(inputStream);
        return dataIterator.readAll();
    }
}
