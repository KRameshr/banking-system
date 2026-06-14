
package com.banking;

import io.specmatic.test.SpecmaticJUnitSupport;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = {
        "server.port=8080"
    }
)
@ActiveProfiles("test")
public class BankingContractTest extends SpecmaticJUnitSupport {

    @BeforeAll
    public static void setup() {
        // Use system properties to guide the Specmatic runner
        System.setProperty("host", "localhost");
        System.setProperty("port", "8080");
        System.setProperty("endpointsAPI", "http://localhost:8080/api-docs");
        System.setProperty("testBaseURL", "http://localhost:8080");
    }

    @Override
    public void contractTest() throws Throwable {
        super.contractTest();
    }
}