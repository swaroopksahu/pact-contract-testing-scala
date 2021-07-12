package io.github.swaroopksahu.rest;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import io.github.swaroopksahu.util.ConsumerClient;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static java.util.Collections.singletonMap;
import static org.junit.Assert.assertEquals;

@PactDirectory("pacts/rest")
public class RestBased {

    private static final String HTTP_PROVIDER_NAME = "my_http_provider";
    private static final String PACT_VERIFICATIONS_CONSUMER_NAME = "my-consumer";
    private static final String OTHER_PACT_VERIFICATIONS_CONSUMER_NAME = "my-other-consumer";

    @Rule
    public PactProviderRule httpProvider =
            new PactProviderRule(HTTP_PROVIDER_NAME, PactSpecVersion.V3, this);

    @Pact(provider = HTTP_PROVIDER_NAME, consumer = PACT_VERIFICATIONS_CONSUMER_NAME)
    public RequestResponsePact httpPact(PactDslWithProvider builder) {
        return builder
                .given("a good state")
                .uponReceiving("a query test interaction")
                .path("/state")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body("{\"name\": \"harry\"}")
                .toPact();
    }

    @Test
    @PactVerification(value = HTTP_PROVIDER_NAME, fragment = "httpPact")
    public void shouldTestAllHttpPacts() throws Exception {
        Assert.assertEquals(new ConsumerClient(httpProvider.getUrl()).getAsMap("/state", ""),
                singletonMap("name", "harry"));
    }

    @Pact(provider = HTTP_PROVIDER_NAME, consumer = OTHER_PACT_VERIFICATIONS_CONSUMER_NAME)
    public RequestResponsePact otherHttpPact(PactDslWithProvider builder) {
        return builder
                .given("another good state")
                .uponReceiving("another interesting interaction")
                .path("/dummy")
                .method("GET")
                .willRespondWith()
                .status(202)
                .body("{\"message\": \"In-Progress\"}")
                .toPact();
    }

    @Test
    @PactVerification(value = HTTP_PROVIDER_NAME, fragment = "otherHttpPact")
    public void shouldTestDummyHttpPact() throws Exception {
        assertEquals(new ConsumerClient(httpProvider.getUrl()).getAsMap("/dummy", ""),
                singletonMap("message", "In-Progress"));
    }
}

