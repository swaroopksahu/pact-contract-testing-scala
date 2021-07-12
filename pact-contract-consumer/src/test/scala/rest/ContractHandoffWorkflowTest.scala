package io.github.swaroopksahu.rest

import au.com.dius.pact.consumer.MessagePactBuilder
import au.com.dius.pact.core.model.annotations.{Pact, PactDirectory}
import au.com.dius.pact.core.model.messaging.MessagePact
import io.github.swaroopksahu.util.DSLGenerator

@PactDirectory("pacts/event/fcEngine")
class ContactHandoffWorkFlowTest extends BaseConsumerContractTest {

  final val PROVIDER_ID = "fcEngineService"
  final val CONSUMER_ID = "ContactHandOffWorkflowCoreServiceConsumer"

  @Pact(provider = PROVIDER_ID, consumer = CONSUMER_ID) def pact(builder: MessagePactBuilder): MessagePact =
    builder
      .expectsToReceive("ContactHandOffWorkflow")
      .withContent(DSLGenerator.contactHandOffWorkflow)
      .toPact
}