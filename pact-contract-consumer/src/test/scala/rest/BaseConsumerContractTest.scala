package io.github.swaroopksahu.rest

import au.com.dius.pact.consumer.junit.{MessagePactProviderRule, PactVerification}
import org.junit.{Assert, Before, Rule, Test}
import org.scalatest.junit.JUnitSuite

abstract class BaseConsumerContractTest extends JUnitSuite {
  @Before def startUp(): Unit = {}

  @Rule def provider(): MessagePactProviderRule = new MessagePactProviderRule(this)

  @Test
  @PactVerification()
  def test(): Unit = Assert.assertTrue(true)
}