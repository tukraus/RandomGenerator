import Foundation

#if os(Linux)
import Glibc
#else
import Darwin.C
#endif

func generateRandomNumber(min: Int, max: Int) -> Int {
  #if os(Linux)
  var currentTime = timeval();
  gettimeofday(&currentTime, nil);
  srandom(UInt32(currentTime.tv_usec))
  return Int(random() % (max - min)) + min;
  #else
  return Int(arc4random_uniform(UInt32(max)))
  #endif
}

func forward(_ s1: Int, _ s2: Int) -> Bool {
  return s1 < s2
}

func currentTimeMillis() -> UInt64 {
      var darwinTime : timeval = timeval(tv_sec: 0, tv_usec: 0)
      gettimeofday(&darwinTime, nil)
      return UInt64(darwinTime.tv_sec * 1000) + UInt64(darwinTime.tv_usec / 1000)
}

class LuckyRandomGenerator {

  func printSetOfTickets(tickets: Array<Int>) {
    var ticketsStringArray = [String]()
    for currentTicket in tickets {
      ticketsStringArray.append(String(currentTicket))
    }
    let joinedString = ticketsStringArray.joined(separator: " - ")
    print("Generated Numbers: " + joinedString)
  }

  func generateLotteryTicket(numbersToGenerate: Int) -> Array<Int> {
    var luckyNumbers = Set<Int>()
    if (numbersToGenerate < 1) {
      return [Int]()
    }
    while (luckyNumbers.count < numbersToGenerate) {
      let numberGenerated = generateRandomNumber(min: Constants.Defaults.smallestAllowedValue, max: Constants.Defaults.biggestAllowedValueInUk)
      luckyNumbers.insert(numberGenerated)
    }
    let sortedLuckyNumbers = luckyNumbers.sorted(by: forward)
    return sortedLuckyNumbers
  }
}

extension String {

  func generateRandomString(length: Int) -> String {
    if (length < 1) {
      return String()
    }
    let base = Constants.Defaults.baseString
    var randomString: String = ""

    for _ in 0..<length {
      let randomValue = generateRandomNumber(min: 0, max: base.characters.count)
      randomString += "\(base[base.index(base.startIndex, offsetBy: Int(randomValue))])"
    }
    return randomString
  }
}
