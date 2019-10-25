package com.cognizant.intr;

import com.cognizant.intr.domain.ValidatedRecord;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StatementProcessorIT extends AbstractIT {

    @Test
    public void test() {
        File file = new File("src/it/resources/records.csv");
        ResponseEntity response = (ResponseEntity) super.postFile("/statement/process", file);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        ArrayList<ValidatedRecord> list = (ArrayList<ValidatedRecord>) response.getBody();
        assertThat(list).isNotNull();
    }

}
