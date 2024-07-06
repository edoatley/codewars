from solution import solution
import codewars_test as test
from random import randint


fixed_tests_True = (
    ( "samurai", "ai"    ),
    ( "ninja",   "ja"    ),
    ( "sensei",  "i"     ),
    ( "abc",     "abc"   ),
    ( "abcabc",  "bc"    ),
    ( "fails",   "ails"  ),
)

fixed_tests_False = (
    ( "sumo",    "omo"   ),
    ( "samurai", "ra"    ),
    ( "abc",     "abcd"  ),
    ( "ails",    "fails" ),
    ( "this",    "fails" ),
    ( "spam",    "eggs"  )
)

@test.describe("Fixed Tests")
def test_group():
    @test.it("True Cases")
    def test_case():
        for text, ending in fixed_tests_True:
            test.assert_equals(solution(text, ending), True)
    @test.it("False Cases")
    def test_case():
        for text, ending in fixed_tests_False:
            test.assert_equals(solution(text, ending), False)