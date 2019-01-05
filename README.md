# FoodSearch
[![Build Status](https://dev.azure.com/meilcli/FoodSearch/_apis/build/status/FoodSearch-CI?branchName=master)](https://dev.azure.com/meilcli/FoodSearch/_build/latest?definitionId=6?branchName=master) [![Maintainability](https://api.codeclimate.com/v1/badges/a7bde630bb380f6b71de/maintainability)](https://codeclimate.com/github/MeilCli/FoodSearch/maintainability)

![](features/base/src/main/res/mipmap-xhdpi/icon_launcher_round.png) > ごはん検索 / FoodSearch

作成中

## Google Play Track

|Track|Azure Pipeline|Google Play Url|
|:--:|:--:|:--:|
|Production|N/A|N/A|
|Beta|N/A|N/A|
|Alpha|N/A|N/A|
|Internal|[![Build Status](https://dev.azure.com/meilcli/FoodSearch/_apis/build/status/FoodSearch-Track-Internal?branchName=master)](https://dev.azure.com/meilcli/FoodSearch/_build/latest?definitionId=8?branchName=master)|Secret|

## Build
1. Make directory `{ROOT}/Secure`
2. Create empty file `{ROOT}/Secure/google-play-service-account-key.json`
3. Create empty file `{ROOT}/Secure/google-play-upload-key.jks`
4. Create file `{ROOT}/Secure/signing.gradle`
content:
```gradle
signingConfigs {
    release { }
}
```
5. Create file `{ROOT}/Secure/version.gradle`
content:
```gradle
ext {
    instantVersionCode = 1
    installedVersionCode = 2
    versionNamePostfix = 1
    googlePlayTrack = "internal"
}
```
6. Create file `{ROOT}/Secure/key.gradle`
content:
```gradle
ext {
    gnaviApiKey = ""
}
```
7. Build.

## ApiKey
Key configuration locate `{ROOT}/Secure/key.gradle`.

- Get `gnaviApiKey` in [https://api.gnavi.co.jp/api/](https://api.gnavi.co.jp/api/)

## Set Up CI/CD (for Developer as a other application)
First, clone repository.  

important files:
- `.codeclimate.yml`
- `azure-pipelines.yml`
- `azure-pipelines-track-**.yml`
- `build.gradle`
- `configuration.gradle`
- `app/build.gradle`

Next, upload your repository to Github.
### Code Climate
1. Sign up Code Climate Quality using Github account
2. Add Repository as a Open Source
3. Go to repository settings
4. Select Github
5. Enable to `Pull request comments`, `Inline issue comments` and `Pull request stats updates`

### Google Play
policy:
- use Google Play App Signin 
- enable Google Play Publisher API

1. Make credential file in Android Studio, save to `{ROOT}/Secure/google-play-upload-key.jks`
2. Make signin setting file `{ROOT}/Secure/signing.gradle`  
content:
```gradle
signingConfigs  {
    release {
        storeFile file("../Secure/google-play-upload-key.jks")
        storePassword "{YOUR_PASSWORD}"
        keyAlias "{YOUR_ALIAS}"
        keyPassword "{YOUR_PASSWORD}"
    }
}
```
3. Make first Android App Bundle file, example command: `gradlew bundleRelease`
4. Open Google Play Console
5. Make new application
6. Set all information of required settings
7. Add first bundle file to target track, example: `internal`
8. Go to setting
9. Select API access
10. Enable link `Google Play Android Developer`
11. Create service account
12. Download created service account key as a json, save to `{ROOT}/Secure/google-play-service-account-key.json`
13. Add permissions to created service account in Google Play Console settings

### Azure Pipeline CI
1. Set up Azure Pipeline from Github Marketplace, make Azure Pipeline project
2. Set up first pipeline from `azure-pipelines.yml`

### Azure Pipeline CD (for each track)
if added secure files, skip to 6
1. Open pipeline's `Library`
2. Select Secure files
3. Add `{ROOT}/Secure/google-play-service-account-key.json`
4. Add `{ROOT}/Secure/google-play-upload-key.jks`
5. Add `{ROOT}/Secure/signing.gradle`
6. Add `{ROOT}/Secure/key.gradle`
7. Create pipeline from `azure-pipelines-track-***.yml`
8. Open created pipeline, click `Edit`
9. Click `Edit in the visual designer`
10. Select Triggers, select Pull request validation
11. Enable `Override YAML Pull Request trigger from here`
12. Check `Disable pull request validation`, this option mean is Security for guard from pull request attack
13. Save setting

when not download secure file, see that: https://stackoverflow.com/questions/53401927/vsts-anybody-managed-to-use-secure-file-in-azure-pipelines-yml/53488887

## License
This application is published by [MIT License](LICENSE.txt)

### Using Libraries
Build:

- [Google Play Publisher](https://github.com/Triple-T/gradle-play-publisher), published by [MIT License](https://github.com/Triple-T/gradle-play-publisher/blob/master/LICENSE)

Application:

- Kotlin Standard Library, published by [Apache License v2](https://github.com/JetBrains/kotlin/blob/master/license/LICENSE.txt)
- kotlinx.coroutines, published by [Apache License v2](https://github.com/Kotlin/kotlinx.coroutines/blob/master/LICENSE.txt)
- AndroidX, published by [Apache License v2](https://github.com/aosp-mirror/platform_frameworks_support/blob/androidx-master-dev/LICENSE.txt)
- com.google.android.instantapps, published by [Android Software Development Kit License Agreement](https://developer.android.com/studio/terms)
- [Moshi](https://github.com/square/moshi), published by [Apache License v2](https://github.com/square/moshi/blob/master/LICENSE.txt)
- [OkHttp](https://github.com/square/okhttp), published by [Apache License v2](https://github.com/square/okhttp/blob/master/LICENSE.txt)

Test:

- junit4, published by [Eclipse Public License v1](https://github.com/junit-team/junit4/blob/master/LICENSE-junit.txt)
- [Robolectric](https://github.com/robolectric/robolectric), published by [MIT License](https://github.com/robolectric/robolectric/blob/master/LICENSE)