package io.github.swaroopksahu.util

import au.com.dius.pact.consumer.dsl.PactDslJsonBody

object DSLGenerator {

  def contactHandOffWorkflow: PactDslJsonBody = {
    val dsl = new PactDslJsonBody
    dsl
      .stringValue("eventType", "RoutingMessage")
      .stringValue("type", "ContactHandOffWorkflow")
      .uuid("orgId")
      .uuid("interactionId")
      .uuid("trackingId")
      .stringType("handOffTo", "flow2")
      .booleanType("isEntryPoint", false)
      .`object`("flowContext")
      .closeObject
    dsl
  }
}