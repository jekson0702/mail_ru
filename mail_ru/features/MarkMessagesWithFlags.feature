
Feature: Mark messages with flags

  Scenario: MarkMessagesWithFlags
    Given I am on InboxPage
    When I mark three messages with flags
    Then I see three marked messages