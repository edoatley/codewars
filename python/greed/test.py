import codewars_test as test
from solution import score

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it('Basic Test Cases')
    def basic_test_cases():
        test.assert_equals(score([5, 1, 3, 4, 1]), 250)
        test.assert_equals(score([1, 1, 1, 3, 1]), 1100)
        test.assert_equals(score([2, 4, 4, 5, 4]), 450)