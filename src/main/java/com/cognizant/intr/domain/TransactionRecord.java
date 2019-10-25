package com.cognizant.intr.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "record")
public class TransactionRecord {
    @JsonProperty("Reference")
    private String reference;

    @JsonProperty("AccountNumber")
    private String accountNumber;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Start Balance")
    private BigDecimal startBalance;

    @JsonProperty("Mutation")
    private BigDecimal mutation;

    @JsonProperty("End Balance")
    private BigDecimal endBalance;

    @XmlAttribute
    public String getReference(){
        return reference;
    }
    @Override
    public String toString(){
        return "TransactionRecord [reference=" + reference + "" +
                ", AccountNumber=" + accountNumber + "" +
                ", Description= "  + description +
                ", Start Balance= "  + startBalance +
                ", Mutation= "  + mutation +
                ", End Balance= "  + endBalance +
                "]";
    }
}
