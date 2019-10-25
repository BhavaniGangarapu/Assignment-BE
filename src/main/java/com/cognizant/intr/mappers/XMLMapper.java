package com.cognizant.intr.mappers;


import com.cognizant.intr.domain.TransactionRecord;
import com.cognizant.intr.domain.TransactionRecords;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

@Component
public class XMLMapper {

    public static List<TransactionRecord> readRecordsFromXML(InputStream inputStream) throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionRecords.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        TransactionRecords transactionRecords = (TransactionRecords) jaxbUnmarshaller.unmarshal(new StringReader(IOUtils.toString(inputStream, UTF_8.name())));
        return transactionRecords.getRecords().stream().collect(Collectors.toList());
    }
}
