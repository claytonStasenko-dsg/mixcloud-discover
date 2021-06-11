# Mixcloud Discover

### Discover New Shows on Mixcloud!

Search for most popular shows by tag on the Mixcloud Api. The top 5 Results are displayed in an infinite, looping carousel. Cards within the carousel are clickable, and serve a deep link that will either open the show in the Mixcloud app (if you have Mixcloud installed on your device) or in your web browser

Search for most recent mixes by user. The 10 most recent shows are displayed in an infinite looping carousel. Cards within the carousel are clickable and serve a deep link that will either open the show in the Mixcloud app (if you have Mixcloud installed on your device) or in your web browser.
* Mixes by user search still needs to be fully built out.

**You can build the project by:**
* Cloning and running in Android studio
* Cloning the repo, running the command `./gradlew clean assembleDebug` in your terminal (`gradle clean assembleDebug` on windows), and installing to device or emulator with ADB

## Features

### Demo
* A quick demo home page to show you the slider in action! Shows the 5 most popular shows for the tag "nts" aka [NTS Radio](https://www.mixcloud.com/NTSRadio/)

![image](https://user-images.githubusercontent.com/58704239/121705633-d4e19b80-caa2-11eb-80dd-22c2b9c44aa7.png)

### Discover
* The Discover Page lets you put in a tag to search, and it returns the 5 most popular shows per the given tag. Please see the example below for searching "rinsefm"
* **Please be advised that full retrofit error handling hasn't been built out, so currently if you search for a tag that doesn't exist in the system the app will crash. This doesn't happen very often**

![image](https://user-images.githubusercontent.com/58704239/121705749-f773b480-caa2-11eb-87a4-1711a8861772.png)


### Users
* The Users page lets you search for a Mixcloud User, and returns their 10 most recent shows. Full search functionality is still to come. This page currently is hardcoded to return the 10 most recent shows from [The Lot Radio](https://www.mixcloud.com/thelotradio/)

![image](https://user-images.githubusercontent.com/58704239/121705871-16724680-caa3-11eb-8782-1d96aef7b1ed.png)

## Features to be Added (Listed in Order of Priority)
* Add Github Actions pipeline
* Full error handling (Especially Retrofit error handling)
* Enlarge focus area for EditText on DiscoverFragment
* User search functionality
* Caching with Room
* Switch tab icons in Bottom Navigation View (currently using defaults)
* More fully fleshed out styling. (ie. fix margins on fragments. adding styles.xml, flesh out theming)
* More fully fleshed out unit testing (ie. add instrumented tests. add unit tests that cover more than the happy path)
* Add functionality for user to pass a limit param to the mixcloud api which would define the size of the image carousel
* Dependency Injection cleanup (add scoping, break up modules if needed)
* Code cleanup (make better use of strings.xml, implement custom rules for ktlint)

**All of the above bullet points will soon be added as cards under the Projects tab**



