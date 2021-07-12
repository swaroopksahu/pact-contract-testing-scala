package io.github.swaroopksahu.rest;

import au.com.dius.pact.consumer.junit.ConsumerPactTest;
import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.PactTestExecutionContext;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@PactDirectory("pacts/rest")
public class StatusServiceConsumerPactTest extends ConsumerPactTest {

    @Override
    protected RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap();
        headers.put("testreqheader", "testreqheadervalue");
        String body = "{\"anchorId\":\"2\",\"dateBegin\":[1619852400000],\"dateEnd\":[1621062000000],\"timezone\":\"America/Los_Angeles\",\"numberOfRecords\":1000000,\"lastNRecords\":false,\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"teamSystemId__s\",\"value\":\"AXhtQPltLFzF9Q3k41bd\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"AXPiuyX_QsskgWmGhHYF\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"siteSystemId__s\",\"value\":\"AXhtP4D0KG6QduY0TQMa\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"AXJaa8TbIwL7fuV2v_05\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"queueSystemId__s\",\"value\":\"AXJ5JVZ4BdoJvrEovWeV\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPit39z9ic5vAs7Lrib\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPmiEzCQsskgWmGhUqs\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]}],\"activityType\":\"INTERACTION\",\"aggregateQueryProperties\":{\"computeInterval\":1,\"computeIntervalUnit\":\"DAYS\",\"lookbackCount\":0,\"frequency\":0,\"movingWindow\":false,\"cumulative\":false,\"rowSegmentSet\":[{\"columnName\":\"agentName__s\",\"name\":\"agentName__s\",\"rangeBased\":false,\"regexBased\":false}],\"columnSegmentSet\":[],\"queryType\":\"TEMPORAL\",\"requestType\":\"SEG_PROFILE\",\"intervalAxis\":\"ROW\",\"computeSummaries\":true},\"aggregations\":[{\"id\":0,\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]}],\"aggregationType\":\"SUM\",\"computeColumnName\":\"talkCount__i\"}]}";

        return builder
                .uponReceiving("status")
                .path("/status")
                .method("POST")
                .headers(headers)
                .body(body)
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(response, ContentType.TEXT_PLAIN).toPact();
    }

    @Override
    protected String providerName() {
        return "provider";
    }

    @Override
    protected String consumerName() {
        return "consumer";
    }

    @Override
    protected void runTest(MockServer mockServer, PactTestExecutionContext context) throws IOException {
        StatusServiceClient statusServiceClient = new StatusServiceClient(mockServer.getUrl());

        String currentQuestionnairePage = statusServiceClient.getCurrentQuestionnairePage(null);

        assertEquals(currentQuestionnairePage, response);
    }

    String response = "{\"query\":{\"dateBegin\":[1619852400000],\"dateEnd\":[1621062000000],\"timezone\":\"America/Los_Angeles\",\"numberOfRecords\":1000000,\"lastNRecords\":false,\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"teamSystemId__s\",\"value\":\"AXhtQPltLFzF9Q3k41bd\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"AXPiuyX_QsskgWmGhHYF\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"siteSystemId__s\",\"value\":\"AXhtP4D0KG6QduY0TQMa\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"AXJaa8TbIwL7fuV2v_05\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"queueSystemId__s\",\"value\":\"AXJ5JVZ4BdoJvrEovWeV\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPit39z9ic5vAs7Lrib\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPmiEzCQsskgWmGhUqs\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]}],\"activityType\":\"INTERACTION\",\"aggregateQueryProperties\":{\"computeInterval\":1,\"computeIntervalUnit\":\"DAYS\",\"lookbackCount\":0,\"frequency\":0,\"cumulative\":false,\"rowSegmentSet\":[{\"columnName\":\"agentName__s\",\"name\":\"agentName__s\",\"rangeBased\":false,\"regexBased\":false}],\"columnSegmentSet\":[],\"queryType\":\"TEMPORAL\",\"requestType\":\"SEG_PROFILE\",\"intervalAxis\":\"ROW\",\"computeSummaries\":true},\"aggregations\":[{\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]}],\"aggregationType\":\"SUM\",\"computeColumnName\":\"talkCount__i\",\"id\":0}],\"anchorId\":\"2\"},\"lastAccessTimestamp\":0,\"numberOfRecordsFound\":11626,\"numberOfRecordsInDataset\":11626,\"isComplete\":true}\n" +
            "SEG_PROFILE\n" +
            "\"__ROW_TYPE\",__INTERVAL,agentName__s,_0\n" +
            "3,0,null,0.0\n" +
            "0,1620111600000,,0.0\n" +
            "0,1620802800000,,0.0\n" +
            "0,1620975600000,,0.0\n";

    private class StatusServiceClient {
        private String baseUrl;

        public StatusServiceClient(String baseUrl) {
            this.baseUrl = baseUrl;
        }
        String body = "{\"anchorId\":\"2\",\"dateBegin\":[1619852400000],\"dateEnd\":[1621062000000],\"timezone\":\"America/Los_Angeles\",\"numberOfRecords\":1000000,\"lastNRecords\":false,\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"teamSystemId__s\",\"value\":\"AXhtQPltLFzF9Q3k41bd\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"AXPiuyX_QsskgWmGhHYF\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"teamSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"siteSystemId__s\",\"value\":\"AXhtP4D0KG6QduY0TQMa\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"AXJaa8TbIwL7fuV2v_05\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"siteSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]},{\"operator\":\"OR\",\"valueFilters\":[{\"columnName\":\"queueSystemId__s\",\"value\":\"AXJ5JVZ4BdoJvrEovWeV\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPit39z9ic5vAs7Lrib\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"AXPmiEzCQsskgWmGhUqs\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false},{\"columnName\":\"queueSystemId__s\",\"value\":\"NA\",\"operator\":\"EQUAL\",\"includeIfColumnNotPresent\":false}]}],\"activityType\":\"INTERACTION\",\"aggregateQueryProperties\":{\"computeInterval\":1,\"computeIntervalUnit\":\"DAYS\",\"lookbackCount\":0,\"frequency\":0,\"movingWindow\":false,\"cumulative\":false,\"rowSegmentSet\":[{\"columnName\":\"agentName__s\",\"name\":\"agentName__s\",\"rangeBased\":false,\"regexBased\":false}],\"columnSegmentSet\":[],\"queryType\":\"TEMPORAL\",\"requestType\":\"SEG_PROFILE\",\"intervalAxis\":\"ROW\",\"computeSummaries\":true},\"aggregations\":[{\"id\":0,\"filterGroups\":[{\"operator\":\"AND\",\"valueFilters\":[]}],\"aggregationType\":\"SUM\",\"computeColumnName\":\"talkCount__i\"}]}";

        public String getCurrentQuestionnairePage(Object page) throws IOException {
            Request
                    .Post(baseUrl + "/status")
                    .addHeader("testreqheader", "testreqheadervalue")
                    .bodyString(body, ContentType.APPLICATION_JSON)
                    .execute();
/*            Request.Get(baseUrl + "/status")
                .addHeader("testreqheader", "testreqheadervalue")
                .execute();*/
            return response;
        }
    }
}
