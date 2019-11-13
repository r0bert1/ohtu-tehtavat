Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given command new is selected
        When  username "kayttaja" and password "salainen" are entered
        Then  system will respond with "new user registered"
    
    Scenario: creation fails with already taken username and valid password
        Given user "eero" with password "salainen1" is created
        And   command new is selected
        When  username "eero" and password "salainen1" are entered
        Then  system will respond with "new user not registered"

    Scenario: creation fails with too short username and valid password
        Given command new is selected
        When  username "asd2" and password "asd12345" are entered
        Then  system will respond with "new user not registered"
