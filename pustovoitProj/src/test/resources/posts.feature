@PostTest @FullRegression
Feature: Post Feature

  Background:
    Given User opens 'Home' page

    @BeforeDeletingAllPostsForDefaultUser
    @AfterDeletingAllPostsForDefaultUser
    @R011
  Scenario Outline: R011 Check number of posts
    Given Create <numberOfPosts> new posts via API for 'default' user and 'default' password
    When User clicks on 'MyProfile' button on 'Home' page
    Then User was redirected to 'MyProfile' page
    And User sees <numberOfPosts> posts in Posts list on 'MyProfile' page

    Examples:
      | numberOfPosts |
      | 2             |