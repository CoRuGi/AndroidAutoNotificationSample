# Android Auto Notifications Sample

An Android app that shows you how to get a notification from a message app to
the screen of an Android Auto system. The system will read the notification
aloud and you are then able to respond by speech.

If you are using the Android Auto API simulator however the speech function will
not be working and a canned reply will be send to your app.

## Installation

1. Install Android Studio
2. New -> Project From Version Control -> GitHub
3. Use the above URL as the Repository URL
4. Install the Android Auto API Simulators trough the SDK Manager
5. Install the simulator onto your device or emulator
    1. In android studio go to the Terminal
    2. Browse to the folder sdk/extras/google/simulators
    3. Execute "adb install messaging-simulator.apk"

## License

Copyright 2016 Coen Rundberg

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements. See the NOTICE file distributed with this work for
additional information regarding copyright ownership. The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License. You may obtain a copy of
the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
License for the specific language governing permissions and limitations under
the License.