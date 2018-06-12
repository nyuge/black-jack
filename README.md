Coding "Black Jack" for graduation examination of programming-primers
==============================

This is my works of the development with [Gradle](https://gradle.org/), that is the build-system on the Java environment.
The material for my studies in here came from [the site posted by @hirossyi73 ](https://qiita.com/hirossyi73/items/cf8648c31898216312e5) .
A lot of appreciations !!!


## Specs
+ Windows 10 Home
+ Java 1.8
+ Gradle 4.6
  - Groovy 2.4.12
  - Apache Ant 1.9.9


## Playing Black Jack
First, you may install Java, Gradle and this project in your PC. (I am not sure whether all these systems are really needed...)

Next, you should move to the directory where `build.gradle` is.  

Then, you type the command as follows...
```bash
  # In Windows
  > gradlew -q run
```

```bash
  # In Mac or Linux
  $ ./gradlew -q run
```

## TODOs
+ Implement the high-level choices in the Black Jack game.
+ Implement base classes and interfaces for all games.
