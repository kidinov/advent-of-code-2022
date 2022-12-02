@main def run(): Unit =
  println(
    Source.fromFile("advert_input.txt").getLines()
      .map(line => calcResultScore(line.charAt(2)) + calcMyPickScore(line.charAt(2), line.charAt(0)))
      .sum
  )

def calcResultScore(result: Char) =
  result match
    case 'X' => 0
    case 'Y' => 3
    case 'Z' => 6

def calcMyPickScore(result: Char, opponent: Char) =
  result match
    case 'Y' => opponent - 64
    case 'Z' => opponent match
      case 'A' => 2
      case 'B' => 3
      case 'C' => 1
    case 'X' => opponent match
      case 'A' => 3
      case 'B' => 1
      case 'C' => 2
