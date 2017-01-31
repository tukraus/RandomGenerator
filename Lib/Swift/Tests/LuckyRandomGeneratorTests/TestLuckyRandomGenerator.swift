import XCTest
import Foundation

@testable import LuckyRandomGenerator

class TestLuckyRandomGenerator: XCTestCase {

  static var allTests: [(String, (TestLuckyRandomGenerator) -> () throws -> Void)] {
      return [
          ("testPositiveLotteryTicketsGenerator", testPositiveLotteryTicketsGenerator),
          ("testZeroLotteryTicketsGenerator", testZeroLotteryTicketsGenerator),
          ("testNegativeLotteryTicketsGenerator", testNegativeLotteryTicketsGenerator),
          ("testPositiveLengthStringGenerator", testPositiveLengthStringGenerator)
      ]
  }

  var luckyGenerator : LuckyRandomGenerator!

  override func setUp() {
    super.setUp()
    luckyGenerator = LuckyRandomGenerator()
  }

  func testPositiveLotteryTicketsGenerator() {
    let shouldGenerateCountNumbers = 6
    let luckyNumbers = luckyGenerator.generateLotteryTicket(numbersToGenerate: shouldGenerateCountNumbers)
    luckyGenerator.printSetOfTickets(tickets: luckyNumbers)
    XCTAssertEqual(luckyNumbers.count, shouldGenerateCountNumbers)
  }

  func testZeroLotteryTicketsGenerator() {
    let shouldGenerateCountNumbers = 0
    let luckyNumbers = luckyGenerator.generateLotteryTicket(numbersToGenerate: shouldGenerateCountNumbers)
    luckyGenerator.printSetOfTickets(tickets: luckyNumbers)
    XCTAssertEqual(luckyNumbers.count, shouldGenerateCountNumbers)
  }

  func testNegativeLotteryTicketsGenerator() {
    let shouldGenerateCountNumbers = -2
    let luckyNumbers = luckyGenerator.generateLotteryTicket(numbersToGenerate: shouldGenerateCountNumbers)
    luckyGenerator.printSetOfTickets(tickets: luckyNumbers)
    XCTAssertEqual(luckyNumbers.count, 0)
  }

  func testPositiveLengthStringGenerator() {
    let stringLen = 20
    let aRandomString = String().generateRandomString(length: stringLen)
    print(aRandomString);
    XCTAssertEqual(aRandomString.characters.count, stringLen)
  }
}
