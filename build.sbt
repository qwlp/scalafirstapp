val scala3Version = "3.8.1"

// 1. Determine the OS for JavaFX binaries
val osName = System.getProperty("os.name").toLowerCase match {
  case n if n.startsWith("win") => "win"
  case n if n.startsWith("mac") => "mac"
  case _                        => "linux"
}

// 2. Define the JavaFX version (matching your ScalaFX version)
val javaFXVersion = "23.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "helloworld",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,

    // 3. Add JavaFX Dependencies with the OS classifier
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.scalafx" %% "scalafx" % "23.0.1-R34"
    ),

    // Add the platform-specific JavaFX modules
    libraryDependencies ++= Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
      .map { m =>
        "org.openjfx" % s"javafx-$m" % javaFXVersion classifier osName
      },

    // 4. Important: Fork the JVM for running
    // This prevents "Double initialization" errors and classpath issues with JavaFX
    fork := true
  )
