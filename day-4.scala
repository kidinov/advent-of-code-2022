@main def run(): Unit =
  println(
    Source.fromFile("advert_input.txt")
      .getLines()
      .map(_.split(","))
      .map(arr => (arr(0).split("-"), arr(1).split("-")))
      .map(arr => (Set.range(arr(0)(0).toInt, arr(0)(1).toInt + 1), Set.range(arr(1)(0).toInt, arr(1)(1).toInt + 1)))
      .map(set => if ((set._1 intersect set._2).isEmpty) 0 else 1)
      .sum
  )
