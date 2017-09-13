# HelloFresh Dev Team - Mobile Android Developer Test

The goal of this test is to assert (to some degree) your coding skills.

Feel free to add at any point any particular technique or algorithm that you think might enrich the overall quality of the end result. You're allowed and encouraged to use third party libraries, as long as you put them together yourself without relying on a framework to do it for you. An effective developer knows what to build and what to reuse, but also how his tools work. Be prepared to answer some questions about those packages.

If you can complete the test in 7 days it would be ideal. However, we'd like you to take your time and do it to the best of your availability so don't rush it :+1:

_Note: While we love open source here at HelloFresh, please do not create a public repo with your test in! This challenge is only shared with people interviewing, and for obvious reasons we'd like it to remain this way._

## Instructions

1. Create a new branch.
2. Write your code, in various commits so we can see your development timeline. We won't be looking at the time each task took you. We're much more interested in your train of thought!
3. When finished, create a pull request.
4. Let us know when we may start reviewing your code.

## Exercise

You will have to build an app that displays a list of recipes. Each recipe from this list should be clickable. When clicked, the recipe should open a detail page with all relevant information. 

You are free to add any cool features (other than the ones mentioned below) as long as they are relevant to the content.


#### First screen: List of recipes

* The list of recipes should loaded from the [recipes.json](recipes.json) file. You can either store the JSON file in the project and read it in runtime, or make an HTTP request to get the raw file from github.
* The image of each recipe should also be displayed in the list.

#### Second screen: Detail page of a recipe

* A recipe can be favorited or unfavorited.
* A recipe can also be rated or unrated. Rating should be from 1 to 5. The rating can be represented as a rating bar.
* You are recommended to save the favoriting status and rating of each recipe either by using SharedPreferences or some kind of offline storage.

#### Third screen: Log in

* A user should be able to enter a valid email and password to proceed.
* The email and password can be hardcoded in the app as there is no server/API for this test.
* You can either have this screen as the start-up screen in the app, or have an option from the list of recipes screen to open this screen.

 

## Requirements

* Your app MUST be developed in Java.
* Your app MUST have API 16 as minSdkVersion.
* You MUST implement layouts that adapt to most phones _dimensions_ and _orientation_.
* You MUST name all labels, variable names, and identifiers of any kind, in English.
* You MUST document your code.
* You CANNOT use app development frameworks (like cordova or others). But again, feel free to use any third party libs that might save you time.
* You SHOULD write tests. Feel free to use Java or Kotlin for your tests.

Good luck, and have fun!
