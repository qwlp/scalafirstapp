val scala3Version = "3.8.1"

val osName = System.getProperty("os.name").toLowerCase match {
  case n if n.startsWith("win") => "win"
  case n if n.startsWith("mac") => "mac"
  case _                        => "linux"
}

val javaFXVersion = "23.0.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "helloworld",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    javaOptions ++= Seq(
      "-Dscala.color=true"
    ),
    libraryDependencies ++= Seq(
      "org.scalameta" %% "munit" % "1.0.0" % Test,
      "org.scalafx" %% "scalafx" % "23.0.1-R34"
    ),

    // Ensure JavaFX binaries are included (as we did before)
    libraryDependencies ++= Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
      .map { m =>
        "org.openjfx" % s"javafx-$m" % "23.0.1" classifier osName
      }
  )
