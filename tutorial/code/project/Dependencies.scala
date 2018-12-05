import sbt._
import Keys._
import Versions._

object Dependencies {

  object Cats {
    val cats              = "org.typelevel"                %%   "cats-core"                      % catsVersion
    val catsEffect        = "org.typelevel"                %%   "cats-effect"                    % catsEffectVersion
    val catsMtl           = "org.typelevel"                %%   "cats-mtl-core"                  % catsMtlVersion
  }

  object Monix {
    val monix             = "io.monix"                     %% "monix"                            % monixVersion
  }

  val macroParadise = compilerPlugin("org.scalamacros" % "paradise" % macroParadiseVersion cross CrossVersion.full)
  val kindProjector = compilerPlugin("org.spire-math" %% "kind-projector" % kindProjectorVersion)

  val commonDependencies: Seq[ModuleID] = Seq(Cats.cats, Cats.catsEffect)
  val monixDependencies: Seq[ModuleID] = Seq(Monix.monix)

  val tradingDependencies: Seq[ModuleID] = commonDependencies ++ Seq(Cats.catsMtl) ++ Seq(macroParadise, kindProjector) ++ monixDependencies
}
