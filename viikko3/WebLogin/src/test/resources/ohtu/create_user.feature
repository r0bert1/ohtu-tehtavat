Feature: A new user account can be created if a proper unused username and password are given

    Scenario: creation is successful with valid username and password
        Given new user is selected
        When  a valid username "liisa" and password "salainen1" and matching password confirmation are entered
        Then  a new user is created   

    Scenario: creation fails with too short username and valid password
        Given new user is selected
        When  a too short username "as" and valid password "salainen1" and matching password confirmation are entered
        Then user is not created and error "username should have at least 3 characters" is reported   

    Scenario: creation fails with correct username and too short password
        Given new user is selected
        When  a valid username "asd" and too short password "asd123" and matching password confirmation are entered
        Then user is not created and error "password should have at least 8 characters" is reported   

    Scenario: creation fails when password and password confirmation do not match
        Given new user is selected
        When  a valid username "asd" and password "asd123456" and nonmatching password confirmation "asd1234567" are entered
        Then user is not created and error "password and password confirmation do not match" is reported
