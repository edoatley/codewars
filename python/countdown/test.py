import codewars_test as test
from solution import longest_word

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it('Basic Test Cases')
    def basic_test_cases():
        example_test_cases = (
            ('GQEMAUVXY', ['GAME']),
            ('TDWAYZROE', ['TODAY', 'TOWER', 'TRADE', 'WATER']),
            ('EAEEAYITB', ['BEAT', 'BITE', 'BYTE']),
            ('AKUIYOOLO', ['LOOK', 'YOLK']),
            ('GVDTCAESU', ['CAGES', 'CAUSE', 'CAVES', 'DATES', 'GATES', 'GUEST', 'STAGE', 'USAGE'])
        )
        for x, y in example_test_cases:
            test.assert_equals(longest_word(x), y, "Should return correct array")
            
        test.assert_equals(longest_word(""), None, "Should return None for empty string")
        test.assert_equals(longest_word("MKMKMKMKM"), None, "Should return None for empty array")
        test.assert_equals(longest_word("IIIWUGEZI"), None, "Should return None for empty array")
        
    @test.it('Mixed Test Cases')
    def basic_test_cases():
        mixed_cases = (
            ("WTGAEUAUD", ['DATA', 'DATE', 'GATE', 'WAGE']),
            ("MVIIIZEKQ", None),
            ("SIARIERRW", ['RAISE', 'WIRES']),
            ("YIOVOORUF", ['IVORY']),
            ("EWFEREIUH", ['FIRE', 'HERE', 'WIRE']),
            ("NLGIEOTSA", ['GASOLINE']),
            ("ASIULSIEI", ['ISSUE', 'SAILS', 'SALES', 'SEALS']),
            ("AFDEEONEA", ['FEED', 'NEED']),
            ("OFRDACESN", ['CODERS', 'FORCES', 'OCEANS', 'REASON', 'SECOND']),
            ("YBOEDSLSU", ['BUOYS', 'BUSES', 'DOSES', 'SLEDS', 'SOLES']),
            ("GIABBLPBL", ['BAIL', 'BALL', 'BILL', 'PAIL']),
            ("RAOQILBIN", ['ALIBI', 'BRAIN', 'LABOR', 'LORAN']),
            ("KIEWVELIL", ['KEEL', 'KILL', 'VIEW', 'WEEK']),
            ("DDAYCEEQR", ['DECAY'])
        )

        for x, y in mixed_cases:
            test.assert_equals(longest_word(x), y)