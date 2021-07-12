package io.github.swaroopksahu.rest;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit.PactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import io.github.swaroopksahu.util.ConsumerClient;
import org.apache.http.entity.ContentType;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import static java.util.Collections.singletonMap;

@PactDirectory("pacts/rest")
public class RestBasedPlain {

    private static final String HTTP_PROVIDER_NAME = "my_plain_http_provider";
    private static final String PACT_VERIFICATIONS_CONSUMER_NAME = "my-plain-consumer";

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
                .body("{\"name\": \"harry\"}" +
                                "SEG_PROFILE\n" +
                                "\"__ROW_TYPE\",__INTERVAL,agentName__s,_0\n" +
                                "3,0,null,0.0\n" +
                                "0,1620111600000,,0.0\n" +
                                "0,1620802800000,,0.0\n" +
                                "0,1620975600000,,0.0",
                        ContentType.TEXT_PLAIN)
                .toPact();
    }

    @Test
    @PactVerification(value = HTTP_PROVIDER_NAME, fragment = "httpPact")
    public void shouldTestAllHttpPacts() throws Exception {
        Assert.assertEquals(new ConsumerClient(httpProvider.getUrl()).getAsMap("/state", ""),
                singletonMap("name", "harry"));
    }
}

