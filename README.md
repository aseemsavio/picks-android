# picks-android
This project is an Android Mobile Application that retrieves news articles from over _70 news sources_. It uses the popular _NewsAPI_ service as its source. A registered user gets around _700 news articles_ every day and can save their favorites in their respective profiles. 
## Getting Started
This project is backed by _Firebase_. So, post importing the project to _Android Studio_, you are expected to create an account from their website and replace the files such as `google-services.json` with the ones you generate. 
_Firebase Authentication_ used in this project requires us to provide the _SHA1 fingerprint_ of the certificate used to sign the app. You might also need to take care of this before building.
You also need to acquire a new API key from NewsAPI whose pricing is free for open source projects. 
## Built with
* [Android Studio](https://developer.android.com/studio/) - IDE used
* [Firebase](https://firebase.google.com/) - Backend 
* [NewsAPI](https://newsapi.org/) - JSON API for Live News
### A word of caution
This project might give you a hard time building if the API service has upgraded/altered their infrastructure. When this project was built, NewsAPI supported 70 News Sources.

Do contribute by forking and sending Pull Requests.
