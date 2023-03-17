<h1>톡뽀끼</h1>

<p>
  <a href="https://kotlinlang.org"><img alt="Kotlin Version" src="https://img.shields.io/badge/Kotlin-1.6.10-blueviolet.svg?style=flat"/></a>
  <a href="https://developer.android.com/studio/releases/gradle-plugin"><img alt="AGP" src="https://img.shields.io/badge/AGP-7.4.0-blue?style=flat"/></a>
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
</p>

톡뽀끼와 함께라면, 쉬워지는 스몰토크
[PlayStore](https://play.google.com/store/apps/details?id=com.hammer.talkbbokki&pli=1)
![Talkbbokki_Grapic](https://user-images.githubusercontent.com/7722921/223384675-fb5ed84c-cc2e-4e8c-8bf4-8a66e57b5547.png)


## 기술 스택 및 오픈소스 라이브러리

### Android

- Minimum SDK level 26
- Target SDK level 33
- [Kotlin](https://kotlinlang.org/) 기반 [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) 비동기 처리
- JetPack
  - [Compose](https://developer.android.com/jetpack/compose/documentation) - 100% Jetpack Compose로 UI 구현
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
  - [Room](https://developer.android.com/training/data-storage/room)
  - [Navigation](https://developer.android.com/guide/navigation)
  - [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
- [Hilt](https://dagger.dev/hilt/)
- [Coil](https://coil-kt.github.io/coil/)
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit)

### API Data & Management

- [API](https://github.com/Nexters/talkbbokki-server)
- [Firebase](https://firebase.google.com/)

## Features


> ### 대화주제 카드뽑기
><div>
><img src="https://user-images.githubusercontent.com/7722921/223750955-bab6e5e4-5246-4051-b249-eb6c8709548a.gif" width="300px"/> 
></div>


> ### 북마크
><div>
><img src="https://user-images.githubusercontent.com/7722921/223753262-b4f8e590-59ed-4359-a17f-73400be9ef59.gif" width="300px"/> 
></div>


> ### 공유하기
><div>
><img src="https://user-images.githubusercontent.com/7722921/223754095-b9bafbce-79c1-4602-be2f-01cb8b13f33a.gif" width="300px"/> 
></div>


## Architecture

MVVM architecture and the Repository pattern.

## License

```xml
Designed and developed by 2022 Junseop Lim

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
