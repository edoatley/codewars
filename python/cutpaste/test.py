import codewars_test as test
from solution import fix_cut_paste

@test.describe("Fixed Tests")
def fixed_tests():
    @test.it("Basic Test Cases")
    def basic_test_cases():
        text = "Here is some piece of text piece of text that was was accidentally double double pasted."
        expected = "Here is some piece of text that was accidentally double pasted."
        test.assert_equals(fix_cut_paste(text), expected)
        
    @test.it("empty case")
    def test_case():
        text = ""
        expected = ""
        test.assert_equals(fix_cut_paste(text), expected)
        
    @test.it("no repeats")
    def test_case():
        text = "There are no repeated words in this text!"
        expected = "There are no repeated words in this text!"
        test.assert_equals(fix_cut_paste(text), expected)

    @test.it("case")
    def test_case():
        test.assert_equals(fix_cut_paste("This this was not a cut/paste error but THAT THAT is!"), "This this was not a cut/paste error but THAT is!")

    @test.it("simple")
    def test_case():
        test.assert_equals(fix_cut_paste("FIRST FIRST word repeats"), "FIRST word repeats")
        test.assert_equals(fix_cut_paste("A word in the MIDDLE MIDDLE is repeated"), "A word in the MIDDLE is repeated")
        test.assert_equals(fix_cut_paste("A word that repeated is LAST LAST"), "A word that repeated is LAST")

    @test.it("misc")
    def test_case():
        test.assert_equals(fix_cut_paste("aardvark"), "aardvark")
        test.assert_equals(fix_cut_paste("address"), "address")
        test.assert_equals(fix_cut_paste("this is something thing"), "this is something thing")
        test.assert_equals(fix_cut_paste("123A123A"), "123A")
        test.assert_equals(fix_cut_paste("A123A123"), "A123")
        test.assert_equals(fix_cut_paste("x"), "x")
        
    @test.it("repeated everything")
    def test_case():
        test.assert_equals(fix_cut_paste("Oops.Oops.Oops.Oops.Oops.Oops.Oops.Oops.Oops."), "Oops.")
        test.assert_equals(fix_cut_paste("Oops.Oops.Oops.Oops.Oops.Oops.Oops.Oops.Oops."), "Oops.")
        test.assert_equals(fix_cut_paste("Oops Oops Oops Oops Oops Oops Oops Oops Oops"), "Oops Oops")
        test.assert_equals(fix_cut_paste("Oops Oops Oops Oops Oops Oops Oops Oops Oops Oops"), "Oops Oops")
        test.assert_equals(fix_cut_paste(" x x x x x x x"), " x")
        test.assert_equals(fix_cut_paste("x x x x x x x "), "x ")
        
    @test.it(" Different words: 1x and 2x pasting")
    def test_case():
        test.assert_equals(fix_cut_paste("REPEATED WORDS REPEATED WORDS at the start"), "REPEATED WORDS at the start")
        test.assert_equals(fix_cut_paste("REPEATED WORDS REPEATED WORDS REPEATED WORDS at the start"), "REPEATED WORDS at the start")
        test.assert_equals(fix_cut_paste("Here are some REPEATED WORDS REPEATED WORDS in the middle"), "Here are some REPEATED WORDS in the middle")
        test.assert_equals(fix_cut_paste("Here are some REPEATED WORDS REPEATED WORDS REPEATED WORDS in the middle"), "Here are some REPEATED WORDS in the middle")
        test.assert_equals(fix_cut_paste("This ends with some REPEATED WORDS REPEATED WORDS"), "This ends with some REPEATED WORDS")
        test.assert_equals(fix_cut_paste("This ends with some REPEATED WORDS REPEATED WORDS REPEATED WORDS"), "This ends with some REPEATED WORDS")

    @test.it("Same word: Odd and even x pasting")
    def test_case():
        test.assert_equals(fix_cut_paste("REPEATED REPEATED REPEATED words at the start"), "REPEATED words at the start")
        test.assert_equals(fix_cut_paste("REPEATED REPEATED REPEATED REPEATED words at the start"), "REPEATED words at the start")
        test.assert_equals(fix_cut_paste("Here are some REPEATED REPEATED REPEATED words in the middle"), "Here are some REPEATED words in the middle")
        test.assert_equals(fix_cut_paste("Here are some REPEATED REPEATED REPEATED REPEATED words in the middle"), "Here are some REPEATED words in the middle")
        test.assert_equals(fix_cut_paste("This ends with some words that are REPEATED REPEATED REPEATED"), "This ends with some words that are REPEATED")
        test.assert_equals(fix_cut_paste("This ends with some words that are REPEATED REPEATED REPEATED REPEATED"), "This ends with some words that are REPEATED")
    
    @test.it("punctuation and spaces")
    def test_case():
        test.assert_equals(fix_cut_paste("Here are                     LOTS      LOTS of spaces"), "Here are                     LOTS of spaces")
        test.assert_equals(fix_cut_paste(".....SOMETHING SOMETHING at the front"), ".....SOMETHING at the front")
        test.assert_equals(fix_cut_paste("Something at the END END..."), "Something at the END...")
        test.assert_equals(fix_cut_paste("!!!"), "!!!")
        test.assert_equals(fix_cut_paste("     "), "     ")
        
    @test.it("tricky ones")
    def test_case():
        test.assert_equals(fix_cut_paste("CCCC!CCCC BBBB!AAAA CCCC CCCC BBBB BBBB BBBB BBBB!BBBB!BBBB!AAAA!"), "CCCC!CCCC BBBB!AAAA CCCC BBBB!BBBB!AAAA!")
        test.assert_equals(fix_cut_paste("A_X_A_X_O_X_O_X_"), "A_X_O_X_")
        test.assert_equals(fix_cut_paste("A A B A A B "), "A B A B ")