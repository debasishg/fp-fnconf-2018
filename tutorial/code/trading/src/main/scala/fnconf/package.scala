package fnconf

package object trading {
  type ErrorOr[A] = Either[String, A]
}
