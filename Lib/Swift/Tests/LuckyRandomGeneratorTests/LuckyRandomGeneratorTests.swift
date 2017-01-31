import XCTest
import Foundation
@testable import LuckyRandomGenerator

class LuckyRandomGeneratorTests: XCTestCase {
  var luckyGenerator : LuckyRandomGenerator!

  override func setUp() {
    super.setUp()
    luckyGenerator = LuckyRandomGenerator()
  }

  func testLotteryTicketsGenerator() {
    let shouldGenerateCountNumbers = 6
    let luckyNumbers = luckyGenerator.generateLotteryTicket(numbersToGenerate: shouldGenerateCountNumbers)
    luckyGenerator.printSetOfTickets(tickets: luckyNumbers)
    XCTAssertEqual(luckyNumbers.count, shouldGenerateCountNumbers)
  }

}
