@messageToSpamFeature
Feature: Test move message to spam

  Scenario: MessageToSpamTest
    Given I am on InboxPage
    When I choose first message
    When I click 'to spam'
    Then I see 'Moved to spam alert'

