@main def run(): Unit =
  println(
    Source.fromFile("advert_input.txt")
      .getLines()
      .sliding(3, 3)
      .map(_.toList)
      .map(parts => parts(0).toSet intersect parts(1).toSet intersect parts(2).toSet)
      .flatMap(_.toList)
      .map(char => if (char.isLower) char - 96 else char - 64 + 26)
      .sum
  )
