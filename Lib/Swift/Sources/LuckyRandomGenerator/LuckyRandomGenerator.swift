import Foundation

#if os(Linux)
import Glibc
#else
import Darwin.C
#endif

class LuckyRandomGenerator {

  func forward(_ s1: Int, _ s2: Int) -> Bool {
      return s1 < s2
  }


  func generateRandomLuckyNumber(min: Int, max: Int) -> Int {
    #if os(Linux)
    let time = Int(Date().timeIntervalSinceReferenceDate)
    srand(UInt32(time))
    return Int(random() % (max - min)) + min;
    #else
    return Int(arc4random_uniform(UInt32(max)))
    #endif
  }

  func printSetOfTickets(tickets: Array<Int>) {
    for currentTicket in tickets {
      print(String(currentTicket))
    }
  }

  func generateLotteryTicket(numbersToGenerate: Int) -> Array<Int> {
    var luckyNumbers = Set<Int>()
    if (numbersToGenerate < 1) {
      return [Int]()
    }
    while (luckyNumbers.count < numbersToGenerate) {
      let numberGenerated = generateRandomLuckyNumber(min: Constants.Defaults.smallestAllowedValue, max: Constants.Defaults.biggestAllowedValue)
      luckyNumbers.insert(numberGenerated)
    }
    let sortedLuckyNumbers = luckyNumbers.sorted(by: forward)
    return sortedLuckyNumbers
  }
}
