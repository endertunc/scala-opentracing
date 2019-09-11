package com.colisweb.tracing

import cats.effect.Resource
import cats.Applicative

class NoOpTracingContext[F[_]: Applicative] extends TracingContext[F] {

  def addTags(tags: Tags): F[Unit] = Applicative[F].unit

  def childSpan(operationName: String, tags: Tags): TracingContextResource[F] =
    Resource.pure(NoOpTracingContext())

}

object NoOpTracingContext {
  def apply[F[_]: Applicative]() = new NoOpTracingContext[F]

  def getNoOpTracingContextBuilder[F[_]: Applicative]: F[TracingContextBuilder[F]] =
    Applicative[F].pure((_, _) => Resource.pure(NoOpTracingContext[F]()))
}
