import codewars_test as test
from solution import DNA_strand

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it('Basic Test Cases')
    def basic_test_cases():     
        test.assert_equals(DNA_strand("AAAA"),"TTTT","String AAAA is")
        test.assert_equals(DNA_strand("ATTGC"),"TAACG","String ATTGC is")
        test.assert_equals(DNA_strand("GTAT"),"CATA","String GTAT is")