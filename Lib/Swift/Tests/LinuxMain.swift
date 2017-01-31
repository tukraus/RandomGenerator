import XCTest

@testable import LuckyRandomGeneratorTests

extension LuckyRandomGeneratorTests {
    static var allTests : [(String, (LuckyRandomGeneratorTests) -> () throws -> Void)] {
        return [
            ("testLotteryTicketsGenerator", testLotteryTicketsGenerator)
        ]
    }
}
