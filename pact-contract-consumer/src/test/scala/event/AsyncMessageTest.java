package io.github.swaroopksahu.event;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.Matchers;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit.MessagePactProviderRule;
import au.com.dius.pact.consumer.junit.PactVerification;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.core.model.messaging.MessagePact;
import org.junit.Rule;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@PactDirectory("pacts/event")
public class AsyncMessageTest {
    @Rule
    public MessagePactProviderRule mockProvider = new MessagePactProviderRule("test_provider", this);

    @Pact(provider = "test_provider", consumer = "test_consumer_v3")
    public MessagePact createPact(MessagePactBuilder builder) {
        PactDslJsonBody body = new PactDslJsonBody();
        body.stringValue("testParam1", "value1");
        body.stringValue("testParam2", "value2");

        Map<String, Object> metadata = new HashMap<>();
        metadata.put("Content-Type", "application/json");
        metadata.put("destination", Matchers.regexp("\\w+\\d+", "X001"));

        return builder.given("SomeProviderState")
                .expectsToReceive("a test message")
                .withMetadata(metadata)
                .withContent(body)
                .toPact();
    }

    @Pact(provider = "test_provider", consumer = "test_consumer_v3")
    public MessagePact createPact2(MessagePactBuilder builder) {
        PactDslJsonBody body = new PactDslJsonBody();
        body.stringValue("testParam1", "value3");
        body.stringValue("testParam2", "value4");

        Map<String, String> metadata = new HashMap<String, String>();
        metadata.put("Content-Type", "application/json");

        return builder.given("SomeProviderState2")
                .expectsToReceive("a test message")
                .withMetadata(metadata)
                .withContent(body)
                .toPact();
    }

    @Test
    @PactVerification(value = "test_provider", fragment = "createPact")
    public void test() throws Exception {
        byte[] currentMessage = mockProvider.getMessage();
        assertThat(new String(currentMessage), is("{\"testParam1\":\"value1\",\"testParam2\":\"value2\"}"));
    }

    @Test
    @PactVerification(value = "test_provider", fragment = "createPact2")
    public void test2() {
        byte[] currentMessage = mockProvider.getMessage();
        assertThat(new String(currentMessage), is("{\"testParam1\":\"value3\",\"testParam2\":\"value4\"}"));
    }
}