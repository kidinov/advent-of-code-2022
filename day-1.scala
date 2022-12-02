    input.split("\\n\\n")
      .filter(_.nonEmpty)
      .map(
        _.trim.split("\\n")
          .map(_.toInt)
          .sum
      )
      .sorted(Ordering.Int.reverse)
      .sliding(3, 3)
      .map(_.sum)
      .toList
      .head
