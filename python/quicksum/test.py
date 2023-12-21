import codewars_test as test
from solution import quicksum

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it('Basic Test Cases')
    def basic_test_cases():
        test.assert_equals(quicksum("ACM"), 46)
        test.assert_equals(quicksum("MID CENTRAL"), 650)
        test.assert_equals(quicksum("BBC"), 15)
        test.assert_equals(quicksum("???"), 0)
        test.assert_equals(quicksum("axg "), 0)
        test.assert_equals(quicksum("234 234 WEF ASDF AAA 554211 ???? "), 0)
        test.assert_equals(quicksum("A C M"), 75)
        test.assert_equals(quicksum("ABCDEFGHIJKLMNOPQRSTUVWXYZ"), 6201)
        test.assert_equals(quicksum("A B C D E F G H I J K L M N O P Q R S T U V W X Y Z"), 12051)
        test.assert_equals(quicksum("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ"), 848640)
        test.assert_equals(quicksum("Z     A"), 33)
        test.assert_equals(quicksum("12312 123 123 asd asd 123 $$$$/()="), 0)
        test.assert_equals(quicksum("As "), 0)