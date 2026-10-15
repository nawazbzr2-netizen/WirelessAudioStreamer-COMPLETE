# Build Guide - Wireless Audio Streamer

## Requirements
- Android Studio Hedgehog or newer (or JDK 17 + Android SDK)
- Gradle 8.9+
- Min SDK 26, Target SDK 34

## Local Build
```bash
cd WirelessAudioStreamer-source
./gradlew assembleRelease
```
APK will be at: `app/build/outputs/apk/release/app-release-unsigned.apk`

## GitHub Actions
Push to main or run workflow_dispatch. Artifact `WirelessAudioStreamer-release` contains the APK.
