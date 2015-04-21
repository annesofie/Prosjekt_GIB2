name := "prosjektgib2"

version := "1.0"

lazy val `prosjektgib2` = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq( javaJdbc , javaEbean , cache , javaWs, "mysql" % "mysql-connector-java" % "5.1.18")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  