
## Latest Movies

This is a simple app loading the latest movies from https://www.themoviedb.org/. It supports
infinite scrolling and a detail screen for each movie.

## Usage


The Api Key is supplied in the build.gradle default config. Please replace this with your own key.

## Architecture


MVVM is the base architecture for the complete app. Navigation is handled by the jetpack navigation
component.

The movie list is loaded via the paging library 3 and coroutine.flow. Basic error handling and
Loading and error footers are displayed and offer a retry option. The screen supports swipe to
refresh. Data is displayed with dataBinding.

The detail screen shows more info about the selected movie and uses dataBinding to set data and hide
unused views.

Picture loading on either screen is handled by Glide.

The basic Unit test use mockito.

## Known issues


- Unit testing is basic
- I need a designer to supply taste
- error handling does not distinguish between error types
- optional feature is not included