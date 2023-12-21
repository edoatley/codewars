import codewars_test as test
from solution import count_digit, base_10_to_new_base,other_base_to_base_10

test.describe("Example Tests")

tests = (
    (("133", "3"), 2),
    (("10", "a", 11), 1),
    (("1100101110101", "d", 15, 2), 1)
)

for args, exp in tests:
    test.assert_equals(count_digit(*args), exp)
    
@test.describe("Fixed Tests")
def fixed_tests():
    @test.it('Basic Test Cases')
    def basic_test_cases():
        test.assert_equals(base_10_to_new_base(29, 2), '11101')
        test.assert_equals(other_base_to_base_10('25', 8), 21)
        test.assert_equals(count_digit('0', '0', 4, 5), 1)
        test.assert_equals(count_digit('185gg9f8i6e415228617haa7d5e3a6cbehf6223acgfc44820ee1d7a0f1b2he50h9bah6562ih5b9022834i46bcc9g6f', 'm', 28, 19), 1)
        