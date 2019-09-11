package com.colisweb

package object tracing {
  import cats.effect.Resource
  type Tags = Map[String, String]
  type TracingContextResource[F[_]] = Resource[F, TracingContext[F]]

  trait TracingContextBuilder[F[_]] {
    def apply(
        operationName: String,
        tags: Tags = Map.empty
    ): TracingContextResource[F]
  }
}
