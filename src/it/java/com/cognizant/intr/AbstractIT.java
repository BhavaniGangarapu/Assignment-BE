package com.cognizant.intr;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;

import java.io.File;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "it")
@SpringBootTest(classes = CustomerStatementProcessorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class AbstractIT {

    @Autowired
    private final TestRestTemplate restTemplate= new TestRestTemplate();

    public Object postFile(String url, File file)  {
        LinkedMultiValueMap<String,Object> requestEntity = new LinkedMultiValueMap<>();
        requestEntity.add("file", new FileSystemResource(file));

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT,MediaType.APPLICATION_JSON_UTF8_VALUE);
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String,Object>> httpEntity = new HttpEntity(requestEntity, headers);
        return restTemplate.exchange(url, HttpMethod.POST, httpEntity, Object.class);
    }
}
