  print(
    Source.fromFile("advert_input.txt")
      .sliding(14, 1)
      .zipWithIndex
      .map(w => if (w._1.toSet.size == w._1.length) w._2 else 0)
      .filterNot(_ == 0)
      .toList.head + 14
  )
