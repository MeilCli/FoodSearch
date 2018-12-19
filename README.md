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
1. make directory `{ROOT}/Secure`
2. create empty file `{ROOT}/Secure/google-play-service-account-key.json`
3. create empty file `{ROOT}/Secure/google-play-upload-key.jks`
4. create file `{ROOT}/Secure/signing.gradle`  
content:
```gradle
signingConfigs {
    release { }
}
```
5. create file `{ROOT}/Secure/version.gradle`  
content:
```gradle
ext {
    instantVersionCode = 1
    installedVersionCode = 2
    versionNamePostfix = 1
    googlePlayTrack = "internal"
}

```
6. Build.

## Set Up CI/CD (for Developer as a other application)
First, clone repository.  

important files:
- `.codeclimate.yml`
- `azure-pipelines.yml`
- `azure-pipelines-track-**.yml`
- `build.gradle`
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
10. enable link `Google Play Android Developer`
11. create service account
12. download created service account key as a json, save to `{ROOT}/Secure/google-play-service-account-key.json`

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
6. Create pipeline from `azure-pipelines-track-***.yml`
7. Open created pipeline, click `Edit`
8. Click `Edit in the visual designer`
9. Select Triggers, select Pull request validation
10. Enable `Override YAML Pull Request trigger from here`
11. Check `Disable pull request validation`, this option mean is Security for guard from pull request attack
12. Save setting
