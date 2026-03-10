---
name: android-app-builder
description: Android StudioベースのAndroidアプリ開発を一貫実行するスキル。新規プロジェクトの基本ディレクトリ構成の作成・確認、要件に沿ったアプリ実装、Makefile作成による`make run`ビルド運用を行う。ユーザーがAndroidアプリを0から作る、既存プロジェクトを標準構成に整える、CLIで再現可能なビルド手順を追加したい場合に使う。
---

# Android App Builder

以下の順序で進める。

## 1) 前提を確認する

- 作業ディレクトリを確認する。
- `gradlew` が存在しない場合は、Android Studioでプロジェクトを初期化するか、既存テンプレートから最低限のGradle構成を作る。
- 既存ファイルがある場合は削除せず、差分で整える。

## 2) Android Studio標準の基本構成を作成または検証する

最低限、次の構成を満たすようにする。

```text
<project-root>/
  app/
    build.gradle(.kts)
    src/
      main/
        AndroidManifest.xml
        java/ or kotlin/
        res/
          layout/
          values/
  build.gradle(.kts)
  settings.gradle(.kts)
  gradle.properties
  gradlew
  gradlew.bat
```

- Kotlinプロジェクトを優先する。
- UIは `res/layout` または Compose のどちらかで実装方針を固定する。
- パッケージ名、`minSdk`、`targetSdk`、`applicationId` を要件に合わせる。

## 3) 目的アプリを実装する

- 先にアプリの形を定義する: 画面一覧、主要機能、状態遷移。
- 次に `MainActivity` と必要なクラスを作成する。
- UI、イベント処理、状態管理、データ層の順で実装する。
- 必要に応じて `AndroidManifest.xml` に Activity、権限、Intent Filter を追加する。
- 実装時はビルド可能な最小単位で段階的に進める。

## 4) Makefileを追加する

プロジェクトルートに `Makefile` を作成し、少なくとも次のルールを持たせる。

```makefile
.PHONY: run clean

run:
	./gradlew assembleDebug

clean:
	./gradlew clean
```

- `run` は常にビルド可能状態を検証する入口として扱う。
- ユーザーが要求した場合は `install` ルールを追加する。

```makefile
install:
	./gradlew installDebug
```

## 5) 検証する

- `make run` を実行してビルド成功を確認する。
- 失敗時は次の順で切り分ける。
  - JDK/Android SDK のパス
  - Gradle Wrapper の有無と実行権限
  - `minSdk` / `targetSdk` / 依存関係の整合性
  - Manifest と実装クラスの参照不整合

## 6) 作業結果を報告する

- 追加・変更ファイルを列挙する。
- `make run` の実行結果を要約する。
- 未解決事項があれば、再現手順付きで短く示す。
