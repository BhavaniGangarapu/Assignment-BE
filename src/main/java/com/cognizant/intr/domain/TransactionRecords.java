package com.cognizant.intr.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "records")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionRecords {
    @XmlElement(name = "record")
    private List<TransactionRecord> records = null;

    public List<TransactionRecord> getRecords() {
        return records;
    }
}
