import XCTest
import Foundation
@testable import LuckyRandomGenerator

class TestLuckyRandomGenerator: XCTestCase {

  static var allTests: [(String, (TestLuckyRandomGenerator) -> () throws -> Void)] {
      return [
          ("testLotteryTicketsGenerator", testLotteryTicketsGenerator)
      ]
  }

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
