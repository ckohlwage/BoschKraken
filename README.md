
## Kraken overview

This is a simple app loading the latest tradeable assets from https://docs.kraken.com/rest/. There is an overview 
of tradeable assets with basic info and a detail screen for each entry with some additional info. 
The screens are refreshed every 60 seconds if visible. The last update time is displayed on each screen. 
Basic error handling and display of load state are implemented. 

## Architecture

MVVM is the base architecture for the complete app. Navigation is handled by the jetpack navigation
component.

All data is loaded via coroutine.flow. The screens supports swipe to
refresh. The reload timer is restarted if manually refreshed. Data is displayed with dataBinding.

The detail screen shows more info about the selected asset and uses dataBinding to set data.

## Known issues

- I need a designer to supply taste
- error handling does not distinguish between error types
- the detail screen data is displayed without much context. 
