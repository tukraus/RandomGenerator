import Foundation

func generateRandomLuckyNumber(max: Int) -> Int {
  #if os(Linux)
  return Int(random() % (max + 1))
  #else
  return Int(arc4random_uniform(UInt32(max)))
  #endif
}

func generateLotteryTicket() {
  let time = Int(Date().timeIntervalSinceReferenceDate)
  srand(UInt32(time))
  let luckyNumber = generateRandomLuckyNumber(max: 59)
  print(String(luckyNumber))
}

generateLotteryTicket()
